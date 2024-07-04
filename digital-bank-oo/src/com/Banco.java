package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.contas.Conta;
import com.contas.ContaCorrente;
import com.contas.ContaPoupanca;

public class Banco {
    private static String nomeBanco = "Banco DIO";
    private static List<Conta> listaContas = new ArrayList<>() ;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        boolean continuarPrograma = true;

        System.out.printf("Bem vindo ao banco %s", nomeBanco);

        while(continuarPrograma) {
            listarFuncionalidades();
            int funcaoSelecionada = Integer.parseInt(scanner.nextLine());

            switch (funcaoSelecionada) {
                case 1:
                    CriarContaPoupanca();
                    break;
                case 2:
                    CriarContaCorrente();
                    break;
                case 3: 
                    System.out.println(getContas());
                    break;
                case 4:
                    realizarSaque();
                    break;
                case 5:
                    realizarDeposito();
                    break;
                case 6:
                    realizarTransferencia();
                    break;
                case 7:
                    continuarPrograma = false;
                    break;
                default:
                    break;
            }
        }

        scanner.close();
    }

    private static void listarFuncionalidades() {
        System.out.println("--- Lista de funcionalidades ---");
        System.out.println("1 - Criar conta poupanca.");
        System.out.println("2 - Criar conta corrente.");
        System.out.println("3 - Listar contas.");
        System.out.println("4 - Realizar saque.");
        System.out.println("5 - Realizar deposito.");
        System.out.println("6 - Realizar transferencia.");
        System.out.println("7 - Sair do programa.");
        System.out.println("-------------------------------");
        System.out.println("Digite qual ação deseja realizar:");
    } 

    private static void CriarContaCorrente() {
        Cliente novoCliente = cadastrarCliente();
        ContaCorrente novaConta = new ContaCorrente(novoCliente);
        listaContas.add(novaConta);

        System.out.println("Conta corrente criada com sucesso!");
    }

    private static void CriarContaPoupanca() {
        Cliente novoCliente = cadastrarCliente();
        ContaPoupanca novaConta = new ContaPoupanca(novoCliente);
        listaContas.add(novaConta);

        System.out.println("Conta poupanca criada com sucesso!");
    }

    private static Cliente cadastrarCliente() {
        System.out.println("Iniciando cadastro de Cliente...");

        System.out.println("Insira o nome completo:");
        String nomeCliente = scanner.nextLine();
        System.out.println("Insira o telefone(somente numeros):");
        int telefoneCliente = Integer.parseInt(scanner.nextLine());

        Cliente novoCliente = new Cliente(nomeCliente, telefoneCliente);

        return novoCliente;
    }

    private static List<Conta> getContas() {
        return listaContas;
    }

    private static void realizarSaque() {

        System.out.println("Digite o número da conta para saque:");
        int numeroConta = Integer.parseInt(scanner.nextLine());
        Conta contaSelecionada = null;

        for (Conta c : listaContas) {
            if(c.getNumero() == numeroConta) {
                contaSelecionada = c;
            }
        }

        if(contaSelecionada == null ) {
            System.out.println("Conta não encontrada!");
        } else {
            System.out.println("Digite o valor à ser saque:");
            double valorSaque = scanner.nextDouble();

            contaSelecionada.sacar(valorSaque);;
            System.out.printf("Saque de %.2f realizado na conta de numero %d%n", valorSaque, contaSelecionada.getNumero());
            scanner.nextLine();
        } 
    }

    private static void realizarDeposito() {
        System.out.println("Digite o número da conta para deposito:");
        int numeroConta = Integer.parseInt(scanner.nextLine());
        Conta contaSelecionada = null;

        for (Conta c : listaContas) {
            if(c.getNumero() == numeroConta) {
                contaSelecionada = c;
            }
        }

        if(contaSelecionada == null ) {
            System.out.println("Conta não encontrada!");
        } else {
            System.out.println("Digite o valor à ser depositado:");
            double valorDeposito = scanner.nextDouble();

            contaSelecionada.depositar(valorDeposito);
            System.out.printf("Deposito de %.2f realizado na conta de numero %d%n", valorDeposito, contaSelecionada.getNumero());
            scanner.nextLine();
        } 
    }

    private static void realizarTransferencia() {
        System.out.println("Digite o número da conta que deseja sacar:");
        int numeroContaSacar = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite o número da conta que deseja transferir:");
        int numeroContaDepositar = Integer.parseInt(scanner.nextLine());
        Conta contaSacar = null;
        Conta contaDepositar = null;

        for (Conta c : listaContas) {
            if(c.getNumero() == numeroContaSacar) {
                contaSacar = c;
            }

            if(c.getNumero() == numeroContaDepositar) {
                contaDepositar = c;
            }
        }

        if(contaSacar == null ) {
            System.out.println("Conta à sacar não encontrada!");
        } else if (contaDepositar == null) {
            System.out.println("Conta à transferir não encontrada!");
        } else {
            System.out.println("Digite o valor à ser transferido:");
            double valorTransferencia = scanner.nextDouble();

            contaSacar.sacar(valorTransferencia);
            contaDepositar.depositar(valorTransferencia);

            System.out.printf("Transferencia de %.2f realizado da conta %d para a conta %d%n", valorTransferencia, contaSacar.getNumero(), contaDepositar.getNumero());
            scanner.nextLine();
        } 
    }
}
