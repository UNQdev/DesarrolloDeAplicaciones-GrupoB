package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.unq.desapp.grupob.builders.DevengerBuilder;

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
		List<Operation> operations = new ArrayList<Operation>();
		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withUnConsolidatedOperations(operations)
				.build();

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
	public void testAcrrualDateCalculation() {
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
	}
	/**
	 *
	 */
	@Test
	public void testOperationConsolidated() {
		DateTime systemDate = DateTime.parse("2014-09-12T01:00");

		DateTime operation1Date = DateTime.parse("2014-09-01T01:00");
		double operation1Amount = 100;
		Operation operation = mock(Operation.class);
		when(operation.getDate()).thenReturn(operation1Date);
		when(operation.getAmount()).thenReturn(operation1Amount);

		List<Operation> consolidatedOperations = mock(ArrayList.class);
		when(consolidatedOperations.add(operation)).thenReturn(true);

		BankAccount bankAccount = mock(BankAccount.class);
		when(bankAccount.getOperations()).thenReturn(consolidatedOperations);

		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withConsolidationPeriod(5)
				.withAccount(bankAccount)
				.withSystemDate(systemDate)
				.build();

		assertFalse(devenger.getUnConsolidatedOperations().contains(operation));
		verify(bankAccount, times(1)).addOperation(operation);
		 
		assertEquals(devenger.consolidateOperations(), 100,0);
	}
	/**
	 *
	 */
	@Test
	public void testOperationNOTConsolidated() {
		
	}
}
