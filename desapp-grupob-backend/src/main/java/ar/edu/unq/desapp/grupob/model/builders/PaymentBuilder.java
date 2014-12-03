package ar.edu.unq.desapp.grupob.model.builders;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.model.Account;
import ar.edu.unq.desapp.grupob.model.AccountType;
import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.model.InvoiceType;
import ar.edu.unq.desapp.grupob.model.Operation;
import ar.edu.unq.desapp.grupob.model.OperationType;
import ar.edu.unq.desapp.grupob.model.Payment;
import ar.edu.unq.desapp.grupob.model.Shift;
import ar.edu.unq.desapp.grupob.model.SubCategory;
import ar.edu.unq.desapp.grupob.model.Vendor;

public class PaymentBuilder {

    // Access
    public static PaymentBuilder aPaymentBuilder() {
        return new PaymentBuilder();
    }

    // Instances 
    private DateTime inputDate;
    private Vendor invoiceVendor;
    private String invoiceNumber;
    private DateTime invoiceDate;
    private double invoiceSubTotal;
    private double invoiceTotal;
    private InvoiceType invoiceType;
    private String invoiceTaxCode;
    private String invoiceDescription;
    private AccountType operationAccount;
    private Category operationCategory;
    private SubCategory operationSubCategory;
    private Shift operationShift;

    // Constructor method
    public Payment build() {

    Payment payment = new Payment(inputDate,
    			invoiceVendor, invoiceNumber,
        		invoiceDate,
        		invoiceSubTotal, invoiceTotal,
        		invoiceType, invoiceTaxCode,
        		invoiceDescription,
        		operationAccount,
        		operationCategory, operationSubCategory,
        		operationShift);
        return payment;
    }

    // Methods
    public PaymentBuilder withInputDate(final DateTime inputDate) {
        this.inputDate = inputDate;
        return this;
    }
    public PaymentBuilder withInvoiceVendor(final Vendor invoiceVendor) {
        this.invoiceVendor = invoiceVendor;
        return this;
    }
    public PaymentBuilder withInvoiceNumber(final String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }
    public PaymentBuilder withInvoiceDate(final DateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
        return this;
    }
    public PaymentBuilder withInvoiceSubTotal(final double invoiceSubTotal) {
        this.invoiceSubTotal = invoiceSubTotal;
        return this;
    }
    public PaymentBuilder withInvoiceTotal(final double invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
        return this;
    }
    public PaymentBuilder withInvoiceType(final InvoiceType invoiceType) {
    	this.invoiceType = invoiceType;
    	return this;
    }
    public PaymentBuilder withInvoiceTaxCode(final String invoiceTaxCode) {
    	this.invoiceTaxCode = invoiceTaxCode;
    	return this;
    }
    public PaymentBuilder withInvoiceDescription(final String invoiceDescription) {
        this.invoiceDescription = invoiceDescription;
        return this;
    }
    public PaymentBuilder withOperationAccount(final AccountType account) {
    	this.operationAccount = account;
    	return this;
    }
    public PaymentBuilder withOperationCategory(final Category category) {
        this.operationCategory = category;
        return this;
    }
    public PaymentBuilder withOperationSubCategory(final SubCategory subcategory) {
        this.operationSubCategory = subcategory;
        return this;
    }
    public PaymentBuilder withOperationShift (final Shift shift) {
    	this.operationShift = shift;
    	return this;
    }

}
