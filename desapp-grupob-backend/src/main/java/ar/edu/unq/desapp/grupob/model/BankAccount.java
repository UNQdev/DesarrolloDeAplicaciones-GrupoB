package ar.edu.unq.desapp.grupob.model;

import java.util.List;

public class BankAccount extends Account {

    private static final long serialVersionUID = -3529073389645955219L;
    
    private Devenger devenger;
    private double available;

    public BankAccount() {}
    
    public BankAccount(int consolidationPeriod) {
        super();
        this.setAccountName(AccountType.Bank);
        this.setAvailable(0);
        this.setDevenger(new Devenger(consolidationPeriod));
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
			amount += operation.getAmount();
		}
    	return amount;
	}
    /**
     *
     * @param amount
     */
    public void updateAvailableAmount(double amount) {
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
	 */
	public void updateAccountBalance(double amount){
        this.setAccountBalance(this.getAvailable() + amount);
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
    /**
     * FOR TEST PURPOSE ONLY
     * @param amount
     * @param operations
     * @param devenger
     * @param period
     */
    public BankAccount(double amount, List<Operation> operations, Devenger devenger, int period) {
    	super();
    	this.setAvailable(amount);
    	this.setOperations(operations);
        this.setDevenger(devenger);
    }
}