package datamanager;


/**
 * This class represents an attribute of a table.
 * @author Thiago
 */
public class Attribute {
    
    private String name;
    private String type;
    private int attributeSize;
    
    /**
     * The Attribute constructor.
     * @param name The name of the attribute.
     * @param type The type of the attribute.
     */
    public Attribute(String name, String type) {
        this.name = name;
        this.type = type;
        
        if (this.type.equalsIgnoreCase("int") || this.type.equalsIgnoreCase("Integer") || this.type.equalsIgnoreCase("float")) {
            this.attributeSize = 4;
        } else if (this.type.equalsIgnoreCase("double")) {
            this.attributeSize = 8;
        } else if (this.type.equalsIgnoreCase("boolean")) {
            this.attributeSize = 1;
        } else {
            this.attributeSize = 12;
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
    public String getType() {
        return this.type;
    }
    
    public int getSize() {
        return this.attributeSize;
    }
}
