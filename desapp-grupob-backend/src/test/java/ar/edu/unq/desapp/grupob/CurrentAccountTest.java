package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unq.desapp.grupob.builders.OperationBuilder;

public class CurrentAccountTest {
    
    @Test
    public void testCurrentAccountConstructor() {
        CurrentAccount cc = new CurrentAccount();

        assertEquals(cc.getAccountBalance(), 0, 0);
        assertEquals(cc.getOperations().size(), 0);
    }

    @Test
    public void testCurrentAccountAddOperationTypeIncoming() {
        CurrentAccount cc = new CurrentAccount();

        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        Operation operation = builder.withAccount(cc).withAmount(100)
                .withType(OperationType.Incoming).build();

        cc.addOperation(operation);

        assertTrue(cc.getOperations().contains(operation));
        assertEquals(operation.getRealAmount(), 100,0);
        assertEquals(cc.getAccountBalance(), operation.getRealAmount(), 0);
    }

    @Test
    public void testCurrentAccountAddOperationTypeOutcoming() {
        CurrentAccount cc = new CurrentAccount();

        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        Operation operation = builder.withAccount(cc).withAmount(100)
                .withType(OperationType.Outcoming).build();

        cc.addOperation(operation);

        assertTrue(cc.getOperations().contains(operation));
        assertEquals(operation.getRealAmount(), -100,0);
        assertEquals(cc.getAccountBalance(), -100, 0);
    }
    
    @Test
    public void testCurrentAccountRemoveOperationTypeIncoming() {
        CurrentAccount cc = new CurrentAccount();

        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        Operation operation = builder.withAccount(cc).withAmount(100)
                .withType(OperationType.Incoming).build();

        
        cc.removeOperation(operation);

        assertFalse(cc.getOperations().contains(operation));
        assertEquals(operation.getRealAmount(), 100,0);
        assertEquals(cc.getAccountBalance(), operation.getRealAmount(), 0);
    }
    
    @Test
    public void testCurrentAccountRemoveOperationTypeOutcoming() {
        CurrentAccount cc = new CurrentAccount();

        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        Operation operation = builder.withAccount(cc).withAmount(100)
                .withType(OperationType.Outcoming).build();

        cc.removeOperation(operation);

        assertFalse(cc.getOperations().contains(operation));
        assertEquals(operation.getRealAmount(), -100,0);
        assertEquals(cc.getAccountBalance(), -100, 0);
    }
    
    @Test
    public void testCurrentAccountBalance () {
        CurrentAccount cc = new CurrentAccount();
        cc.consolidate();
        
        assertEquals(cc.getAccountBalance(), 0,0 );
    }

}
