package Restaurante.gui.outros;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaAlteradoComSucesso implements Initializable {

    private Stage tela;

    @FXML
    private Pane pane;

    @FXML
    private Label lbFinalizado;

    @FXML
    private Button btOk;

    @FXML
    private ImageView imgTop;


    @FXML
    private void acaoBotaoOk (ActionEvent event) {
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
