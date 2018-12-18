package Restaurante.gui.guiDoGerente.funcionario;

import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
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

public class ControleTelaBuscaFuncionario implements Initializable {
    private IFachadaGerente fachada;

    public ControleTelaBuscaFuncionario(){
        this.fachada = Fachada.getInstance();
    }

    @FXML
    private Label lbCpfBusca, lbNome, lbCpf, lbIdade, lbSexo, lbFuncao, lbNomeClientePego, lbCpfPego, lbIdadePega, lbSexoPego, lbFuncaoPega;

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
        this.lbIdadePega.setVisible(b);
        this.lbIdade.setVisible(b);
        this.lbSexoPego.setVisible(b);
        this.lbSexo.setVisible(b);
        this.lbFuncaoPega.setVisible(b);
        this.lbFuncao.setVisible(b);
    }

    @FXML
    private void acaoBotaoVoltar(ActionEvent event){
        mudarVisibilidades(true, false);
    }

    @FXML
    private void acaoBotaoSend(ActionEvent event) {
        String nomeDoPratoBuscado = this.tfNomeBuscado.getText();
        try {
            Funcionario clienteBuscado = (Funcionario) this.fachada.buscarUmFuncionario(this.tfNomeBuscado.getText());
            this.lbNomeClientePego.setText(clienteBuscado.getNome());
            String preco = String.valueOf(clienteBuscado.getIdade());
            this.lbIdadePega.setText(preco);
            this.lbFuncaoPega.setText(clienteBuscado.getFuncao());
            this.lbSexoPego.setText(clienteBuscado.getSexo());
            this.lbCpfPego.setText(clienteBuscado.getCpf());
            mudarVisibilidades(false, true);
        } catch (ObjetoNaoExisteErro objetoNaoExisteErro) {
            Main.chamarJanela("../gui/erros/TelaPessoaNaoExisteErro.fxml", 400, 150);
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
