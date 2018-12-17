package Restaurante.excessoes;

/**
 * Classe para tratar o erro caso o objeto já esteja em uso; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class ObjetoEmUsoErro extends Exception {
    private String nomeDoObjeto;

     public ObjetoEmUsoErro(String nomeDoObjeto){
         this.nomeDoObjeto = nomeDoObjeto;
     }
}
