package Restaurante.gui.remocao;

import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.excessoes.ObjetoEmUsoErro;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaDeletarClientes implements Initializable {
    private IFachada fachada;

    public ControleTelaDeletarClientes(){
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
    private void acaoBotaoSendPessoa(ActionEvent event) {
        try {
            boolean existeReservaNoNome = this.fachada.verificarExistenciaDeUmaReserva(this.tfPessoa.getText());
            if (existeReservaNoNome) {
                Pessoa donoDaReserva = this.fachada.buscarUmCliente(this.tfPessoa.getText());
                fachada.cancelarOuDeletarUmaReserva(donoDaReserva);
                fachada.deletarUmCliente(this.tfPessoa.getText());
                Main.chamarJanela("../gui/outros/TelaRemovidoComSucesso.fxml", 400, 150);
            } else {
                fachada.deletarUmCliente(this.tfPessoa.getText());
                Main.chamarJanela("../gui/outros/TelaRemovidoComSucesso.fxml", 400, 150);
            }
            } catch(ObjetoNaoExisteErro objetoNaoExisteErro){
                Main.chamarJanela("../gui/erros/TelaPessoaNaoExisteErro.fxml", 400, 150);
            } catch(ObjetoEmUsoErro objetoEmUsoErro){
                Main.chamarJanela("../gui/erros/TelaObjetoEmUsoErro.fxml", 400, 150);
            }
    }

    @FXML
    private void acaoBotaoCancelar(ActionEvent event){
        Main.chamarJanela("../gui/objetos/TelaCliente.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
