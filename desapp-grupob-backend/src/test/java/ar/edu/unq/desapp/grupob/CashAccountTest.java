package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unq.desapp.grupob.model.CashAccount;
import ar.edu.unq.desapp.grupob.model.Operation;
import ar.edu.unq.desapp.grupob.model.OperationType;
import ar.edu.unq.desapp.grupob.model.builders.OperationBuilder;

public class CashAccountTest {
    @Test
    public void testCashAccountConstructor() {
        CashAccount cash = new CashAccount();

        assertEquals(cash.getAccountBalance(), 0, 0);
        assertEquals(cash.getOperations().size(), 0);
    }

    @Test
    public void testCashAccountAddOperationTypeIncoming() {
        CashAccount cash = new CashAccount();

        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        Operation operation = builder.withAccount(cash).withAmount(100)
                .withType(OperationType.Incoming).build();

        cash.addOperation(operation);

        assertTrue(cash.getOperations().contains(operation));
        assertEquals(operation.getRealAmount(), 100,0);
        assertEquals(cash.getAccountBalance(), operation.getRealAmount(), 0);
    }

    @Test
    public void testCashAccountAddOperationTypeOutcoming() {
        CashAccount cash = new CashAccount();

        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        Operation operation = builder.withAccount(cash).withAmount(100)
                .withType(OperationType.Outcoming).build();

        cash.addOperation(operation);

        assertTrue(cash.getOperations().contains(operation));
        assertEquals(operation.getRealAmount(), -100,0);
        assertEquals(cash.getAccountBalance(), 0, 0);
    }
    
    @Test
    public void testCashAccountRemoveOperationTypeIncoming() {
        CashAccount cash = new CashAccount();

        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        Operation operation = builder.withAccount(cash).withAmount(100)
                .withType(OperationType.Incoming).build();

        
        cash.removeOperation(operation);

        assertFalse(cash.getOperations().contains(operation));
        assertEquals(operation.getRealAmount(), 100,0);
        assertEquals(cash.getAccountBalance(), operation.getRealAmount(), 0);
    }
    
    @Test
    public void testCashAccountRemoveOperationTypeOutcoming() {
        CashAccount cash = new CashAccount();

        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        Operation operation = builder.withAccount(cash).withAmount(100)
                .withType(OperationType.Outcoming).build();

        cash.removeOperation(operation);

        assertFalse(cash.getOperations().contains(operation));
        assertEquals(operation.getRealAmount(), -100,0);
        assertEquals(cash.getAccountBalance(), 0, 0);
    }
    
    @Test
    public void testCashAccountBalance () {
        CashAccount cash = new CashAccount();
        cash.consolidate();
        
        assertEquals(cash.getAccountBalance(), 0,0 );
    }

}
