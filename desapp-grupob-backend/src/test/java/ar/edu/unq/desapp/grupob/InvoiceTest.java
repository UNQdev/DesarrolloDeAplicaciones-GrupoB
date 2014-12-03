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
    public void testInvoiceXConstructor() {
    	InvoiceBuilder builder = InvoiceBuilder.anInvoiceBuilder();
        InvoiceType invoiceType = InvoiceType.B;
        Vendor vendor = mock(Vendor.class);
        when(vendor.getName()).thenReturn("FERNANDEZ");
        when(vendor.getTaxCode()).thenReturn("20-12345678-9");
        double subtotal = 100.00;
        double total = 100.00 ;
        String number = "001";
        DateTime date = DateTime.now();
        String description = "another description";
        Invoice invoice = builder.withDate(date)
                .withNumber(number)
                .withVendor(vendor)
        		.withTaxCode(vendor.getTaxCode())
        		.withInvoiceType(invoiceType)
        		.withDescription(description)
                .withSubTotal(subtotal)
                .buildA();

        assertEquals(invoice.getInvoiceType(), invoiceType);
        assertEquals(invoice.getTaxCode(), "20-12345678-9");
        assertEquals(invoice.getSubtotal(), subtotal, 0);
        assertEquals(invoice.getTotal(), total, 0);
        assertEquals(invoice.getVendor(), vendor);

        assertNotNull(invoice.getDate());
        assertNotNull(invoice.getNumber());
        assertNotNull(invoice.getDescription());
    }

    @Test
    public void testInvoiceAConstructor() {
        InvoiceBuilder builder = InvoiceBuilder.anInvoiceBuilder();
        InvoiceType invoiceType = InvoiceType.A;
        Vendor vendor = mock(Vendor.class);
        when(vendor.getName()).thenReturn("FERNANDEZ");
        when(vendor.getTaxCode()).thenReturn("20-12345678-9");
        double subtotal = 100.00;
        double total = 121.00 ;
        String number = "001";
        DateTime date = DateTime.now();
        String description = "another description";
        Invoice invoice = builder.withDate(date)
                .withNumber(number)
                .withVendor(vendor)
                .withTax(0.21)
        		.withTaxCode(vendor.getTaxCode())
        		.withInvoiceType(invoiceType)
        		.withDescription(description)
                .withSubTotal(subtotal)
                .buildA();

        assertEquals(invoice.getInvoiceType(), invoiceType);
        assertEquals(invoice.getTaxCode(), "20-12345678-9");
        assertEquals(invoice.getSubtotal(), subtotal, 0);
        assertEquals(invoice.getTotal(), total, 0);
        assertEquals(invoice.getVendor(), vendor);

        assertNotNull(invoice.getDate());
        assertNotNull(invoice.getNumber());
        assertNotNull(invoice.getDescription());
    }
}
