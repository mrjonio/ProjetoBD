package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.entidade.abstrato.Pedido;
import Restaurante.entidade.concretos.PratoCardapio;
import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.excessoes.NaoOuveLucroErro;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;

import java.time.LocalDate;

/**
 * Abaixo temos a interface a ser implementada na camada "pedidos", com suas assinaturas de métodos e exceções.
 */

public interface ICamadaPedidos {
    void removerPedidosDeUmPeriodoDeTempo(LocalDate dataInicial, LocalDate dataFinal) throws ObjetosInsuficientesErro;
    double calcularLucroDosPedidos(LocalDate dataInicial, LocalDate dataFinal) throws NaoOuveLucroErro, ObjetosInsuficientesErro;
    double calcularGastoDeUmCliente(Pessoa cliente, LocalDate dataInicial, LocalDate dataFinal) throws NaoOuveLucroErro, ObjetosInsuficientesErro;
    int[] criarRankingPratosMaisVendidos(PratoCardapio[] vetorDePratos, LocalDate dataInicial, LocalDate dataFinal) throws ObjetosInsuficientesErro;
    int[] criarRankingClientesQueMaisConsomem(Pessoa[] vetorClientes, LocalDate dataInicial, LocalDate dataFinal) throws ObjetosInsuficientesErro;
    void armazenarUmPedido(Pedido pedidoQueSeraArmazenado);
    Pedido[] gerarVetorDePedidos(LocalDate dataInicial, LocalDate dataFinal) throws ObjetosInsuficientesErro, RepositorioVazioErro;
    double calcularGastoDeUmCliente(Pessoa cliente) throws NaoOuveLucroErro, ObjetosInsuficientesErro;
}
