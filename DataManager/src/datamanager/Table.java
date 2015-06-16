package datamanager;

import datamanager.Exceptions.AttributeAlreadyExistsException;
import datamanager.Exceptions.AttributeNotFoundException;
import java.util.ArrayList;

/**
 * This class represents a table.
 * @author Thiago
 */
public class Table {
    
    private final String name;
    private final Catalog catalog;
    private final ArrayList<Attribute> attributes;
    
    /**
     * The table constructor.
     * @param name The name of the table.
     */
    public Table(String name) {
        this.name = name;
        this.catalog = Catalog.getInstance();
        this.attributes = new ArrayList<>();
    }
    
    /**
     * Gets the name of the table.
     * @return The name of the table.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Gets all the attributes of the table.
     * @return The attributes of the table.
     */
    public ArrayList getAttributes() {
        return this.attributes;
    }
    
    /**
     * Adds a new attribute to the table.
     * @param name The name of the new attribute.
     * @param type The type of the new attribute.
     * @throws AttributeAlreadyExistsException If the attribute already exists.
     */
    public void addAttribute(String name, String type) throws AttributeAlreadyExistsException {
        for (Attribute att : this.attributes) {
            if (att.getName().equalsIgnoreCase(name)) {
                throw new AttributeAlreadyExistsException();
            }
        }
        
        Attribute att = new Attribute(name, type);
        
        this.attributes.add(att);
        
        this.catalog.addAttributeToTable(att, this.name);
    }
    
    /**
     * Deletes an attribute from the table.
     * @param name The name of the attribute.
     * @throws AttributeNotFoundException If the attribute doesn't exist.
     */
    public void deleteAttribute(String name) throws AttributeNotFoundException {
        boolean removed = false;
        
        for (Attribute att : this.attributes) {
            if (att.getName().equalsIgnoreCase(name)) {
                removed = this.attributes.remove(att);
            }
        }
        
        if (removed == false) {
            throw new AttributeNotFoundException();
        }
    }
}
