/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dojoarvoreb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import utils.Arquivos;

/**
 *
 * @author vanessa
 */
public class ArvoreBTest {

    ArvoreB instance = null;

    Metadados tabMetadados = null;
    Metadados tabMetadadosSaida = null;
    
    List<No> tabDados = null;
    List<No> tabDadosSaida = null;
       
    private static final String NOME_ARQUIVO_DADOS = "clientes.dat";
    private static final String NOME_ARQUIVO_METADADOS = "metadados.dat";

    public ArvoreBTest() {
    }
    
    @Before
    public void setUp() {
        instance = new ArvoreB();
        tabMetadados = new Metadados();
        tabMetadadosSaida = new Metadados();        
        tabDados = new ArrayList<No>();
        tabDadosSaida = new ArrayList<No>();
        
        //deleta o arquivo da rodada anterior
        Arquivos.deletaArquivo(NOME_ARQUIVO_DADOS);        
        Arquivos.deletaArquivo(NOME_ARQUIVO_METADADOS);        
    }

    /*
     * Arvore B de altura H = 1, com apenas 1 nó (raiz é uma folha) que tem 2 registros
     * Ver arquivo ArvoreTesteDOJO.pdf para o desenho da árvore
     */
    private void montaArvoreH1() throws FileNotFoundException, IOException {
        tabMetadados = new Metadados(0,1*No.TAMANHO);
        Arquivos.salva(NOME_ARQUIVO_METADADOS, tabMetadados);
        List<Cliente> clientes = new ArrayList<Cliente>();        
        clientes.add(new Cliente(10,"JOAO      "));
        clientes.add(new Cliente(13,"ANA MARIA "));
        List<Integer> p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        tabDados.add(new No(2,-1,p,clientes));
        Arquivos.salva(NOME_ARQUIVO_DADOS, tabDados);        
    }

    /*
     * Arvore B de altura H = 1, com apenas 1 nó (raiz é uma folha) que tem 4 registros
     * Ver arquivo ArvoreTesteDOJO.pdf para o desenho da árvore
     */
    private void montaArvoreH1Cheia() throws FileNotFoundException, IOException {            
        tabMetadados = new Metadados(0,1*No.TAMANHO);
        Arquivos.salva(NOME_ARQUIVO_METADADOS, tabMetadados);

        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(10,"JOAO      "));
        clientes.add(new Cliente(13,"ANA MARIA "));
        clientes.add(new Cliente(15,"BIANCA    "));
        clientes.add(new Cliente(26,"CLARA     "));
        List<Integer> p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        p.add(-1);
        p.add(-1);
        tabDados.add(new No(4,-1,p,clientes));
        Arquivos.salva(NOME_ARQUIVO_DADOS, tabDados);    
        
    }
    
    /*
     * Arvore B com altura H = 2, com raiz e mais 3 folhas     
     * Ver arquivo ArvoreTesteDOJO.pdf para o desenho da árvore
     */
    private void montaArvoreH2() throws FileNotFoundException, IOException {
        tabMetadados = new Metadados(0, 4*No.TAMANHO);
        Arquivos.salva(NOME_ARQUIVO_METADADOS, tabMetadados);
        //Estrutura do No: m, pontPai, Lista de ponteiros, Lista de Clientes
        //ATENCAO: os ponteiros são absolutos       
        
        //Nó Raiz
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(15, "JOSE      "));
        clientes.add(new Cliente(25, "RONALDO   "));        
        List<Integer> p = new ArrayList<Integer>();        
        p.add(1*No.TAMANHO);
        p.add(2*No.TAMANHO);
        p.add(3*No.TAMANHO);
        tabDados.add(new No(2,-1, p, clientes));
        
        //Filho 1
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(10,"JOAO      "));
        clientes.add(new Cliente(13,"ANA MARIA "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(2, 0, p, clientes));

        //Filho 2
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(17,"JOICE     "));
        clientes.add(new Cliente(20,"MARIANA   "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(2, 0, p, clientes));

        //Filho 3
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(30,"BRUNA     "));
        clientes.add(new Cliente(35,"MARCELA   "));
        clientes.add(new Cliente(37,"LEONARDO  "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(3, 0, p, clientes));

        Arquivos.salva(NOME_ARQUIVO_DADOS, tabDados);
    }

    /*
     * Arvore B com altura H = 2, com raiz e mais 3 folhas, 1 delas cheia     
     * Ver arquivo ArvoreTesteDOJO.pdf para o desenho da árvore
     */
    private void montaArvoreH2Cheia() throws FileNotFoundException, IOException {
        tabMetadados = new Metadados(0, 4*No.TAMANHO);
        Arquivos.salva(NOME_ARQUIVO_METADADOS, tabMetadados);
        //Estrutura do No: m, pontPai, Lista de ponteiros, Lista de Clientes
        //ATENCAO: os ponteiros são absolutos       
        
        //Nó Raiz
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(15, "JOSE      "));
        clientes.add(new Cliente(25, "RONALDO   "));        
        List<Integer> p = new ArrayList<Integer>();
        p.add(1*No.TAMANHO);
        p.add(2*No.TAMANHO);
        p.add(3*No.TAMANHO);
        tabDados.add(new No(2,-1, p, clientes));
        
        //Filho 1
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(10,"JOAO      "));
        clientes.add(new Cliente(13,"ANA MARIA "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(2, 0, p, clientes));

        //Filho 2
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(17,"JOICE     "));
        clientes.add(new Cliente(20,"MARIANA   "));
        clientes.add(new Cliente(21,"DEIA      "));
        clientes.add(new Cliente(23,"BRUNO     "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        p.add(-1);        
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(4, 0, p, clientes));

        //Filho 3
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(30,"BRUNA     "));
        clientes.add(new Cliente(35,"MARCELA   "));
        clientes.add(new Cliente(37,"LEONARDO  "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(3, 0, p, clientes));

        Arquivos.salva(NOME_ARQUIVO_DADOS, tabDados);
    }

    /*
     * Arvore B com altura H = 3, com raiz que aponta para 2 nós internos, e mais 6 folhas     
     * Ver arquivo ArvoreTesteDOJO.pdf para o desenho da árvore
     */
    private void montaArvoreH3() throws FileNotFoundException, IOException {
        tabMetadados = new Metadados(0, 9*No.TAMANHO);
        Arquivos.salva(NOME_ARQUIVO_METADADOS, tabMetadados);
        //Estrutura do No Interno: m, apontaFolha, pontPai, Lista de ponteiros, Lista de Chaves
        //ATENCAO: os ponteiros são absolutos                

        //Raiz        
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(37, "RICARDO   "));
        List<Integer> p = new ArrayList<Integer>();
        p.add(1*No.TAMANHO);
        p.add(2*No.TAMANHO);
        tabDados.add(new No(1,-1, p, clientes));
        
        //Filho 1 da Raiz
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(15, "JOSE      "));
        clientes.add(new Cliente(25, "RONALDO   "));        
        p = new ArrayList<Integer>();
        p.add(3*No.TAMANHO);
        p.add(4*No.TAMANHO);
        p.add(5*No.TAMANHO);
        tabDados.add(new No(2, 0, p, clientes));
        
        //Filho 2 da Raiz
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(40, "JOAO      "));
        clientes.add(new Cliente(55, "CATARINA  "));        
        p = new ArrayList<Integer>();
        p.add(6*No.TAMANHO);
        p.add(7*No.TAMANHO);
        p.add(8*No.TAMANHO);
        tabDados.add(new No(2, 0, p, clientes));
        
        //Folha 1
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(10,"JOAO      "));
        clientes.add(new Cliente(13,"ANA MARIA "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        tabDados.add(new No(2, 1*No.TAMANHO, p, clientes));
        
        //Folha 2
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(17,"JOICE     "));
        clientes.add(new Cliente(20,"MARIANA   "));        
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        tabDados.add(new No(2, 1*No.TAMANHO, p, clientes));
        
        //Folha 3
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(30,"BRUNA     "));
        clientes.add(new Cliente(35,"MARCELA   "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        tabDados.add(new No(2, 1*No.TAMANHO, p, clientes));
        
        //Folha 4
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(38,"ADRIANA   "));
        clientes.add(new Cliente(39,"FABIO     "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        tabDados.add(new No(2, 2*No.TAMANHO, p, clientes));
        
        //Folha 5
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(43,"LARISSA   "));
        clientes.add(new Cliente(50,"TATIANA   "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        tabDados.add(new No(2, 2*No.TAMANHO, p, clientes));
        
        //Folha 6
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(57,"ALICE     "));
        clientes.add(new Cliente(60,"JC        "));
        clientes.add(new Cliente(70,"LUCAS     "));
        
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        p.add(-1);
        tabDados.add(new No(3, 2*No.TAMANHO, p, clientes));
        Arquivos.salva(NOME_ARQUIVO_DADOS, tabDados);
    }    

    /**
     * Testa busca 
     */
    @Test
    public void testaBusca1() throws FileNotFoundException, Exception {
        montaArvoreH1();

        //Testa busca -- chave procurada está na raiz. Raiz é um nó folha
        ResultBusca result = instance.busca(13,NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);                
        assertEquals(0, result.pont);
        assertEquals(1, result.pos);
        assertEquals(true, result.encontrou);        
    }

    /**
     * Testa busca
     */
    @Test
    public void testaBusca2() throws FileNotFoundException, Exception {
        montaArvoreH1();
        //Testa busca -- chave procurada não está na árvore. Raiz é um nó folha
        ResultBusca result = instance.busca(6,NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);        
        assertEquals(0, result.pont);
        assertEquals(0, result.pos);//posição onde a chave deveria estar
        assertEquals(false, result.encontrou);
    }
    
    /**
     * Testa busca
     */
    @Test
    public void testaBusca3() throws FileNotFoundException, Exception {        
        montaArvoreH2();
        //Testa busca -- chave está na árvore
        ResultBusca result = instance.busca(20,NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);
        assertEquals(2*No.TAMANHO, result.pont);
        assertEquals(1, result.pos);
        assertEquals(true, result.encontrou);        
    }    

    /**
     * Testa busca
     */
    @Test
    public void testaBusca4() throws FileNotFoundException, Exception {
        montaArvoreH2();

        //Testa busca -- chave não está na árvore
        ResultBusca result = instance.busca(16,NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);
        assertEquals(2*No.TAMANHO, result.pont);
        assertEquals(0, result.pos);
        assertEquals(false, result.encontrou);
    }

    /**
     * Testa busca
     */
    @Test
    public void testaBusca5() throws FileNotFoundException, Exception {
        montaArvoreH3();

        //Testa busca -- chave está na árvore
        ResultBusca result = instance.busca(50,NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);
        assertEquals(7*No.TAMANHO, result.pont);
        assertEquals(1, result.pos);
        assertEquals(true, result.encontrou);
    }

    /**
     * Testa busca
     */
    @Test
    public void testaBusca6() throws FileNotFoundException, Exception {
        montaArvoreH3();

        //Testa busca -- chave não está na árvore
        ResultBusca result = instance.busca(26,NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);
        assertEquals(5*No.TAMANHO, result.pont);
        assertEquals(0, result.pos);
        assertEquals(false, result.encontrou);
    }

    /**
     * Testa inserção em árvore de altura H=1, sem particionamento
     */
    @Test
    public void testaInsere1() throws FileNotFoundException, Exception {
        montaArvoreH1();
        
        int end = instance.insere(11,"VANESSA   ", NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);
        assertEquals(0, end);
        
        tabDados = new ArrayList<No>();
        List<Cliente> clientes = new ArrayList<Cliente>();        
        clientes.add(new Cliente(10,"JOAO      "));
        clientes.add(new Cliente(11,"VANESSA   "));
        clientes.add(new Cliente(13,"ANA MARIA "));
        List<Integer> p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        p.add(-1);
        tabDados.add(new No(3,-1,p,clientes));                                
        tabDadosSaida = Arquivos.leNos(NOME_ARQUIVO_DADOS);        
                
        assertArrayEquals(tabDados.toArray(), tabDadosSaida.toArray());
    }

    /**
     * Testa inserção em árvore de altura H=2, sem particionamento
     */
    @Test
    public void testaInsere2() throws FileNotFoundException, Exception {
        montaArvoreH2();

        int end = instance.insere(11,"VANESSA   ", NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);
        assertEquals(1*No.TAMANHO, end);
        
        tabDados = new ArrayList<No>();
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(15, "JOSE      "));
        clientes.add(new Cliente(25, "RONALDO   "));        
        List<Integer> p = new ArrayList<Integer>();        
        p.add(1*No.TAMANHO);
        p.add(2*No.TAMANHO);
        p.add(3*No.TAMANHO);
        tabDados.add(new No(2,-1, p, clientes));
        
        //Filho 1
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(10,"JOAO      "));
        clientes.add(new Cliente(11,"VANESSA   "));
        clientes.add(new Cliente(13,"ANA MARIA "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        p.add(-1);
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(3, 0, p, clientes));

        //Filho 2
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(17,"JOICE     "));
        clientes.add(new Cliente(20,"MARIANA   "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(2, 0, p, clientes));

        //Filho 3
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(30,"BRUNA     "));
        clientes.add(new Cliente(35,"MARCELA   "));
        clientes.add(new Cliente(37,"LEONARDO  "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(3, 0, p, clientes));

        tabDadosSaida = Arquivos.leNos(NOME_ARQUIVO_DADOS);
        assertArrayEquals(tabDados.toArray(), tabDadosSaida.toArray());        
    }

    /**
     * Testa inserção em árvore de altura H=2
     * Exige particionamento de uma página folha
     */
    @Test
    public void testaInsere3() throws FileNotFoundException, Exception {
        //Árvore tem um dos nós folha cheio, e é neste nó que a inserção ocorrerá
        montaArvoreH2Cheia();

        int end = instance.insere(16,"VANESSA   ", NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);
        assertEquals(2*No.TAMANHO, end);

        tabDados = new ArrayList<No>();
    
        //Nó Raiz
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(15, "JOSE      "));
        clientes.add(new Cliente(20, "MARIANA   "));
        clientes.add(new Cliente(25, "RONALDO   "));        
        List<Integer> p = new ArrayList<Integer>();
        p.add(1*No.TAMANHO);
        p.add(2*No.TAMANHO);
        p.add(4*No.TAMANHO);
        p.add(3*No.TAMANHO);
        tabDados.add(new No(3,-1, p, clientes));
        
        //Filho 1
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(10,"JOAO      "));
        clientes.add(new Cliente(13,"ANA MARIA "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(2, 0, p, clientes));

        //Filho 2
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(16,"VANESSA   "));
        clientes.add(new Cliente(17,"JOICE     "));                
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);                
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(2, 0, p, clientes));

        //Filho 3
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(30,"BRUNA     "));
        clientes.add(new Cliente(35,"MARCELA   "));
        clientes.add(new Cliente(37,"LEONARDO  "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(3, 0, p, clientes));

        //Novo Filho
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(21,"DEIA      "));
        clientes.add(new Cliente(23,"BRUNO     "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(2, 0, p, clientes));
        
        tabDadosSaida = Arquivos.leNos(NOME_ARQUIVO_DADOS);
        assertArrayEquals(tabDados.toArray(), tabDadosSaida.toArray());        
    }

    /**
     * Testa inserção em árvore de altura H=2, chave do registro ja existe -- nao inserir
     */
    @Test
    public void testaInsere4() throws FileNotFoundException, Exception {
        montaArvoreH2();

        int end = instance.insere(13,"MARIANA   ", NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);
        assertEquals(-1, end);

        tabDados = new ArrayList<No>();
        //Nó Raiz
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(15, "JOSE      "));
        clientes.add(new Cliente(25, "RONALDO   "));        
        List<Integer> p = new ArrayList<Integer>();        
        p.add(1*No.TAMANHO);
        p.add(2*No.TAMANHO);
        p.add(3*No.TAMANHO);
        tabDados.add(new No(2,-1, p, clientes));
        
        //Filho 1
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(10,"JOAO      "));
        clientes.add(new Cliente(13,"ANA MARIA "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(2, 0, p, clientes));

        //Filho 2
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(17,"JOICE     "));
        clientes.add(new Cliente(20,"MARIANA   "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(2, 0, p, clientes));

        //Filho 3
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(30,"BRUNA     "));
        clientes.add(new Cliente(35,"MARCELA   "));
        clientes.add(new Cliente(37,"LEONARDO  "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(3, 0, p, clientes));

        tabDadosSaida = Arquivos.leNos(NOME_ARQUIVO_DADOS);
        assertArrayEquals(tabDados.toArray(), tabDadosSaida.toArray());        
    }

    /**
     * Testa inserção em árvore de altura H=1 cheia, que causa aumento na altura da árvore
     */
    @Test
    public void testaInsere5() throws FileNotFoundException, Exception {
        montaArvoreH1Cheia();

        int end = instance.insere(11,"VANESSA   ", NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);
        assertEquals(0, end);
        
        tabDados = new ArrayList<No>();
        //Antigo nó raiz
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(10,"JOAO      "));
        clientes.add(new Cliente(11,"VANESSA   "));
        List<Integer> p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        tabDados.add(new No(2,2*No.TAMANHO,p,clientes));
        
        //Novo nó folha
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(15,"BIANCA    "));
        clientes.add(new Cliente(26,"CLARA     "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        tabDados.add(new No(2,2*No.TAMANHO,p,clientes));
        
        //Novo nó raiz
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(13,"ANA MARIA "));        
        p = new ArrayList<Integer>();
        p.add(0);
        p.add(1*No.TAMANHO);        
        tabDados.add(new No(1,-1,p,clientes));
                
        tabDadosSaida = Arquivos.leNos(NOME_ARQUIVO_DADOS);
        assertArrayEquals(tabDados.toArray(), tabDadosSaida.toArray());        
        
        //Vê se Metadados foram atualizados de acordo
        tabMetadadosSaida = Arquivos.leMetadadosArvoreB(NOME_ARQUIVO_METADADOS);
        assertEquals(tabMetadadosSaida.pontRaiz, 2*No.TAMANHO);
        assertEquals(tabMetadadosSaida.pontProxNoLivre, 3*No.TAMANHO);
    }
    
    /**
     * Testa exclusão em árvore de altura H=2
     * Não é necessário concatenação
     */
    //@Test
    public void testaExclui1() throws FileNotFoundException, Exception {
        //Árvore tem um dos nós folha cheio, e é neste nó que a exclusão ocorrerá
        montaArvoreH2Cheia();

        int end = instance.exclui(20, NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);
        assertEquals(2*No.TAMANHO, end);

        tabDados = new ArrayList<No>();
        //Nó Raiz
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(15, "JOSE      "));
        clientes.add(new Cliente(25, "RONALDO   "));        
        List<Integer> p = new ArrayList<Integer>();
        p.add(1*No.TAMANHO);
        p.add(2*No.TAMANHO);
        p.add(3*No.TAMANHO);
        tabDados.add(new No(2,-1, p, clientes));
        
        //Filho 1
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(10,"JOAO      "));
        clientes.add(new Cliente(13,"ANA MARIA "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(2, 0, p, clientes));

        //Filho 2
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(17,"JOICE     "));        
        clientes.add(new Cliente(21,"DEIA      "));
        clientes.add(new Cliente(23,"BRUNO     "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        p.add(-1);                
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(3, 0, p, clientes));

        //Filho 3
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(30,"BRUNA     "));
        clientes.add(new Cliente(35,"MARCELA   "));
        clientes.add(new Cliente(37,"LEONARDO  "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(3, 0, p, clientes));
        
        tabDadosSaida = Arquivos.leNos(NOME_ARQUIVO_DADOS);
        assertArrayEquals(tabDados.toArray(), tabDadosSaida.toArray());        
    }

    /**
     * Testa exclusão em árvore de altura H=2
     * Não é necessário concatenação
     */
    //@Test
    public void testaExclui2() throws FileNotFoundException, Exception {        
        montaArvoreH2Cheia();

        //Exclusão ocorre em um nó interno
        int end = instance.exclui(15, NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);
        assertEquals(2*No.TAMANHO, end);

        tabDados = new ArrayList<No>();
        //Nó Raiz atualizado
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(17, "JOICE     "));  
        clientes.add(new Cliente(25, "RONALDO   "));        
        List<Integer> p = new ArrayList<Integer>();
        p.add(1*No.TAMANHO);
        p.add(2*No.TAMANHO);
        p.add(3*No.TAMANHO);
        tabDados.add(new No(2,-1, p, clientes));
        
        //Filho 1
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(10,"JOAO      "));
        clientes.add(new Cliente(13,"ANA MARIA "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(2, 0, p, clientes));

        //Filho 2 - atualizado
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(20,"MARIANA   "));      
        clientes.add(new Cliente(21,"DEIA      "));
        clientes.add(new Cliente(23,"BRUNO     "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        p.add(-1);                
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(3, 0, p, clientes));

        //Filho 3
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(30,"BRUNA     "));
        clientes.add(new Cliente(35,"MARCELA   "));
        clientes.add(new Cliente(37,"LEONARDO  "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(3, 0, p, clientes));
        
        tabDadosSaida = Arquivos.leNos(NOME_ARQUIVO_DADOS);
        assertArrayEquals(tabDados.toArray(), tabDadosSaida.toArray());        
    }

    /**
     * Testa exclusão em árvore de altura H=2
     * É necessário concatenação
     */
    //@Test
    public void testaExclui3() throws FileNotFoundException, Exception {        
        montaArvoreH2();

        int end = instance.exclui(17, NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);
        assertEquals(2*No.TAMANHO, end);

        tabDados = new ArrayList<No>();
        //Nó Raiz atualizado
        List<Cliente> clientes = new ArrayList<Cliente>();        
        clientes.add(new Cliente(25, "RONALDO   "));        
        List<Integer> p = new ArrayList<Integer>();        
        p.add(1*No.TAMANHO);        
        p.add(3*No.TAMANHO);
        tabDados.add(new No(1,-1, p, clientes));
        
        //Filho 1 atualizado
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(10,"JOAO      "));
        clientes.add(new Cliente(13,"ANA MARIA "));
        clientes.add(new Cliente(15,"JOSE      "));
        clientes.add(new Cliente(20,"MARIANA   "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        p.add(-1);
        p.add(-1);
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(4, 0, p, clientes));

        //Filho 2 agora é lixo Dependendo da implementação, tem que mudar este código de teste.        
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(17,"JOICE     "));
        clientes.add(new Cliente(20,"MARIANA   "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(2, 0, p, clientes));

        //Filho 3
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(30,"BRUNA     "));
        clientes.add(new Cliente(35,"MARCELA   "));
        clientes.add(new Cliente(37,"LEONARDO  "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(3, 0, p, clientes));
        tabDadosSaida = Arquivos.leNos(NOME_ARQUIVO_DADOS);

        assertArrayEquals(tabDados.toArray(), tabDadosSaida.toArray());        
    }
    
    /**
     * Testa exclusão em árvore de altura H=2
     * É necessário redistribuição
     */
    //@Test
    public void testaExclui4() throws FileNotFoundException, Exception {        
        montaArvoreH2Cheia();

        int end = instance.exclui(13, NOME_ARQUIVO_METADADOS, NOME_ARQUIVO_DADOS);
        assertEquals(1*No.TAMANHO, end);

        tabDados = new ArrayList<No>();
        //Nó Raiz atualizado
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(17,"JOICE     "));        
        clientes.add(new Cliente(25, "RONALDO   "));        
        List<Integer> p = new ArrayList<Integer>();
        p.add(1*No.TAMANHO);
        p.add(2*No.TAMANHO);
        p.add(3*No.TAMANHO);
        tabDados.add(new No(2,-1, p, clientes));
        
        //Filho 1 atualizado
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(10,"JOAO      "));
        clientes.add(new Cliente(15,"JOSE      "));        
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(2, 0, p, clientes));

        //Filho 2 atualizado
        clientes = new ArrayList<Cliente>();        
        clientes.add(new Cliente(20,"MARIANA   "));
        clientes.add(new Cliente(21,"DEIA      "));
        clientes.add(new Cliente(23,"BRUNO     "));
        p = new ArrayList<Integer>();        
        p.add(-1);
        p.add(-1);        
        p.add(-1);        
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(3, 0, p, clientes));

        //Filho 3
        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(30,"BRUNA     "));
        clientes.add(new Cliente(35,"MARCELA   "));
        clientes.add(new Cliente(37,"LEONARDO  "));
        p = new ArrayList<Integer>();
        p.add(-1);
        p.add(-1);
        p.add(-1);        
        p.add(-1);        
        //Estrutura do nó folha: m, pontPai, Lista de ponteiros, Lista de Clientes
        tabDados.add(new No(3, 0, p, clientes));
        tabDadosSaida = Arquivos.leNos(NOME_ARQUIVO_DADOS);

        assertArrayEquals(tabDados.toArray(), tabDadosSaida.toArray());        
    }
}