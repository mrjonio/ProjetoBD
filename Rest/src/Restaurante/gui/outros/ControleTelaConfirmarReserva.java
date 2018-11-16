package Restaurante.gui.outros;

import Restaurante.camadasDeNegocio.entidade.pessoas.Pessoa;
import Restaurante.excessoes.ObjetoExistencia.ObjetoEmUsoErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.excessoes.ReservaExpiradaErro;
import Restaurante.excessoes.ReservaJaConfirmadaErro;
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

public class ControleTelaConfirmarReserva implements Initializable {
    private IFachadaGerente fachada;

    public ControleTelaConfirmarReserva(){
        this.fachada = Fachada.getInstance();
    }

    @FXML
    private Label lbCpfBusca, lbNome, lbCpf, lbSexo, lbNomeClientePego, lbCpfPego, lbSexoPego;

    @FXML
    private Button btSend, btCancelar, btVoltar;

    @FXML
    private ImageView imgPhoto, imgTop;

    @FXML
    private Pane pane;

    private Stage tela;

    @FXML
    private TextField tfNomeBuscado;

    @FXML
    private void acaoBotaoSend(ActionEvent event) {
        try {
            Pessoa clienteQueReservou = this.fachada.buscarUmCliente(this.tfNomeBuscado.getText());
            this.fachada.confirmarUmaReserva(clienteQueReservou);
            Main.chamarJanela("../gui/outros/TelaAlteradoComSucesso.fxml", 400, 150);
        } catch (ObjetoNaoExisteErro objetoNaoExisteErro) {
            Main.chamarJanela("../gui/erros/TelaPessoaNaoExisteErro.fxml", 400, 150);
        } catch (ReservaJaConfirmadaErro reservaJaConfirmadaErro) {
            Main.chamarJanela("../gui/erros/TelaObjetoEmUsoErro.fxml", 400, 150);
        } catch (ReservaExpiradaErro reservaExpiradaErro) {
            Pessoa clienteQueReservou = null;
            try {
                clienteQueReservou = this.fachada.buscarUmCliente(this.tfNomeBuscado.getText());
                this.fachada.cancelarOuDeletarUmaReserva(clienteQueReservou);
                Main.chamarJanela("../gui/erros/TelaReservaNaoExisteErro.fxml", 400, 150);
            } catch (ObjetoNaoExisteErro objetoNaoExisteErro) {
            } catch (ObjetoEmUsoErro objetoEmUsoErro) {
            }
        }
    }

    @FXML
    private void acaoBotaoCancelar(ActionEvent event){
        Main.chamarJanela("../gui/objetos/TelaReservas.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
