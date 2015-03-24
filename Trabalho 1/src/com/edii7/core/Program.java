/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii7.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Walace
 */
public class Program {
    public static void main(String[] args){
        Scanner leitor = new Scanner(System.in);
        ManipulaFuncionario manipula = new ManipulaFuncionario();
        
        System.out.println("Por favor digite as informações dos funcionários.\n");
        
        boolean continuar = false;
        do{
            System.out.print("Digite o nome: ");
            String nome = leitor.next();
            System.out.print("Digite o cpf: ");
            int cpf = leitor.nextInt();
            System.out.print("Digite a data de nascimento: ");
            String dataNascimento = leitor.next();
            System.out.print("Digite o salário: ");
            float salario = leitor.nextFloat();
            
            manipula.Criar(nome, cpf, dataNascimento, salario);
            
            System.out.println("Funcionário adicionado pressione S para adicionar outro,\nou N para sair.");
            continuar = leitor.next().toLowerCase().equals("s");
            
        }while(continuar);
        
        try{
            manipula.Gravar("Saida.txt");
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado!");
        }catch(IOException e){
            System.out.println("Erro na gravação!");
        }
    }
}
