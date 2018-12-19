package Restaurante.excessoes.ObjetoExistencia;



/**
 * Classe para tratar o erro caso o objeto já esteja em uso; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class ObjetoEmUsoErro extends ExcecaoObjetoExistencia {

    public ObjetoEmUsoErro(String nomeObjeto) {
        super(nomeObjeto + " em uso!");
    }
}
