package ar.edu.unq.desapp.grupob.builders;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.grupob.Bank;
import ar.edu.unq.desapp.grupob.Operation;

/**
 * 
 * @author bananee
 *
 */
public class BankAccountBuilder {

	/**
	 *
	 * @return
	 */
    public static BankAccountBuilder aBankAccountBuilder() {
        return new BankAccountBuilder();
    }

    // Instances
    /**
     *
     */
    private int consolidationPeriod = 15;
    /**
     *
     */
    private List<Operation> consolidatedOperations = new ArrayList<Operation>();
    /**
     * Constructor method
     * @return
     */
    public final Bank build() {
        Bank bank = new Bank(consolidationPeriod);
        return bank;
    }

    /**
     * Methods
     * @param countdown
     * @return
     */
    public BankAccountBuilder withConsolidationPeriod(final int countdown) {
        this.consolidationPeriod = countdown;
        return this;
    }
    /**
     * 
     * @param operation
     * @return
     */
    public BankAccountBuilder withConsolidatedOperation(final Operation operation) {
    	this.consolidatedOperations.add(operation);
    	return this;
    }
}
