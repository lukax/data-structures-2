/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dojoarvoreb;
import java.io.RandomAccessFile;
public class ArvoreB {    
    /**
     * Executa busca em Arquivos utilizando Arvore B
     * Assumir que ponteiro para próximo nó é igual a -1 quando não houver próximo nó
     * @param codCli: chave do cliente que está sendo buscado
     * @param nomeArquivoMetadados nome do arquivo que contém os metadadso
     * @param nomeArquivoDados nome do arquivo que contém a árvore B
     * @return uma instancia de ResultBusca, preenchida da seguinte forma:
     * Caso a chave codCli seja encontrada:
    encontrou = true
    pont aponta para a página (nó) que contém a chave
    pos aponta para a posição em que a chave se encontra dentro da página (nó)
    Caso a chave codCli não seja encontrada:
    encontrou = false
    pont aponta para a última página (nó) examinada
    pos informa a posição, nessa página, onde a chave deveria estar inserida
     */
    public ResultBusca busca(int codCli, String nomeArquivoMetadados, String nomeArquivoDados) throws Exception {
        Metadados metadado = Metadados.le(new RandomAccessFile(nomeArquivoMetadados, "r"));
        
        RandomAccessFile arquivoDados = new RandomAccessFile(nomeArquivoDados, "r");
        
        int pos = metadado.pontRaiz;
        
        while(true){
            arquivoDados.seek(pos);
            No no = No.le(arquivoDados);
            for(int i = 0; i < no.m; i++){
                if(no.clientes.get(i).codCliente == codCli){
                    return new ResultBusca(pos, i, true);
                }else if(no.clientes.get(i).codCliente > codCli){
                    if(no.p.get(i) == -1){
                        return new ResultBusca(pos, i, false);
                    }else{
                        pos = no.p.get(i);
                        break;
                    }
                }else if(i == no.m - 1){
                    pos = no.p.get(i + 1);
                    break;
                }
            }
            System.out.println(no);
        }
    }
    /**
     * Executa inserção em Arquivos Indexados por Arvore B
     * @param codCli: código do cliente a ser inserido
     * @param nomeCli: nome do Cliente a ser inserido
     * @param nomeArquivoMetadados nome do arquivo que contém os metadados
     * @param nomeArquivoDados nome do arquivo de dados que contém a arvore B)
     * @return endereço da folha onde o cliente foi inserido, -1 se não conseguiu inserir
     */
    public int insere(int codCli, String nomeCli, String nomeArquivoMetadados, String nomeArquivoDados) throws Exception {
       ResultBusca resultadoDaBusca = busca(codCli, nomeArquivoMetadados, nomeArquivoDados);
      if(resultadoDaBusca.encontrou){
          return -1;
      } 
      else{
          RandomAccessFile arquivoDados = new RandomAccessFile(nomeArquivoDados, "rw");
          arquivoDados.seek(resultadoDaBusca.pos);
          No no = No.le(arquivoDados);
          System.out.println(no);
          Cliente novoCliente = new Cliente(codCli, nomeCli);
          if (no.m<2*No.d){ //pode inserir
            no.m++;
            no.clientes.add(novoCliente);
            return 0;
          }
          return 0;
      }     
    }
    /**
     * Executa exclusão em Arquivos Indexados por Arvores B
     * @param codCli: chave do cliente a ser excluído
     * @param nomeArquivoMetadados nome do arquivo que contém os metadados
     * @param nomeArquivoDados nome do arquivo de dados que contém a arvore B)
     * @return endereço do nó de onde o cliente foi excluído, -1 se cliente não existe
     */
    public int exclui(int CodCli, String nomeArquivoMetadados, String nomeArquivoDados) {
        //TODO: Inserir aqui o código do algoritmo de remoção
        return Integer.MAX_VALUE;
    }
}