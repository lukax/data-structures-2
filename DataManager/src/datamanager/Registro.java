/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamanager;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Peterson Andrade
 */
public class Registro {

    
   private ArrayList<Object> values = new ArrayList<>();
   public boolean isLiberado;
   private int tamanhoRegistro;
   private Table tabela;
   public int prox;
   
    Registro(Table tabela) {
        this.tabela = tabela;
        this.tamanhoRegistro=tabela.tamanhoRegistro;
    }
   
   public void addValue (Object value){
       this.values.add(value);
   }
   
   public ArrayList<Object> getValues (){
       return this.values;
   }
   
   public void salva(RandomAccessFile dataFile) throws IOException {
       
        for (int i=0; i<this.tabela.attributes.size(); i++){
            String tipo = tabela.attributes.get(i).getType().toUpperCase();
            
             switch (tipo){
                        case ("INT"):
                            dataFile.writeInt((int) values.get(i));
                            break;
                        case ("INTEGER"):
                            dataFile.writeInt((int) values.get(i));
                            break;
                        case ("STRING"):
                            dataFile.writeUTF((String) values.get(i));
                            break;
                        case ("BOOLEAN"):
                            dataFile.writeBoolean((boolean) values.get(i));
                            break;
                        case ("FLOAT"):
                            dataFile.writeFloat((float) values.get(i));
                            break;
                        case ("DOUBLE"):
                            dataFile.writeDouble((double) values.get(i));
                            break;
                    } 
        }
        this.isLiberado=false;
        dataFile.writeBoolean(isLiberado);
    }
   
   public int getAtributoPrimario (){
       return (int)this.values.get(0);
   }
   
   public int getTamanhoRegistro (){
       return this.tamanhoRegistro;
   }
   
    /**
     *
     * @param dataFile arquivo que vai ser lido
     * @param tabela tabela para recuperar a estrutura dos dados
     * @return registro com os dados populados
     */
    public static Registro le(RandomAccessFile dataFile, Table tabela) throws IOException {
         
        Registro novoRegistro = new Registro(tabela);
        for (int i=0; i<tabela.attributes.size(); i++){
         
            String tipo = tabela.attributes.get(i).getType().toUpperCase();
            
             switch (tipo){
                        case ("INT"):
                            novoRegistro.addValue(dataFile.readInt());
                            break;
                        case ("INTEGER"):
                            novoRegistro.addValue(dataFile.readInt());
                            break;
                        case ("STRING"):
                            novoRegistro.addValue(dataFile.readUTF());
                            break;
                        case ("BOOLEAN"):
                            novoRegistro.addValue(dataFile.readBoolean());
                            break;
                        case ("FLOAT"):
                            novoRegistro.addValue(dataFile.readFloat());
                            break;
                        case ("DOUBLE"):
                            novoRegistro.addValue(dataFile.readDouble());
                            break;
                    } 
        }
        novoRegistro.isLiberado = dataFile.readBoolean();
        return novoRegistro;
    }
    

}
