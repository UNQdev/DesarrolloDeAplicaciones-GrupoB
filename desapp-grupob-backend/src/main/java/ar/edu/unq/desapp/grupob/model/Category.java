package ar.edu.unq.desapp.grupob.model;

import java.util.List;

public class Category {
    
    /**
     * Instances
     */
    private String name;
    private List<SubCategory> subcategories;

    /**
     * Constructor
     */
    public Category (String name, List<SubCategory> subcategories) {
        this.name = name;
        this.subcategories = subcategories;
    }

    /**
     * Gets & Sets
     */
    public String getName() {
        return name;
    }

    public List<SubCategory> getSubcategories() {
        return subcategories;
    }
    
    /**
     * Methods
     */
    public void createSubCategory (SubCategory sub) {
        this.subcategories.add(sub);
    }
    
    public void modifySubcategory (SubCategory subOrigin, SubCategory subMod) {
        this.subcategories.remove(subOrigin);
        this.subcategories.add(subMod);
    }
    
    public void deleteSubcategory (SubCategory sub){
        this.subcategories.remove(sub);
    }
    
    
}
