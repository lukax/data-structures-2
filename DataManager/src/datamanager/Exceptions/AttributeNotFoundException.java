package datamanager.Exceptions;

/**
 * This class represents an Exception thrown when an attribute was not found on a table.
 * @author Thiago
 */
public class AttributeNotFoundException extends Exception {
    
    /**
     * The empty constructor of the Exception.
     */
    public AttributeNotFoundException() {
        super();
    }
    
    /**
     * The constructor of the Exception with a message.
     * @param message The error message.
     */
    public AttributeNotFoundException(String message) {
        super(message);
    }
}
