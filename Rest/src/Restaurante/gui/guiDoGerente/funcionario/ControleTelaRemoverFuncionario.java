package Restaurante.gui.guiDoGerente.funcionario;

import Restaurante.excessoes.ObjetoExistencia.ExcecaoObjetoExistencia;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
import Restaurante.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaRemoverFuncionario implements Initializable {
    private IFachadaGerente fachada;

    public ControleTelaRemoverFuncionario(){
        this.fachada = Fachada.getInstance();
    }


    private Stage tela;

    @FXML
    private Button btSendPessoa, btCancelar;

    @FXML
    private Label lbPessoa;

    @FXML
    private TextField tfPessoa;

    @FXML
    private ImageView imgTop;

    @FXML
    private Pane pane;


    @FXML
    private void acaoBotaoSendPessoa(ActionEvent event){
        try {
            fachada.deletarUmFuncionario(this.tfPessoa.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sucesso");
            alert.setContentText("Removido com sucesso Completo!");
            alert.showAndWait();
        } catch (ExcecaoObjetoExistencia objetoNaoExisteErro) {
            objetoNaoExisteErro.alertar();
        }
    }

    @FXML
    private void acaoBotaoCancelar(ActionEvent event){
        Main.chamarJanela("../gui/guiDoGerente/funcionario/TelaFuncionario.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
