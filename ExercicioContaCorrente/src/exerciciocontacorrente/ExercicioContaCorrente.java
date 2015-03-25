/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exerciciocontacorrente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thiago
 */
public class ExercicioContaCorrente {
    
    private static final Scanner keyboard = new Scanner(System.in);
    
    private static final List<Agencia> agenciasCadastradas = new ArrayList<>();
    private static final List<ContaCorrente> contasCadastradas = new ArrayList<>();
    
    private static final String arquivoAgencia = "agencias.dat";
    private static final String arquivoContas = "contas.dat";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            lerDadosAgencia();
        } catch (IOException ex) {
            Logger.getLogger(ExercicioContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            lerDadosConta();
        } catch (IOException ex) {
            Logger.getLogger(ExercicioContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while (true) {
            System.out.println("1. Cadastrar");
            System.out.println("2. Ler");
            System.out.println("3. Sair");
            
            int option = keyboard.nextInt();
            
            switch (option) {
                case 1:
                    System.out.print("Informe a o codigo da agencia: ");
                    int numAgencia = keyboard.nextInt();
                    boolean agenciaExiste = false;
                    
                    for (Agencia agencia : agenciasCadastradas) {
                        if (agencia.getCod() == numAgencia) {
                            agenciaExiste = true;
                            
                            System.out.println("Agencia existente. Criando conta...\n\n");
                            
                            cadastrarConta(numAgencia);
                            
                            break;
                        }
                    }
                    
                    if (!agenciaExiste) {
                        System.out.println("Agencia nao existente. Criando agencia...\n\n");
                    
                        cadastrarAgencia(numAgencia);
                    }
                    
                    continue;
                case 2:
                    for (Agencia agencia : agenciasCadastradas) {
                        System.out.println(agencia.toString());
                        
                        System.out.println("\n");
                        
                        for (ContaCorrente conta : agencia.getContas()) {
                            System.out.println(conta.toString());
                            
                            System.out.println("");
                        }
                    }
                    
                    continue;
                case 3:
                    break;
                default:
                    System.out.println("Opcao invalida");
                    
                    continue;
            }
            
            break;
        }
        
        try {
            salvarDados();
        } catch (IOException ex) {
            Logger.getLogger(ExercicioContaCorrente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void cadastrarConta(int codAgencia) {
        System.out.print("Informe o numero da conta: ");
        int numConta = keyboard.nextInt();
        
        for (ContaCorrente conta : contasCadastradas) {
            if (conta.getCod() == numConta) {
                if (conta.getCodAgencia() == codAgencia) {
                    System.out.println("Essa conta já existe nesse agencia.");
                    
                    return;
                }
            }
        }
        
        System.out.print("Informe o saldo: ");
        double saldo = keyboard.nextDouble();
        
        ContaCorrente conta = new ContaCorrente(numConta, codAgencia, saldo);
        
        for (Agencia agencia : agenciasCadastradas) {
            if (agencia.getCod() == codAgencia) {
                agencia.adicionarConta(conta);
            }
        }
        
        contasCadastradas.add(conta);
    }
    
    public static void cadastrarAgencia(int cod) {
        int numAgencia = cod;
        
        System.out.print("Informe o nome da agencia: ");
        String nomeAgencia = keyboard.next();
        
        System.out.print("Informe o nome do gerente: ");
        String nomeGerente = keyboard.next();
        
        Agencia agencia = new Agencia(numAgencia, nomeAgencia, nomeGerente);
        agenciasCadastradas.add(agencia);
    }
    
    public static void salvarDados() throws IOException {
        try (DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(arquivoAgencia, true)))) {
            for (Agencia agencia : agenciasCadastradas) {
                output.writeInt(agencia.getCod());
                output.writeUTF(agencia.getNome());
                output.writeUTF(agencia.getGerente());
            }
        }
        
        try (DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(arquivoContas, true)))) {
            for (ContaCorrente conta : contasCadastradas) {
                output.writeInt(conta.getCod());
                output.writeInt(conta.getCodAgencia());
                output.writeDouble(conta.getSaldo());
            }
        }
    }
    
    public static void lerDadosAgencia() throws IOException, FileNotFoundException {
        DataInputStream input = null;
        
        try {
            input = new DataInputStream(new BufferedInputStream(new FileInputStream(arquivoAgencia)));
            
            while (true) {
                int cod = input.readInt();
                String nome = input.readUTF();
                String gerente = input.readUTF();

                agenciasCadastradas.add(new Agencia(cod, nome, gerente));
            }
        } catch (FileNotFoundException | EOFException fnf) {
            // do nothing.
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }
    
    public static void lerDadosConta() throws IOException, FileNotFoundException {
        DataInputStream input = null;
        
        try {
            input = new DataInputStream(new BufferedInputStream(new FileInputStream(arquivoContas)));
                
            while (true) {
                int cod = input.readInt();
                int codAg = input.readInt();
                double saldo = input.readDouble();
                
                ContaCorrente conta = new ContaCorrente(cod, codAg, saldo);
                
                for (Agencia agencia : agenciasCadastradas) {
                    if (agencia.getCod() == codAg) {
                        agencia.adicionarConta(conta);
                        
                        break;
                    }
                }
                
                contasCadastradas.add(conta);
            }
        } catch (FileNotFoundException | EOFException fnf) {
            // do nothing.
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }
}
