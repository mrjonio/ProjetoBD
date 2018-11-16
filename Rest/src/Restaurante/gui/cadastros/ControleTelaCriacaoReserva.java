package Restaurante.gui.cadastros;

import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.camadasDeNegocio.entidade.pessoas.Pessoa;
import Restaurante.excessoes.*;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ControleTelaCriacaoReserva implements Initializable {
    private IFachadaGerente fachada;

    public ControleTelaCriacaoReserva(){
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private Label lbDtHr, lbDia, lbMes, lbAno, lbHora, lbMinuto, lbCpf;

    @FXML
    private Button btSend, btCancelar;

    @FXML
    private ImageView imgTop;

    @FXML
    private Pane pane;

    @FXML
    private TextField tfDia, tfMes, tfAno, tfHora, tfMinuto, tfCpf;

    private Mesa mesaParaReserva(int index, LocalDateTime dataHora) throws RepositorioVazioErro, ObjetosInsuficientesErro {
        Mesa mesaQueSeraUsada = this.fachada.buscarUmaMesa(index);
        boolean mesaEstaDisponivel = this.fachada.verificarSeUmaMesaEstaDisponivelEmDeterminadoMomento(mesaQueSeraUsada, dataHora);
        if (!mesaEstaDisponivel){
            mesaQueSeraUsada = mesaParaReserva(++index, dataHora);
        }
        return mesaQueSeraUsada;
    }

    @FXML
    private void acaoBotaoSend(ActionEvent event){
        try {
            LocalDateTime datHoraReserva = MetodosOutros.retornaDataHora(tfDia.getText(), tfMes.getText(), tfAno.getText(), tfHora.getText(), tfMinuto.getText());
            Pessoa clienteQueReservou = this.fachada.buscarUmCliente(this.tfCpf.getText());
            Mesa mesaQueSeraUsada = mesaParaReserva(0, datHoraReserva);
            Reserva novaReserva = new Reserva(datHoraReserva, mesaQueSeraUsada, clienteQueReservou);
            fachada.cadastrarUmaNovaReserva(novaReserva);
            Main.chamarJanela("../gui/outros/TelaCadastradoComSucesso.fxml", 400, 150);
        } catch (RuntimeException erro) {
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
        } catch (ObjetoNaoExisteErro objetoNaoExisteErro) {
            Main.chamarJanela("../gui/erros/TelaPessoaNaoExisteErro.fxml", 400, 150);
        } catch (ObjetosInsuficientesErro objetosInsuficientesErro) {
            Main.chamarJanela("../gui/erros/TelaSemMesasDisponiveisErro.fxml", 400, 150);
        } catch (RepositorioVazioErro repositorioVazioErro) {
            Main.chamarJanela("../gui/erros/TelaSemMesasDisponiveisErro.fxml", 400, 150);
        } catch (ObjetoJaExisteErro objetoJaExisteErro) {
            Main.chamarJanela("../gui/erros/TelaReservaJaExisteErro.fxml", 400, 150);
        } catch (ClienteJaReservouErro clienteJaReservouErro) {
            Main.chamarJanela("../gui/erros/TelaClienteJaReservouErro.fxml", 400, 150);
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
