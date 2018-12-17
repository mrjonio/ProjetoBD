package Restaurante.excessoes;

/**
 * Classe para tratar o erro de parâmetro inválido; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class ParametroInvalidoErro extends Exception{
    private String nomeDoParametro;

	public ParametroInvalidoErro(String nomeDoParametro){
	    this.nomeDoParametro = nomeDoParametro;
    }
}
