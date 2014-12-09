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
        Devenger devenger = mock(Devenger.class);
        when(devenger.getUnConsolidatedAmount()).thenReturn(0.0);

    	BankAccountBuilder builder = BankAccountBuilder.aBankAccountBuilder();
        BankAccount bank = builder.withDevenger(devenger)
        		.build();
        
        
        assertEquals(bank.getAccountBalance(), 0, 0);
        assertEquals(bank.getOperations().size(), 0);
    }
    /**
     * @throws Exception 
     *
     */
    @Test
    public void testAddOperation() throws Exception {
    	Operation operation = mock(Operation.class);
        when(operation.getAmount()).thenReturn((double) 100);

        Devenger devenger = mock(Devenger.class);

        BankAccountBuilder builder = BankAccountBuilder.aBankAccountBuilder();
        BankAccount bank = builder.withDevenger(devenger)
        		.build();

        bank.addOperation(operation);

        verify(devenger, times(1)).addOperation(operation);
        assertFalse(bank.getOperations().contains(operation));
    }
    /**
     *
     */
    @Test
    public void testRemoveConsolidatedOperation() {
    	Operation operation = mock(Operation.class);
        when(operation.getAmount()).thenReturn((double) 100);

        Devenger devenger = mock(Devenger.class);

        BankAccountBuilder builder = BankAccountBuilder.aBankAccountBuilder();
        BankAccount bank = builder.withOperation(operation)
        		.withDevenger(devenger)
        		.build();

        bank.removeOperation(operation);

        assertFalse(bank.getOperations().contains(operation));
        assertEquals(bank.getAccountBalance(), 0,0);
    }
    /**
     *
     */
    @Test
    public void testUpdatePOSITIVEAvailableAmount() {
    	double amount = 100.0;

    	BankAccountBuilder builder = BankAccountBuilder.aBankAccountBuilder();
    	BankAccount bank = builder.withAvailableAmount(0)
    			.build();

    	bank.updateAvailableAmount(amount);

    	assertEquals(bank.getAvailable(), amount,0);
    }
    @Test
    public void testUpdateNEGATIVEAvailableAmount() {
    	double amount = -100.0;

    	BankAccountBuilder builder = BankAccountBuilder.aBankAccountBuilder();
    	BankAccount bank = builder.withAvailableAmount(0)
    			.build();

    	bank.updateAvailableAmount(amount);

    	assertEquals(bank.getAvailable(), amount,0);
    }
    @Test
    public void testOperationIsConsolidated() {
    	
    }
    @Test
    public void testAccountBalanceCalculation() {
    	Devenger devenger = mock(Devenger.class);
        when(devenger.getUnConsolidatedAmount()).thenReturn(100.0);

        BankAccountBuilder builder = BankAccountBuilder.aBankAccountBuilder();
    	BankAccount bank = builder.withAvailableAmount(5.0)
    			.withDevenger(devenger)
    			.build();

    	double accountBalanced = devenger.getUnConsolidatedAmount()+bank.getAvailable();

    	assertEquals(bank.getAccountBalance(), accountBalanced,0);
    }
    @Test
    public void testOperationConsolidation() {
    	Operation operation = mock(Operation.class);
        when(operation.getAmount()).thenReturn((double) 100);

        List<Operation> consolidatedOperations = new ArrayList<Operation>();
        consolidatedOperations.add(operation);

        Devenger devenger = mock(Devenger.class);
        when(devenger.consolidateOperations()).thenReturn(consolidatedOperations);

        BankAccountBuilder builder = BankAccountBuilder.aBankAccountBuilder();
    	BankAccount bank = builder.withDevenger(devenger)
    			.withAvailableAmount(0)
    			.build();

    	bank.consolidate();

    	assertTrue(bank.getOperations().contains(operation));
    	assertEquals(bank.getAvailable(), operation.getAmount(),0);
    }
}
