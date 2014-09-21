package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.unq.desapp.grupob.model.Invoice;
import ar.edu.unq.desapp.grupob.model.InvoiceType;
import ar.edu.unq.desapp.grupob.model.Vendor;
import ar.edu.unq.desapp.grupob.model.builders.InvoiceBuilder;

public class InvoiceTest {

    @Test
    public void testInvoiceConstructorDefault() {
        InvoiceBuilder builder = InvoiceBuilder.anInvoiceBuilder();
        Invoice invoice = builder.build();

        assertNotNull(invoice.getDate());
        assertNotNull(invoice.getNumber());
        assertNotNull(invoice.getDescription());

        assertNull(invoice.getInvoiceType());
        assertEquals(invoice.getTaxCode(),0);
        assertEquals(invoice.getSubtotal(),0,0);
        assertEquals(invoice.getTotal(),0,0);
        assertNull(invoice.getVendor());
    }

    @Test
    public void testInvoiceConstructorFullyLoaded() {
        InvoiceBuilder builder = InvoiceBuilder.anInvoiceBuilder();
        InvoiceType invoiceType = mock(InvoiceType.class);
        Vendor vendor = mock(Vendor.class);
        int taxCode = 201234567;
        int subtotal = 20;
        int total = 30;
        String number = "001";
        DateTime date = DateTime.now();
        String description = "another description";
        Invoice invoice = builder.withInvoiceType(invoiceType)
                .withTaxCode(taxCode).withSubTotal(subtotal).withTotal(total)
                .withVendor(vendor).withNumber(number).withDate(date)
                .withDescription(description).build();

        assertEquals(invoice.getInvoiceType(), invoiceType);
        assertEquals(invoice.getTaxCode(), taxCode);
        assertEquals(invoice.getSubtotal(), subtotal, 0);
        assertEquals(invoice.getTotal(), total, 0);
        assertEquals(invoice.getVendor(), vendor);

        assertNotNull(invoice.getDate());
        assertNotNull(invoice.getNumber());
        assertNotNull(invoice.getDescription());
    }
}
