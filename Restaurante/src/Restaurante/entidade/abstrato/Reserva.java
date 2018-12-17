package Restaurante.entidade.abstrato;


import Restaurante.entidade.concretos.Mesa;
import Restaurante.entidade.pessoas.Pessoa;

import java.time.LocalDateTime;

/**
 * Classe modelo para os objetos do tipo "reserva"; seus atributos e seu construtor.
 */
public class Reserva {
    private LocalDateTime dataHora;
    private Mesa mesa; //Não tem mais
    private Pessoa clienteQueReservou; //Não tem mais
    private boolean confirmacaoDaReserva; //Não tem mais

    /**
     * Classe com as informações da reserva e seu construtor.
     * @param data Data para o uso da reserva.
     * @param mesa Mesa reservada.
     * @param clienteQueReservou Informações do cliente que fez a reserva.
     */
    public Reserva(LocalDateTime data, Mesa mesa, Pessoa clienteQueReservou) { //Reformular
        this.dataHora = data;
        this.confirmacaoDaReserva = false;
        this.mesa = mesa;
        this.clienteQueReservou = clienteQueReservou;
    }

    /**
     * Método para a verificação de confirmação de uma reserva.
     * @return Retorna a confirmação.
     */
    public boolean isConfirmacaoDaReserva() {
        return confirmacaoDaReserva;
    } //Não tem mais

    public void setConfirmacaoDaReserva(boolean confirmacaoDaReserva) { //Não tem mais
        this.confirmacaoDaReserva = confirmacaoDaReserva;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    } //Não tem mais

    public void setDataHora(LocalDateTime data) {
        this.dataHora = data;
    } //Reformular

    public Pessoa getClienteQueReservou() {
        return clienteQueReservou;
    } //Não tem mais

    public void setClienteQueReservou(Pessoa clienteQueReservou) {
        this.clienteQueReservou = clienteQueReservou;
    } //Não tem mais

    public Mesa getMesa() {
        return mesa;
    } //Não tem mais

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    } //Não tem mais


    /**
     * Método para a exibição da reserva, contendo a data e a hora que a reserva será usada, a mesa da reserva,
     * o cliente que fez a reserva, o cpf do cliente que fez a reserva e a confirmação da reserva.
     * @return Retorna a reserva.
     */
    @Override
    public String toString() { //Reformular
        return "Reserva: " +
                " || Data e Hora = " + dataHora +
                " || Mesa=" + mesa +
                " || Cliente que reservou = " + clienteQueReservou.getNome() +
                " || Cpf do cliente = " + clienteQueReservou.getCpf() +
                ", || Status = " + confirmacaoDaReserva +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva)) return false;

        Reserva reserva = (Reserva) o;

        if (isConfirmacaoDaReserva() != reserva.isConfirmacaoDaReserva()) return false;
        if (getDataHora() != null ? !getDataHora().equals(reserva.getDataHora()) : reserva.getDataHora() != null)
            return false;
        if (getMesa() != null ? !getMesa().equals(reserva.getMesa()) : reserva.getMesa() != null) return false;
        return getClienteQueReservou() != null ? getClienteQueReservou().equals(reserva.getClienteQueReservou()) : reserva.getClienteQueReservou() == null;
    }

    @Override
    public int hashCode() {
        int result = getDataHora() != null ? getDataHora().hashCode() : 0;
        result = 31 * result + (getMesa() != null ? getMesa().hashCode() : 0);
        result = 31 * result + (getClienteQueReservou() != null ? getClienteQueReservou().hashCode() : 0);
        result = 31 * result + (isConfirmacaoDaReserva() ? 1 : 0);
        return result;
    }
}
