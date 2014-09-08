package ar.edu.unq.desapp.grupob;

import java.util.List;

public abstract class Account {

    private double accountBalance;
    private List<Operation> operations;

    
    public void addOperation(Operation operation) {
        this.operations.add(operation);
        this.updateAccountBalance(operation.getRealAmount());
    }
    
    public void removeOperation(Operation operation){
        this.operations.remove(operation);
        this.updateAccountBalance(operation.getRealAmount());
    }
    
    public void updateAccountBalance(double amount) {
        this.accountBalance += amount;
    }
    
    /**
     * FIXME que hace esto? deberia usar los montos parciales que tengo de 
     * las operaciones y sumarlos al total. 
     */
    public void consolidate() {
        this.updateAccountBalance(0);
    }
    
    
    
    
    
    
    
    
    
    /*
     * GETTERS & SETTERS
     * 
     */
    public List<Operation> getOperations() {
        return operations;
    }
    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
    public double getAccountBalance() {
        return accountBalance;
    }
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
