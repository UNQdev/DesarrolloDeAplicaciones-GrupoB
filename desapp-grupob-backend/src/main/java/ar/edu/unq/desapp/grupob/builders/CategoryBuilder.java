package ar.edu.unq.desapp.grupob.builders;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.grupob.Category;
import ar.edu.unq.desapp.grupob.SubCategory;

public class CategoryBuilder {

    public static CategoryBuilder aCategoryBuilder (){
        return new CategoryBuilder();
    }

    private String name = "no name";
    private List<SubCategory> subcategories = new ArrayList<SubCategory>();
    
    public Category build (){
        Category category = new Category (name, subcategories);
        return category;
    }
    
    public CategoryBuilder withName (final String name){
        this.name = name;
        return this;
    }
    
    public CategoryBuilder withSubCategoryName (final String name) {
        this.subcategories.add(new SubCategory(name));
        return this;
    }
}
