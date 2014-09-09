package ar.edu.unq.desapp.grupob;

import java.util.ArrayList;
import java.util.List;

import org.joda.*;
import org.joda.time.DateTime;

public class Devenger {

    private Account account;
    private int consolidationPeriod;
    private List<Operation> unConsolidatedOperations;
    private DateTime currentDate;

    public Devenger(Account account, int days) {
        this.account = account;
        this.consolidationPeriod = days;
        this.unConsolidatedOperations = new ArrayList<Operation>();
        this.currentDate = DateTime.now();
    }

    public void addOperation(Operation operation) {
        this.unConsolidatedOperations.add(operation);
    }

    public void removeOperation(Operation operation) {
        this.unConsolidatedOperations.remove(operation);
    }

    public DateTime getDeadlineToAccrueFromTheOperation(Operation op) {
        return op.getDate().plusDays(consolidationPeriod);
    }

    // FIXME testear si alcanzo la fecha -plusDays
    public double consolidateOperations() {
        double unConsolidatedTotalAmount = 0;
        for (Operation operation : this.unConsolidatedOperations) {
            if (reachedConsolidationDate(operation)) {
                account.getOperations().add(operation);
                this.removeOperation(operation);
                unConsolidatedTotalAmount += operation.getAmount();
            }
        }
        return unConsolidatedTotalAmount;
    }

    private boolean reachedConsolidationDate(Operation operation) {
        return operation.reachedConsolidationDate(
                this.getDeadlineToAccrueFromTheOperation(operation),
                this.getCurrentDate());
    }

    public int getConsolidationPeriod() {
        return consolidationPeriod;
    }

    public void setConsolidationPeriod(int consolidationPeriod) {
        this.consolidationPeriod = consolidationPeriod;
    }

    public List<Operation> getUnConsolidatedOperations() {
        return unConsolidatedOperations;
    }

    public void setUnConsolidatedOperations(
            List<Operation> unConsolidatedOperations) {
        this.unConsolidatedOperations = unConsolidatedOperations;
    }

    public DateTime getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(DateTime currentDate) {
        this.currentDate = currentDate;
    }

}
