package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import org.junit.Test;
import ar.edu.unq.desapp.grupob.builders.OperationBuilder;

public class CashAccountTest {
    @Test
    public void testCashAccountConstructor() {
        Cash cash = new Cash();

        assertEquals(cash.getAccountBalance(), 0, 0);
        assertEquals(cash.getOperations().size(), 0);
    }

    @Test
    public void testCashAccountAddOperationTypeIncoming() {
        Cash cash = new Cash();

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
        Cash cash = new Cash();

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
        Cash cash = new Cash();

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
        Cash cash = new Cash();

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
        Cash cash = new Cash();
        cash.consolidate();
        
        assertEquals(cash.getAccountBalance(), 0,0 );
    }

}
