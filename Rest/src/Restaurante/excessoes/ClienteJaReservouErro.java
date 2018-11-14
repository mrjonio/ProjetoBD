package Restaurante.excessoes;

/**
 * Classe para tratar o erro caso o cliente já tenha feito uma reserva; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class ClienteJaReservouErro extends Exception {
    private String cpfDoCliente;

    public ClienteJaReservouErro(String cpfDoCliente){
        this.cpfDoCliente = cpfDoCliente;
    }
}
