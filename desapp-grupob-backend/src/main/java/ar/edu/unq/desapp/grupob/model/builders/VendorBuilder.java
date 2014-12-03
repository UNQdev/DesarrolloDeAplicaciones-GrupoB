package ar.edu.unq.desapp.grupob.model.builders;

import ar.edu.unq.desapp.grupob.model.Vendor;

public class VendorBuilder {

    // Access
    public static VendorBuilder aVendorBuilder() {
        return new VendorBuilder();
    }

    // Instances
    private String name = "no name";
    private String taxCode = "";

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
    
    public VendorBuilder withTaxCode(final String taxCode) {
        this.taxCode = taxCode;
        return this;
    }
}
