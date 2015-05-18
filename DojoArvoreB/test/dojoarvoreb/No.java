/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dojoarvoreb;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vanessa
 * Representa um nó de uma árvore B de ordem 2
 * Tamanho em bytes do nó:
 * = 4 + 4 + 5*4 + 4*16 =  92 bytes
 */
public class No implements Entidade {

    public static int TAMANHO = 4 + 4 + 5*4 + 4*Cliente.TAMANHO;
    public static int d = 2;

    public int m; //m é o número total de chaves armazenados no nó    
    public int pontPai; //ponteiro para o nó pai
    //essa lista tera sempre m+1 ponteiros
    public List<Integer> p;
    // essa lista tera sempre m clientes
    public List<Cliente> clientes;

    /*
     * Construtor do Nó 
     */
    public No() {
        this.m = 0;        
        this.pontPai = -1;
        this.p = new ArrayList<Integer>();
        this.clientes = new ArrayList<Cliente>();
    }

    public No(int m, int pontPai, List<Integer> p, List<Cliente> clientes) {
        this.m = m;        
        this.pontPai = pontPai;
        this.p = p;
        this.clientes = clientes;
    }

    /*
     * Grava uma pagina (nó) no arquivo em disco
     */
    @Override
    public void salva(RandomAccessFile out) throws IOException {
        out.writeInt(m);        
        out.writeInt(pontPai);
        //garantidamente, sempre havera pelo menos 1 chave no no'
        //portanto, p0 sempre vai existir
        out.writeInt(p.get(0));
        for (int i = 0; i < m; i++) {
            clientes.get(i).salva(out);
            out.writeInt(p.get(i+1));
        }

        //Termina de gravar dados vazios no restante do espaço do nó
        for (int i=m; i < 2*d; i++) {
            Cliente c = new Cliente(-1, "          ");
            c.salva(out);
            out.writeInt(-1);
        }

    }

    /*
     * Le uma pagina (nó) do disco
     */
    public static No le(RandomAccessFile in) throws IOException {
        No n = new No();
        n.m = in.readInt();        
        n.pontPai = in.readInt();
        n.p.add(in.readInt());

        for (int i = 0; i< n.m; i++) {
            n.clientes.add(Cliente.le(in));
            n.p.add(in.readInt());
        }

        //Termina de ler dados nulos para resolver problema do cursor
        //Dados lidos são descartados
        for (int i=n.m; i < 2*d; i++) {
            Cliente.le(in);
            in.readInt();
        }
        return n;
    }

    /*
     * Compara o Nó atual com outro Nó
     * retorna true se os valores dos atributos de ambos os nós forem iguais,
     * e falso caso contrário
     * @param obj Nó a ser comparado
     * @return True se nós forem iguais, False caso contrário
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final No other = (No) obj;
        if (this.m != other.m || this.pontPai != other.pontPai ) {
            return false;
        }
        if (!this.p.equals(other.p)) {
            return false;
        }
        if (!this.clientes.equals(other.clientes)) {
            return false;
        }
        return true;
    }

     /*
     * Gera o hashCode para uma instância
     * @return hashCode gerado
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.m + this.pontPai;        
        return hash;
    }

    /**
     * Gera uma String com uma representação de um Nó
     */
    @Override
    public String toString() {
        String s;
        s = this.m + ", " + this.pontPai;
        for (int i=0; i< this.m+1; i++)
        {
            s = s + ", " + this.p.get(i).toString();
        }
        for (int i=0; i< this.m; i++)
        {
            s = s + ", " + this.clientes.get(i).toString();
        }
        return s;
    }

}
