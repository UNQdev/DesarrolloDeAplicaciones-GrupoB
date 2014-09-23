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
        assertEquals(bank.getAccrued(), 0, 0);
    }
    /**
     *
     */
    @Test
    public void testAddOperation() {
    	Operation operation = mock(Operation.class);
        when(operation.getAmount()).thenReturn((double) 100);

        List<Operation> unConsolidatedOperations = mock(List.class);
        Devenger devenger = mock(Devenger.class);
        /*
         * TODO: Proper mock for addOperation stubbing
         */
//      when(devenger.addOperation(operation))

        BankAccountBuilder builder = BankAccountBuilder.aBankAccountBuilder();
        BankAccount bank = builder.build();

        bank.addOperation(operation);

//        verify(unConsolidatedOperations, times(1)).add(operation);
        assertEquals(bank.getAccrued(), operation.getAmount(), 0);
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
}
