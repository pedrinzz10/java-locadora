package com.fiap.locadora.servico;

import com.fiap.locadora.modelo.Cliente;
import com.fiap.locadora.modelo.Locacao;
import com.fiap.locadora.modelo.Veiculo;

import java.time.LocalDate;
import java.util.*;

public class ServicoLocacao {
    private final Map<Integer, Veiculo> veiculos = new HashMap<>();
    private final Map<Integer, Cliente> clientes = new HashMap<>();
    private final List<Locacao> locacoes = new ArrayList<>();

    public void adicionarVeiculo(Veiculo v) {
        veiculos.put(v.getId(), v);
    }

    public void adicionarCliente(Cliente c) {
        clientes.put(c.getId(), c);
    }

    public Locacao iniciarLocacao(int clienteId, int veiculoId) {
        Cliente c = Optional.ofNullable(clientes.get(clienteId))
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado."));
        Veiculo v = Optional.ofNullable(veiculos.get(veiculoId))
                .orElseThrow(() -> new NoSuchElementException("Veículo não encontrado."));

        if (!v.isDisponivel()) {
            throw new IllegalStateException("Veículo indisponível.");
        }
        boolean temLocacaoAberta = locacoes.stream()
                .anyMatch(l -> l.getCliente().getId() == clienteId && l.estaAberta());
        if (temLocacaoAberta) {
            throw new IllegalStateException("Cliente já possui locação em aberto.");
        }

        Locacao loc = new Locacao(c, v, LocalDate.now());
        locacoes.add(loc);
        return loc;
    }

    public void encerrarLocacao(Locacao locacao) {
        locacao.encerrar(LocalDate.now());
    }

    public List<Veiculo> listarVeiculosDisponiveis() {
        List<Veiculo> disponiveis = new ArrayList<>();
        for (Veiculo v : veiculos.values()) {
            if (v.isDisponivel()) {
                disponiveis.add(v);
            }
        }
        return disponiveis;
    }
}
