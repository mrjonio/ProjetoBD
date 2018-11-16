package Restaurante.excessoes.ObjetoExistencia;

import Restaurante.excessoes.ObjetoExistencia.ExcecaoObjetoExistencia;

/**
 * Classe para tratar o erro de objetos insuficientes; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class ObjetosInsuficientesErro extends ExcecaoObjetoExistencia {
    public ObjetosInsuficientesErro(String nomeObjeto) {
        super(nomeObjeto);
    }
}
