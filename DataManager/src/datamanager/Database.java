/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datamanager;

import datamanager.Exceptions.TableAlreadyExistsException;
import java.util.ArrayList;

/**
 * This class represents a database.
 * @author Thiago
 */
public class Database {
    
    private final Catalog catalog;
    private final ArrayList<Table> tables;
    
    
    /**
     * The Database constructor.
     */
    public Database() {
        this.catalog = Catalog.getInstance();
        this.tables = new ArrayList<>();
    }
    
    /**
     * Adds a table to the Database.
     * @param name The table name.
     * @return The table added.
     * @throws TableAlreadyExistsException If the table already exists on the database.
     */
    public Table addTable(String name) throws TableAlreadyExistsException {
        for (Table t : this.tables) {
            if (name.equalsIgnoreCase(t.getName())) {
                throw new TableAlreadyExistsException();
            }
        }
        
        Table table = new Table(name);
        
        this.tables.add(table);
        this.catalog.addTableToCatalog(name);
        
        return table;
    }
    
    /**
     * Gets the tables from the database.
     * @return The tables from the database.
     */
    public ArrayList getTables() {
        return this.tables;
    }

    Table getTable(String nomeTabela) {
        for (Table t : this.tables){
            if (nomeTabela.equalsIgnoreCase(t.getName())){
                return t;
            }
        }
        return null;
    }
}
