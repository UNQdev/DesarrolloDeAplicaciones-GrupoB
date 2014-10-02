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
 * @author bananee
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
		DateTime operationDate = DateTime.parse("2014-09-01T01:00");
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
		DateTime operationDate = DateTime.parse("2014-09-08T01:00");
		DateTime systemDate = DateTime.parse("2014-09-15T01:01");

		Operation operation = mock(Operation.class);
		when(operation.getDate()).thenReturn(operationDate);

		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withConsolidationPeriod(5)
				.withSystemDate(systemDate)
				.build();

		assertTrue(devenger.reachedConsolidationDate(operation));
		verify(operation, times(2)).getDate();
	}
	/**
	 *
	 */
	@Test
	public void testConsolidationDateNOTReached() {
		DateTime operationDate = DateTime.parse("2014-09-08T01:00");
		DateTime systemDate = DateTime.parse("2014-09-12T01:00");

		Operation operation = mock(Operation.class);
		when(operation.getDate()).thenReturn(operationDate);

		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withConsolidationPeriod(5)
				.withSystemDate(systemDate)
				.build();

		assertFalse(devenger.reachedConsolidationDate(operation));		
		verify(operation, times(2)).getDate();
	}
	/**
	 *
	 */
	@Test
	public void testOperationConsolidated() {
		DateTime systemDate = DateTime.parse("2014-09-12T01:00");
		DateTime operationDate = DateTime.parse("2014-09-01T01:00");
		double operationAmount = 100;
		Operation operation = mock(Operation.class);
		when(operation.getDate()).thenReturn(operationDate);
		when(operation.getAmount()).thenReturn(operationAmount);

		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withConsolidationPeriod(5)
				.withSystemDate(systemDate)
				.withSpecificOperation(operation)
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
		DateTime systemDate = DateTime.parse("2014-09-12T01:00");
		DateTime operationDate = DateTime.parse("2014-09-08T01:00");
		double operationAmount = 100;
		Operation operation = mock(Operation.class);
		when(operation.getDate()).thenReturn(operationDate);
		when(operation.getAmount()).thenReturn(operationAmount);

		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withConsolidationPeriod(5)
				.withSystemDate(systemDate)
				.withSpecificOperation(operation)
				.build();

		devenger.consolidateOperations();

		verify(operation, times(2)).getDate();
		verify(operation, times(0)).getRealAmount();
		assertTrue(devenger.getUnConsolidatedOperations().contains(operation));
	}
	@Test
	public void testUnConsolidatedAmountPOSITIVEUpdate() {
		double amount = 100.0;
		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.build();

		devenger.updateUnConsolidatedAmount(amount);

		assertEquals(devenger.getUnConsolidatedAmount(), amount,0);
	}
	@Test
	public void testUnConsolidatedAmountNEGATIVEUpdate() {
		double amount = -100.0;
		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.build();

		devenger.updateUnConsolidatedAmount(amount);

		assertEquals(devenger.getUnConsolidatedAmount(), amount,0);
	}
}