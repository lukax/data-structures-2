/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii7;

import java.util.List;

/**
 *
 * @author Walace
 */
public class Locadora {
    private int id;
    private int telefone;
    private String endereco;
    private List<Veiculo> veiculos; 
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the veiculos
     */
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    /**
     * @param veiculos the veiculos to set
     */
    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
}
