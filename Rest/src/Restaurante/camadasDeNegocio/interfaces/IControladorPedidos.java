package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.excessoes.NaoOuveLucroErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetosInsuficientesErro;
import java.time.LocalDateTime;

/**
 * Abaixo temos a interface a ser implementada na camada "pedidos", com suas assinaturas de métodos e exceções.
 */

public interface IControladorPedidos {
    void removerPedidosDeUmPeriodoDeTempo(LocalDateTime dataInicial, LocalDateTime dataFinal) throws ObjetosInsuficientesErro;
    double calcularLucroDosPedidos(LocalDateTime dataInicial, LocalDateTime dataFinal) throws NaoOuveLucroErro, ObjetosInsuficientesErro;
    void armazenarUmPedido(Pedido pedidoQueSeraArmazenado);

}
