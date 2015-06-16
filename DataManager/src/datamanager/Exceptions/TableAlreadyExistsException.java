/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datamanager.Exceptions;

/**
 * This class represents then Exception thrown when a table already exists on the database.
 * @author Thiago
 */
public class TableAlreadyExistsException extends Exception {
    
    /**
     * The empty constructor.
     */
    public TableAlreadyExistsException() {
        super();
    }
    
    /**
     * The constructor with an error message.
     * @param message The error message.
     */
    public TableAlreadyExistsException(String message) {
        super(message);
    }
}
