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
    public  int tamanhoRegistro = 0;
    private final String name;
    private final String hashFile;
    private final String dataFile;
    private final String sizeFile;
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
        this.sizeFile = this.name.toLowerCase() + "size.dat";
        this.hashFile = this.name.toLowerCase() + "hash.dat";
        this.dataFile = this.name.toLowerCase() + "data.dat";
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
        this.atualizaTamanhoRegistro();
        System.out.println("Teste: tamanho do reg: "+this.tamanhoRegistro);
        //for(int i=0; i<novoRegistro.getValues().size(); i++){
        //    System.out.println("Sistema esperando um tipo: "+ this.attributes.get(i).getType());
        //    System.out.println("Recebendo um tipo: "+ novoRegistro.getValues().get(i).getClass().getName());//ok está recuperando 
        // }
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
            hashFile.close();
        }
        hashFile = new RandomAccessFile (nomeArquivoHash,"rw");
        
        //se não existirem ele cria
        //TODO 2-abrir o arquivo de dados
        RandomAccessFile dataFile;
        dataFile = new RandomAccessFile(nomeArquivoDados, "rw");
        
        System.out.print("Fazendo Escrita Via: " + this.attributes.get(0).getType().toUpperCase()+" ");
        System.out.println(this.attributes.get(0).getName().toLowerCase());
        
        int numCompart = novoRegistro.getAtributoPrimario()%Table.hashCode; //num do compartimento q vai ficar o registro inserido
        System.out.println("Compartimento que vai ficar o novo registro: "+ numCompart);
        int posArqHash = CompartimentoHash.tamanhoRegistro*numCompart;
        int posArqDados;
        int posRetorno;
        boolean over = false;
        Registro registroLido;
        
        hashFile.seek(posArqHash);
        compartimento = CompartimentoHash.le(hashFile);
        posRetorno = -1;
        System.out.println("Compartimento.prox: " + compartimento.prox);

        novoRegistro.prox=-1;
         if (compartimento.prox==-1){
            System.out.println("O compartimento está vazio, primeira inserção no mesmo");
            hashFile.seek(posArqHash);
            compartimento.prox=recordCount; // recebe Numero de registros em dataFile
            compartimento.salva(hashFile);
            posArqDados=novoRegistro.getTamanhoRegistro()*this.recordCount; // posicao em dataFile
            dataFile.seek(posArqDados); 
            novoRegistro.salva(dataFile); //salve o novo registro na posição
            posRetorno=recordCount; //essa variavel serve para?
        }   
         else {
               posArqDados = novoRegistro.getTamanhoRegistro()*compartimento.prox;
              do{
                dataFile.seek(posArqDados);
                registroLido = Registro.le(dataFile, this);
                //se o registro já existe no arquivo...
                if (registroLido.getAtributoPrimario()==novoRegistro.getAtributoPrimario()){
                    posRetorno=-1;
                    over=true;
                }
                else{
                    if (registroLido.isLiberado==true){ //um registro que foi excluido anteriormente
                        dataFile.seek(posArqDados);
                        novoRegistro.prox=registroLido.prox;
                        novoRegistro.salva(dataFile);
                        posRetorno=posArqDados/this.tamanhoRegistro;
                        over=true;
                        }
                    else{
                        //se estiver ocupado...
                        //verifica o proximo
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
        System.out.println("test 8 checked");
   
        return posRetorno; 
        
        
        
           }
    

    public Attribute getAttribute(int i) {
        return this.attributes.get(i);
    }

    void imprimirEstrutura() throws IOException {
       
        RandomAccessFile hashFile, dataFile;
        String nomeArquivoHash = this.hashFile;
        String nomeArquivoDados = this.dataFile;
        
        hashFile = new RandomAccessFile(nomeArquivoHash, "r");
        CompartimentoHash compartimento;
        dataFile = new RandomAccessFile(nomeArquivoDados, "r");
        Registro registroLido;
        System.out.println("Imprimindo Estrutura do arquivo hash");
        int posArqDados;
        boolean apontaPraNull;
        for (int i=0; i<Table.hashCode; i++){
            System.out.print("["+i+"]->");
            compartimento=CompartimentoHash.le(hashFile);
            posArqDados = compartimento.prox;
            System.out.println(compartimento.prox);
         }
       
        System.out.println("Imprimindo Estrutura do arquivo hash + dados");
        for (int i=0; i<Table.hashCode; i++){
             hashFile.seek(i*CompartimentoHash.tamanhoRegistro);
            System.out.print("["+i+"]->");
            compartimento=CompartimentoHash.le(hashFile);
            System.out.print(compartimento.prox+"->");
            
            if (compartimento.prox!=-1){
                do{
                posArqDados = compartimento.prox*this.tamanhoRegistro;
                dataFile.seek(posArqDados);
                registroLido = Registro.le(dataFile, this);
                System.out.print("|"+registroLido.getAtributoPrimario()+"|->");
                posArqDados=registroLido.prox;
                }while(registroLido.prox!=-1);
            }
            System.out.println("");
         }
        
        hashFile.close();
        dataFile.close();
       
        
    }

    void validaTabela() throws FileNotFoundException, IOException {
        this.tamanhoRegistro+=1; //tamanho de um booelano (isLiberado)
        this.tamanhoRegistro+=4; //tamanho de um int (prox)
        RandomAccessFile sizeFile = new RandomAccessFile(this.sizeFile, "rw");
        sizeFile.writeInt(this.tamanhoRegistro);
        sizeFile.close();
    }
    
    public void atualizaTamanhoRegistro () throws FileNotFoundException, IOException{
        RandomAccessFile sizeFile = new RandomAccessFile (this.sizeFile, "r");
        this.tamanhoRegistro = sizeFile.readInt();
        sizeFile.close();
    }



}
