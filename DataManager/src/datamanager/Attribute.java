package datamanager;


/**
 * This class represents an attribute of a table.
 * @author Thiago
 */
public class Attribute {
    
    private final String name;
    private Object type;
    
    /**
     * This Attribute constructor.
     * @param name The name of the attribute.
     * @param type The type of the attribute.
     */
    public Attribute(String name, String type) {
        this.name = name;
        
        if (type.equalsIgnoreCase("int") || type.equalsIgnoreCase("integer")) {
            this.type = Integer.class;
        } else if (type.equalsIgnoreCase("float")) {
            this.type = Float.class;
        } else if (type.equalsIgnoreCase("double")) {
            this.type = Double.class;
        } else if (type.equalsIgnoreCase("boolean")) {
            this.type = Boolean.class;
        } else if (type.equalsIgnoreCase("string")) {
            this.type = String.class;
        }
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
    public Object getType() {
        return this.type;
    }
}
