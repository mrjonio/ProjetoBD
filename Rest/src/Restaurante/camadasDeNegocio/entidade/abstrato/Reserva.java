package Restaurante.camadasDeNegocio.entidade.abstrato;

import java.time.LocalDateTime;

/**
 * Classe modelo para os objetos do tipo "reserva"; seus atributos e seu construtor.
 */
public class Reserva {
    private LocalDateTime dataHora;
    private String cpfClienteQueReservou;

    /**
     * Classe com as informações da reserva e seu construtor.
     * @param data Data para o uso da reserva.
     * @param cpfClienteQueReservou Informações do cliente que fez a reserva.
     */
    public Reserva(LocalDateTime data,String cpfClienteQueReservou) { //Reformular
        this.dataHora = data;
        this.cpfClienteQueReservou = cpfClienteQueReservou;
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



}
