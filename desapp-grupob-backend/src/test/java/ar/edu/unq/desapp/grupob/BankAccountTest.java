package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.unq.desapp.grupob.model.BankAccount;
import ar.edu.unq.desapp.grupob.model.Devenger;
import ar.edu.unq.desapp.grupob.model.Operation;
import ar.edu.unq.desapp.grupob.model.builders.BankAccountBuilder;

/**
 *
 * @author bananee
 *
 */
public class BankAccountTest {

    /**
     *
     */
    @Test
    public void testBankAccountConstructor() {
        BankAccountBuilder builder = BankAccountBuilder.aBankAccountBuilder();
        BankAccount bank = builder.withConsolidationPeriod(15).build();
        assertEquals(bank.getAccountBalance(), 0, 0);
        assertEquals(bank.getOperations().size(), 0);
        assertEquals(bank.getAvailable(), bank.getAccountBalance(), 0);
    }
    /**
     *
     */
    @Test
    public void testAddOperation() {
    	Operation operation = mock(Operation.class);
        when(operation.getAmount()).thenReturn((double) 100);

        Devenger devenger = mock(Devenger.class);

        BankAccountBuilder builder = BankAccountBuilder.aBankAccountBuilder();
        BankAccount bank = builder.withDevenger(devenger)
        		.buildBankWithJUSTADevenger();

        bank.addOperation(operation);

        verify(devenger, times(1)).addOperation(operation);
        assertFalse(bank.getOperations().contains(operation));
    }
    /**
     *
     */
    @Test
    public void testRemoveConsolidatedOperation() {
    	DateTime date = DateTime.parse("2014-09-01T01:00");
        Operation operation = mock(Operation.class);
        when(operation.getAmount()).thenReturn((double) 100);
        when(operation.getDate()).thenReturn(date);
        BankAccountBuilder builder = BankAccountBuilder.aBankAccountBuilder();
        BankAccount bank = builder.withConsolidationPeriod(6)
        		.build();
        bank.removeOperation(operation);
        assertFalse(bank.getOperations().contains(operation));
        assertEquals(bank.getAccountBalance(), 0,0);
    }
    
    @Test
    public void testOperationConsolidation() {
    	
    }
}
