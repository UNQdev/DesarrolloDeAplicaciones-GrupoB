package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unq.desapp.grupob.model.OperationType;

public class OperationTypeTest {

    @Test
    public void testOperationTypeIncoming () {
        OperationType operationType = OperationType.Incoming;
        double amount = 100;
        
        assertEquals(operationType.getValue(amount), amount,0);
    }
    
    @Test
    public void testOperationTypeOutcoming () {
        OperationType operationType = OperationType.Outcoming;
        double amount = 100;
        
        assertEquals(operationType.getValue(amount), -amount,0);
    }
}
