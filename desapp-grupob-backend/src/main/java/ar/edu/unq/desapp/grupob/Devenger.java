package ar.edu.unq.desapp.grupob;

import java.util.ArrayList;
import java.util.List;
import org.joda.*;

public class Devenger {

    private Account account;
    private int consolidationPeriod;
    private List<Operation> unConsolidatedOperations;
    
    public Devenger(Account account, int days) {
        this.account = account;
        this.consolidationPeriod = days;
        this.unConsolidatedOperations = new ArrayList<Operation>();
    }
    
    public void addOperation(Operation operation) {
        this.unConsolidatedOperations.add(operation);
    }    
    public void removeOperation(Operation operation) {
        this.unConsolidatedOperations.remove(operation);
    }
    // FIXME testear si alcanzo la fecha -plusDays
    public double consolidateOperations() {
        double unConsolidatedTotalAmount = 0;
        for(Operation operation : this.unConsolidatedOperations) {
            if(operation.reachedConsolidationDate(operation.getDate().plusDays(consolidationPeriod))) {
                account.getOperations().add(operation);
                this.removeOperation(operation);
                unConsolidatedTotalAmount += operation.getAmount();
            }
        }
        return unConsolidatedTotalAmount;
    }
}