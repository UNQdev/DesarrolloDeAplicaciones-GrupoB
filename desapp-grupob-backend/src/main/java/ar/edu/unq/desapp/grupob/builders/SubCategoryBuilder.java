package ar.edu.unq.desapp.grupob.builders;

import ar.edu.unq.desapp.grupob.SubCategory;

public class SubCategoryBuilder {
    
    // Access
    public static SubCategoryBuilder aSubCategoryBuilder() {
        return new SubCategoryBuilder();
    }

    // Instances
    private String name = "no name";

    // Constructor method
    public SubCategory build() {
        SubCategory SubCategory = new SubCategory(name);
        return SubCategory;
    }

    // Methods
    public SubCategoryBuilder withName(final String name) {
        this.name = name;
        return this;
    }

}
