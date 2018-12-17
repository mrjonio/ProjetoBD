package Restaurante.excessoes;

/**
 * Classe para tratar o erro de limites de funcionários atingidos; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class LimiteDeFuncionarioAtingidoErro extends Exception {

    public LimiteDeFuncionarioAtingidoErro(String nomeFuncao){
        super("Quantidade de" + nomeFuncao + "já suficiente!");
    }
}
