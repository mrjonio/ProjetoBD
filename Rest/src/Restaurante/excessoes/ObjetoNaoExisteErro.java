package Restaurante.excessoes;

/**
 * Classe para tratar o erro caso o objeto não exista; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class ObjetoNaoExisteErro extends Exception {
    private String nomeDoObjeto;

    public ObjetoNaoExisteErro(String nomeDoObjeto){
         this.nomeDoObjeto = nomeDoObjeto;
     }

     public String getNomeDoObjeto(){
        return this.nomeDoObjeto;
     }
}
