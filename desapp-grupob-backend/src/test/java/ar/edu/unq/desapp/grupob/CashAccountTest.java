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
    public void testCashAccountAddOperationTypeIncoming() throws Exception {
        CashAccount cash = new CashAccount();

        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        Operation operation = builder.withAccount(cash.getAccountName()).withAmount(100)
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
        Operation operation = builder.withAccount(cash.getAccountName()).withAmount(100)
                .withType(OperationType.Outcoming).build();

        try {
            cash.addOperation(operation);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Monto inválido, la cuenta efectivo no puede quedar en negativo!");
        }

        
        assertFalse(cash.getOperations().contains(operation));
        assertEquals(cash.getAccountBalance(), 0, 0);
    }
    
    @Test
    public void testCashAccountRemoveOperationTypeIncoming() throws Exception {
        CashAccount cash = new CashAccount();

        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        Operation operation = builder.withAccount(cash.getAccountName()).withAmount(100)
                .withType(OperationType.Incoming).build();

        
        cash.removeOperation(operation);

        assertFalse(cash.getOperations().contains(operation));
        assertEquals(operation.getRealAmount(), 100,0);
        assertEquals(cash.getAccountBalance(), operation.getRealAmount(), 0);
    }
    
    @Test
    public void testCashAccountRemoveOperationTypeOutcoming() throws Exception {
        CashAccount cash = new CashAccount();
        cash.setAccountBalance(100);
        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        Operation operation = builder.withAccount(cash.getAccountName()).withAmount(100)
                .withType(OperationType.Outcoming).build();

        cash.removeOperation(operation);

        assertFalse(cash.getOperations().contains(operation));
        assertEquals(operation.getRealAmount(), -100,0);
        assertEquals(cash.getAccountBalance(), 0, 0);
    }
    
    @Test
    public void testCashAccountBalance () throws Exception {
        CashAccount cash = new CashAccount();
        cash.consolidate();
        
        assertEquals(cash.getAccountBalance(), 0,0 );
    }

}
