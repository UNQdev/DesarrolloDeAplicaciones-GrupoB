package ar.edu.unq.desapp.grupob.model;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

/**
 * 
 * @author Marcelo Rubini
 *
 */
public class Devenger extends Entity {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8422958801431584987L;
	
	/**
	 * 
	 */
	private int consolidationPeriod;
    private List<Operation> unConsolidatedOperations;
    private DateTime systemDate;
    private double unConsolidatedAmount;

    public Devenger () {};
    /**
     *
     * @param days
     */
    public Devenger(int days) {
        this.setConsolidationPeriod(days);
        this.setUnConsolidatedOperations(new ArrayList<Operation>());
        this.setUnConsolidatedAmount(0);
    }
    /**
     *
     * @param operation
     */
    public void addOperation(Operation operation) {
        this.getUnConsolidatedOperations().add(operation);
        this.updateUnConsolidatedAmount(+operation.getRealAmount());
    }
    /**
     *
     * @param operation
     */
    public void removeOperation(Operation operation) {
    	if (this.getUnConsolidatedOperations().contains(operation)) {
    		this.getUnConsolidatedOperations().remove(operation);
    		this.updateUnConsolidatedAmount(-operation.getRealAmount());
    	}
    }

    /**
     *
     * @param amount
     */
    public void updateUnConsolidatedAmount(double amount) {
    	this.unConsolidatedAmount += amount;
	}
    /**
     *
     * @param operation
     * @return
     */
    public boolean reachedConsolidationDate(Operation operation) {
    	return (this.getAccrualDate(operation).isEqual(this.getSystemDate()) 
    			|| this.getAccrualDate(operation).isBefore(this.getSystemDate()));
    }

    /**
     *
     * @param operation
     * @return
     */
    public DateTime getAccrualDate(Operation operation) {
        return operation.getDate().plusDays(this.getConsolidationPeriod());
    }
    /**
     *
     * @return
     */
    public List<Operation> consolidateOperations() {
    	List<Operation> consolidatedOperations = new ArrayList<Operation>();
        for (Operation operation : this.getUnConsolidatedOperations()) {
            if (this.reachedConsolidationDate(operation)) {
            	consolidatedOperations.add(operation);
            }
        }
        this.removeConsolidatedOperations(consolidatedOperations);
        return consolidatedOperations;
    }
    /**
     *
     * @param consolidatedOperations
     */
    private void removeConsolidatedOperations(
			List<Operation> consolidatedOperations) {
		for(Operation operation : consolidatedOperations) {
			this.removeOperation(operation);
		}
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
            List<Operation> operations) {
        this.unConsolidatedOperations = operations;
    }
    /**
     *
     * @return
     */
    public DateTime getSystemDate() {
		return systemDate;
	}
    /**
     *
     * @param systemDate
     */
	public void setSystemDate(DateTime date) {
		this.systemDate = date;
	}
	/**
	 *
	 * @return
	 */
	public double getUnConsolidatedAmount() {
		return unConsolidatedAmount;
	}
	/**
	 *
	 * @param unConsolidatedAmount
	 */
	public void setUnConsolidatedAmount(double amount) {
		this.unConsolidatedAmount = amount;
	}
	/**
	 * FOR TEST PURPOSE ONLY
	 * @param operations
	 * @param days
	 * @param date
	 */
	public Devenger(double amount, List<Operation> operations,
			int days, DateTime date) {
		this.setConsolidationPeriod(days);
		this.setSystemDate(date);
		this.setUnConsolidatedOperations(operations);
		this.setUnConsolidatedAmount(amount);
	}
}
