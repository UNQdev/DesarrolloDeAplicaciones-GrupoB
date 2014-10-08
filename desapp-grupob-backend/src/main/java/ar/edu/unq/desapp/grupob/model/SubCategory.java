package ar.edu.unq.desapp.grupob.model;

/**
 * @author damian
 */
public class SubCategory extends Entity{
    
    private static final long serialVersionUID = -2075537441356061681L;
    /**
     * Instances
     */
    private String name;
    
    /**
     * Constructor
     */
    public SubCategory (String name) {
        this.setName(name);
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
}
