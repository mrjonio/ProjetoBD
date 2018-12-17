package Restaurante.gui.guiDoGerente.funcionario;

import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
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
    private IFachadaGerente fachada;

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
        Main.chamarJanela("../gui/guiDoGerente/funcionario/TelaCadastroFuncionario.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoRemove (ActionEvent event) {
        Main.chamarJanela("../gui/guiDoGerente/funcionario/TelaRemoverFuncionario.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoBusca (ActionEvent event) {
        Main.chamarJanela("../gui/guiDoGerente/funcionario/TelaBuscaFuncionario.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoLista (ActionEvent event) {
        Main.chamarJanela("../gui/guiDoGerente/funcionario/TelaListarFuncionarios.fxml", 627, 400);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoAlteraAtributo (ActionEvent event) {
        Main.chamarJanela("../gui/guiDoGerente/funcionario/TelaMudancaFuncionario.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoCalcularFolha (ActionEvent event) {
        Main.chamarJanela(".../gui/guiDoGerente/funcionario/FolhaDePagamento.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }


    @FXML
    private void acaoBotaoVolta (ActionEvent event) {
        //Main.chamarJanela("../gui/objetos/TelaPrincipal.fxml", 780, 411);
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
