package Restaurante.gui.objetos;


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

import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Stage;

public class ControleTelaLogin implements Initializable{
    private IFachadaGerente fachada;

    public ControleTelaLogin(){
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private Label lbCpf, lbSenha;

    @FXML
    private Button btLogin;

    @FXML
    private TextField txCpf, txSenha;

    @FXML
    private ImageView imgTop;

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    private void acaoBotaoLogin (ActionEvent event) {
        String nomeDoGerente = this.txCpf.getText();
        try {
            Funcionario possivelGerente = this.fachada.buscarUmFuncionario(nomeDoGerente);
            Main.chamarJanela("../gui/objetos/TelaPrincipal.fxml", 780, 411);
            this.tela = (Stage) this.pane.getScene().getWindow();
            tela.close();
        } catch (ObjetoNaoExisteErro objetoNaoExisteErro) {
            Main.chamarJanela("../gui/erros/TelaPessoaNaoExisteErro.fxml", 400, 150);
        }
    }

}
