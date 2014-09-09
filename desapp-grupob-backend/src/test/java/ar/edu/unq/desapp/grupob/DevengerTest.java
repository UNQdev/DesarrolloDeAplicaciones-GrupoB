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
}
