package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.entidade.abstrato.Reserva;
import Restaurante.entidade.concretos.Mesa;
import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.excessoes.*;
import java.time.LocalDateTime;

/**
 *  Abaixo temos a interface a ser implementada na camada "reservas", com suas assinaturas de métodos e exceções.
 */

public interface ICamadaReservas {

    void fazerNovaReserva(Reserva reservaQueSeraFeita) throws ClienteJaReservouErro, ObjetoJaExisteErro;
    boolean verificacaoDeDisponibilidadeDeUmaMesa(Mesa mesa, LocalDateTime data) ;
    void deletarUmaReserva(Pessoa clienteQueReservou) throws ObjetoEmUsoErro, ObjetoNaoExisteErro;
    void confirmarReserva(Pessoa donoDaReserva) throws ReservaJaConfirmadaErro, ObjetoNaoExisteErro, ReservaExpiradaErro;
    void mudarReserva(Reserva novosAtributos, Pessoa donoDaReserva) throws ObjetoJaExisteErro, ObjetoNaoExisteErro;
    Reserva buscarReserva(Pessoa donoDaReserva) throws ObjetoNaoExisteErro;
    Reserva[] gerarVetorDeReservas() throws ObjetosInsuficientesErro, RepositorioVazioErro;
    boolean verificarExistenciaDeUmaReserva(String nomeDoDono);
}
