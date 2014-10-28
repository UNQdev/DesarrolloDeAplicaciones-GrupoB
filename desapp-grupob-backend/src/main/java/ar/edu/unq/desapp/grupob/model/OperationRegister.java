package ar.edu.unq.desapp.grupob.model;

import java.util.ArrayList;
import java.util.List;

public class OperationRegister {

    private List<Account> accounts;

    public OperationRegister() {
        this.setAccounts(new ArrayList<Account>());
        this.getAccounts().add(new CashAccount());
        this.getAccounts().add(new BankAccount(7));
        this.getAccounts().add(new CurrentAccount());
    }

    
    public void registerOperation(Operation op) {
        op.getAccount().addOperation(op);
    }
    public void deleteOperation(Operation op) {
        op.getAccount().removeOperation(op);
    }
    public void modifyOperation(Operation opOriginal, Operation opModificada) {
        opOriginal.getAccount().removeOperation(opOriginal);
        opModificada.getAccount().addOperation(opModificada);
    }
  
    public void accountConsolidation() {
        for (Account account : accounts) {
            account.consolidate();
        }
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}