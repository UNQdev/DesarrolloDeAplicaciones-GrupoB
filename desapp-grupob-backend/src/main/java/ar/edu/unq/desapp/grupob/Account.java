package ar.edu.unq.desapp.grupob;

import java.util.List;

public abstract class Account {

    private double accountBalance;
    private List<Operation> operations;

    
    public void addOperation(Operation operation) {
        this.operations.add(operation);
        this.updateAccountBalance(operation.getAmount());
    }
    public void removeOperation(Operation operation){
      this.operations.remove(operation);
      this.updateAccountBalance(-operation.getAmount());
    }    
    public abstract void updateAccountBalance(double amount);

    
    
    
    
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
