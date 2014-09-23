package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import ar.edu.unq.desapp.grupob.model.Invoice;
import ar.edu.unq.desapp.grupob.model.InvoiceLoader;

public class InvoiceLoaderTest {

    @Test
    public void testInvoiceLoaderConstructorWithOutInvoices() {
        InvoiceLoader invoiceLoader = new InvoiceLoader();

        assertTrue(invoiceLoader.getFullyLoadedInvoices().isEmpty());
    }

    @Test
    public void testInvoiceLoaderLoadInvoice() {
        Invoice invoice = mock(Invoice.class);
        String invoiceNumber = "011";
        when(invoice.getNumber()).thenReturn(invoiceNumber);

        InvoiceLoader invoiceLoader = new InvoiceLoader();

        invoiceLoader.loadInvoice(invoice);

        assertEquals(invoiceLoader.getFullyLoadedInvoices().get(invoiceNumber),
                invoice);
    }

    @Test
    public void testInvoiceLoaderUnLoadInvoice() {
        Invoice invoice = mock(Invoice.class);
        String invoiceNumber = "011";
        when(invoice.getNumber()).thenReturn(invoiceNumber);

        InvoiceLoader invoiceLoader = new InvoiceLoader();

        assertNull(invoiceLoader.getFullyLoadedInvoices().get(invoiceNumber));
        
        invoiceLoader.loadInvoice(invoice);

        assertEquals(invoiceLoader.getFullyLoadedInvoices().get(invoiceNumber),
                invoice);

        invoiceLoader.unLoadInvoice(invoiceNumber);

        assertNull(invoiceLoader.getFullyLoadedInvoices().get(invoiceNumber));
    }

    @Test
    public void testInvoiceModifyInvoice() {
        Invoice oldInvoice = mock(Invoice.class);
        String oldInvoiceNumber = "011";
        when(oldInvoice.getNumber()).thenReturn(oldInvoiceNumber);

        Invoice newInvoice = mock(Invoice.class);
        String newInvoiceNumber = oldInvoiceNumber;
        when(newInvoice.getNumber()).thenReturn(newInvoiceNumber);
        
        InvoiceLoader invoiceLoader = new InvoiceLoader();

        invoiceLoader.loadInvoice(oldInvoice);

        assertEquals(invoiceLoader.getFullyLoadedInvoices().get(oldInvoiceNumber),
                oldInvoice);

        invoiceLoader.modifyInvoice(oldInvoice, newInvoice);

        assertNotEquals(invoiceLoader.getFullyLoadedInvoices().get(oldInvoiceNumber), oldInvoice);
        assertEquals(invoiceLoader.getFullyLoadedInvoices().get(newInvoiceNumber), newInvoice);
    }
}
