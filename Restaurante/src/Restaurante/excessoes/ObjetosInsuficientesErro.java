package Restaurante.excessoes;

/**
 * Classe para tratar o erro de objetos insuficientes; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class ObjetosInsuficientesErro extends Exception {
    private String nomeDoObjeto;

    public ObjetosInsuficientesErro(String nomeDoObjeto){
        this.nomeDoObjeto = nomeDoObjeto;
    }
}
