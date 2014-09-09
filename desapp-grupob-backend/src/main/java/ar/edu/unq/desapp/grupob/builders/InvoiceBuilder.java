package ar.edu.unq.desapp.grupob.builders;

import java.util.Date;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.Invoice;
import ar.edu.unq.desapp.grupob.InvoiceType;
import ar.edu.unq.desapp.grupob.Vendor;

public class InvoiceBuilder {

    // Access
    public static InvoiceBuilder anInvoiceBuilder() {
        return new InvoiceBuilder();
    }

    // Instances
    private String number = "";
    private DateTime date = DateTime.now();
    private Vendor vendor;
    private InvoiceType invoiceType;
    private int taxCode;
    private String description = "no description";
    private double subtotal;
    private double total;

    // Construct method
    public Invoice build() {
        Invoice invoice = new Invoice(number, date, vendor, invoiceType,
                taxCode, description, subtotal, total);
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

    public InvoiceBuilder withInvoiceType(final InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
        return this;
    }

    public InvoiceBuilder withTaxCode(final int taxCode) {
        this.taxCode = taxCode;
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

    public InvoiceBuilder withTotal(final double total) {
        this.total = total;
        return this;
    }
}
