package ar.edu.unq.desapp.grupob.model.builders;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.model.Invoice;
import ar.edu.unq.desapp.grupob.model.InvoiceType;
import ar.edu.unq.desapp.grupob.model.Vendor;

public class InvoiceBuilder {

    // Access
    public static InvoiceBuilder anInvoiceBuilder() {
        return new InvoiceBuilder();
    }

    // Instances
    private String number = "";
    private DateTime date = DateTime.now();
    private Vendor vendor;
    private String taxCode;
    private InvoiceType invoiceType;
    private String description = "no description";
    private double subtotal;
    private double tax;    
    private boolean hasIIBB;
    private double noGrabado;
    

    // Construct method
    public Invoice buildA() {
        Invoice invoice = new Invoice(number, date, vendor, invoiceType,
                description, subtotal, tax, hasIIBB, noGrabado);
        return invoice;
    }
    public Invoice buildX() {

        Invoice invoice = new Invoice(number, date, vendor, invoiceType,
                description, subtotal);
        return invoice;
    }

    public InvoiceBuilder withNumber(final String number) {
        this.number = number;
        return this;
    }
    public InvoiceBuilder withDate(final DateTime date) {
        this.date = date;
        return this;
    }
    public InvoiceBuilder withVendor(final Vendor vendor) {
        this.vendor = vendor;
        return this;
    }
    public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public InvoiceBuilder withInvoiceType(final InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
        return this;
    }
    public InvoiceBuilder withDescription(final String description) {
        this.description = description;
        return this;
    }
    public InvoiceBuilder withSubTotal(final double subtotal) {
        this.subtotal = subtotal;
        return this;
    }
    public InvoiceBuilder withTax(final double tax) {
        this.tax = tax;
        return this;
    }
    public InvoiceBuilder withTaxCode(final String taxCode) {
        this.setTaxCode(taxCode);
        return this;
    }
    public InvoiceBuilder withHasIIBB(final boolean hasIIBB) {
        this.hasIIBB = hasIIBB;
        return this;
    }
    public InvoiceBuilder withNoGrabado(final double noGrabado) {
        this.noGrabado = noGrabado;
        return this;
    }
}
