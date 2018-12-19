package Restaurante.excessoes.ObjetoExistencia;

import javafx.scene.control.Alert;

public class ExcecaoObjetoExistencia extends Exception {
    protected String nomeObjeto;
    public ExcecaoObjetoExistencia(String nomeObjeto){
        this.nomeObjeto = nomeObjeto;
    }

    public void alertar(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("ERRO");
        alert.setContentText(this.nomeObjeto);
        alert.showAndWait();
    }

}
