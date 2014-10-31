package ar.edu.unq.desapp.grupob.model;

/**
 * 
 * @author bananee
 *
 */
public class Vendor extends Entity{

	private static final long serialVersionUID = 2975676284158433554L;
    /**
	 *
	 */
	private String name;
	private int taxCode;

	/**
	 *
	 * @param taxCode
	 * @param name
	 */
    public Vendor(int taxCode, String name) {
    	this.setTaxCode(taxCode);
    	this.setName(name);
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(int taxCode) {
		this.taxCode = taxCode;
	}
}
