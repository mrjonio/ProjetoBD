package Restaurante.gui;

import Restaurante.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ControleTelaSelecao {

    @FXML
    private Window tela;

    @FXML
    private void clienteView(ActionEvent event) {
        Main.chamarJanela("../gui/guiDoCliente/TelaCardapioCliente.fxml",710, 468);
        this.tela = ((Node)event.getTarget()).getScene().getWindow();
        //((Stage) this.tela.getScene().getWindow()).close();
    }
    @FXML
    private void cozinheiroView(ActionEvent event) {
        Main.chamarJanela("../gui/guiDoCozinheiro/TelaCozinheiro.fxml",710, 468);
        this.tela = ((Node)event.getTarget()).getScene().getWindow();
        //((Stage) this.tela.getScene().getWindow()).close();
    }
    @FXML
    private void gerenteView(ActionEvent event) {
        Main.chamarJanela("../gui/guiDoGerente/SelecaoGerenciamento.fxml",710, 468);
        this.tela = ((Node)event.getTarget()).getScene().getWindow();
        //((Stage) this.tela.getScene().getWindow()).close();
    }
    @FXML
    private void atendenteView(ActionEvent event) {
        Main.chamarJanela("../gui/guiDoAtendente/TelaAtendente.fxml",710, 468);
        this.tela = ((Node)event.getTarget()).getScene().getWindow();
        //((Stage) this.tela.getScene().getWindow()).close();
    }

}
