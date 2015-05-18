/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dojoarvoreb;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author vanessa
 * Esta classe representa os metadados da árvore B
 */
public class Metadados {
    public static int TAMANHO = 4 + 4;
    //ponteiro para a raiz da arvore
    public int pontRaiz; 
    //ponteiro para o próximo nó livre no arquivo de dados (ou seja, para o final do último nó)
    public int pontProxNoLivre;

    /*
     * Construtor dos Metadados 
     */
    public Metadados() {
        this.pontRaiz = 0;        
        this.pontProxNoLivre = 1 * No.TAMANHO;
    }

    public Metadados(int pontRaiz, int pontProxNoLivre) {
        this.pontRaiz = pontRaiz;        
        this.pontProxNoLivre = pontProxNoLivre;
    }

    /*
     * Grava os metadados no arquivo em disco 
     */
    public void salva(RandomAccessFile out) throws IOException {
        out.writeInt(pontRaiz);        
        out.writeInt(pontProxNoLivre);        
    }

    /*
     * Le os metadados do disco
     */
    public static Metadados le(RandomAccessFile in) throws IOException {
        Metadados m = new Metadados();
        m.pontRaiz = in.readInt();        
        m.pontProxNoLivre = in.readInt();
        return m;
    }    
}
