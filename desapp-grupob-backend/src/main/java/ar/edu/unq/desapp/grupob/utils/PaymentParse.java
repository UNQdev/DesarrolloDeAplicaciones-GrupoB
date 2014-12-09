package ar.edu.unq.desapp.grupob.utils;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.model.AccountType;
import ar.edu.unq.desapp.grupob.model.CardType;
import ar.edu.unq.desapp.grupob.model.InvoiceType;
import ar.edu.unq.desapp.grupob.model.OperationType;
import ar.edu.unq.desapp.grupob.model.Shift;

public class PaymentParse {
    
    private int id;
    private OperationType type;
    private Shift shift;
    private DateTime date;
    private double amount;
    private String concept;
    private int category;
    private int subcategory;
    private AccountType accountType; 
    private CardType cardType;
    private InvoiceType invoiceType;
    private int vendorId;
    private String vendorTaxCode;
    private String invoiceNumber;
    private Boolean hasIIBB;
    
    public PaymentParse(){};
    
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorTaxCode() {
        return vendorTaxCode;
    }

    public void setVendorTaxCode(String vendorTaxCode) {
        this.vendorTaxCode = vendorTaxCode;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Boolean getHasIIBB() {
        return hasIIBB;
    }

    public void setHasIIBB(Boolean hasIIBB) {
        this.hasIIBB = hasIIBB;
    }

}
