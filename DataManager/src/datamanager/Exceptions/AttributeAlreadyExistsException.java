package datamanager.Exceptions;

/**
 * This class represents an Exception thrown when an attribute already exists in a table.
 * @author Thiago
 */
public class AttributeAlreadyExistsException extends Exception {
    
    /**
     * The empty constructor of the Exception.
     */
    public AttributeAlreadyExistsException() {
        super();
    }
    
    /**
     * The constructor of the Exception with a message.
     * @param message The error message.
     */
    public AttributeAlreadyExistsException(String message) {
        super(message);
    }
}
