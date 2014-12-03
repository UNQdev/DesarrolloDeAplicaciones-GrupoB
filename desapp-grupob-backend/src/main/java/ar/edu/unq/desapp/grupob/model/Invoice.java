package ar.edu.unq.desapp.grupob.model;

import javax.xml.bind.annotation.XmlTransient;

import org.joda.time.DateTime;

public class Invoice extends Entity{

    private static final long serialVersionUID = 8526907700926481970L;
    
    private String number;
    private DateTime date;
    private Vendor vendor;
    private InvoiceType invoiceType;
    private String taxCode;
    private String description;
    private double subtotal;
    private double total;

    public Invoice(String number, DateTime date, Vendor entity, InvoiceType invoiceType, 
            String description, double subtotal) {
        this.setNumber(number);
        this.setDate(date);
        this.setVendor(entity);
        this.setInvoiceType(invoiceType);
        this.setTaxCode(entity.getTaxCode());
        this.setDescription(description);
        this.setSubtotal(subtotal);
        this.setTotal(invoiceType.getTotal(subtotal, 0.0));
    }
    
    public Invoice(String number, DateTime date, Vendor entity, InvoiceType invoiceType, 
            String description, double subtotal, double tax, boolean hasIIBB, double noGrabado) {
        this.setNumber(number);
        this.setDate(date);
        this.setVendor(entity);
        this.setInvoiceType(invoiceType);
        this.setTaxCode(entity.getTaxCode());
        this.setDescription(description);
        this.setSubtotal(subtotal);
        this.setTotalIIBB(invoiceType, subtotal, tax, hasIIBB, noGrabado);
    }
    
    public Invoice() {};
    
	/*
     * GETTERS & SETTERS
     *
     */
    public String getNumber() {
        return number;
    }
    @XmlTransient
    public DateTime getDate() {
        return date;
    }
    public String getDateToString(){
        return this.getDate().toString("YYYY-MM-dd");
    }    
    public Vendor getVendor() {
        return vendor;
    }
    public InvoiceType getInvoiceType() {
        return invoiceType;
    }
    public String getTaxCode() {
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

    public void setTaxCode(String taxCode) {
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
    
    public void setTotalIIBB(InvoiceType invoiceType, double subtotal, double tax, boolean hasIIBB, double noGrabado) {
        if (hasIIBB) {
        	this.setTotal(invoiceType.getFullTotal(subtotal, tax) + noGrabado);
        } else {
        	this.setTotal(invoiceType.getTotal(subtotal, tax) + noGrabado);
        }
    }
}
