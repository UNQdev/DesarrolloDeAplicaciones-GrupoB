package ar.edu.unq.desapp.grupob;

public class Cash extends Account {

    public Cash() {
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
