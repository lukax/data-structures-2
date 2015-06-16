package datamanager;


/**
 * This class represents an attribute of a table.
 * @author Thiago
 */
public class Attribute {
    
    private final String name;
    private final String type;
    
    /**
     * The Attribute constructor.
     * @param name The name of the attribute.
     * @param type The type of the attribute.
     */
    public Attribute(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    /**
     * Gets the name of the attribute.
     * @return The name of the attribute.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Gets the type of the attribute.
     * @return The type of the attribute.
     */
    public String getType() {
        return this.type;
    }
}
