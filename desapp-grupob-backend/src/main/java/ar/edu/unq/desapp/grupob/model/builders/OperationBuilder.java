package ar.edu.unq.desapp.grupob.model.builders;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.model.Account;
import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.model.Invoice;
import ar.edu.unq.desapp.grupob.model.Operation;
import ar.edu.unq.desapp.grupob.model.OperationType;
import ar.edu.unq.desapp.grupob.model.Shift;
import ar.edu.unq.desapp.grupob.model.SubCategory;

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
    private Category category;
    private SubCategory subcategory;
    private Account account;

    // Constructor method
    public Operation build() {
        Operation operation = new Operation(
        		type, shift,
        		date, amount,
        		concept, 
                category, subcategory,
                account);
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
    public OperationBuilder withCategory(final Category category) {
        this.category = category;
        return this;
    }
    public OperationBuilder withSubCategory(final SubCategory subcategory) {
        this.subcategory = subcategory;
        return this;
    }
    public OperationBuilder withAccount(final Account account) {
        this.account = account;
        return this;
    }
}
