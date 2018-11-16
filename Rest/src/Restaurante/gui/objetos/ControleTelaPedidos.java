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

public class ControleTelaPedidos implements Initializable{
    private IFachadaGerente fachada;

    public ControleTelaPedidos(){
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private Button btCadastro, btRemove, btLista, btLucro, btSair, btVolta;

    @FXML
    private Label lbCadastro, lbRemove, lbLista, lbLucro;

    @FXML
    private Pane pane;

    @FXML
    private ImageView imgTop;

    @FXML
    private void acaoBotaoCadastro(ActionEvent event){
        Main.chamarJanela("../gui/cadastros/TelaCadastroPedidos.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoRemove(ActionEvent event){
        Main.chamarJanela("../gui/remocao/TelaRemoverPedidos.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();

    }

    @FXML
    private void acaoBotaoLucro(ActionEvent event){
        Main.chamarJanela("../gui/outros/LucroPratos.fxml", 711, 480);
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
    private void acaoBotaoListar(ActionEvent event){
        Main.chamarJanela("../gui/listagens/TelaListarPedidos.fxml", 780, 411);
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
