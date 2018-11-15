package Restaurante.excessoes.ObjetoExistencia;

public class ExcecaoObjetoExistencia extends Exception {
    protected String nomeObjeto;
    public ExcecaoObjetoExistencia(String nomeObjeto){
        this.nomeObjeto = nomeObjeto;
    }

}
