package com.fiap.locadora.modelo;

public class Cliente {
    private final int id;
    private final String nome;
    private final String cpf;
    private final String cnh;

    public Cliente(int id, String nome, String cpf, String cnh) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getCnh() { return cnh; }
}
