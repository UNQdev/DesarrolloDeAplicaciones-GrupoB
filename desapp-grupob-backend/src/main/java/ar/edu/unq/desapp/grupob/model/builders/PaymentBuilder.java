package ar.edu.unq.desapp.grupob.model.builders;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.model.Account;
import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.model.Invoice;
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
    private Invoice invoice;
    private Operation operation;


    // Constructor method
    public Payment build() {

    Payment payment = new Payment(inputDate, invoice, operation);
        return payment;
    }

    // Methods
    public PaymentBuilder withInputDate(final DateTime inputDate) {
        this.inputDate = inputDate;
        return this;
    }
    
    public PaymentBuilder withInvoice(final Invoice invoice) {
        this.invoice = invoice;
        return this;
    }
    
    public PaymentBuilder withOperation(final Operation operation) {
        this.operation = operation;
        return this;
    }
    

}
