package ar.edu.unq.desapp.grupob.builders;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.Account;
import ar.edu.unq.desapp.grupob.Category;
import ar.edu.unq.desapp.grupob.Invoice;
import ar.edu.unq.desapp.grupob.Operation;
import ar.edu.unq.desapp.grupob.OperationType;
import ar.edu.unq.desapp.grupob.Shift;

public class OperationBuilder {

    // Access
    public static OperationBuilder aOperationBuilder() {
        return new OperationBuilder();
    }

    // Instances
    private OperationType type;
    private Shift shift;
    private DateTime date;
    private double amount;
    private String concept;
    private Invoice invoice;
    private Category category;
    private Account account;

    // Constructor method
    public Operation build() {
        Operation operation = new Operation(type, shift, date, amount, invoice,
                category, concept, account);
        return operation;
    }

    // Methods
    public OperationBuilder withType(final OperationType type) {
        this.type = type;
        return this;
    }

    public OperationBuilder withShift (final Shift shift) {
        this.shift = shift;
        return this;
    }
    public OperationBuilder withDate(final DateTime date) {
        this.date = date;
        return this;
    }
    public OperationBuilder withAmount(final double amount) {
        this.amount = amount;
        return this;
    }
    public OperationBuilder withConcept(final String concept) {
        this.concept = concept;
        return this;
    }
    public OperationBuilder withInvoice(final Invoice invoice) {
        this.invoice = invoice;
        return this;
    }
    public OperationBuilder withCategory(final Category category) {
        this.category = category;
        return this;
    }
    public OperationBuilder withAccount(final Account account) {
        this.account = account;
        return this;
    }
}
