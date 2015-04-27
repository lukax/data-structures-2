/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dojohash;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class EncadeamentoExterior {

    /**
     * Cria uma tabela hash vazia de tamanho tam, e salva no arquivo nomeArquivoHash
     * Compartimento que não tem lista encadeada associada deve ter valor igual a -1
     * @param nomeArquivoHash nome do arquivo hash a ser criado
     * @param tam tamanho da tabela hash a ser criada
     */
    public void criaHash(String nomeArquivoHash, int tam) throws FileNotFoundException, IOException {
        RandomAccessFile output;
        output = new RandomAccessFile(nomeArquivoHash, "rw");
        CompartimentoHash hashTable[];
        hashTable = new CompartimentoHash[tam];
        for (int i=0; i<tam; i++){
            hashTable[i] = new CompartimentoHash(-1);
            hashTable[i].salva(output);
        }
        output.close();

    }
    
    /**
    * Executa busca em Arquivos por Encadeamento Exterior (Hash)
    * Assumir que ponteiro para próximo nó é igual a -1 quando não houver próximo nó
    * @param codCli: chave do cliente que está sendo buscado
    * @param nomeArquivoHash nome do arquivo que contém a tabela Hash
    * @param nomeArquivoDados nome do arquivo onde os dados estão armazenados    
    * @return o endereco onde o cliente foi encontrado, ou -1 se não for encontrado
    */
   public int busca(int codCli, String nomeArquivoHash, String nomeArquivoDados) throws Exception {
       int tam = 7; 
       int hashCode = codCli % tam;
        int posHash = hashCode * CompartimentoHash.tamanhoRegistro;
        
        RandomAccessFile arqHash = new RandomAccessFile(nomeArquivoHash, "r");
        arqHash.seek(posHash);
        
        CompartimentoHash compartimento = CompartimentoHash.le(arqHash);

        arqHash.close();

        if(compartimento.prox == -1){
            return -1;
        }
        
        
        RandomAccessFile arqDados = new RandomAccessFile(nomeArquivoDados, "r");
        int posArq, posTabela, resultadoParcialExclusao;
        //posTabela == no do cliente na tabela
        posArq = compartimento.prox * Cliente.tamanhoRegistro;
        arqDados.seek(posArq);
        Cliente lido = null;    
        do{
            lido = Cliente.le(arqDados);
            if(lido.codCliente == codCli && lido.flag!=Cliente.LIBERADO){
                arqDados.close();
                posTabela=posArq/Cliente.tamanhoRegistro;      
                return posTabela; 
            }
            else{
                posArq = lido.prox * Cliente.tamanhoRegistro;
                if(posArq>=0)
                arqDados.seek(posArq); 
            }
            
        }while(lido.prox != -1);
        
        arqDados.close();
        return -1;
        
        
     }

    /**
    * Executa inserção em Arquivos por Encadeamento Exterior (Hash)
    * @param codCli: código do cliente a ser inserido
    * @param nomeCli: nome do Cliente a ser inserido
    * @param nomeArquivoHash nome do arquivo que contém a tabela Hash
    * @param nomeArquivoDados nome do arquivo onde os dados estão armazenados
     *@param numRegistros numero de registros que já existem gravados no arquivo
    * @return endereço onde o cliente foi inserido, -1 se não conseguiu inserir
    */
    public int insere(int codCli, String nomeCli, String nomeArquivoHash, String nomeArquivoDados, int numRegistros) throws Exception {
        //TODO: Inserir aqui o código do algoritmo de inserção
        int tam = 7;
        CompartimentoHash compartimento;
        //TODO 1-abrir o arquivo de hash
        RandomAccessFile hashFile;
        hashFile = new RandomAccessFile(nomeArquivoHash, "rw");
        
        //TODO 2-abrir o arquivo de dados
        RandomAccessFile dataFile;
        dataFile = new RandomAccessFile(nomeArquivoDados, "rw");
        
        
        //TODO 3-Código do Cliente mod TAM para saber onde colocar na tabela hash
        Cliente clienteASerInserido = new Cliente(codCli, nomeCli, -1, Cliente.OCUPADO);
        int numCompart = codCli%tam;
        int posArqHash = CompartimentoHash.tamanhoRegistro*numCompart;
        int posArqDados;
        int posRetorno;
        boolean over=false;
        Cliente clienteLido;
        
        hashFile.seek(posArqHash);
        compartimento = CompartimentoHash.le(hashFile);
        posRetorno=-1;
        
        
        if (compartimento.prox==-1){
            hashFile.seek(posArqHash);
            compartimento.prox=numRegistros;
            compartimento.salva(hashFile);
            posArqDados=Cliente.tamanhoRegistro*numRegistros;
            dataFile.seek(posArqDados);
            clienteASerInserido.salva(dataFile);
            posRetorno=numRegistros;
        }   
        else {
           
               posArqDados = Cliente.tamanhoRegistro*compartimento.prox;
              do{
                dataFile.seek(posArqDados);
                clienteLido=Cliente.le(dataFile);
                if (clienteLido.codCliente==clienteASerInserido.codCliente){
                    posRetorno=-1;
                    over=true;
                }
                else{
                    if (clienteLido.flag==Cliente.LIBERADO){
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

    /**
    * Executa exclusão em Arquivos por Encadeamento Exterior (Hash)
    * @param codCli: chave do cliente a ser excluído
    * @param nomeArquivoHash nome do arquivo que contém a tabela Hash
    * @param nomeArquivoDados nome do arquivo onde os dados estão armazenados
    * @return endereço do cliente que foi excluído, -1 se cliente não existe
    */
    public int exclui(int CodCli, String nomeArquivoHash, String nomeArquivoDados) throws Exception {
        int posTabela;
        RandomAccessFile dataFile;
        posTabela=busca(CodCli, nomeArquivoHash, nomeArquivoDados);
        if(posTabela==-1){
            return -1;
        }
        else{
            dataFile = new RandomAccessFile(nomeArquivoDados, "rw");
            dataFile.seek(Cliente.tamanhoRegistro*posTabela);
            Cliente lido = Cliente.le(dataFile);
            lido.flag=Cliente.LIBERADO;
            dataFile.seek(Cliente.tamanhoRegistro*posTabela);
            lido.salva(dataFile);
        }
        dataFile.close();
        return posTabela;
    }

}
