package ar.edu.unq.desapp.grupob.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Account extends Entity{

    private static final long serialVersionUID = -6329867468802681632L;
    
    private AccountType accountName;
    private double accountBalance = 0;
    private List<Operation> operations = new ArrayList<Operation>();

    /**
     * 
     * @param operation
     * @throws Exception 
     */
    public void addOperation(Operation operation) throws Exception {
        this.updateAccountBalance(operation.getRealAmount());
        this.getOperations().add(operation);
        operation.setAccountType(getAccountName()); //necesario para que sea consistente y evitar un switch en el rest service
    }
    /**
     * 
     * @param operation
     * @throws Exception 
     */
    public void removeOperation(Operation operation) throws Exception  {
        this.getOperations().remove(operation);
        this.updateAccountBalance(operation.getRealAmount());
    }
    /**
     * 
     * @param amount
     * @throws Exception 
     */
    public void updateAccountBalance(double amount) throws Exception{
        this.setAccountBalance(this.getAccountBalance() + amount);
    }
    /**
     * @throws Exception 
     * 
     */
    public void consolidate() throws Exception {
        this.updateAccountBalance(0);
    }

    /*
     * GETTERS & SETTERS
     */
    /**
     * 
     * @return
     */
    public AccountType getAccountName() {
 		return accountName;
 	}
    /**
     * 
     * @param account_name
     */
 	public void setAccountName(AccountType accountName) {
 		this.accountName = accountName;
 	}
    /**
     * 
     * @return
     */
    public List<Operation> getOperations() {
        return operations;
    }
    /**
     * 
     * @return
     */
    public double getAccountBalance() {
        return accountBalance;
    }
	/**
	 * 
	 * @param accountBalance
	 */
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
    /**
     * 
     * @param operations
     */
    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
