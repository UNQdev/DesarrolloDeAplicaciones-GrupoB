package ar.edu.unq.desapp.grupob.model.builders;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.model.BankAccount;
import ar.edu.unq.desapp.grupob.model.Devenger;
import ar.edu.unq.desapp.grupob.model.Operation;

public class DevengerBuilder {
	// Access
	public static DevengerBuilder aDevengerBuilder() {
		return new DevengerBuilder();
	}
	// Instances
	private int consolidationPeriod = 15;
	private double unConsolidatedAmount = 0.0;
	private List<Operation> unConsolidatedOperations = new ArrayList<Operation>();

	// Constructor Method
	public Devenger build() {
		Devenger devenger = new Devenger(unConsolidatedAmount, unConsolidatedOperations, consolidationPeriod);
        return devenger;
    }
	// Methods
	public DevengerBuilder withConsolidationPeriod(final int days) {
		this.consolidationPeriod = days;
		return this;
	}
	public DevengerBuilder withUnConsolidatedAmount(final double amount) {
		this.unConsolidatedAmount = amount;
		return this;
	}
	public DevengerBuilder withSpecificOperation(final Operation operation) {
		this.unConsolidatedOperations.add(operation);
		return this;
	}
}