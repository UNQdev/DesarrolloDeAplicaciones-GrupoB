package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class InvoiceLoaderTest {

    @Test
    public void testInvoiceLoaderConstructorWithOutInvoices() {
        InvoiceLoader invoiceLoader = new InvoiceLoader();

        assertNull(invoiceLoader.getFullyLoadedInvoice("0"));
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

        invoiceLoader.loadInvoice(invoice);

        assertEquals(invoiceLoader.getFullyLoadedInvoices().get(invoiceNumber),
                invoice);
        
        invoiceLoader.unLoadInvoice(invoiceNumber);
        
        assertNull(invoiceLoader.getFullyLoadedInvoice(invoiceNumber));
    }
    
    //TODO: agregar test de modifyInvoice(oldInvoice, newInvoice)
}
