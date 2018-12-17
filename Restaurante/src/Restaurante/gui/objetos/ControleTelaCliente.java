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

public class ControleTelaCliente implements Initializable {
    private IFachada fachada;

    public ControleTelaCliente(){
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private Button btCadastro, btRemove, btBusca, btRanking, btListar, btVoltar, btMudar, btSair;

    @FXML
    private Label lbCadastro, lbRemove, lbBusca, lbRanking, lbListar, lbMudar;

    @FXML
    private Pane pane;

    @FXML
    private ImageView imgTop;


    @FXML
    private void acaoBotaoCadastro (ActionEvent event) {
        Main.chamarJanela("../gui/cadastros/TelaCadastroCliente.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoRemove (ActionEvent event) {
        Main.chamarJanela("../gui/remocao/TelaDeletarCliente.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoBusca (ActionEvent event) {
        Main.chamarJanela("../gui/buscas/TelaBuscaCliente.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoLista (ActionEvent event) {
        Main.chamarJanela("../gui/listagens/TelaListarClientes.fxml", 627, 400);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoAlteraAtributo (ActionEvent event) {
        Main.chamarJanela("../gui/mudancas/TelaMudancaCliente.fxml", 600 , 400);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoRanking (ActionEvent event) {
        Main.chamarJanela("../gui/listagens/TelaExibicaoRankingClientes.fxml", 600, 400);
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
