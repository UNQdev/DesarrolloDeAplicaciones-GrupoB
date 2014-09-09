package ar.edu.unq.desapp.grupob;

import java.util.List;

public abstract class Account {

    private double accountBalance = 0;
    private List<Operation> operations;

    public void addOperation(Operation operation) {
        this.operations.add(operation);
        this.updateAccountBalance(operation.getRealAmount());
    }

    public void removeOperation(Operation operation) {
        this.operations.remove(operation);
        this.updateAccountBalance(operation.getRealAmount());
    }

    public void updateAccountBalance(double amount) {
        this.accountBalance += amount;
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
