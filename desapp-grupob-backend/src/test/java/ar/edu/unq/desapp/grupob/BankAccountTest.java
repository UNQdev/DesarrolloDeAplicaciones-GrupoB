package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.unq.desapp.grupob.builders.BankAccountBuilder;

public class BankAccountTest {

    @Test
    public void TestBankAccountConstructor () {
        BankAccountBuilder builder = BankAccountBuilder.aBankAccountBuilder();
        Bank bank = builder.withConsolidationPeriod(15).build();
        
        assertEquals(bank.getAccountBalance(), 0, 0);
        assertEquals(bank.getOperations().size(), 0);
        assertEquals(bank.getAvailable(), bank.getAccountBalance(),0);
        assertEquals(bank.getAccrued(), 0, 0);
    }
    
    @Test
    public void TestBankAccountAddOperation(){
        BankAccountBuilder builder = BankAccountBuilder.aBankAccountBuilder();
        Bank bank = builder.withConsolidationPeriod(15).build();
        Operation operation = mock(Operation.class);
        when(operation.getAmount()).thenReturn((double) 100);
        bank.addOperation(operation);
        
        assertTrue(bank.getDevenger().getUnConsolidatedOperations().contains(operation));
        assertEquals(bank.getAccrued(), operation.getAmount(),0);
    }

    @Test
    public void TestBankAccountWithRemoveOperationConsolidated(){
        BankAccountBuilder builder = BankAccountBuilder.aBankAccountBuilder();
        Bank bank = builder.withConsolidationPeriod(6).build();
        Operation operation = mock(Operation.class);
        when(operation.getAmount()).thenReturn((double) 100);
        when(operation.getDate()).thenReturn(DateTime.parse("2014-09-01T01:00"));
        
        bank.addOperation(operation);
        bank.getDevenger().setCurrentDate(DateTime.parse("2014-09-07T01:00"));
        bank.consolidate();
        bank.removeOperation(operation);
        
        assertFalse(bank.getOperations().contains(operation));
        assertEquals(bank.getAccountBalance(), 0,0);
    }
}
