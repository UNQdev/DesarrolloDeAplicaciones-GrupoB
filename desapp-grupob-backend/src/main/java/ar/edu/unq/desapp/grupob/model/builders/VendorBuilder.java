package ar.edu.unq.desapp.grupob.model.builders;

import ar.edu.unq.desapp.grupob.model.SubCategory;
import ar.edu.unq.desapp.grupob.model.Vendor;

public class VendorBuilder {

    // Access
    public static VendorBuilder aVendorBuilder() {
        return new VendorBuilder();
    }

    // Instances
    private String name = "no name";
    private int taxCode;

    // Constructor method
    public Vendor build() {
        Vendor vendor = new Vendor(taxCode, name);
        return vendor;
    }

    // Methods
    public VendorBuilder withName(final String name) {
        this.name = name;
        return this;
    }
    
    public VendorBuilder withTaxCode(final int taxCode) {
        this.taxCode = taxCode;
        return this;
    }
}
