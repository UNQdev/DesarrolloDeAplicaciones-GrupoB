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
    public void modifyInvoice(Invoice oldInvoice, Invoice newInvoice) {
    	this.getFullyLoadedInvoices().remove(oldInvoice.getNumber());
    	this.getFullyLoadedInvoices().put(newInvoice.getNumber(), newInvoice);
    }

    public Invoice getFullyLoadedInvoice(String invoiceNumber) {
        return this.fullyLoadedInvoices.get(invoiceNumber);
    }
    public HashMap<String, Invoice> getFullyLoadedInvoices() {
        return fullyLoadedInvoices;
    }
}