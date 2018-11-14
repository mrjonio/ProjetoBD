package Restaurante.excessoes;

/**
 * Classe para tratar o erro caso a reserva já tenha sido confirmada; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class ReservaJaConfirmadaErro extends Exception {
    private String cpfDeQuemConfirmou;

    public ReservaJaConfirmadaErro(String cpfDeQuemConfirmou) {
        this.cpfDeQuemConfirmou = cpfDeQuemConfirmou;
    }
}
