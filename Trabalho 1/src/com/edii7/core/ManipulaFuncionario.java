/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii7.core;

import com.edii7.Funcionario;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Walace
 */
public class ManipulaFuncionario {
    private final List<Funcionario> funcionarios = new ArrayList<>();
    
    public void Criar(String nome, int cpf, String dataNascimento, float salario){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setDataNascimento(dataNascimento);
        funcionario.setSalario(salario);
        funcionario.setId(funcionarios.size());
        
        funcionarios.add(funcionario);
    }
    
    
    public void Gravar(String local) throws FileNotFoundException, IOException{
        DataOutputStream gravador = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(local)));
        for (Funcionario f : funcionarios){
            gravador.writeUTF(f.getNome());
            gravador.writeInt(f.getCpf());
            gravador.writeInt(f.getId());
            gravador.writeUTF(f.getDataNascimento());
            gravador.writeFloat(f.getSalario());
        }
        
        gravador.close();
    }
}
