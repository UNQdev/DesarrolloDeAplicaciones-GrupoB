package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.unq.desapp.grupob.model.BankAccount;
import ar.edu.unq.desapp.grupob.model.Devenger;
import ar.edu.unq.desapp.grupob.model.Operation;
import ar.edu.unq.desapp.grupob.model.builders.DevengerBuilder;

/**
 * @author Marcelo Rubini
 *
 */
public class DevengerTest {

	/**
	 *
	 */
	@Test
	public void testDevengerConstructor() {
		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withConsolidationPeriod(5)
				.build();

		assertEquals(devenger.getConsolidationPeriod(), 5);
		assertEquals(devenger.getUnConsolidatedOperations().size(), 0);
	}
	/**
	 *
	 */
	@Test
	public void testAddOperation() {
		Operation operation = mock(Operation.class);

		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.build();

		devenger.addOperation(operation);

		assertTrue(devenger.getUnConsolidatedOperations().contains(operation));
	}
	/**
	 *
	 */
	@Test
	public void testRemoveUnConsolidatedOperation() {
		Operation operation = mock(Operation.class);
		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withSpecificOperation(operation)
				.build();

		devenger.removeOperation(operation);

		assertFalse(devenger.getUnConsolidatedOperations().contains(operation));
	}
	/**
	 *
	 */
	@Test
	public void testAccrualDateCalculation() {
		DateTime operationDate = DateTime.now().minusDays(5);
		Operation operation = mock(Operation.class);
		when(operation.getDate()).thenReturn(operationDate);

		int period = 5;

		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withConsolidationPeriod(period)
				.build();

		assertEquals(devenger.getAccrualDate(operation),
				operationDate.plusDays(period));
	}
	/**
	 *
	 */
	@Test
	public void testConsolidationDateReached() {
		DateTime operationDate = DateTime.now().minusDays(5);

		Operation operation = mock(Operation.class);
		when(operation.getDate()).thenReturn(operationDate);

		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withConsolidationPeriod(5)
				.build();

		assertTrue(devenger.reachedConsolidationDate(operation));
		verify(operation, times(1)).getDate();
	}
	/**
	 *
	 */
	@Test
	public void testConsolidationDateNOTReached() {
		DateTime operationDate = DateTime.now().minus(4);

		Operation operation = mock(Operation.class);
		when(operation.getDate()).thenReturn(operationDate);

		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withConsolidationPeriod(5)
				.build();

		assertFalse(devenger.reachedConsolidationDate(operation));		
		verify(operation, times(2)).getDate();
	}
	/**
	 *
	 */
	@Test
	public void testOperationConsolidated() {
		DateTime operationDate = DateTime.now().minusDays(6);
		double operationAmount = 100;
		Operation operation = mock(Operation.class);
		when(operation.getDate()).thenReturn(operationDate);
		when(operation.getAmount()).thenReturn(operationAmount);

		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withConsolidationPeriod(5)
				.withSpecificOperation(operation)
				.withUnConsolidatedAmount(operationAmount)
				.build();

		devenger.consolidateOperations();

		verify(operation, times(2)).getDate();
		verify(operation, times(1)).getRealAmount();
		assertFalse(devenger.getUnConsolidatedOperations().contains(operation));
	}
	/**
	 *
	 */
	@Test
	public void testOperationNOTConsolidated() {
		DateTime operationDate = DateTime.now().minusDays(4);
		double operationAmount = 100;
		Operation operation = mock(Operation.class);
		when(operation.getDate()).thenReturn(operationDate);
		when(operation.getAmount()).thenReturn(operationAmount);

		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withConsolidationPeriod(5)
				.withSpecificOperation(operation)
				.withUnConsolidatedAmount(operationAmount)
				.build();

		devenger.consolidateOperations();

		verify(operation, times(2)).getDate();
		verify(operation, times(0)).getRealAmount();
		assertTrue(devenger.getUnConsolidatedOperations().contains(operation));
	}
	/**
	 *
	 */
	@Test
	public void testUnConsolidatedAmountPOSITIVEUpdate() {
		double amount = 100.0;
		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.build();

		devenger.updateUnConsolidatedAmount(amount);

		assertEquals(devenger.getUnConsolidatedAmount(), amount,0);
	}
	/**
	 *
	 */
	@Test
	public void testUnConsolidatedAmountNEGATIVEUpdate() {
		double amount = -100.0;
		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.build();

		devenger.updateUnConsolidatedAmount(amount);

		assertEquals(devenger.getUnConsolidatedAmount(), amount,0);
	}
}