package Restaurante.excessoes;

/**
 * Classe para tratar o erro caso tente remover um prato que está sendo utilizado; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */

public class PratoPendenteErro extends Exception{

    public PratoPendenteErro(String nomeDoPrato){
        super("Esse prato " + nomeDoPrato + " está pendente (EM USO). Não é possível removê-lo!");
    }

}


