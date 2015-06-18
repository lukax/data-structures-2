package datamanager;

import datamanager.Exceptions.AttributeAlreadyExistsException;
import datamanager.Exceptions.AttributeNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * This class represents a table.
 * @author Thiago
 */
public class Table {
    
    private int recordCount;// Number of records saved on the table.
    private static int hashCode = 5;
    private int tamanhoRegistro;
    private final String name;
    private final String hashFile;
    private final String dataFile;
    private final Catalog catalog;
    private final ArrayList<Attribute> attributes;
    private boolean liberado;
    /**
     * The table constructor.
     * @param name The name of the table.
     */
    public Table(String name) {
        this.recordCount = 0;
        this.name = name;
        this.hashFile = this.name.toLowerCase() + "hash.dat";
        this.dataFile = this.name.toLowerCase() + "data.dat";
        this.catalog = Catalog.getInstance();
        this.attributes = new ArrayList<>();
        this.tamanhoRegistro=0;
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
        this.tamanhoRegistro +=att.getSize();
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
    
    public void select() {
        
    }
    
    public void insert(ArrayList<Object> atributos) throws FileNotFoundException, IOException{
        for(int i=0; i<atributos.size(); i++){
            System.out.println("Sistema esperando um tipo: "+ this.attributes.get(i).getType());
            System.out.println("Recebendo um tipo: "+ atributos.get(i).getClass().getName());//ok está recuperando 
        }
       String nomeArquivoHash = this.hashFile;
       String nomeArquivoDados = this.dataFile;
        CompartimentoHash compartimento;
        
       
       //TODO 1-abrir o arquivo de hash
        RandomAccessFile hashFile;
        try{
        hashFile = new RandomAccessFile(nomeArquivoHash, "r");
        }catch (FileNotFoundException e){
            System.out.println("Primeira vez adicionando registro nesta tabela. (arquivo nao encontrado)");
            hashFile = new RandomAccessFile(nomeArquivoHash, "rw");
            compartimento = new CompartimentoHash(-1);
            for (int i=0; i<Table.hashCode; i++)
            compartimento.salva(hashFile);
        }
        
        //se não existirem ele cria
        //TODO 2-abrir o arquivo de dados
        RandomAccessFile dataFile;
        dataFile = new RandomAccessFile(nomeArquivoDados, "rw");
        
        System.out.print("Fazendo Escrita Via: " + this.attributes.get(0).getType().toUpperCase()+" ");
        System.out.println(this.attributes.get(0).getName().toLowerCase());
        
        int numCompart = (int)atributos.get(0)%Table.hashCode;
        int posArqHash = CompartimentoHash.tamanhoRegistro*numCompart;
        int posArqDados;
        int posRetorno;
        boolean over = false;
        
        hashFile.seek(posArqHash);
        compartimento = CompartimentoHash.le(hashFile);
        posRetorno = -1;
        System.out.println("Compartimento.prox: " + compartimento.prox);
        
         if (compartimento.prox==-1){
            hashFile.seek(posArqHash);
            compartimento.prox=recordCount;
            compartimento.salva(hashFile);
            posArqDados=this.tamanhoRegistro*this.recordCount;
            dataFile.seek(posArqDados);
            this.salva(dataFile, atributos);
            posRetorno=recordCount;
        }   
         else {
               posArqDados = this.tamanhoRegistro*compartimento.prox;
              do{
                dataFile.seek(posArqDados);
                ArrayList<Attribute> registroLido = this.le(dataFile);
                if (registroLido.get(0)==atributos.get(0)){
                    posRetorno=-1;
                    over=true;
                }
                else{
                    if (this.flag==Cliente.LIBERADO){
                        dataFile.seek(posArqDados);
                        clienteASerInserido.prox=clienteLido.prox;
                        clienteASerInserido.salva(dataFile);
                        posRetorno=posArqDados/Cliente.tamanhoRegistro;
                        over=true;
                        
                        }
                    else{
                       if(clienteLido.prox==-1){
                           clienteASerInserido.salva(dataFile);
                           clienteLido.prox=(posArqDados/Cliente.tamanhoRegistro)+1;
                           dataFile.seek(posArqDados);
                           clienteLido.salva(dataFile);
                           posArqDados=Cliente.tamanhoRegistro*clienteLido.prox;
                           posRetorno=posArqDados/Cliente.tamanhoRegistro;
                           dataFile.seek(posArqDados);
                           clienteLido=Cliente.le(dataFile);
                           over=true;
                       }
                    }
                }
               posArqDados=Cliente.tamanhoRegistro*clienteLido.prox;
              }while(clienteLido.prox!=-1 && over==false);

            
        }
        
        numRegistros++;
        hashFile.close();
        dataFile.close();
   
        return posRetorno; 
        
        
        
    
    }

    public Attribute getAttribute(int i) {
        return this.attributes.get(i);
    }

    private void salva(RandomAccessFile dataFile, ArrayList<Object> atributos) throws IOException {
        for (int i=0; i<this.attributes.size(); i++){
            String tipo = attributes.get(i).getType().toUpperCase();
            
             switch (tipo){
                        case ("INT"):
                            dataFile.writeInt((int) atributos.get(i));
                            break;
                        case ("INTEGER"):
                            dataFile.writeInt((int) atributos.get(i));
                            break;
                        case ("STRING"):
                            dataFile.writeUTF((String) atributos.get(i));
                            break;
                        case ("BOOLEAN"):
                            dataFile.writeBoolean((boolean) atributos.get(i));
                            break;
                        case ("FLOAT"):
                            dataFile.writeFloat((float) atributos.get(i));
                            break;
                        case ("DOUBLE"):
                            dataFile.writeDouble((double) atributos.get(i));
                            break;
                    } 
        }
        this.liberado=false;
        dataFile.writeBoolean(liberado);
    }

    private ArrayList<Attribute> le(RandomAccessFile dataFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
