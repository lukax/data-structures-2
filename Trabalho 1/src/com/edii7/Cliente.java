/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii7;

/**
 *
 * @author Walace
 */
public class Cliente extends Pessoa{

    private String endereco;
    private int telefone;
    private int numeroCartao;
    private String expiracaoCartao;

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the telefone
     */
    public int getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the numeroCartao
     */
    public int getNumeroCartao() {
        return numeroCartao;
    }

    /**
     * @param numeroCartao the numeroCartao to set
     */
    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    /**
     * @return the expiracaoCartao
     */
    public String getExpiracaoCartao() {
        return expiracaoCartao;
    }

    /**
     * @param expiracaoCartao the expiracaoCartao to set
     */
    public void setExpiracaoCartao(String expiracaoCartao) {
        this.expiracaoCartao = expiracaoCartao;
    }
}
