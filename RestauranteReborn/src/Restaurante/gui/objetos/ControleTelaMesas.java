package Restaurante.gui.objetos;

import Restaurante.entidade.concretos.Mesa;
import Restaurante.excessoes.LetraNoLugarDeNumeroErro;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachada;
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
    private IFachada fachada;

    public ControleTelaMesas(){
        this.fachada = Fachada.getInstance();
    }

    @FXML
    private Button btSend1, btSend2, btVoltar, btVoltar2, btRemove, btCadastrar, btSair;

    @FXML
    private Label lbCadastro, lbRemover, lbQtd, lbQtdMesa;

    @FXML
    private TextField txQtd;

    @FXML
    private Pane pane;

    @FXML
    private ImageView imgEsq, imgMesa;

    private Stage tela;



    private void mudarVisibilidades(boolean a, boolean b){}

    @FXML
    private void cadastrar(ActionEvent a){
        btCadastrar.setVisible(false);
        btRemove.setVisible(false);
        btSair.setVisible(false);
        btVoltar.setVisible(false);
        btVoltar2.setVisible(true);
        btSend1.setVisible(true);
        txQtd.setVisible(true);
        lbCadastro.setVisible(false);
        lbRemover.setVisible(false);
        lbQtd.setVisible(true);
        lbQtdMesa.setVisible(false);
        imgMesa.setVisible(false);
    }

    @FXML
    private void remover(ActionEvent a){
        btCadastrar.setVisible(false);
        btRemove.setVisible(false);
        btSair.setVisible(false);
        btVoltar.setVisible(false);
        btVoltar2.setVisible(true);
        btSend2.setVisible(true);
        txQtd.setVisible(true);
        lbCadastro.setVisible(false);
        lbRemover.setVisible(false);
        lbQtd.setVisible(true);
        lbQtdMesa.setVisible(false);
        imgMesa.setVisible(false);
    }


    @FXML
    private void voltarTelaMesa(ActionEvent a){
        btCadastrar.setVisible(true);
        btRemove.setVisible(true);
        btSair.setVisible(true);
        btVoltar.setVisible(false);
        btVoltar2.setVisible(false);
        txQtd.setVisible(false);
        lbCadastro.setVisible(true);
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
        try{
        int qtd = Integer.parseInt(this.txQtd.getText());
        if (this.fachada.qtdMesas() > 0) {
            for (int i = 0; i < qtd; i++) {
                try {
                    Mesa mesaQueSeraDeletada = this.fachada.buscarUmaMesa(0);
                    this.fachada.deletarMesasDoSistema(mesaQueSeraDeletada);
                } catch (RepositorioVazioErro repositorioVazioErro) {
                } catch (ObjetosInsuficientesErro objetosInsuficientesErro) {
                }
            }
            Main.chamarJanela("../gui/outros/TelaRemovidoComSucesso.fxml", 400, 150);
        } else {
            Main.chamarJanela("../gui/erros/TelaRepositorioVazioErro.fxml", 400, 150);
        }
        } catch (RuntimeException erro){
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
        }
    }

    @FXML
    private void acaoBotaoSend1(ActionEvent event) {
        try {
            int qtd = Integer.parseInt(this.txQtd.getText());
            for (int i = 0; i < qtd; i++) {
                Mesa mesa = new Mesa();
                this.fachada.adicionarUmaMesa(mesa);
            }
            Main.chamarJanela("../gui/outros/TelaCadastradoComSucesso.fxml", 400, 150);
        } catch (RuntimeException erro){
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
        }
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
