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
        //TODO: Inserir aqui o código do algoritmo        
        return Integer.MAX_VALUE;
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
        Cliente cliente = new Cliente(codCli, nomeCli, -1, Cliente.OCUPADO);
        int numCompart = codCli%tam;
        hashFile.seek(CompartimentoHash.tamanhoRegistro*numCompart);
        compartimento = CompartimentoHash.le(hashFile);
        hashFile.seek(CompartimentoHash.tamanhoRegistro*numCompart);
        
        if (compartimento.prox==-1){
            compartimento.prox=numRegistros;
            compartimento.salva(hashFile);
            //ir ate o cursos da arq de dados e salva o cliente
            dataFile.seek(Cliente.tamanhoRegistro*numRegistros);
            cliente.salva(dataFile);
        }           
        numRegistros++;
        hashFile.close();
        dataFile.close();
        
   
        return (numRegistros-1);
    }

    /**
    * Executa exclusão em Arquivos por Encadeamento Exterior (Hash)
    * @param codCli: chave do cliente a ser excluído
    * @param nomeArquivoHash nome do arquivo que contém a tabela Hash
    * @param nomeArquivoDados nome do arquivo onde os dados estão armazenados
    * @return endereço do cliente que foi excluído, -1 se cliente não existe
    */
    public int exclui(int CodCli, String nomeArquivoHash, String nomeArquivoDados) {
        //TODO: Inserir aqui o código do algoritmo de remoção
        return Integer.MAX_VALUE;
    }

}
