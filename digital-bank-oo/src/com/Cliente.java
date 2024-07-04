package com;

public class Cliente {
    private String nome;
    private int telefone;

    public Cliente(String nome, int telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public int getTelefone() {
        return telefone;
    }
}
