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

	@Test
	public void testDevengerConstructor() {
		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withConsolidationPeriod(5)
				.build();
		
		assertEquals(devenger.getConsolidationPeriod(), 5);
		assertEquals(devenger.getUnConsolidatedOperations().size(), 0);
	}
	
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
	
	@Test
	public void testRemoveUnConsolidatedOperation() {
		Operation operation = mock(Operation.class);
		
		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withSpecificOperation(operation)
				.build();

		devenger.removeOperation(operation);

		assertFalse(devenger.getUnConsolidatedOperations().contains(operation));
	}

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

	@Test
	public void testConsolidationDateReached() {
		DateTime operationDate = DateTime.parse("2014-09-08T01:00");
		DateTime date = DateTime.parse("2014-09-15T01:00");
		Operation operation = mock(Operation.class);
		when(operation.getDate()).thenReturn(operationDate);

		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withConsolidationPeriod(5)
				.withSystemDate(date)
				.build();

		assertTrue(devenger.reachedConsolidationDate(operation));
		assertEquals(devenger.getUnConsolidatedOperations().size(), 0);
	}
	
	@Test
	public void testConsolidationDateNOTReached() {
		DateTime operationDate = DateTime.parse("2014-09-05T01:00");
		DateTime date = DateTime.parse("2014-09-04T01:00");
		Operation operation = mock(Operation.class);
		when(operation.getDate()).thenReturn(operationDate);

		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();
		Devenger devenger = builder.withConsolidationPeriod(5)
				.withSystemDate(date)
				.build();		
		
		assertFalse(devenger.reachedConsolidationDate(operation));
		assertEquals(devenger.getUnConsolidatedOperations().size(), 1);
	}
	
	@Test
	public void testOperationConsolidation() {
		DateTime operation1Date = DateTime.parse("2014-09-01T01:00");
		double operation1Amount = 100;
		DateTime operation2Date = DateTime.parse("2014-09-07T01:00");
		DateTime date = DateTime.parse("2014-09-06T01:00");
		Operation operation1 = mock(Operation.class);
		when(operation1.getDate()).thenReturn(operation1Date);
		when(operation1.getAmount()).thenReturn(operation1Amount);
		Operation operation2 = mock(Operation.class);
		when(operation2.getDate()).thenReturn(operation2Date);
		List<Operation> consolidatedOperations = new ArrayList<Operation>();
		Bank bankAccount = mock(Bank.class);
		when(bankAccount.getOperations())
			.thenReturn(consolidatedOperations);

		DevengerBuilder builder = DevengerBuilder.aDevengerBuilder();

		Devenger devenger = builder.withConsolidationPeriod(5)
				.withSpecificOperation(operation1)
				.withSpecificOperation(operation2)
				.withAccount(bankAccount)
				.withSystemDate(date)
				.build();

		double consolidatedAmount = devenger.consolidateOperations();

		assertEquals(devenger.getUnConsolidatedOperations().size(), 1);
		assertTrue(devenger.getUnConsolidatedOperations().contains(operation2));
		assertFalse(devenger.getUnConsolidatedOperations().contains(operation1));
		assertEquals(consolidatedAmount, 100,0);
	}
}
