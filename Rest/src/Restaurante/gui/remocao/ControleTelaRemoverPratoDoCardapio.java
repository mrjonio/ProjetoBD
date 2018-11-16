package Restaurante.gui.remocao;

import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
import Restaurante.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaRemoverPratoDoCardapio implements Initializable {
    private IFachadaGerente fachada;

    public ControleTelaRemoverPratoDoCardapio(){
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private Button btSendPrato, btCancelar;

    @FXML
    private Label lbPrato;

    @FXML
    private TextField tfPrato;

    @FXML
    private ImageView imgTop;

    @FXML
    private Pane pane;

    @FXML
    private void acaoBotaoSendPrato(ActionEvent event){
        String nomeDoPratoQueSeraRemovido = this.tfPrato.getText();
        try{
            this.fachada.retirarUmPratoDoCardapio(nomeDoPratoQueSeraRemovido);
            Main.chamarJanela("../gui/outros/TelaRemovidoComSucesso.fxml", 400, 150);
        } catch (ObjetoNaoExisteErro objetoNaoExisteErro) {
            Main.chamarJanela("../gui/erros/TelaPratoNaoExisteErro.fxml", 400, 150);
        }
    }

    @FXML
    private void acaoBotaoCancelar(ActionEvent event){
        Main.chamarJanela("../gui/objetos/TelaCardapio.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
