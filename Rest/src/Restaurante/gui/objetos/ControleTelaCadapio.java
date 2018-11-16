package Restaurante.gui.objetos;

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

public class ControleTelaCadapio implements Initializable {
    private IFachadaGerente fachada;

    public ControleTelaCadapio(){
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private Button btCadastro, btRemove, btBusca, btLista, btSair, btVolta, btAlteraAtributo, btRanking;

    @FXML
    private Label lbCadastro, lbRemove, lbBusca, lbLista, lbRanking;

    @FXML
    private ImageView imgTop;

    @FXML
    private Pane pane;



    @FXML
    private void acaoBotaoCadastro (ActionEvent event) {
        Main.chamarJanela("../gui/cadastros/TelaAdicionarAoCardapio.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoRemove (ActionEvent event) {
        Main.chamarJanela("../gui/remocao/TelaRemoverPratoDoCardapio.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoBusca (ActionEvent event) {
        Main.chamarJanela("../gui/buscas/TelaBuscaCardapio.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoLista (ActionEvent event) {
        Main.chamarJanela("../gui/listagens/TelaListarPratosDoCardapio.fxml", 627, 400);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoAlteraAtributo (ActionEvent event) {
        Main.chamarJanela("../gui/mudancas/TelaMudancaPratoCardapio.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoRanking (ActionEvent event) {
        Main.chamarJanela("../gui/listagens/TelaExibicaoRankingPratos.fxml", 600, 400);
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
