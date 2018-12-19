package Restaurante.repositorios.interfaces;


import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.excessoes.PratoPendenteErro;

import java.time.LocalDateTime;
import java.util.List;

/**
 *  Abaixo temos a interface a ser implementada no repositório de pedidos, com suas assinaturas de métodos.
 */

public interface IRepositorioPedidos {

    double calcularLucro(LocalDateTime dataInicial, LocalDateTime dataFinal); //OK
    void deletarPedidos(LocalDateTime dataInicial, LocalDateTime dataFinal); //OK

    //AQUI NO CASO FICOU PRA QUANDO O COZINHEIRO COZINHA O PEDIDO
    //A CRIAÇÃO DO PEDIDO SÓ OCORRO QUANDO A MESA CONFIRMA O PEDIDO
    void adicionarPedido(Pedido pedidoQueSeraAdicionado); //OK
    void removerPedido(Pedido pedidoQueSeraRemovido); //OK
    Pedido buscarPedido(Pedido pedidoQueSeraBuscado); //OK
    List<Pedido> gerarVetorPedido(LocalDateTime inicio, LocalDateTime fim); //OK
    public boolean verificarPratoEmPedido(String nomePrato) throws PratoPendenteErro;
}
