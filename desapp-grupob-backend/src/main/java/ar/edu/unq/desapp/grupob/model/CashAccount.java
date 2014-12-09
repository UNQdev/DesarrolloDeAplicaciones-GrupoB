package ar.edu.unq.desapp.grupob.model;

public class CashAccount extends Account {

    private static final long serialVersionUID = -470197111939036602L;

    public CashAccount() {
        super();
        this.setAccountName(AccountType.Cash);
    }

    @Override
    public void updateAccountBalance(double amount) throws Exception {
        double updatedBalance = super.getAccountBalance() + amount;
        if (updatedBalance >= 0) {
            super.setAccountBalance(super.getAccountBalance() + amount);
        }
        else {
            throw new Exception ("Monto inválido, la cuenta efectivo no puede quedar en negativo!");
        }
    }
}
