package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.unq.desapp.grupob.model.Account;
import ar.edu.unq.desapp.grupob.model.AccountType;
import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.model.Invoice;
import ar.edu.unq.desapp.grupob.model.InvoiceType;
import ar.edu.unq.desapp.grupob.model.Operation;
import ar.edu.unq.desapp.grupob.model.Payment;
import ar.edu.unq.desapp.grupob.model.OperationType;
import ar.edu.unq.desapp.grupob.model.Shift;
import ar.edu.unq.desapp.grupob.model.SubCategory;
import ar.edu.unq.desapp.grupob.model.Vendor;
import ar.edu.unq.desapp.grupob.model.builders.OperationBuilder;
import ar.edu.unq.desapp.grupob.model.builders.PaymentBuilder;

public class PaymentTest {

	@Test
    public void testOperationConstructor() {
        PaymentBuilder builder = PaymentBuilder.aPaymentBuilder();
        DateTime inputDate = DateTime.parse("2014-10-01T01:00");
        Invoice invoice = mock(Invoice.class);
        Operation operation = mock(Operation.class);

        Payment payment = builder.withInputDate(inputDate)
        	.withInvoice(invoice)
        	.withOperation(operation)
            .build();

        assertEquals(payment.getInvoice(), invoice);
        assertEquals(payment.getOperation(), operation);
    }
}
