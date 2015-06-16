package datamanager;

import datamanager.Exceptions.AttributeAlreadyExistsException;
import datamanager.Exceptions.TableAlreadyExistsException;

/**
 * The main class.
 * @author Thiago
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws datamanager.Exceptions.AttributeAlreadyExistsException
     * @throws datamanager.Exceptions.TableAlreadyExistsException
     */
    public static void main(String[] args) throws AttributeAlreadyExistsException, TableAlreadyExistsException {
        Database db = Catalog.getInstance().buildDatabaseFromCatalog();
        
        Table a = db.addTable("PESSOA");
        a.addAttribute("COD", "int");
        a.addAttribute("NOME", "string");
        a.addAttribute("IDADE", "int");
        
        Table b = db.addTable("CARRO");
        b.addAttribute("COD", "int");
        b.addAttribute("MARCA", "string");
        b.addAttribute("ANO", "int");
        
        Table c = db.addTable("POSSUI_CARRO");
        c.addAttribute("COD_PESSOA", "int");
        c.addAttribute("COD_CARRO", "int");
        c.addAttribute("ANO", "int");
    }
}
