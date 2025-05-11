package com.fiap.locadora.aplicacao;

import com.fiap.locadora.modelo.Cliente;
import com.fiap.locadora.modelo.Veiculo;
import com.fiap.locadora.modelo.Locacao;
import com.fiap.locadora.servico.ServicoLocacao;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) {
        ServicoLocacao servico = new ServicoLocacao();
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("=== Cadastro de veículo ===");
        System.out.print("ID: "); int vid = sc.nextInt(); sc.nextLine();
        System.out.print("Placa: "); String placa = sc.nextLine();
        System.out.print("Marca: "); String marca = sc.nextLine();
        System.out.print("Modelo: "); String modelo = sc.nextLine();
        servico.adicionarVeiculo(new Veiculo(vid, placa, marca, modelo));

        System.out.println("=== Cadastro de cliente ===");
        System.out.print("ID: "); int cid = sc.nextInt(); sc.nextLine();
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("CPF: "); String cpf = sc.nextLine();
        System.out.print("CNH: "); String cnh = sc.nextLine();
        servico.adicionarCliente(new Cliente(cid, nome, cpf, cnh));

        System.out.println("=== Veículos disponíveis ===");
        List<Veiculo> disponiveis = servico.listarVeiculosDisponiveis();
        disponiveis.forEach(v ->
                System.out.printf("%d – %s %s (%s)%n",
                        v.getId(), v.getMarca(), v.getModelo(), v.getPlaca())
        );

        System.out.print("Escolha ID do veículo para alugar: ");
        int escolhido = sc.nextInt();
        Locacao loc = servico.iniciarLocacao(cid, escolhido);
        System.out.println("Locação iniciada em " + loc.getDataInicio().format(fmt));

        System.out.print("Encerrar locação agora? (s/n): ");
        if (sc.next().equalsIgnoreCase("s")) {
            servico.encerrarLocacao(loc);
            System.out.println("Locação encerrada em " + loc.getDataFim().format(fmt));
        }

        sc.close();
    }
}

