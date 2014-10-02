package ar.edu.unq.desapp.grupob.model;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

/**
 * 
 * @author Marcelo Rubini
 *
 */
public class Devenger {

    private int consolidationPeriod;
    private List<Operation> unConsolidatedOperations;
    private DateTime systemDate;
    private double unConsolidatedAmount;

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
    	List<Operation> unConsolidatedOperationsCLONE = new ArrayList<Operation>(this.getUnConsolidatedOperations());
        for (Operation operation : unConsolidatedOperationsCLONE) {
            if (this.reachedConsolidationDate(operation)) {
            	consolidatedOperations.add(operation);
            	this.removeOperation(operation);
            }
        }
        this.setUnConsolidatedOperations(unConsolidatedOperationsCLONE);
        return consolidatedOperations;
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
	public Devenger(List<Operation> operations,
			int days, DateTime date) {
		this.setConsolidationPeriod(days);
		this.setSystemDate(date);
		this.setUnConsolidatedOperations(operations);
		this.setUnConsolidatedAmount(0);
	}
}
