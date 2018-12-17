package Restaurante.gui.objetos;

import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachada;
import Restaurante.main.Main;
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

public class ControleTelaReserva implements Initializable {
    private IFachada fachada;

    public ControleTelaReserva(){
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private Label lbCadastro, lbRemove, lbBusca, lbLista, lbAltera1, lbAltera2, lbConfirmar;

    @FXML
    private Pane pane;

    @FXML
    private ImageView imgTop;

    @FXML
    private Button btCadastro, btRemove, btBusca, btLista, btAltera, btVolta, btSair, btConfirmar;

    @FXML
    private void acaoBotaoVolta (ActionEvent event) {
        Main.chamarJanela("../gui/objetos/TelaPrincipal.fxml", 780, 411);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoCadastro (ActionEvent event) {
        Main.chamarJanela("../gui/cadastros/TelaCriacaoReserva.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoAltera (ActionEvent event) {
        Main.chamarJanela("../gui/mudancas/TelaMudancaReserva.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoRemove (ActionEvent event) {
        Main.chamarJanela("../gui/remocao/TelaRemoverReserva.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoBusca (ActionEvent event) {
        Main.chamarJanela("../gui/buscas/TelaBuscaReserva.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoLista (ActionEvent event) {
        Main.chamarJanela("../gui/listagens/TelaListarReservas.fxml", 600, 400);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoConfirmar (ActionEvent event) {
        Main.chamarJanela("../gui/outros/TelaConfirmarReserva.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }


    @FXML
    private void acaoBotaoSair (ActionEvent event) {
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
