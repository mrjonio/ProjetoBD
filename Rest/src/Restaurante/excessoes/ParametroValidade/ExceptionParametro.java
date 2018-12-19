package Restaurante.excessoes.ParametroValidade;

import javafx.scene.control.Alert;

public class ExceptionParametro extends Exception{
    protected String nomeParametro;

    public ExceptionParametro(String nomeParametro){
        this.nomeParametro = nomeParametro;
    }

    public void alertar(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("ERRO");
        alert.setContentText(this.nomeParametro);
        alert.showAndWait();
    }
}
