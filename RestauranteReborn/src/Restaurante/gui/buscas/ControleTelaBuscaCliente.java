package Restaurante.gui.buscas;

import Restaurante.entidade.pessoas.cliente.Cliente;
import Restaurante.excessoes.NaoOuveLucroErro;
import Restaurante.excessoes.ObjetoNaoExisteErro;
import Restaurante.excessoes.ObjetosInsuficientesErro;
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

public class ControleTelaBuscaCliente implements Initializable {
    private IFachada fachada;

    public ControleTelaBuscaCliente(){
        this.fachada = Fachada.getInstance();
    }

    @FXML
    private Label  lbCpfBusca, lbNome, lbCpf, lbIdade, lbSexo, lbPreferencia, lbNomeClientePego, lbCpfPego, lbIdadePega, lbSexoPego, lbPreferenciaPega, lbGasto, lbGastoPego;

    @FXML
    private Button btSend, btCancelar, btVoltar;

    @FXML
    private ImageView imgPhoto, imgTop;

    @FXML
    private Pane pane;

    private Stage tela;

    @FXML
    private TextField tfNomeBuscado;

    /*
    * Mudar visibilidades de componentes de tela quando clciar em um determinado botão, esse metodo funciona de forma igual
    * em todas as classes que ele é implementado
     */


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
        this.lbIdadePega.setVisible(b);
        this.lbIdade.setVisible(b);
        this.lbSexoPego.setVisible(b);
        this.lbSexo.setVisible(b);
        this.lbPreferenciaPega.setVisible(b);
        this.lbPreferencia.setVisible(b);
        this.lbGasto.setVisible(b);
        this.lbGastoPego.setVisible(b);
    }

    @FXML
    private void acaoBotaoVoltar(ActionEvent event){
        mudarVisibilidades(true, false);
    }


    @FXML
    private void acaoBotaoSend(ActionEvent event) {
        String nomeDoPratoBuscado = this.tfNomeBuscado.getText();
        try {
            Cliente clienteBuscado = (Cliente) this.fachada.buscarUmCliente(this.tfNomeBuscado.getText());
            this.lbNomeClientePego.setText(clienteBuscado.getNome());
            String preco = String.valueOf(clienteBuscado.getIdade());
            this.lbIdadePega.setText(preco);
            this.lbPreferenciaPega.setText(clienteBuscado.getPreferencia());
            this.lbSexoPego.setText(clienteBuscado.getSexo());
            this.lbCpfPego.setText(clienteBuscado.getCpf());
            mudarVisibilidades(false, true);
            double gasto = this.fachada.calcularLucroGeradoPorUmCliente(clienteBuscado);
            this.lbGastoPego.setText(String.valueOf(gasto));
        } catch (ObjetoNaoExisteErro objetoNaoExisteErro) {
            Main.chamarJanela("../gui/erros/TelaPessoaNaoExisteErro.fxml", 400, 150);
        } catch (NaoOuveLucroErro naoOuveLucroErro) {
        } catch (ObjetosInsuficientesErro objetosInsuficientesErro) {
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
