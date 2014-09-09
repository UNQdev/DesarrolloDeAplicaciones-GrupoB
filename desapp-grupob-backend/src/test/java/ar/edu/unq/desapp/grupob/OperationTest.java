package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.unq.desapp.grupob.builders.OperationBuilder;

public class OperationTest {

    @Test
    public void testOperationConstructor() {
        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        OperationType operationType = mock(OperationType.class);
        Shift shift = mock(Shift.class);
        DateTime date = DateTime.parse("2014-09-01T01:00");
        Invoice invoice = mock(Invoice.class);
        Category category = mock(Category.class);
        Account account = mock(Account.class);
        double amount = 0;
        String concept = "";

        Operation operation = builder.withAccount(account).withAmount(amount)
                .withCategory(category).withConcept(concept).withDate(date)
                .withInvoice(invoice).withShift(shift).withType(operationType)
                .build();
        assertEquals(operation.getAccount(), account);
        assertEquals(operation.getAmount(), amount, 0);
        assertEquals(operation.getCategory(), category);
        assertEquals(operation.getConcept(), concept);
        assertEquals(operation.getDate(), date);
        assertEquals(operation.getInvoice(), invoice);
        assertEquals(operation.getShift(), shift);
        assertEquals(operation.getType(), operationType);
    }

    @Test
    public void testGetRealAmountOfIncoming() {
        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        double amount = 100;
        Operation operation = builder.withType(OperationType.Incoming)
                .withAmount(amount).build();

        assertEquals(operation.getRealAmount(), amount, 0);
    }
    
    @Test
    public void testGetRealAmountOfOutcoming() {
        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        double amount = 100;
        Operation operation = builder.withType(OperationType.Outcoming)
                .withAmount(amount).build();

        assertEquals(operation.getRealAmount(), -amount, 0);
    }
    
    @Test
    public void testReachedConsolidationDate() {
        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        Operation operation = builder.build();

        DateTime limitDateOperation = DateTime.parse("2014-09-10T01:00");
        DateTime currentDateTime =  DateTime.parse("2014-09-10T01:00");
        assertTrue(operation.reachedConsolidationDate(limitDateOperation, currentDateTime));
    }
}
