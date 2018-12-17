
package Restaurante.repositorios.interfaces;

import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;

/**
 *  Abaixo temos a interface a ser implementada no repositório de reservas, com suas assinaturas de métodos.
 */

public interface IRepositorioReserva {
    //TODO: por enquanto só pode ter 1 reserva por cpf
    void adicionarReserva(Reserva reserva); //OK
    void deletarReserva(Reserva reservaQueSeraDeletada); //OK
    boolean verificarExistenciaReserva(Reserva reservaQueSeraComparada); //OK
    boolean verificarExistenciaReserva(String cpf); //OK
    int pegarIdex(Reserva reservaQueSeDesejaSaberIdex); //NAO PRECISA
    Reserva pegarReserva(Reserva reserva) ; //OK
    Reserva pegarReserva(String cpf) ; // OK
    void mudarUmaReserva(Reserva novosDados, Reserva antiga); //OK
}