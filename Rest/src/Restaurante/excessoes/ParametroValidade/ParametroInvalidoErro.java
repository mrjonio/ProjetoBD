package Restaurante.excessoes.ParametroValidade;


/**
 * Classe para tratar o erro de parâmetro inválido; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class ParametroInvalidoErro extends ExceptionParametro {

    public ParametroInvalidoErro(String nomeParametro) {
        super(nomeParametro);
    }
}
