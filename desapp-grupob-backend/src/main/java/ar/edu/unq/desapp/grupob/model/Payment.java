package ar.edu.unq.desapp.grupob.model;

import javax.xml.bind.annotation.XmlTransient;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.model.builders.InvoiceBuilder;
import ar.edu.unq.desapp.grupob.model.builders.OperationBuilder;

public class Payment extends Entity {
    private static final long serialVersionUID = 4150568739587426311L;
    /*
     * PROPERTIES
     */
    private DateTime inputDate;
    private Invoice invoice;
    private Operation operation;

    /*
     * CONSTRUCTORS
     */
    public Payment () {};

    public Payment(DateTime inputDate, Invoice invoice, Operation operation) {
        this.setInputDate(inputDate);
        this.setInvoice(invoice);
        this.setOperation(operation);
    }

    // INPUT DATE
    @XmlTransient
	public DateTime getInputDate() {
        return inputDate;
    }
    public void setInputDate(DateTime inputDate) {
        this.inputDate = inputDate;
    }
    public String getDateToString(){
        return this.getInputDate().toString("YYYY-MM-dd");
    }

    // INVOICE
    public String getVendorName() {
    	return this.invoice.getVendor().getName();
    }
    public String getInvoiceType() {
    	return this.invoice.getInvoiceType().toString();
    }
    public String getInvoiceNumber() {
    	return this.invoice.getNumber();
    }
    public String getInvoiceDescription() {
    	return this.invoice.getDescription();
    }
    public double getSubtotal() {
    	return this.invoice.getSubtotal();
    }
    public double getTotal() {
    	return this.invoice.getTotal();
    }
    //OPERATION
    public String getCategoryName() {
    	return this.operation.getCategory().getName();
    }
    public String getSubCategoryName() {
    	return this.operation.getSubcategory().getName();
    }
    public double getAmount() {
    	return this.operation.getRealAmount();
    }
    // PAYMENT GETTERS & SETTERS
    public Invoice getInvoice() {
        return invoice;
    }
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    public Operation getOperation() {
        return operation;
    }
    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
