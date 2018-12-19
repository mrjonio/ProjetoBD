package Restaurante.excessoes.ParametroValidade;

/**
 * Classe para tratar o erro de parâmetros iguais.
 * (Extensão da classe Exception (exceção)).
 */
public class ParametrosIguaisErro extends ExceptionParametro {

    public ParametrosIguaisErro(String nomeParametro) {
        super("Parametros iguais: " + nomeParametro);
    }
}
