package Restaurante.excessoes.ParametroValidade;

/**
 * Classe para tratar o erro de campo vazio; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class CampoVazioErro extends ExceptionParametro {
    public CampoVazioErro(String nomeParametro) {
        super("Campo Vazio: " + nomeParametro);
    }
}
