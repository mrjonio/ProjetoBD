package Restaurante.excessoes.ParametroValidade;

/**
 * Classe para tratar o erro de pessoa menor de idade; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class PessoaMenorDeIdadeErro extends ExceptionParametro{

    public PessoaMenorDeIdadeErro(){
        super("Impossivel cadastrar pessoas menores de idade!");
    }

}
