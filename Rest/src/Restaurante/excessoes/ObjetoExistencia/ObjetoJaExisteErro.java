package Restaurante.excessoes.ObjetoExistencia;


/**
 * Classe para tratar o erro caso o objeto já exista; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class ObjetoJaExisteErro extends ExcecaoObjetoExistencia {

    public ObjetoJaExisteErro( String nomeDoObjeto) {
        super(nomeDoObjeto + " já cadastrado");
    }

}
