package ar.edu.unq.desapp.grupob.model;

import java.util.List;

public class BankAccount extends Account {

    private Devenger devenger;
    private double available;

    public BankAccount(int consolidationPeriod) {
        super();
        this.setDevenger(new Devenger(consolidationPeriod));
        this.setAvailable(super.getAccountBalance());
    }
    /**
     *
     */
	@Override
    public void addOperation(Operation operation) {
        this.getDevenger().addOperation(operation);
    }
	/**
	 *
	 */
    @Override
    public void removeOperation(Operation operation) {
        if (this.operationIsConsolidated(operation)) {
            super.getOperations().remove(operation);
            this.updateAccountBalance(operation.getRealAmount());
        } else {
            this.getDevenger().removeOperation(operation);
        }
    }
    /**
     *
     */
    @Override
    public void consolidate() {
        List<Operation> consolidatedOperations = this.getDevenger().consolidateOperations();
        this.getOperations().addAll(consolidatedOperations);
        double consolidatedAmount = this.getConsolidationAmount(consolidatedOperations);
        this.updateAvailableAmount(consolidatedAmount);
    }
	/**
     *
     * @param consolidatedOperations
     * @return
     */
    private double getConsolidationAmount(List<Operation> operations) {
    	double amount = 0.0;
    	for(Operation operation : operations) {
			amount += operation.getRealAmount();
		}
    	return amount;
	}
    /**
     *
     * @param amount
     */
    private void updateAvailableAmount(double amount) {
    	this.available += amount;
    }
    /**
     *
     * @param operation
     * @return
     */
	private boolean operationIsConsolidated(Operation operation) {
        return super.getOperations().contains(operation);
    }
	/**
	 *
	 * @return
	 */
    public double getUnConsolidatedAmount() {
    	return this.getDevenger().getUnConsolidatedAmount();
    }
    /**
     *
     */
    @Override
    public double getAccountBalance() {
    	return (this.getAvailable() + this.getUnConsolidatedAmount()); 
    }
    /*
     * GETTERS & SETTERS
     */
    /**
     *
     * @return
     */
    public double getAvailable() {
        return available;
    }
    /**
     *
     * @param available
     */
    public void setAvailable(double available) {
        this.available = available;
    }
    /**
     *
     * @return
     */
    public Devenger getDevenger() {
        return devenger;
    }
    /**
     *
     * @param devenger
     */
    private void setDevenger(Devenger devenger) {
    	this.devenger = devenger;
	}
    /**
     * FOR TEST PURPOSE ONLY
     * @param devenger
     */
    public BankAccount(Devenger devenger) {
    	super();
        this.setDevenger(devenger);
        this.setAvailable(super.getAccountBalance());
    }
}