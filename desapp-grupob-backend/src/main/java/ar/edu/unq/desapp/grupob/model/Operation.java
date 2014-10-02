package ar.edu.unq.desapp.grupob.model;

import org.joda.time.DateTime;

public class Operation {
    /*
     * PROPERTIES
     */
    private OperationType type;
    private Shift shift;

    private DateTime date;
    private double amount;
    private String concept;

    private Invoice invoice;

    private Category category;

    private Account account;

    /*
     * CONSTRUCTORS
     */
    public Operation(OperationType type, Shift shift, DateTime date,
            double amount, Invoice invoice, Category category, String concept,
            Account account) {
        this.setType(type);
        this.setShift(shift);
        this.setDate(date);
        this.setAmount(amount);
        this.setInvoice(invoice);
        this.setCategory(category);
        this.setConcept(concept);
        this.setAccount(account);
    }
    /**
     * Depending the OperationType of this (a.k.a Incomming/Outcomming),
     * translates amount value to negative or positive.
     */
    public double getRealAmount() {
        return this.getType().getValue(this.getAmount());
    }
    /*
     * ACCESORS
     */
	public OperationType getType() {
		return type;
	}
	public void setType(OperationType type) {
		this.type = type;
	}
	public Shift getShift() {
		return shift;
	}
	public void setShift(Shift shift) {
		this.shift = shift;
	}
	public DateTime getDate() {
		return date;
	}
	public void setDate(DateTime date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getConcept() {
		return concept;
	}
	public void setConcept(String concept) {
		this.concept = concept;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
    
}
