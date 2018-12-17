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

public class ControleTelaFuncionario implements Initializable {
    private IFachada fachada;

    public ControleTelaFuncionario(){
        this.fachada = Fachada.getInstance();
    }


    private Stage tela;

    @FXML
    private Button btCadastro, btListar, btBuscar, btFolhaDePagamento, btRemove, btVoltar, btMudanca, btSair;

    @FXML
    private Label lbCadastro, lbListar, lbBuscar, lbFolhaDePagamento, lbRemove, lbMudar;

    @FXML
    private Pane pane;

    @FXML
    private ImageView img;


    @FXML
    private void acaoBotaoCadastro (ActionEvent event) {
        Main.chamarJanela("../gui/cadastros/TelaCadastroFuncionario.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoRemove (ActionEvent event) {
        Main.chamarJanela("../gui/remocao/TelaRemoverFuncionario.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoBusca (ActionEvent event) {
        Main.chamarJanela("../gui/buscas/TelaBuscaFuncionario.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoLista (ActionEvent event) {
        Main.chamarJanela("../gui/listagens/TelaListarFuncionarios.fxml", 627, 400);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoAlteraAtributo (ActionEvent event) {
        Main.chamarJanela("../gui/mudancas/TelaMudancaFuncionario.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoCalcularFolha (ActionEvent event) {
        Main.chamarJanela("../gui/outros/FolhaDePagamento.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }


    @FXML
    private void acaoBotaoVolta (ActionEvent event) {
        Main.chamarJanela("../gui/objetos/TelaPrincipal.fxml", 780, 411);
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
