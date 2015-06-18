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
    public static int hashCode = 5;
    public int tamanhoRegistro;
    private final String name;
    private final String hashFile;
    private final String dataFile;
    private final Catalog catalog;
    public final ArrayList<Attribute> attributes;
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
    
    public int insert(Registro novoRegistro) throws FileNotFoundException, IOException{
        for(int i=0; i<novoRegistro.getValues().size(); i++){
            System.out.println("Sistema esperando um tipo: "+ this.attributes.get(i).getType());
            System.out.println("Recebendo um tipo: "+ novoRegistro.getValues().get(i).getClass().getName());//ok está recuperando 
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
        
        int numCompart = novoRegistro.getAtributoPrimario()%Table.hashCode;
        int posArqHash = CompartimentoHash.tamanhoRegistro*numCompart;
        int posArqDados;
        int posRetorno;
        boolean over = false;
        Registro registroLido;
        
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
            novoRegistro.salva(dataFile);
            posRetorno=recordCount;
        }   
         else {
               posArqDados = novoRegistro.getTamanhoRegistro()*compartimento.prox;
              do{
                dataFile.seek(posArqDados);
                registroLido = Registro.le(dataFile, this);
                if (registroLido.getAtributoPrimario()==novoRegistro.getAtributoPrimario()){
                    posRetorno=-1;
                    over=true;
                }
                //até aki está pronto 
                //criar a classe Registro (vai facilitar a vida)
                else{
                    if (registroLido.isLiberado==true){
                        dataFile.seek(posArqDados);
                        novoRegistro.prox=registroLido.prox;
                        novoRegistro.salva(dataFile);
                        posRetorno=posArqDados/this.tamanhoRegistro;
                        over=true;
                        
                        }
                    else{
                       if(registroLido.prox==-1){
                           novoRegistro.salva(dataFile);
                           registroLido.prox=(posArqDados/this.tamanhoRegistro)+1;
                           dataFile.seek(posArqDados);
                           registroLido.salva(dataFile);
                           posArqDados=this.tamanhoRegistro*registroLido.prox;
                           posRetorno=posArqDados/this.tamanhoRegistro;
                           dataFile.seek(posArqDados);
                           registroLido=Registro.le(dataFile, this);
                           over=true;
                       }
                    }
                }
               posArqDados=this.tamanhoRegistro*registroLido.prox;
              }while(registroLido.prox!=-1 && over==false);

            
        }
        
        this.recordCount++;
        hashFile.close();
        dataFile.close();
   
        return posRetorno; 
        
        
        
           }
    

    public Attribute getAttribute(int i) {
        return this.attributes.get(i);
    }


}
