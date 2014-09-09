package ar.edu.unq.desapp.grupob;

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
        this.account = account;
        this.consolidationPeriod = days;
        this.unConsolidatedOperations = new ArrayList<Operation>();
    }
    /**
     *
     * @param account
     * @param days
     * @param date
     */
    public Devenger(Account account, int days, DateTime date) {
        this.account = account;
        this.consolidationPeriod = days;
        this.systemDate = date;
        this.unConsolidatedOperations = new ArrayList<Operation>();
    }
    /**
     *
     * @param operation
     */
    public void addOperation(Operation operation) {
        this.unConsolidatedOperations.add(operation);
    }
    /**
     *
     * @param operation
     */
    public void removeOperation(Operation operation) {
    	if (this.unConsolidatedOperations.contains(operation)) {
    		this.unConsolidatedOperations.remove(operation);
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
    	this.systemDate = DateTime.now();
        return this.getAccrualDate(operation).getDayOfMonth()
        		<= this.systemDate.getDayOfMonth();
    }
    /**
     *
     * @return
     */
    public double consolidateOperations() {
        double unConsolidatedTotalAmount = 0;
        for (Operation operation : unConsolidatedOperations) {
            if (this.reachedConsolidationDate(operation)) {
                account.getOperations().add(operation);
                this.removeOperation(operation);
                unConsolidatedTotalAmount += operation.getAmount();
            }
        }
        return unConsolidatedTotalAmount;
    }

    /*
     * GETTERS & SETTERS
     *
     *
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
}
