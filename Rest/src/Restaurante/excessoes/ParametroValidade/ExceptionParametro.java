package Restaurante.excessoes.ParametroValidade;

public class ExceptionParametro extends Exception{
    protected String nomeParametro;

    public ExceptionParametro(String nomeParametro){
        this.nomeParametro = nomeParametro;
    }
}
