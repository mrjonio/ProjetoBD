package Restaurante.gui.cadastros;

import Restaurante.entidade.abstrato.Pedido;
import Restaurante.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.excessoes.ObjetoNaoExisteErro;
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

public class ControleTelaCadastroPedidos implements Initializable {
    private IFachada fachada;

    public ControleTelaCadastroPedidos(){
        fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private Pane pane;

    @FXML
    private Label lbDia, lbMes, lbAno, lbData, lbCpf, lbNome;

    @FXML
    private TextField tfDia, tfMes, tfAno, tfCpf, tfNome;

    @FXML
    private Button btSend, btVoltar, btSair;

    @FXML
    private void acaoBotaoSend(ActionEvent event){
        try {
            LocalDate data = MetodosOutros.retornaData(this.tfDia.getText(), this.tfMes.getText(), this.tfAno.getText());
            PratoCardapio prato = this.fachada.buscarUmPratoDoCardapio(this.tfNome.getText());
            try {
                Pessoa cliente = this.fachada.buscarUmCliente(this.tfCpf.getText());
                Pedido pedido = new Pedido(data);
                pedido.setPratoPedido(prato);
                pedido.setClienteQuePediu(cliente);
                this.fachada.armazenarUmPedido(pedido);
                Main.chamarJanela("../gui/outros/TelaCadastradoComSucesso.fxml", 400, 150);
            } catch (ObjetoNaoExisteErro ob){
                Main.chamarJanela("../gui/erros/TelaPessoaNaoExisteErro.fxml", 400, 150);
            }
        } catch (RuntimeException erro) {
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
        } catch (ObjetoNaoExisteErro objetoNaoExisteErro) {
            Main.chamarJanela("../gui/erros/TelaPratoNaoExisteErro.fxml", 400, 150);
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
