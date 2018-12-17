package Restaurante.gui.mudancas;

import Restaurante.entidade.abstrato.Reserva;
import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.entidade.pessoas.cliente.Cliente;
import Restaurante.excessoes.LetraNoLugarDeNumeroErro;
import Restaurante.excessoes.ObjetoJaExisteErro;
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

public class ControleTelaMudancaCliente implements Initializable {
    private IFachada fachada;

    public ControleTelaMudancaCliente(){
        this.fachada = Fachada.getInstance();
    }


    private Stage tela;

    @FXML
    private ImageView imgTop;

    @FXML
    private Button btSend, btSair, btAjuda;

    @FXML
    private Label lbNome, lbCpf, lbIdade, lbSexo, lbPreferencia, lbCpfAtual;

    @FXML
    private Pane pane;

    @FXML
    private TextField tfNome, tfCpf, tfSexo, tfPreferencia, tfIdade, tfCpfAtual;

    @FXML
    private Tooltip ttAjuda;


    @FXML
    private void acaoBotaoSend(ActionEvent event){
        String novoNome = this.tfNome.getText();
        String novoCpf = this.tfCpf.getText();
        String novoSexo = this.tfSexo.getText();
        String novaPreferencia = this.tfPreferencia.getText();
        boolean existeReservaNoNomeDoCliente = this.fachada.verificarExistenciaDeUmaReserva(this.tfCpfAtual.getText());
        try {
            if (existeReservaNoNomeDoCliente) {
                Pessoa donoDaReserva = this.fachada.buscarUmCliente(this.tfCpf.getText());
                Reserva reserva = this.fachada.buscarUmaReserva(donoDaReserva);
                int idade = Integer.parseInt(tfIdade.getText());
                Pessoa novosAtributos = new Cliente(novoNome, novoCpf, idade, novoSexo, novaPreferencia);
                reserva.setClienteQueReservou(novosAtributos);
                this.fachada.alterarAtributosDeUmaReserva(reserva, donoDaReserva);
                fachada.alterarAtributosDeUmCliente(novosAtributos, this.tfCpfAtual.getText());
                Main.chamarJanela("../gui/outros/TelaAlteradoComSucesso.fxml", 400, 150);
            } else {
                int idade = Integer.parseInt(tfIdade.getText());
                Pessoa novosAtributos = new Cliente(novoNome, novoCpf, idade, novoSexo, novaPreferencia);
                fachada.alterarAtributosDeUmCliente(novosAtributos, this.tfCpfAtual.getText());
                Main.chamarJanela("../gui/outros/TelaAlteradoComSucesso.fxml", 400, 150);
            }
            } catch (RuntimeException erro) {
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
        } catch (ObjetoNaoExisteErro objetoNaoExisteErro) {
            Main.chamarJanela("../gui/erro/TelaPessoaNaoExisteErro.fxml", 400, 150);
        } catch (ObjetoJaExisteErro objetoJaExisteErro) {
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
