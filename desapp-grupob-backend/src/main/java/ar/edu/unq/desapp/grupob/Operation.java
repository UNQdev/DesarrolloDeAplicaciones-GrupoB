package ar.edu.unq.desapp.grupob;

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
        this.type = type;
        this.shift = shift;
        this.date = date;
        this.amount = amount;
        this.invoice = invoice;
        this.category = category;
        this.concept = concept;
        this.account = account;
    }

    /**
     * Depending the OperationType of this (a.k.a Incomming/Outcomming),
     * translate amount value to negative or positive.
     */
    public double getRealAmount() {
        return this.getType().getValue(this.amount);
    }

    /*
     * ACCESORS
     */
    public OperationType getType() {
        return type;
    }
    public Shift getShift() {
        return shift;
    }
    public DateTime getDate() {
        return date;
    }
    public Invoice getInvoice() {
        return invoice;
    }
    public Category getCategory() {
        return category;
    }
    public String getConcept() {
        return concept;
    }
    public Account getAccount() {
        return account;
    }
    public double getAmount() {
        return amount;
    }
}
