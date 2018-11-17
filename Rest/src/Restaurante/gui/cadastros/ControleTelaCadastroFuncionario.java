package Restaurante.gui.cadastros;

import Restaurante.camadasDeNegocio.entidade.pessoas.Pessoa;
import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ParametroValidade.CampoVazioErro;
import Restaurante.excessoes.ParametroValidade.LetraNoLugarDeNumeroErro;
import Restaurante.excessoes.ParametroValidade.ParametroInvalidoErro;
import Restaurante.excessoes.ParametroValidade.PessoaMenorDeIdadeErro;
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

public class ControleTelaCadastroFuncionario implements Initializable {
    private IFachadaGerente fachada;

    public ControleTelaCadastroFuncionario(){
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private ImageView imgTop;

    @FXML
    private Button btSend, btSair, btGerente, btNormal, btSendGerente;

    @FXML
    private Label lbNome, lbCpf, lbIdade, lbSexo, lbFuncao, lbSalario, lbSenha;

    @FXML
    private Pane pane;

    @FXML
    private TextField tfNome, tfCpf, tfSexo, tfFuncao, tfIdade, tfSalario, tfGerente;

    private void setarVisibilidade(boolean a, boolean b){
        this.btGerente.setVisible(a);
        this.lbFuncao.setVisible(a);
        this.tfFuncao.setVisible(a);
        this.btNormal.setVisible(b);
        this.lbSenha.setVisible(b);
        this.tfGerente.setVisible(b);
        this.btSend.setVisible(a);
        this.btSendGerente.setVisible(b);
    }

    @FXML
    private void acaoBotaoGerente(ActionEvent event){
        setarVisibilidade(false, true);
    }

    @FXML
    private void acaoBotaoNormal(ActionEvent event){
        setarVisibilidade(true, false);
    }

    @FXML
    private void acaoBotaoSend(ActionEvent event){
        String nome = this.tfNome.getText();
        String cpf = this.tfCpf.getText();
        String sexo = this.tfSexo.getText();
        String funcao = this.tfFuncao.getText();
        try {
            double salario = Double.parseDouble(this.tfSalario.getText());
            int idade = Integer.parseInt(this.tfIdade.getText());
            Pessoa funcionarioNormal = new Funcionario(nome, cpf, idade, sexo, funcao, salario);
            funcionarioNormal.eValido();
            this.fachada.cadastrarUmFuncionario(funcionarioNormal);
            Main.chamarJanela("../gui/outros/TelaCadastradoComSucesso.fxml", 400, 150);
        } catch (RuntimeException erro) {
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
        } catch (ParametroInvalidoErro parametroInvalidoErro) {
            Main.chamarJanela("../gui/erros/TelaParametroInvalidoErro.fxml", 400, 150);
        } catch (CampoVazioErro campoVazioErro) {
            Main.chamarJanela("../gui/erros/TelaCampoVazioErro.fxml", 400, 150);
        } catch (PessoaMenorDeIdadeErro pessoaMenorDeIdadeErro) {
            Main.chamarJanela("../gui/erros/TelaMenorIdadeErro.fxml", 400, 150);
        } catch (ObjetoJaExisteErro objetoJaExisteErro) {
           Main.chamarJanela("../gui/erros/TelaFuncionarioJaExisteErro.fxml", 400, 150);
        }
    }

    @FXML
    private void acaoBotaoSendGerente(ActionEvent event){
        String nome = this.tfNome.getText();
        String cpf = this.tfCpf.getText();
        String sexo = this.tfSexo.getText();
        String senha = this.tfGerente.getText();
        try {
            double salario = Double.parseDouble(this.tfSalario.getText());
            int idade = Integer.parseInt(this.tfIdade.getText());
            Pessoa funcionarioNormal = new Gerente(nome, cpf, idade, sexo, salario, senha);
            funcionarioNormal.eValido();
            this.fachada.cadastrarUmFuncionario(funcionarioNormal);
            Main.chamarJanela("../gui/outros/TelaCadastradoComSucesso.fxml", 400, 150);
        } catch (LetraNoLugarDeNumeroErro erro) {
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
        } catch (ParametroInvalidoErro parametroInvalidoErro) {
            Main.chamarJanela("../gui/erros/TelaParametroInvalidoErro.fxml", 400, 150);
        } catch (CampoVazioErro campoVazioErro) {
            Main.chamarJanela("../gui/erros/TelaCampoVazioErro.fxml", 400, 150);
        } catch (PessoaMenorDeIdadeErro pessoaMenorDeIdadeErro) {
            Main.chamarJanela("../gui/erros/TelaMenorIdadeErro.fxml", 400, 150);
        } catch (ObjetoJaExisteErro objetoJaExisteErro) {
            Main.chamarJanela("../gui/erros/TelaFuncionarioJaExisteErro.fxml", 400, 150);
        }
    }

    @FXML
    private void acaoBotaoSair(ActionEvent event){
        Main.chamarJanela("../gui/objetos/TelaFuncionario.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
