package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.entidade.abstrato.Pedido;
import Restaurante.excessoes.NaoOuveLucroErro;
import Restaurante.excessoes.ObjetosInsuficientesErro;

import java.time.LocalDate;

/**
 * Abaixo temos a interface a ser implementada na camada "pedidos", com suas assinaturas de métodos e exceções.
 */

public interface IControladorPedidos {
    void removerPedidosDeUmPeriodoDeTempo(LocalDate dataInicial, LocalDate dataFinal) throws ObjetosInsuficientesErro;
    double calcularLucroDosPedidos(LocalDate dataInicial, LocalDate dataFinal) throws NaoOuveLucroErro, ObjetosInsuficientesErro;
   //refazer int[] criarRankingPratosMaisVendidos(PratoCardapio[] vetorDePratos, LocalDate dataInicial, LocalDate dataFinal) throws ObjetosInsuficientesErro;
    void armazenarUmPedido(Pedido pedidoQueSeraArmazenado);
   //Refazer Pedido[] gerarVetorDePedidos(LocalDate dataInicial, LocalDate dataFinal) throws ObjetosInsuficientesErro, RepositorioVazioErro;
}
