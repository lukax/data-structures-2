package com.edii7;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Walace
 */
public class Veiculo {
    private String placa;
    private String vencimentoSeguro; //*
    private String modelo;
    private int numeroPortas;
    private int id;
    private Boolean arCondicionado;

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the vencimentoSeguro
     */
    public String getVencimentoSeguro() {
        return vencimentoSeguro;
    }

    /**
     * @param vencimentoSeguro the vencimentoSeguro to set
     */
    public void setVencimentoSeguro(String vencimentoSeguro) {
        this.vencimentoSeguro = vencimentoSeguro;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the numeroPortas
     */
    public int getNumeroPortas() {
        return numeroPortas;
    }

    /**
     * @param numeroPortas the numeroPortas to set
     */
    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

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
     * @return the arCondicionado
     */
    public Boolean getArCondicionado() {
        return arCondicionado;
    }

    /**
     * @param arCondicionado the arCondicionado to set
     */
    public void setArCondicionado(Boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }
}
