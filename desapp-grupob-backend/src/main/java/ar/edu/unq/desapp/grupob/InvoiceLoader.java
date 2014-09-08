package ar.edu.unq.desapp.grupob;

import java.util.HashMap;

public class InvoiceLoader {
    
    private HashMap<String, Invoice> fullyLoadedInvoices;
    
    public InvoiceLoader() {
        this.fullyLoadedInvoices = new HashMap<String, Invoice>();
    }
    
    public void loadInvoice(Invoice invoice) {
        this.fullyLoadedInvoices.put(invoice.getNumber(), invoice);
    }
    public void unLoadInvoice(String invoiceNumber) {
        this.fullyLoadedInvoices.remove(invoiceNumber);
    }
    public Invoice getFullyLoadedInvoice(int invoiceNumber) {
        return this.fullyLoadedInvoices.get(invoiceNumber);
    }
}