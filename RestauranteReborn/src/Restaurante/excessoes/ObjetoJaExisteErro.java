package Restaurante.excessoes;

/**
 * Classe para tratar o erro caso o objeto já exista; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class ObjetoJaExisteErro extends Exception{
    private String nomeDoObjeto;


    public ObjetoJaExisteErro( String nomeDoObjeto) {
        this.nomeDoObjeto = nomeDoObjeto;
    }

}
