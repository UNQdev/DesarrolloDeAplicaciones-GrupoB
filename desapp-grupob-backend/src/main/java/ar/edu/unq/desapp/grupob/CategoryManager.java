package ar.edu.unq.desapp.grupob;

import java.util.List;

/**
 * @author damian
 */
public class CategoryManager {

    //Instances

    private List<Category> categories;

    //Constructor.

    public CategoryManager() { }


    //Gets & Sets.

    /**
     * @return categories
     */
    public final List<Category> getCategories() {
        return categories;
    }


    //Methods.

    /**
     * @param cat
     */
    public final void createCategory(Category cat) {
        this.categories.add(cat);
    }

    /**
     * @param catOriginal
     * @param catModified
     */
    public final void modifyCategory (Category catOriginal,Category catModified){
        this.categories.remove(catOriginal);
        this.categories.add(catModified);
    }

    /**
     * @param cat
     */
    public void deleteCategory(Category cat){
        this.categories.remove(cat);
    }
}
