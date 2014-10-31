package ar.edu.unq.desapp.grupob.model;

import org.joda.time.DateTime;

public class Invoice extends Entity{

    private static final long serialVersionUID = 8526907700926481970L;
    
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
        this.setNumber(number);
        this.setDate(date);
        this.setVendor(entity);
        this.setInvoiceType(invoiceType);
        this.setTaxCode(taxCode);
        this.setDescription(description);
        this.setSubtotal(subtotal);
        this.setTotal(total);
    }
    
    public Invoice () {
        
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

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public void setTaxCode(int taxCode) {
        this.taxCode = taxCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
