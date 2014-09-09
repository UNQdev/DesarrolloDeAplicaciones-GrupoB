package ar.edu.unq.desapp.grupob.builders;

import ar.edu.unq.desapp.grupob.Bank;

public class BankAccountBuilder {
    
    // Access
    public static BankAccountBuilder aBankAccountBuilder () {
        return new BankAccountBuilder();
    }

    // Instances
    private int consolidationPeriod = 15;

    // Constructor method
    public Bank build() {
        Bank bank = new Bank(consolidationPeriod);
        return bank;
    }

    // Methods
    public BankAccountBuilder withConsolidationPeriod(final int countdown) {
        this.consolidationPeriod = countdown;
        return this;
    }

}
