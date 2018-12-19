package Restaurante.excessoes.ObjetoExistencia;


/**
 * Classe para tratar o erro caso o objeto não exista; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class ObjetoNaoExisteErro extends ExcecaoObjetoExistencia {

    public ObjetoNaoExisteErro(String nomeDoObjeto){
        super(nomeDoObjeto + " não encontrado!");
     }

}
