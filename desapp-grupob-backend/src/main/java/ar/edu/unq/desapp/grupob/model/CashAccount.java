package ar.edu.unq.desapp.grupob.model;

public class CashAccount extends Account {

    private static final long serialVersionUID = -470197111939036602L;

    public CashAccount() {
        super();
        this.setAccountName("Cash");
    }

    @Override
    public void updateAccountBalance(double amount) {
        double updatedBalance = super.getAccountBalance() + amount;
        if (updatedBalance >= 0) {
            super.setAccountBalance(super.getAccountBalance() + amount);
        }
    }
}
