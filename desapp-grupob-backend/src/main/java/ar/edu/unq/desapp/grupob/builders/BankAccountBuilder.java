package ar.edu.unq.desapp.grupob.builders;

import ar.edu.unq.desapp.grupob.BankAccount;

public class BankAccountBuilder {
    
    // Access
    public static BankAccountBuilder aBankAccountBuilder() {
        return new BankAccountBuilder();
    }

    // Instances
    private int consolidationPeriod = 15;

    // Constructor method
    public BankAccount build() {
        BankAccount bank = new BankAccount(consolidationPeriod);
        return bank;
    }

    // Methods
    public BankAccountBuilder withConsolidationPeriod(final int countdown) {
        this.consolidationPeriod = countdown;
        return this;
    }

}
