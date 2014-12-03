package ar.edu.unq.desapp.grupob.model;

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

	public DateTime getInputDate() {
        return inputDate;
    }

    public void setInputDate(DateTime inputDate) {
        this.inputDate = inputDate;
    }

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
