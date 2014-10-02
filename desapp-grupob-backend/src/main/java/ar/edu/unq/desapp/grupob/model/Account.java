package ar.edu.unq.desapp.grupob.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private double accountBalance = 0;
    private List<Operation> operations = new ArrayList<Operation>();

    /**
     * 
     * @param operation
     */
    public void addOperation(Operation operation) {
        this.getOperations().add(operation);
        this.updateAccountBalance(operation.getRealAmount());
    }
    /**
     * 
     * @param operation
     */
    public void removeOperation(Operation operation)  {
        this.getOperations().remove(operation);
        this.updateAccountBalance(operation.getRealAmount());
    }
    /**
     * 
     * @param amount
     */
    public void updateAccountBalance(double amount){
        this.setAccountBalance(this.getAccountBalance() + amount);
    }
    /**
     * 
     */
    public void consolidate() {
        this.updateAccountBalance(0);
    }
    /*
     * GETTERS & SETTERS
     */
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
