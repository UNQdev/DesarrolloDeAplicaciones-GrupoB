package ar.edu.unq.desapp.grupob.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private double accountBalance = 0;
    private List<Operation> operations = new ArrayList<Operation>();
    

    public void addOperation(Operation operation) {
        this.getOperations().add(operation);
        this.updateAccountBalance(operation.getRealAmount());
    }

    public void removeOperation(Operation operation)  {
        this.getOperations().remove(operation);
        this.updateAccountBalance(operation.getRealAmount());
    }

    public void updateAccountBalance(double amount){
        this.setAccountBalance(this.getAccountBalance() + amount);
    }

    public void consolidate() {
        this.updateAccountBalance(0);
    }

    /*
     * GETTERS & SETTERS
     */
    public List<Operation> getOperations() {
        return operations;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
    
    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
