package ar.edu.unq.desapp.grupob;

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
    public void setName(String name) {
        this.name = name;
    }
    public List<SubCategory> getSubcategories() {
        return subcategories;
    }
    public void setSubcategories(List<SubCategory> subcategories) {
        this.subcategories = subcategories;
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
