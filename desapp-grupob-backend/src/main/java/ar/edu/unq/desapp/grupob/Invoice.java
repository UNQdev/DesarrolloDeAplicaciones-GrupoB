package ar.edu.unq.desapp.grupob;

import java.util.Date;

import org.joda.time.DateTime;

public class Invoice {
    
    private String number;
    private DateTime date;
    private Vendor vendor;
    private InvoiceType invoiceType; 
    private int taxCode;
    private String description;
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
    public void setNumber(String number) {
        this.number = number;
    }
    public DateTime getDate() {
        return date;
    }
    public void setDate(DateTime date) {
        this.date = date;
    }
    public Vendor getVendor() {
        return vendor;
    }
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
    public InvoiceType getInvoiceType() {
        return invoiceType;
    }
    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }
    public int getTaxCode() {
        return taxCode;
    }
    public void setTaxCode(int taxCode) {
        this.taxCode = taxCode;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }    
}
