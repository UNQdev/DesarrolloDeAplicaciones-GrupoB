package ar.edu.unq.desapp.grupob.model.builders;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.model.SubCategory;

public class CategoryBuilder {

    // Access
    public static CategoryBuilder aCategoryBuilder() {
        return new CategoryBuilder();
    }

    // Instances
    private String name = "no name";
    private List<SubCategory> subcategories = new ArrayList<SubCategory>();

    // Constructor method
    public Category build() {
        Category category = new Category(name, subcategories);
        return category;
    }

    // Methods
    public CategoryBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder withSubCategoryName(final String name) {
        this.subcategories.add(new SubCategory(name));
        return this;
    }
}
