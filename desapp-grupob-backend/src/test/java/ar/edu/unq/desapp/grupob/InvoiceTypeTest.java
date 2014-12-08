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
    public void testInvoiceTypeAFullTotal() {
    	InvoiceType invoiceTypeA = InvoiceType.A;
    	double amount = 100.0;
    	double tax = 0.21;
    	double totalExpected = 124.5; 
    	
    	assertEquals(invoiceTypeA.getFullTotal(amount, tax), totalExpected, 0.1);    	
    }
	
	@Test
    public void testInvoiceTypeATotal() {
    	InvoiceType invoiceTypeA = InvoiceType.A;
    	double amount = 100.0;
    	double tax = 0.21;
    	double totalExpected = 121.0; 
    	
    	assertEquals(invoiceTypeA.getTotal(amount, tax), totalExpected, 0.1);    	
    }
    
    @Test
    public void testInvoiceTypeB () {
        InvoiceType invoiceTypeB = InvoiceType.B;
        
        assertEquals(invoiceTypeB, InvoiceType.B);
    }
    
    @Test
    public void testInvoiceTypeBFullTotal() {
    	InvoiceType invoiceTypeB = InvoiceType.B;
    	double amount = 100.0;
    	double tax = 0.21;
    	double totalExpected = 100.0; 
    	
    	assertEquals(invoiceTypeB.getFullTotal(amount, tax), totalExpected, 0.1);    	
    }
	
	@Test
    public void testInvoiceTypeBTotal() {
    	InvoiceType invoiceTypeB = InvoiceType.B;
    	double amount = 100.0;
    	double tax = 0.21;
    	double totalExpected = 100.0; 
    	
    	assertEquals(invoiceTypeB.getTotal(amount, tax), totalExpected, 0.1);    	
    }
    
    @Test
    public void testInvoiceTypeC () {
        InvoiceType invoiceTypeC = InvoiceType.C;
        
        assertEquals(invoiceTypeC, InvoiceType.C);
    }
    
    @Test
    public void testInvoiceTypeCFullTotal() {
    	InvoiceType invoiceTypeC = InvoiceType.C;
    	double amount = 100.0;
    	double tax = 0.21;
    	double totalExpected = 100.0; 
    	
    	assertEquals(invoiceTypeC.getFullTotal(amount, tax), totalExpected, 0.1);    	
    }
	
	@Test
    public void testInvoiceTypeCTotal() {
    	InvoiceType invoiceTypeC = InvoiceType.C;
    	double amount = 100.0;
    	double tax = 0.21;
    	double totalExpected = 100.0; 
    	
    	assertEquals(invoiceTypeC.getTotal(amount, tax), totalExpected, 0.1);    	
    }
    
    @Test
    public void testInvoiceTypeX () {
        InvoiceType invoiceTypeX = InvoiceType.X;
        
        assertEquals(invoiceTypeX, InvoiceType.X);
    }
    
    @Test
    public void testInvoiceTypeXFullTotal() {
    	InvoiceType invoiceTypeX = InvoiceType.X;
    	double amount = 100.0;
    	double tax = 0.21;
    	double totalExpected = 100.0; 
    	
    	assertEquals(invoiceTypeX.getFullTotal(amount, tax), totalExpected, 0.1);    	
    }
	
	@Test
    public void testInvoiceTypeXTotal() {
    	InvoiceType invoiceTypeX = InvoiceType.X;
    	double amount = 100.0;
    	double tax = 0.21;
    	double totalExpected = 100.0; 
    	
    	assertEquals(invoiceTypeX.getTotal(amount, tax), totalExpected, 0.1);    	
    }
}
