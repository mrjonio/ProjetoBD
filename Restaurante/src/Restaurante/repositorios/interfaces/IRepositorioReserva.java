package Restaurante.repositorios.interfaces;

import Restaurante.entidade.abstrato.Reserva;
import Restaurante.entidade.concretos.Mesa;
import Restaurante.entidade.pessoas.Pessoa;

import java.time.LocalDateTime;

/**
 *  Abaixo temos a interface a ser implementada no repositório de reservas, com suas assinaturas de métodos.
 */

public interface IRepositorioReserva {
    void adicionarReserva(Reserva reserva);
    void deletarReserva(Reserva reservaQueSeraDeletada);
    void confirmarReserva(int idexDaReserva);
    boolean verificarExistenciaReserva(Reserva reservaQueSeraComparada);
    int pegarIdex(Reserva reservaQueSeDesejaSaberIdex);
    Reserva pegarReserva(Pessoa clienteQueReservou) ;
    boolean verficarSeUmaMesaJaFoiReservada(Mesa mesa);
    boolean verficarDisponibilidadeDeUmaMesaReservada(Mesa mesa, LocalDateTime dataHora);
    boolean verificarExistenciaReserva(Pessoa donoDaReserva);
    void mudarUmaReserva(Reserva novosDados, int idexDaReserva);
    Reserva[] criarVetorDeReservas();
    boolean verificarSeRepositorioEstaVazio();
}
