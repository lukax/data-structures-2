/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datamanager;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Thiago
 */
public class Catalog {
    
    private Document document;
    private Transformer transformer;
    private DocumentBuilder documentBuilder;
    private TransformerFactory transformerFacotry;
    private DocumentBuilderFactory documentBuilderFactory;
    private final String catalogFileName;
    private static Catalog instance = null;
    
    
    protected Catalog() {
        this.catalogFileName = "catalog.xml";
        
        try {
            this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
            this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
            this.document = this.documentBuilder.parse(this.catalogFileName);
            this.transformerFacotry = TransformerFactory.newInstance();
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(Catalog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            this.document = this.documentBuilder.newDocument();
        }
    }
    
    public static Catalog getInstance() {
        if (instance == null) {
            instance = new Catalog();
        }
        
        return instance;
    }
    
    public void addTable(String name) {
        Element table = this.document.createElement("table");
        this.document.appendChild(table);
        
        Attr attribute = this.document.createAttribute("id");
        table.setAttribute("id", name);
    }
}
