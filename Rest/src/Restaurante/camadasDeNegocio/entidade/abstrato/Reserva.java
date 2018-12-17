package Restaurante.camadasDeNegocio.entidade.abstrato;

import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;

import java.time.LocalDateTime;

/**
 * Classe modelo para os objetos do tipo "reserva"; seus atributos e seu construtor.
 */
public class Reserva {
    private LocalDateTime dataHora;
    private String cpfClienteQueReservou;
    private Mesa mesaReservada;

    /**
     * Classe com as informações da reserva e seu construtor.
     * @param data Data para o uso da reserva.
     * @param cpfClienteQueReservou Informações do cliente que fez a reserva.
     */
    public Reserva(LocalDateTime data,String cpfClienteQueReservou, Mesa mesa) {
        this.dataHora = data;
        this.cpfClienteQueReservou = cpfClienteQueReservou;
        this.mesaReservada = mesa;
    }

    /**
     * Método para a verificação de confirmação de uma reserva.
     * @return Retorna a confirmação.
     */

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getClienteQueReservou() {
        return cpfClienteQueReservou;
    }

    public int getMesa(){
        return this.mesaReservada.getNumero();
    }

    @Override
    public boolean equals(Object reserva) {
        if (reserva instanceof Reserva) {
            if (((Reserva) reserva).cpfClienteQueReservou.equals(this.getClienteQueReservou())
                    && ((Reserva) reserva).dataHora.toString().equals(this.getDataHora().toString())
                    && ((Reserva) reserva).getMesa() == this.getMesa()) {
                return true;
            }
        }
        return false;
    }

}
