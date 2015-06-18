package datamanager;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class handles the catalog containing all the information from the database.
 * @author Thiago
 */
public class Catalog {
    
    private Document document;
    private final String catalogFileName;
    private static Catalog instance = null;
    
    
    /**
     * The Catalog constructor.
     */
    protected Catalog() {
        this.catalogFileName = "catalog.xml";
    }
    
    /**
     * The Singleton Pattern to initialize the Catalog class only once.
     * @return A Catalog instance.
     */
    public static Catalog getInstance() {
        if (instance == null) {
            instance = new Catalog();
        }
        
        return instance;
    }
    
    /**
     * Opens the catalog.xml if it exists.
     * If it doesn't then creates one.
     * @return A XML document.
     */
    public Document openCatalog() {
        DocumentBuilderFactory documentBuilderFactory = null;
        DocumentBuilder documentBuilder = null;
        Document doc = null;
        
        try {
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            doc = documentBuilder.parse(this.catalogFileName);
        } catch (IOException ex) {
            buildNewCatalog(documentBuilder.newDocument());
            
            try {
                doc = documentBuilder.parse(this.catalogFileName);
            } catch (SAXException | IOException ex1) {
                Logger.getLogger(Catalog.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (SAXException | ParserConfigurationException ex) {
            Logger.getLogger(Catalog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return doc;
    }
    
    /**
     * Creates a new Catalog with a root element named database.
     * @param doc An empty XML document.
     */
    public void buildNewCatalog(Document doc) {
        doc.appendChild(doc.createElement("database"));
        
        writeCatalog(doc);
    }
    
    /**
     * Builds a Database from the existing information on a catalog.
     * If there's no information then an empty database is created.
     * @return The database built from the catalog.
     */
    public Database buildDatabaseFromCatalog() {
        Document doc = openCatalog();
        doc.getDocumentElement().normalize();
        
        Database db = new Database();
        
        NodeList tables = doc.getElementsByTagName("table");
        
        if (tables.getLength() != 0) {
            for (int i = 0; i < tables.getLength(); i++) {
                Node node = tables.item(i);
                
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList tableChildNodes = node.getChildNodes();

                    Table t  = null;
                    
                    for (int j = 0; j < tableChildNodes.getLength(); j++) {
                        Node childNode = tableChildNodes.item(j);
                        
                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                            if (childNode.getNodeName().equalsIgnoreCase("name")) {
                                t = new Table(childNode.getTextContent());
                            } else if (childNode.getNodeName().equalsIgnoreCase("attribute")) {
                                NodeList attributeChildNodes = childNode.getChildNodes();
                                
                                String attName = "";
                                String attType = "";

                                for (int k = 0; k < attributeChildNodes.getLength(); k++) {
                                    Node attributeChildNode = attributeChildNodes.item(k);
                                    
                                    if (attributeChildNode.getNodeType() == Node.ELEMENT_NODE) {
                                        if (attributeChildNode.getNodeName().equalsIgnoreCase("name")) {
                                            attName = attributeChildNode.getTextContent();
                                        } else if (attributeChildNode.getNodeName().equalsIgnoreCase("type")) {
                                            attType = attributeChildNode.getTextContent();
                                        }
                                    }
                                }
                                
                                Attribute a = new Attribute(attName, attType);
                                t.getAttributes().add(a);
                            }
                        }
                    }
                    
                    db.getTables().add(t);
                }
            }
        }
        
        return db;
    }
    
    /**
     * Writes new information to the catalog.
     * @param doc A XML document containing new information.
     */
    public void writeCatalog(Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(this.catalogFileName));
            
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Catalog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Catalog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Adds a table to the catalog.
     * @param name The table name.
     */
    public void addTableToCatalog(String name) {
        Document doc = openCatalog();
        doc.getDocumentElement().normalize();
        
        Node node = doc.getElementsByTagName("database").item(0);
        
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element db = (Element) node;
            
            Element table = doc.createElement("table");
            db.appendChild(table);
        
            Element tableName = doc.createElement("name");
            tableName.appendChild(doc.createTextNode(name));
            table.appendChild(tableName);
        }
        
        writeCatalog(doc);
    }
    
    /**
     * Adds an attribute to the catalog.
     * @param attribute The attribute object.
     * @param tableName The table name.
     */
    public void addAttributeToTable(Attribute attribute, String tableName) {
        Document doc = openCatalog();
        doc.getDocumentElement().normalize();
        
        NodeList tables = doc.getElementsByTagName("table");
        
        for (int i = 0; i < tables.getLength(); i++) {
            Node node = tables.item(i);
            
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element table = (Element) node;
                
                if (table.getElementsByTagName("name").item(0).getTextContent().equalsIgnoreCase(tableName)) {
                    Element att = doc.createElement("attribute");
                    
                    Element attributeName = doc.createElement("name");
                    attributeName.appendChild(doc.createTextNode(attribute.getName()));
                    att.appendChild(attributeName);
                    
                    Element attributeType = doc.createElement("type");
                    attributeType.appendChild(doc.createTextNode(attribute.getType()));
                    att.appendChild(attributeType);
                    
                    table.appendChild(att);
                }
            }
        }
        
        writeCatalog(doc);
    }
}
