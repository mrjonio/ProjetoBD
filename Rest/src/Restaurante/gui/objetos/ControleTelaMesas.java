package Restaurante.gui.objetos;

import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.excessoes.ObjetoExistencia.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;
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

public class ControleTelaMesas implements Initializable {
    private IFachadaGerente fachada;

    public ControleTelaMesas(){
        this.fachada = Fachada.getInstance();
    }

    @FXML
    private Button btSend1, btSend2, btVoltar, btVoltar2, btRemove, btSair;

    @FXML
    private Label lbRemover, lbQtd, lbQtdMesa;

    @FXML
    private TextField txQtd;

    @FXML
    private Pane pane;

    @FXML
    private ImageView imgEsq, imgMesa;

    private Stage tela;



    @FXML
    private void cadastrar(ActionEvent a){
        btRemove.setVisible(false);
        btSair.setVisible(false);
        btVoltar.setVisible(false);
        btVoltar2.setVisible(true);
        btSend1.setVisible(true);
        txQtd.setVisible(true);
        lbRemover.setVisible(false);
        lbQtd.setVisible(true);
        lbQtdMesa.setVisible(false);
        imgMesa.setVisible(false);
    }

    @FXML
    private void remover(ActionEvent a){
        btRemove.setVisible(false);
        btSair.setVisible(false);
        btVoltar.setVisible(false);
        btVoltar2.setVisible(true);
        btSend2.setVisible(true);
        txQtd.setVisible(true);
        lbRemover.setVisible(false);
        lbQtd.setVisible(true);
        lbQtdMesa.setVisible(false);
        imgMesa.setVisible(false);
    }


    @FXML
    private void voltarTelaMesa(ActionEvent a){
        btRemove.setVisible(true);
        btSair.setVisible(true);
        btVoltar.setVisible(false);
        btVoltar2.setVisible(false);
        txQtd.setVisible(false);
        lbRemover.setVisible(true);
        lbQtd.setVisible(false);
        lbQtdMesa.setVisible(true);
        if (btSend1.isVisible()){
            btSend1.setVisible(false);
        }
        else {
            btSend2.setVisible(false);
        }
        String qtdMesas = String.valueOf(this.fachada.qtdMesas());
        lbQtdMesa.setText(qtdMesas);
        imgMesa.setVisible(true);
        txQtd.clear();
    }

    @FXML
    private void acaoBotaoSend2(ActionEvent event) {
        //Refazer
    }

    @FXML
    private void acaoBotaoSend1(ActionEvent event) {
        //Refazer
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
