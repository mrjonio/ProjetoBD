package Restaurante.excessoes.ParametroValidade;

/**
 * Classe para tratar o erro de letras no lugar de números; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class LetraNoLugarDeNumeroErro extends ExceptionParametro {

    public LetraNoLugarDeNumeroErro(String nomeParametro) {
        super(nomeParametro);
    }
}
