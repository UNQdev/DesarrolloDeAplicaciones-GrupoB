package ar.edu.unq.desapp.grupob;

public class Bank extends Account {

    private String accountNumber;
	private Devenger devenger;
    private double available;
    private double accrued;

    /**
     * 
     * @param days
     */
    public Bank(int days) {
        super();
        this.devenger = new Devenger(this, days);
        this.setAvailable(super.getAccountBalance());
        this.setAccrued(0);
    }
    @Override
    public void addOperation(Operation operation) {
        this.devenger.addOperation(operation);
        this.setAccrued(operation.getAmount());
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
            this.devenger.removeOperation(operation);
        }
    }
    /**
     * 
     */
    @Override
    public void consolidate(){
        double consolidatedAmount = this.devenger.consolidateOperations();
        this.updateAvailableAndAccrued(consolidatedAmount);
        this.updateAccountBalance(consolidatedAmount);
    }
    /**
     * 
     * @param consolidatedAmount
     */
    private void updateAvailableAndAccrued(double consolidatedAmount) {
        this.available = super.getAccountBalance() - consolidatedAmount;
        this.accrued -= consolidatedAmount;
    }
    /**
     * 
     * @param operation
     * @return
     */
    private boolean operationIsConsolidated(Operation operation) {
        return super.getOperations().contains(operation);
    }    
    /*
     * GETTERS & SETTERS
     */
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
    public double getAvailable() {
        return available;
    }
    public void setAvailable(double available) {
        this.available = available;
    }
    public double getAccrued() {
        return accrued;
    }
    public void setAccrued(double accrued) {
        this.accrued = accrued;
    }    
    public Devenger getDevenger() {
        return devenger;
    }
	public void setDevenger(Devenger devenger) {
		this.devenger = devenger;
	}
}
