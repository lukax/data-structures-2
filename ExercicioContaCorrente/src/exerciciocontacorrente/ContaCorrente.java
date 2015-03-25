/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exerciciocontacorrente;

/**
 *
 * @author Thiago
 */
public class ContaCorrente {
    
    private int cod;
    private int codAgencia;
    private double saldo;
    
    public ContaCorrente(int cod, int codAgencia, double saldo) {
        this.cod = cod;
        this.codAgencia = codAgencia;
        this.saldo = saldo;
    }
    
    public int getCod() {
        return this.cod;
    }
    
    public int getCodAgencia() {
        return this.codAgencia;
    }
    
    public double getSaldo() {
        return this.saldo;
    }
    
    public void setCod(int cod) {
        this.cod = cod;
    }
    
    public void setCodAgencia(int codAgencia) {
        this.cod = codAgencia;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    /**
     *
     * @return Dados da conta
     */
    @Override
    public String toString() {
        String obj;
        
        obj = "Codigo: " + this.cod + "\n";
        obj = obj + "Agencia: " + this.codAgencia + "\n";
        obj = obj + "Saldo: " + this.saldo;
        
        return obj;
    }
}
