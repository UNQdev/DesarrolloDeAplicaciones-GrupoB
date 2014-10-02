package ar.edu.unq.desapp.grupob.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author damian
 */
public class CategoryManager {

    //Instances

    private List<Category> categories;

    //Constructor.

    public CategoryManager() { 
        this.setCategories(new ArrayList<Category>());
    }


    //Gets & Sets.

    /**
     * @return categories
     */
    public final List<Category> getCategories() {
        return categories;
    }
    
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }


    //Methods.

    /**
     * @param cat
     */
    public final void createCategory(Category cat) {
        this.getCategories().add(cat);
    }

    /**
     * @param catOriginal
     * @param catModified
     */
    public final void modifyCategory (Category catOriginal,Category catModified){
        this.getCategories().remove(catOriginal);
        this.getCategories().add(catModified);
    }

    /**
     * @param cat
     */
    public void deleteCategory(Category cat){
        this.getCategories().remove(cat);
    }
}
