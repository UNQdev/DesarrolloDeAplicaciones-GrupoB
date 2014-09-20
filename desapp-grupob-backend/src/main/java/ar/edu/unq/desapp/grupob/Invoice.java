package ar.edu.unq.desapp.grupob;

import org.joda.time.DateTime;

public class Invoice {

    private String number;
    private DateTime date;
    private Vendor vendor;
    private InvoiceType invoiceType;
    private int taxCode;
    private String description;
    /*
     * TODO: Evaluar la posibilidad de un objeto TAX en el cual delegar todas las persepciones de la factura
     * 
     * private Tax taxes;
     */
    private double subtotal;
    private double total;

    public Invoice(String number, DateTime date, Vendor entity, InvoiceType invoiceType, 
            int taxCode, String description, double subtotal, double total) {
        this.number = number;
        this.date = date;
        this.vendor = entity;
        this.invoiceType = invoiceType;
        this.taxCode = taxCode;
        this.description = description;
        this.subtotal = subtotal;
        this.total = total;
    }

    /*
     * GETTERS & SETTERS
     *
     */
    public String getNumber() {
        return number;
    }
    public DateTime getDate() {
        return date;
    }
    public Vendor getVendor() {
        return vendor;
    }
    public InvoiceType getInvoiceType() {
        return invoiceType;
    }
    public int getTaxCode() {
        return taxCode;
    }
    public String getDescription() {
        return description;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public double getTotal() {
        return total;
    }
}
