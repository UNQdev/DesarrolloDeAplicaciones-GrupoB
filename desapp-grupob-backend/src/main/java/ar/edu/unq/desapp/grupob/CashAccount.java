package ar.edu.unq.desapp.grupob;

public class CashAccount extends Account {

    public CashAccount() {
        super();
    }

    @Override
    public void updateAccountBalance(double amount) {
        double updatedBalance = super.getAccountBalance() + amount;
        if (updatedBalance >= 0) {
            super.setAccountBalance(super.getAccountBalance() + amount);
        }
    }
}
