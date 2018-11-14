package Restaurante.gui.mudancas;

import Restaurante.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.excessoes.ObjetoNaoExisteErro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachada;
import Restaurante.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaMudancaPratoCardapio implements Initializable {
    private IFachada fachada;

    public ControleTelaMudancaPratoCardapio(){
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private Button btSend, btCancelar, btAjuda;

    @FXML
    private Label lbNome, lbPreco, lbNomeAtual;

    @FXML
    private Pane pane;

    @FXML
    private TextField tfNome, tfPreco, tfNomeAtual;

    @FXML
    private ImageView imgTop;

    @FXML
    private Tooltip ttAjuda;


    @FXML
    private void acaoBotaoSend(ActionEvent envent){
        String nomeAtual = this.tfNomeAtual.getText();
        String novoNome = this.tfNome.getText();
        try {
            double novoPreco = Double.parseDouble(this.tfPreco.getText());
            PratoCardapio novosAtributos = new PratoCardapio(novoNome, novoPreco);
            this.fachada.alterarAtributosDeUmPratoDoCardapio(novosAtributos, nomeAtual);
            Main.chamarJanela("../gui/outros/TelaAlteradoComSucesso.fxml", 400, 150);
        } catch (RuntimeException erro) {
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
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
