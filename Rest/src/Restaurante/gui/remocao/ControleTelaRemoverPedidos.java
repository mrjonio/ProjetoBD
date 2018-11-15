package Restaurante.gui.remocao;

import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachada;
import Restaurante.main.Main;
import Restaurante.main.MetodosOutros;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControleTelaRemoverPedidos implements Initializable {
    private IFachada fachada;

    public ControleTelaRemoverPedidos(){
        fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private Pane pane;

    @FXML
    private Label lbDia, lbMes, lbAno, lbData, lbdiaFinal, lbMesFinal, lbAnoFinal, lbDataFinal;

    @FXML
    private TextField tfDia, tfMes, tfAno, tfDiaFinal, tfMesFinal, tfAnoFinal;

    @FXML
    private Button btSend, btVoltar, btSair;

    @FXML
    private void acaoBotaoSend(ActionEvent event){
        try {
            LocalDate data = MetodosOutros.retornaData(this.tfDia.getText(), this.tfMes.getText(), this.tfAno.getText());
            LocalDate dataFinal = MetodosOutros.retornaData(this.tfDiaFinal.getText(), this.tfMesFinal.getText(), this.tfAnoFinal.getText());
            this.fachada.deletarPedidosDeUmaDeterminadaEpoca(data, dataFinal);
            Main.chamarJanela("../gui/outros/TelaRemovidoComSucesso.fxml", 400, 150);
        } catch (RuntimeException erro){
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
        } catch (ObjetosInsuficientesErro objetosInsuficientesErro) {
            Main.chamarJanela("../gui/erros/TelaObjetosInsuficientesErro.fxml", 400, 150);
        }
    }

    @FXML
    private void acaoBotaoVolta (ActionEvent event) {
        Main.chamarJanela("../gui/objetos/TelaPedidos.fxml", 711, 480);
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
