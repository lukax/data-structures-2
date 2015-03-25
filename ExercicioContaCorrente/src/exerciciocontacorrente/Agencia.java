/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exerciciocontacorrente;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thiago
 */
public class Agencia {
    
    private int cod;
    private String nome;
    private String gerente;
    private ArrayList<ContaCorrente> contas;
    
    public Agencia(int cod, String nome, String gerente) {
        this.cod = cod;
        this.nome = nome;
        this.gerente = gerente;
        this.contas = new ArrayList<>();
    }
    
    public int getCod() {
        return this.cod;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public String getGerente() {
        return this.gerente;
    }
    
    public ArrayList<ContaCorrente> getContas() {
        return this.contas;
    }
    
    public void setCod(int cod) {
        this.cod = cod;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setGerente(String gerente) {
        this.gerente = gerente;
    }
    
    public void adicionarConta(ContaCorrente conta) {        
        contas.add(conta);
    }
    
    /**
     *
     * @return Dados da agencia
     */
    @Override
    public String toString() {
        String obj;
        
        obj = "Codigo: " + this.cod + "\n";
        obj = obj + "Nome: " + this.nome + "\n";
        obj = obj + "Gerente: " + this.gerente + "\n";
        obj = obj + "Quantidade de contas: " + this.contas.size();
        
        return obj;
    }
}
