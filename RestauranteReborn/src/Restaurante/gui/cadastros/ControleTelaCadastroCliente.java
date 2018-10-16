package Restaurante.gui.cadastros;

import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.entidade.pessoas.cliente.Cliente;
import Restaurante.excessoes.*;
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

public class ControleTelaCadastroCliente implements Initializable {
    private IFachada fachada;

    public ControleTelaCadastroCliente(){
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private ImageView imgTop;

    @FXML
    private Button btSend, btSair;

    @FXML
    private Label lbNome, lbCpf, lbIdade, lbSexo, lbPreferencia;

    @FXML
    private Pane pane;

    @FXML
    private TextField tfNome, tfCpf, tfSexo, tfPreferencia, tfIdade;

    @FXML
    private void acaoBotaoSend(ActionEvent evento) {
        String nome = tfNome.getText();
        String cpf = tfCpf.getText();
        String sexo = tfSexo.getText();
        String preferencia = tfPreferencia.getText();
        try {
            int idade = Integer.parseInt(tfIdade.getText());
            Pessoa clienteQueSeraCadastrado = new Cliente(nome, cpf, idade, sexo, preferencia);
            boolean eValido = clienteQueSeraCadastrado.eValido();
            this.fachada.cadastrarUmCliente(clienteQueSeraCadastrado);
            Main.chamarJanela("../gui/outros/TelaCadastradoComSucesso.fxml", 400, 150);
        } catch (RuntimeException erro){
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
        }catch (ParametroInvalidoErro parametroInvalidoErro) {
            Main.chamarJanela("../gui/erros/TelaParametroInvalidoErro.fxml", 400, 150);
        } catch (PessoaMenorDeIdadeErro pessoaMenorDeIdadeErro) {
            Main.chamarJanela("../gui/erros/TelaPessoaMenorIdadeErro.fxml", 400, 150);
        } catch (CampoVazioErro campoVazioErro) {
            Main.chamarJanela("../gui/erros/TelaCampoVazioErro.fxml", 400, 150);
        } catch (ObjetoJaExisteErro objetoJaExisteErro) {
            Main.chamarJanela("../gui/erros/TelaClienteJaExisteErro.fxml", 400, 150);
        }
    }

    @FXML
    private void acaoBotaoSair(ActionEvent event){
        Main.chamarJanela("../gui/objetos/TelaCliente.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
