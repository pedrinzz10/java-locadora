package com.fiap.locadora.modelo;

public class Veiculo {
    private final int id;
    private final String placa;
    private final String marca;
    private final String modelo;
    private boolean disponivel;

    public Veiculo(int id, String placa, String marca, String modelo) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.disponivel = true;
    }

    public int getId() { return id; }
    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public boolean isDisponivel() { return disponivel; }

    void marcarAlugado() {
        if (!disponivel) {
            throw new IllegalStateException("Veículo já está alugado.");
        }
        disponivel = false;
    }

    void marcarDevolvido() {
        disponivel = true;
    }
}
