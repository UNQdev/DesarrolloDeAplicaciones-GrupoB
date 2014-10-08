package ar.edu.unq.desapp.grupob.model.builders;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.grupob.model.BankAccount;
import ar.edu.unq.desapp.grupob.model.Devenger;
import ar.edu.unq.desapp.grupob.model.Operation;

public class BankAccountBuilder {
    
    // Access
    public static BankAccountBuilder aBankAccountBuilder() {
        return new BankAccountBuilder();
    }

    // Instances
    private double available = 0;
    private int consolidationPeriod = 5;
    private Devenger devenger;
    private List<Operation> consolidatedOperations = new ArrayList<Operation>();
    

    // Constructor method
    public BankAccount build() {
    	BankAccount bank = new BankAccount(available, consolidatedOperations, devenger, consolidationPeriod);
        return bank;
    }
    
    // Methods
    public BankAccountBuilder withConsolidationPeriod(final int countdown) {
        this.consolidationPeriod = countdown;
        return this;
    }
    public BankAccountBuilder withAvailableAmount(final double amount) {
        this.available = amount;
        return this;
    }    
    public BankAccountBuilder withDevenger(final Devenger devenger) {
        this.devenger = devenger;
        return this;
    }
    public BankAccountBuilder withOperation(final Operation operation) {
    	this.consolidatedOperations.add(operation);
    	return this;
    }

}
