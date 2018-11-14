package Restaurante.repositorios.interfaces;


import Restaurante.entidade.abstrato.Pedido;
import Restaurante.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.entidade.pessoas.Pessoa;

import java.time.LocalDate;

/**
 *  Abaixo temos a interface a ser implementada no repositório de pedidos, com suas assinaturas de métodos.
 */

public interface IRepositorioPedidos {

    int contarNumeroDeAparicoesPrato(PratoCardapio pratoQueSeraContado, LocalDate dataInicial, LocalDate dataFinal);
    int contarNumeroDeAparicoesCliente(Pessoa clienteQueSeraContado, LocalDate dataInicial, LocalDate dataFinal);
    double calcularLucro(LocalDate dataInicial, LocalDate dataFinal);
    void deletarPedidos(LocalDate dataInicial, LocalDate dataFinal);
    double calcularGastoDeUmCliente(Pessoa clienteQueSeraCalculado, LocalDate dataInicial, LocalDate dataFinal);
    void adicionarPedido(Pedido pedidoQueSeraAdicionado);
    boolean buscarPeloMenosUmPedido(LocalDate dataInicial, LocalDate dataFinal);
    boolean buscarPeloMenosUmPedido(LocalDate dataInicial, LocalDate dataFinal, Pessoa cliente);
    boolean estaVazio();
    Pedido[] gerarVetorPedidos(LocalDate dataInicial, LocalDate dataFinal);
}
