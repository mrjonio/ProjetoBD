package Restaurante.gui.guiDoGerente.funcionario;

import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
import Restaurante.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class ControleFolhaDePagamento implements Initializable {
    private IFachadaGerente fachada;

    public ControleFolhaDePagamento (){
        fachada = Fachada.getInstance();

    }

    @FXML
    private void valorTotal(ActionEvent event){
        double valor = this.fachada.calcularFolhaDePagamento();
        String valorFormatado = String.valueOf(valor);
        lbTotal.setText(valorFormatado);
        lbTotal.setVisible(true);
        btOk.setVisible(true);
        btCalcular.setVisible(false);
    }

    @FXML
    private Label lbTotal;

    @FXML
    private Button btOk, btCalcular;

    private Stage tela;

    @FXML
    private Pane pane;

    @FXML
    private void funcaoBotaoOk(ActionEvent event){
        Main.chamarJanela("../gui/guiDoGerente/funcionario/TelaFuncionario.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
