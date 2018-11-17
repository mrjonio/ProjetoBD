package Restaurante.repositorios.interfaces;


import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;

import java.time.LocalDateTime;
import java.util.List;

/**
 *  Abaixo temos a interface a ser implementada no repositório de pedidos, com suas assinaturas de métodos.
 */

public interface IRepositorioPedidos {

    double calcularLucro(LocalDateTime dataInicial, LocalDateTime dataFinal);
    void deletarPedidos(LocalDateTime dataInicial, LocalDateTime dataFinal);
    void adicionarPedido(Pedido pedidoQueSeraAdicionado);
    void removerPedido(Pedido pedidoQueSeraRemovido);
    Pedido buscarPedido(Pedido pedidoQueSeraBuscado);
    List<Pedido> gerarVetorPedido(LocalDateTime inicio, LocalDateTime fim);
}
