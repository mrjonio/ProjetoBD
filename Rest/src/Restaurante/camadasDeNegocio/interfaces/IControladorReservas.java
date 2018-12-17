package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;
import Restaurante.excessoes.*;
import Restaurante.excessoes.ObjetoExistencia.ObjetoEmUsoErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;

/**
 *  Abaixo temos a interface a ser implementada na camada "reservas", com suas assinaturas de métodos e exceções.
 */

public interface IControladorReservas {

    void fazerNovaReserva(Reserva reservaQueSeraFeita) throws ClienteJaReservouErro, ObjetoJaExisteErro;
    void deletarUmaReserva(Reserva reservaQueSeraDeletada) throws ObjetoEmUsoErro, ObjetoNaoExisteErro;
    void mudarReserva(Reserva novosAtributos, Reserva antigaReserva) throws ObjetoJaExisteErro, ObjetoNaoExisteErro;
    Reserva buscarReserva(Reserva reserva) throws ObjetoNaoExisteErro;
}
