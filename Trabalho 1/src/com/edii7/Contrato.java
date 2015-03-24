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
enum StatusContrato{
    previsto,
    devolvido
};

public class Contrato {
    private int id;
    private String dataSaida;
    private String dataRetorno;
    private StatusContrato status;
    private Cliente cliente;
    private Veiculo veiculo;

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
     * @return the dataSaida
     */
    public String getDataSaida() {
        return dataSaida;
    }

    /**
     * @param dataSaida the dataSaida to set
     */
    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    /**
     * @return the dataRetorno
     */
    public String getDataRetorno() {
        return dataRetorno;
    }

    /**
     * @param dataRetorno the dataRetorno to set
     */
    public void setDataRetorno(String dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    /**
     * @return the status
     */
    public StatusContrato getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusContrato status) {
        this.status = status;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the veiculo
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * @param veiculo the veiculo to set
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
