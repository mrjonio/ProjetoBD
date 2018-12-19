package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.excessoes.NaoOuveLucroErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetosInsuficientesErro;
import Restaurante.excessoes.PratoPendenteErro;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Abaixo temos a interface a ser implementada na camada "pedidos", com suas assinaturas de métodos e exceções.
 */

public interface IControladorPedidos {
    void removerPedidosDeUmPeriodoDeTempo(LocalDateTime dataInicial, LocalDateTime dataFinal) throws ObjetosInsuficientesErro;
    double calcularLucroDosPedidos(LocalDateTime dataInicial, LocalDateTime dataFinal) throws NaoOuveLucroErro, ObjetosInsuficientesErro;
    void armazenarUmPedido(Pedido pedidoQueSeraArmazenado);
    List<Pedido> criarListaPedidosDeterminadoPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) throws ObjetosInsuficientesErro;
    void removerUmDeterminadopedido(Pedido pedidoQueSeraRemovido) throws ObjetoNaoExisteErro;
    Pedido buscarUmDeterminadoPedido(Pedido pedidoBuscado) throws ObjetoNaoExisteErro;
    public void verificarPratoPendente(String nomePrato) throws PratoPendenteErro;
}
