package ar.edu.unq.desapp.grupob;

import java.util.Date;

public class Operation {
    /*
     * PROPERTIES
     */
    private OperationType type;
    private Shift shift;

    private Date date;
    private double amount;

    private Invoice invoice;

    private Category category;
    private SubCategory subcategory;
    private String concept;

    private Account account;
    /*
     * CONSTRUCTORS
     * 
     */
    public Operation(OperationType type, Shift shift, Date date, double amount, 
            Invoice invoice, Category category, SubCategory subcategory, 
            String concept, Account account) {
        this.type = type;
        this.shift = shift;
        this.date = date;
        this.amount = amount;
        this.invoice = invoice;
        this.category = category;
        this.subcategory = subcategory;
        this.concept = concept;
        this.account = account;
    }

    
    public boolean reachedConsolidationDate(int consolidationPeriod) {
        // TODO comparar que fechaActual vs fechaOperacion = consolidationPeriod
        return false;
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
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
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
    public SubCategory getSubcategory() {
        return subcategory;
    }
    public void setSubcategory(SubCategory subcategory) {
        this.subcategory = subcategory;
    }
    public String getConcept() {
        return concept;
    }
    public void setConcept(String concept) {
        this.concept = concept;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
}
