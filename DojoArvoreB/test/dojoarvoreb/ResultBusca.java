/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dojoarvoreb;

/**
 *
 * @author vanessa
 */
public class ResultBusca {
    public int pont;
    public int pos;
    public boolean encontrou;

        /*
     * Construtor do Cliente
     */
    public ResultBusca(int pont, int pos, boolean encontrou) {
        this.pont = pont;
        this.pos = pos;
        this.encontrou = encontrou;
    }

}
