package ar.edu.unq.desapp.grupob.model;

/**
 * @author damian
 */
public class SubCategory {
    
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
