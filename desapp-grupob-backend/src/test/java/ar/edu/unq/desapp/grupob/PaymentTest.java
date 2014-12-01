package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.unq.desapp.grupob.model.Account;
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
        //INVOICE MOCKS
        Vendor invoiceVendor = mock(Vendor.class);
        when(invoiceVendor.getName()).thenReturn("PELOTAS FERNADEZ");
        String invoiceNumber = "A0001-00000001";
        DateTime invoiceDate = DateTime.parse("2014-09-01T01:00");
        double invoiceSubTotal = (double) 100.0;
        double invoiceTotal = (double) 121.0;
        InvoiceType invoiceType = InvoiceType.A;
        String invoiceTaxCode = "30328304326";
        String invoiceDescription = "Pelotas chicas, pelotas grandes, que pelotas tiene Fernandez!";
        //OPERATION MOCKS
        Account operationAccount = mock(Account.class);
        Category operationCategory = mock(Category.class);     
        SubCategory operationSubcategory = mock(SubCategory.class);
        String operationConcept = "Cancelacion Pago " + 
        		invoiceVendor.getName() + " - " + invoiceDescription;
        Shift operationShift = mock(Shift.class);

        Payment payment = builder.withInputDate(inputDate)
    		.withInvoiceVendor(invoiceVendor)
    		.withInvoiceNumber(invoiceNumber)
    		.withInvoiceDate(invoiceDate)
    		.withInvoiceSubTotal(invoiceSubTotal)
    		.withInvoiceTotal(invoiceTotal)
    		.withInvoiceType(invoiceType)
    		.withInvoiceTaxCode(invoiceTaxCode)
    		.withInvoiceDescription(invoiceDescription)
    		.withOperationAccount(operationAccount)    		
    		.withOperationCategory(operationCategory)
    		.withOperationSubCategory(operationSubcategory)
    		.withOperationShift(operationShift)
            .build();

        assertEquals(payment.getInvoice().getInvoiceType(), invoiceType);
        assertEquals(payment.getInvoice().getTaxCode(), invoiceTaxCode);
        assertEquals(payment.getInvoice().getSubtotal(), invoiceSubTotal, 0);
        assertEquals(payment.getInvoice().getTotal(), invoiceTotal, 0);
        assertEquals(payment.getInvoice().getVendor(), invoiceVendor);
        assertEquals(payment.getInvoice().getDate(), invoiceDate);
        assertEquals(payment.getInvoice().getNumber(), invoiceNumber);
        assertEquals(payment.getInvoice().getDescription(), invoiceDescription);
        
        assertEquals(payment.getOperation().getAmount(), invoiceTotal, 0);
        assertEquals(payment.getOperation().getCategory(), operationCategory);
        assertEquals(payment.getOperation().getSubcategory(), operationSubcategory);
        assertEquals(payment.getOperation().getConcept(), operationConcept);
        assertEquals(payment.getOperation().getDate(), inputDate);
        assertEquals(payment.getOperation().getShift(), operationShift);
        assertEquals(payment.getOperation().getType(), OperationType.Outcoming);

    }
}
