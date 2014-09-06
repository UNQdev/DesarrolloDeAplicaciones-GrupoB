package ar.edu.unq.desapp.grupob;

public class Cash extends Account {

    @Override
    public void updateAccountBalance(double amount) {
        super.setAccountBalance(super.getAccountBalance()+amount);
    }

}
