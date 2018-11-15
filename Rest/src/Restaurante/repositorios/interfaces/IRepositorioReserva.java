package Restaurante.repositorios.interfaces;

import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;

/**
 *  Abaixo temos a interface a ser implementada no repositório de reservas, com suas assinaturas de métodos.
 */

public interface IRepositorioReserva {
    void adicionarReserva(Reserva reserva);
    void deletarReserva(Reserva reservaQueSeraDeletada);
    boolean verificarExistenciaReserva(Reserva reservaQueSeraComparada);
    int pegarIdex(Reserva reservaQueSeDesejaSaberIdex);
    Reserva pegarReserva(Reserva reserva) ;
    void mudarUmaReserva(Reserva novosDados, int idexDaReserva);
}
