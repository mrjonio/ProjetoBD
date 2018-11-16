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

public class ControleTelaPrincipal implements Initializable {
    private IFachadaGerente fachada;

    public ControleTelaPrincipal(){
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private Button btFun, btCliente, btMesa, btPedido, btReserva, btLogoff, btSair, btCardapio;

    @FXML
    private Label lbObj, lbPessoa, lbAbs;

    @FXML
    private Pane pane;

    @FXML
    private ImageView imgTop;


    @FXML
    private void acaoBotaoCliente (ActionEvent event) {
        Main.chamarJanela("../gui/objetos/TelaCliente.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoFuncionario (ActionEvent event) {
        Main.chamarJanela("../gui/objetos/TelaFuncionario.fxml",711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoMesa (ActionEvent event) {
        Main.chamarJanela("../gui/objetos/TelaMesas.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoCardapio (ActionEvent event) {
        Main.chamarJanela("../gui/objetos/TelaCardapio.fxml",711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoPedido (ActionEvent event) {
        Main.chamarJanela("../gui/objetos/TelaPedidos.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }
    @FXML
    private void acaoBotaoReserva (ActionEvent event) {
        Main.chamarJanela("../gui/objetos/TelaReservas.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }
    @FXML
    private void acaoBotaoLogoff (ActionEvent event) {
        Main.chamarJanela("../gui/objetos/TelaLogin.fxml", 711, 480);
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
