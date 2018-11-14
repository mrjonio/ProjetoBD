package Restaurante.gui.buscas;

import Restaurante.entidade.abstrato.Reserva;
import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.entidade.pessoas.cliente.Cliente;
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

public class ControleTelaBuscaReserva implements Initializable {
    private IFachada fachada;

    public ControleTelaBuscaReserva(){
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


    private void mudarVisibilidades(boolean a, boolean b){
        this.btCancelar.setVisible(a);
        this.lbCpfBusca.setVisible(a);
        this.tfNomeBuscado.setVisible(a);
        this.btSend.setVisible(a);
        this.imgPhoto.setVisible(b);
        this.btVoltar.setVisible(b);
        this.lbNomeClientePego.setVisible(b);
        this.lbCpfPego.setVisible(b);
        this.lbNome.setVisible(b);
        this.lbCpf.setVisible(b);
        this.lbSexoPego.setVisible(b);
        this.lbSexo.setVisible(b);
    }

    @FXML
    private void acaoBotaoVoltar(ActionEvent event){
        mudarVisibilidades(true, false);
    }

    @FXML
    private void acaoBotaoSend(ActionEvent event) {
        try {
            Pessoa clienteQueReservou = this.fachada.buscarUmCliente(this.tfNomeBuscado.getText());
            Reserva reserva = this.fachada.buscarUmaReserva(clienteQueReservou);
            this.lbNomeClientePego.setText(reserva.getClienteQueReservou().getNome());
            String dataHoraFormatado = reserva.getDataHora().toString();
            this.lbSexoPego.setText(dataHoraFormatado);
            this.lbCpfPego.setText(reserva.getClienteQueReservou().getCpf());
            mudarVisibilidades(false, true);
        } catch (ObjetoNaoExisteErro objetoNaoExisteErro) {
            Main.chamarJanela("../gui/erros/TelaPessoaNaoExisteErro.fxml", 400, 150);
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
