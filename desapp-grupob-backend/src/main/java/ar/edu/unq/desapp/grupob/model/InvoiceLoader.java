package ar.edu.unq.desapp.grupob.model;

import java.util.HashMap;

public class InvoiceLoader {

    private HashMap<String, Invoice> fullyLoadedInvoices;

    public InvoiceLoader() {
        this.setFullyLoadedInvoices(new HashMap<String, Invoice>());
    }

    public void loadInvoice(Invoice invoice) {
        this.getFullyLoadedInvoices().put(invoice.getNumber(), invoice);
    }
    public void unLoadInvoice(String invoiceNumber) {
        this.getFullyLoadedInvoices().remove(invoiceNumber);
    }
    public void modifyInvoice(Invoice oldInvoice, Invoice newInvoice) {
    	this.getFullyLoadedInvoices().remove(oldInvoice.getNumber());
    	this.getFullyLoadedInvoices().put(newInvoice.getNumber(), newInvoice);
    }
    
    public void setFullyLoadedInvoices(HashMap<String, Invoice> fullyLoadedInvoices) {
        this.fullyLoadedInvoices = fullyLoadedInvoices;
    }
    
    public HashMap<String, Invoice> getFullyLoadedInvoices() {
        return fullyLoadedInvoices;
    }
}