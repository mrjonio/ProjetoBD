package Restaurante.excessoes;

/**
 * Classe para tratar o erro de pessoa menor de idade; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class PessoaMenorDeIdadeErro extends Exception{

    public PessoaMenorDeIdadeErro(){
        super("Impossivel cadastrar pessoas menores de idade!");
    }

}
