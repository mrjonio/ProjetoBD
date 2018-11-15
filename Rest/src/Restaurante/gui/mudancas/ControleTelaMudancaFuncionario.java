package Restaurante.gui.mudancas;

import Restaurante.camadasDeNegocio.entidade.pessoas.Pessoa;
import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
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

public class ControleTelaMudancaFuncionario implements Initializable {
    private IFachada fachada;

    public ControleTelaMudancaFuncionario(){
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private ImageView imgTop;

    @FXML
    private Button btSendFuncionario, btSendGerente, btSair, btGerente, btNormal, btVtGerente, btVtNormal;

    @FXML
    private Label lbNome, lbCpf, lbIdade, lbSexo, lbFuncao, lbSalario, lbSenha, lbEscolha, lbCpfAntigo;

    @FXML
    private Pane pane;

    @FXML
    private TextField tfNome, tfCpf, tfSexo, tfFuncao, tfIdade, tfSalario, tfSenha, tfCpfAntigo;

    @FXML
    private Tooltip ttGerente, ttNormal;


    private void alternarVisibilidade(boolean a, boolean b){
        btGerente.setVisible(a);
        btNormal.setVisible(a);
        lbNome.setVisible(b);
        lbCpf.setVisible(b);
        lbIdade.setVisible(b);
        lbSexo.setVisible(b);
        lbSalario.setVisible(b);
        lbEscolha.setVisible(a);
        tfNome.setVisible(b);
        tfCpf.setVisible(b);
        tfSexo.setVisible(b);
        tfIdade.setVisible(b);
        tfSalario.setVisible(b);
        btSair.setVisible(a);
        lbCpfAntigo.setVisible(b);
        tfCpfAntigo.setVisible(b);
    }


    @FXML
    private void escolhaGerente(ActionEvent event){
        alternarVisibilidade(false, true);
        btSendGerente.setVisible(true);
        btVtGerente.setVisible(true);
        lbSenha.setVisible(true);
        tfSenha.setVisible(true);
    }

    @FXML
    private void escolhaNormal(ActionEvent event){
        alternarVisibilidade(false, true);
        btSendFuncionario.setVisible(true);
        btVtNormal.setVisible(true);
        lbFuncao.setVisible(true);
        tfFuncao.setVisible(true);
    }
    @FXML
    private void voltarNormal(ActionEvent event){
        alternarVisibilidade(true, false);
        btSendFuncionario.setVisible(false);
        btVtNormal.setVisible(false);
        lbFuncao.setVisible(false);
        tfFuncao.setVisible(false);
    }

    @FXML
    private void voltarGerente(ActionEvent event){
        alternarVisibilidade(true, false);
        btSendGerente.setVisible(false);
        btVtGerente.setVisible(false);
        lbSenha.setVisible(false);
        tfSenha.setVisible(false);
    }

    @FXML
    private void acaoBotaoSendFuncionario(ActionEvent event){
        String nome = tfNome.getText();
        String cpf = tfCpf.getText();
        String sexo = tfSexo.getText();
        String funcao = tfFuncao.getText();
        try{
            double salario = Double.parseDouble(tfSalario.getText());
            int idade = Integer.parseInt(tfIdade.getText());
            Pessoa funcionario = new Funcionario(nome, cpf, idade, sexo, funcao, salario);
            this.fachada.alterarAtributosDeUmFuncionario(funcionario, tfCpfAntigo.getText());
            Main.chamarJanela("../gui/outros/TelaAlteradoComSucesso.fxml", 400, 150);
        } catch (RuntimeException erro) {
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
        } catch (ObjetoNaoExisteErro objetoNaoExisteErro) {
            Main.chamarJanela("../gui/erros/TelaPessoaNaoExisteErro.fxml", 400, 150);
        }
    }

    @FXML
    private void acaoBotaoSendGerente(ActionEvent event){
        String nome = tfNome.getText();
        String cpf = tfCpf.getText();
        String sexo = tfSexo.getText();
        String senha = tfSenha.getText();
        try{
            double salario = Double.parseDouble(tfSalario.getText());
            int idade = Integer.parseInt(tfIdade.getText());
            Pessoa funcionario = new Gerente(nome, cpf, idade, sexo, salario, senha);
            this.fachada.alterarAtributosDeUmFuncionario(funcionario, tfCpfAntigo.getText());
            Main.chamarJanela("../gui/outros/TelaAlteradoComSucesso.fxml", 400, 150);
        } catch (RuntimeException erro) {
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
        } catch (ObjetoNaoExisteErro objetoNaoExisteErro) {
            Main.chamarJanela("../gui/erros/TelaPessoaNaoExisteErro.fxml", 400, 150);
        }
    }

    @FXML
    private void acaoBotaoCancelar(ActionEvent event){
        Main.chamarJanela("../gui/objetos/TelaFuncionario.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
