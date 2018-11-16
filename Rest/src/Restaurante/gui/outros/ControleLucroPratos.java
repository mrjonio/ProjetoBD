package Restaurante.gui.outros;

import Restaurante.excessoes.NaoOuveLucroErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetosInsuficientesErro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
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

public class ControleLucroPratos implements Initializable {
    private IFachadaGerente fachada;

    public ControleLucroPratos(){
        fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private Pane pane;

    @FXML
    private Label lbDia, lbMes, lbAno, lbData, lbDiaFinal, lbMesFinal, lbAnoFinal, lbDataFinal, lbLucro, lbLucroNome;

    @FXML
    private TextField tfDia, tfMes, tfAno, tfDiaFinal, tfMesFinal, tfAnoFinal;

    @FXML
    private Button btSend, btVoltar, btSair, btVoltar2;

    private void mudarVisibilidades(boolean a, boolean b){
        this.btSend.setVisible(a);
        this.btSair.setVisible(a);
        this.btVoltar.setVisible(a);
        this.btVoltar2.setVisible(b);
        this.lbAno.setVisible(a);
        this.lbMes.setVisible(a);
        this.lbDia.setVisible(a);
        this.lbAnoFinal.setVisible(a);
        this.lbMesFinal.setVisible(a);
        this.lbDiaFinal.setVisible(a);
        this.lbDataFinal.setVisible(a);
        this.lbData.setVisible(a);
        this.lbLucro.setVisible(b);
        this.lbLucroNome.setVisible(b);
        this.tfDia.setVisible(a);
        this.tfMes.setVisible(a);
        this.tfAno.setVisible(a);
        this.tfMesFinal.setVisible(a);
        this.tfDiaFinal.setVisible(a);
        this.tfAnoFinal.setVisible(a);
    }

    @FXML
    private void acaoBotaoSend(ActionEvent event){
        try {
            LocalDate data = MetodosOutros.retornaData(this.tfDia.getText(), this.tfMes.getText(), this.tfAno.getText());
            LocalDate dataFinal = MetodosOutros.retornaData(this.tfDiaFinal.getText(), this.tfMesFinal.getText(), this.tfAnoFinal.getText());
            double lucro = this.fachada.calcularLucroGeradoPorPedidosEmDetermiadoPeriodoDeTempo(data, dataFinal);
            String lucroFormatado = String.valueOf(lucro);
            this.lbLucro.setText(lucroFormatado);
            mudarVisibilidades(false, true);
        } catch (RuntimeException erro){
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
        } catch (ObjetosInsuficientesErro objetosInsuficientesErro) {
            Main.chamarJanela("../gui/erros/TelaObjetosInsuficientesErro.fxml", 400, 150);
        } catch (NaoOuveLucroErro naoOuveLucroErro) {
            Main.chamarJanela("../gui/erros/TelaNaoOuveLucroErro.fxml", 400, 150);
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

    @FXML
    private void acaoBotaoVolta2(ActionEvent event){
        mudarVisibilidades(true, false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
