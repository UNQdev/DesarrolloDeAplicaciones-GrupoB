package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Test;

public class OperationRegisterTest {

    @Test
    public void testOperationRegisterTest () {
        OperationRegister operationRegister = new OperationRegister();
        assertEquals(operationRegister.getAccounts().size(), 3);
    }
    
    @Test
    public void testRegisterOperation () {
        OperationRegister operationRegister = new OperationRegister();
        Operation operation = mock(Operation.class);
        Account cash = new CashAccount();
        when(operation.getAccount()).thenReturn(cash);
        
        operationRegister.registerOperation(operation);
        
        assertTrue(cash.getOperations().contains(operation));
    }
    
    @Test
    public void testDeleteOperation () {
        OperationRegister operationRegister = new OperationRegister();
        Operation operation = mock(Operation.class);
        Account cash = new CashAccount();
        when(operation.getAccount()).thenReturn(cash);
        
        operationRegister.deleteOperation(operation);
        
        assertFalse(cash.getOperations().contains(operation));
    }
    
    @Test
    public void testModifyOperation() {
        OperationRegister operationRegister = new OperationRegister();
        Operation originalOperation = mock(Operation.class);
        Operation mofifiedOperation = mock(Operation.class);
        Account cash = new CashAccount();
        when(originalOperation.getAccount()).thenReturn(cash);
        when(mofifiedOperation.getAccount()).thenReturn(cash);
        
        operationRegister.modifyOperation(originalOperation, mofifiedOperation);
        
        assertFalse(cash.getOperations().contains(originalOperation));
        assertTrue(cash.getOperations().contains(mofifiedOperation));
    }
    
    @Test
    public void testAccountConsolidation () {
        OperationRegister operationRegister = new OperationRegister();
        ArrayList<Account> accounts = new ArrayList<Account>();
        Account cash = new CashAccount();
        accounts.add(cash);
         
        operationRegister.setAccounts(accounts);
        
        operationRegister.accountConsolidation();
        
        assertEquals(cash.getAccountBalance(), 0,0);
    }
}
