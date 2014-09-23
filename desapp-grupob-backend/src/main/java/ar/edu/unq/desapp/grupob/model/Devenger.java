package ar.edu.unq.desapp.grupob.model;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class Devenger {

    private Account account;
    private int consolidationPeriod;
    private List<Operation> unConsolidatedOperations;
    private DateTime systemDate;

    /**
     *
     * @param account
     * @param days
     */
    public Devenger(Account account, int days) {
        this.setAccount(account);
        this.setConsolidationPeriod(days);
        this.setUnConsolidatedOperations(new ArrayList<Operation>());
    }

    /**
     *
     * @param account
     * @param days
     * @param date
     */
    public Devenger(Account account, int days, DateTime date) {
        this.setAccount(account);
        this.setConsolidationPeriod(days);
        this.setSystemDate(date);
        this.setUnConsolidatedOperations(new ArrayList<Operation>());
    }

    /**
     *
     * @param operation
     */
    public void addOperation(Operation operation) {
        this.getUnConsolidatedOperations().add(operation);
    }

    /**
     *
     * @param operation
     */
    public void removeOperation(Operation operation) {
        if (this.getUnConsolidatedOperations().contains(operation)) {
            this.getUnConsolidatedOperations().remove(operation);
        }
    }

    /**
     * 
     * @param operation
     * @return
     */
    public DateTime getAccrualDate(Operation operation) {
        return operation.getDate().plusDays(consolidationPeriod);
    }

    /**
     *
     * @param operation
     * @return
     */
    public boolean reachedConsolidationDate(Operation operation) {
        return (this.getAccrualDate(operation).isEqual(this.systemDate)
                || this.getAccrualDate(operation).isBefore(this.systemDate));
    }

    /**
     *
     * @return
     */
    public double consolidateOperations() {
        double unConsolidatedTotalAmount = 0;
        for (Operation operation : unConsolidatedOperations) {
            if (this.reachedConsolidationDate(operation)) {
                this.consolidateOperation(operation);
                unConsolidatedTotalAmount += operation.getAmount();
            }
        }
        return unConsolidatedTotalAmount;
    }

    private void consolidateOperation(Operation operation) {
        this.getAccount().getOperations().add(operation);
        this.removeOperation(operation);
    }

    /*
     * GETTERS & SETTERS
     */
    /**
     *
     * @return
     */
    public int getConsolidationPeriod() {
        return consolidationPeriod;
    }

    /**
     *
     * @param period
     */
    public void setConsolidationPeriod(int period) {
        this.consolidationPeriod = period;
    }

    /**
     *
     * @return
     */
    public List<Operation> getUnConsolidatedOperations() {
        return unConsolidatedOperations;
    }

    /**
     *
     * @param unConsolidatedOperations
     */
    public void setUnConsolidatedOperations(
            List<Operation> unConsolidatedOperations) {
        this.unConsolidatedOperations = unConsolidatedOperations;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public DateTime getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(DateTime systemDate) {
        this.systemDate = systemDate;
    }
}
