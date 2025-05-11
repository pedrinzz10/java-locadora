package com.fiap.locadora.modelo;

import java.time.LocalDate;

public class Locacao {
    private final Cliente cliente;
    private final Veiculo veiculo;
    private final LocalDate dataInicio;
    private LocalDate dataFim;

    public Locacao(Cliente cliente, Veiculo veiculo, LocalDate dataInicio) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataInicio = dataInicio;
        this.veiculo.marcarAlugado();
    }

    public Cliente getCliente() { return cliente; }
    public Veiculo getVeiculo() { return veiculo; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataFim() { return dataFim; }

    public void encerrar(LocalDate dataDevolucao) {
        if (dataFim != null) {
            throw new IllegalStateException("Locação já foi encerrada.");
        }
        this.dataFim = dataDevolucao;
        veiculo.marcarDevolvido();
    }

    public boolean estaAberta() {
        return dataFim == null;
    }
}
