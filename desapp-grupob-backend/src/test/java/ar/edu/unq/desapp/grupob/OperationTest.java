package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.unq.desapp.grupob.model.Account;
import ar.edu.unq.desapp.grupob.model.AccountType;
import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.model.Invoice;
import ar.edu.unq.desapp.grupob.model.Operation;
import ar.edu.unq.desapp.grupob.model.OperationType;
import ar.edu.unq.desapp.grupob.model.Shift;
import ar.edu.unq.desapp.grupob.model.SubCategory;
import ar.edu.unq.desapp.grupob.model.builders.OperationBuilder;

public class OperationTest {

    @SuppressWarnings("static-access")
    @Test
    public void testOperationConstructor() {
        OperationBuilder builder = OperationBuilder.aOperationBuilder();
        OperationType operationType = mock(OperationType.class);
        Shift shift = Shift.Afternoon;
        DateTime date = DateTime.parse("2014-09-01T01:00");
        Category category = mock(Category.class);
        SubCategory subcategory = mock(SubCategory.class);
        AccountType accountType = AccountType.Bank;
        double amount = 0;
        String concept = "";

        Operation operation = builder.withAccount(accountType)
        		.withAmount(amount)
                .withCategory(category)
                .withSubCategory(subcategory)
                .withConcept(concept)
                .withDate(date)
                .withShift(shift)
                .withType(operationType)
                .build();
        assertEquals(operation.getAccountType(), accountType.Bank);
        assertEquals(operation.getAmount(), amount, 0);
        assertEquals(operation.getCategory(), category);
        assertEquals(operation.getSubcategory(), subcategory);
        assertEquals(operation.getConcept(), concept);
        assertEquals(operation.getDate(), date);
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
}
