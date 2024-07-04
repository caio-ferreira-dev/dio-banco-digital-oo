package com.contas;

import com.Cliente;

public class Conta {
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.saldo = 0;
        this.cliente = cliente;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valorDeposito) {
        this.saldo += valorDeposito;
    }

    public void sacar(double valorSaque) {
        if(valorSaque > saldo) {
            System.out.println("Saldo insuficiente para realizar o saque!");
        } else {
            this.saldo -= valorSaque;
        }
    }

    public void transferir(double valorTransferencia, Conta contaDestino) {
        if(valorTransferencia > saldo) {
            System.out.println("Saldo insuficiente para realizar a transferência!");
        } else {
            this.sacar(valorTransferencia);
            contaDestino.depositar(valorTransferencia);

            System.out.printf("Valor %.2f depositado na conta %d", valorTransferencia, contaDestino.getNumero());
        }
    }

    @Override
    public String toString() {
        return String.format("Conta {%n nome do cliente: '%s',%n agencia: '%d',%n numero da conta: '%d',%n saldo: '%.2f',%n}", cliente.getNome(), agencia, numero, saldo);
    }

}
