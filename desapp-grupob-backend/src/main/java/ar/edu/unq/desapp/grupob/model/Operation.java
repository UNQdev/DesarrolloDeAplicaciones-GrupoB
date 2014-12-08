package ar.edu.unq.desapp.grupob.model;

import javax.xml.bind.annotation.XmlTransient;

import org.joda.time.DateTime;

public class Operation extends Entity {
    private static final long serialVersionUID = 4150568739587426311L;
    /*
     * PROPERTIES
     */
    private OperationType type;
    private Shift shift;

    private DateTime date;
    private double amount;
    private String concept;

    private Category category;
    private SubCategory subcategory;
    
    private AccountType accountType;
    
    private CardType cardType;

    /*
     * CONSTRUCTORS
     */
    public Operation () {};
    
    public Operation(
    		OperationType type, Shift shift, 
    		DateTime date,
            double amount,
            String concept,
            Category category, SubCategory subcategory,
            AccountType account) {
        this.setType(type);
        this.setShift(shift);
        this.setDate(date);
        this.setAmount(amount);
        this.setCategory(category);
        this.setSubcategory(subcategory);
        this.setConcept(concept);
        this.setAccountType(account);
    }
    /**
     * Depending the OperationType of this (a.k.a Incomming/Outcomming),
     * translates amount value to negative or positive.
     */
    public double getRealAmount() {
        return this.getType().getValue(this.getAmount());
    }
    
    public String getDateToString(){
        return this.getDate().toString("YYYY-MM-dd");
    }
	public boolean isDebit() {
		return this.getCardType().isDebit();
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
	@XmlTransient
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

}
