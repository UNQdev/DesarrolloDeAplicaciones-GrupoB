package ar.edu.unq.desapp.grupob.builders;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.Bank;
import ar.edu.unq.desapp.grupob.Devenger;
import ar.edu.unq.desapp.grupob.Operation;

public class DevengerBuilder {
	// Access
	public static DevengerBuilder aDevengerBuilder() {
		return new DevengerBuilder();
	}
	// Intances
	private Bank account;
	private int consolidationPeriod = 15;
	private List<Operation> unConsolidatedOperations = new ArrayList<Operation>();
	private DateTime systemDate;
	
	// Constructor Method
	public Devenger build() {
		Devenger devenger = new Devenger(account, consolidationPeriod, systemDate);
        return devenger;
    }
	// Methods
	public DevengerBuilder withAccount(final Bank account) {
		this.account = account;
		return this;
	}
	public DevengerBuilder withConsolidationPeriod(final int days) {
		this.consolidationPeriod = days;
		return this;
	}
	public DevengerBuilder withUnConsolidatedOperations(final List<Operation> operations) {
		this.unConsolidatedOperations = operations;
		return this;
	}
	public DevengerBuilder withSpecificOperation(final Operation operation) {
		this.unConsolidatedOperations.add(operation);
		return this;
	}
	public DevengerBuilder withSystemDate(final DateTime date) {
		this.systemDate = date;
		return this;
	}
}

