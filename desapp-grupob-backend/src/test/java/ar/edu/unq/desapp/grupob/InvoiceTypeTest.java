package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unq.desapp.grupob.model.InvoiceType;

public class InvoiceTypeTest {

    @Test
    public void testInvoiceTypeA () {
        InvoiceType invoiceTypeA = InvoiceType.A;
        
        assertEquals(invoiceTypeA, InvoiceType.A);
    }
    
    @Test
    public void testInvoiceTypeB () {
        InvoiceType invoiceTypeB = InvoiceType.B;
        
        assertEquals(invoiceTypeB, InvoiceType.B);
    }
    
    @Test
    public void testInvoiceTypeC () {
        InvoiceType invoiceTypeC = InvoiceType.C;
        
        assertEquals(invoiceTypeC, InvoiceType.C);
    }
    
    @Test
    public void testInvoiceTypeX () {
        InvoiceType invoiceTypeX = InvoiceType.X;
        
        assertEquals(invoiceTypeX, InvoiceType.X);
    }
}
