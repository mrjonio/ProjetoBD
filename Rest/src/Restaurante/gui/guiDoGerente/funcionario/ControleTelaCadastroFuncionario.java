package Restaurante.gui.guiDoGerente.funcionario;


import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;
import Restaurante.excessoes.ObjetoExistencia.ExcecaoObjetoExistencia;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ParametroValidade.CampoVazioErro;
import Restaurante.excessoes.ParametroValidade.ExceptionParametro;
import Restaurante.excessoes.ParametroValidade.ParametroInvalidoErro;
import Restaurante.excessoes.ParametroValidade.PessoaMenorDeIdadeErro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
import Restaurante.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private Button btSend, btSair, btNormal;

    @FXML
    private Label lbNome, lbCpf, lbIdade, lbSexo, lbFuncao, lbSalario, lbSenha;

    @FXML
    private Pane pane;

    @FXML
    private TextField tfNome, tfCpf, tfSexo, tfFuncao, tfIdade, tfSalario, tfGerente;

    private void setarVisibilidade(boolean a, boolean b){
        this.lbFuncao.setVisible(a);
        this.tfFuncao.setVisible(a);
        this.btNormal.setVisible(b);
        this.lbSenha.setVisible(b);
        this.tfGerente.setVisible(b);
        this.btSend.setVisible(a);
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
            Funcionario funcionarioNormal = new Funcionario(nome, cpf, idade, sexo, funcao, salario);
            funcionarioNormal.eValido();
            this.fachada.cadastrarUmFuncionario(funcionarioNormal);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sucesso");
            alert.setContentText("Cadastro Completo!");
            alert.showAndWait();
        } catch (NumberFormatException n){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Erro");
            alert.setContentText("Não use letras ou virgula no salário!");
            alert.showAndWait();
        } catch (RuntimeException erro) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Erro");
            alert.setContentText("Letra no lugar de número");
            alert.showAndWait();
        } catch (ExceptionParametro p) {
            p.alertar();
        } catch (ExcecaoObjetoExistencia e) {
           e.alertar();
        }
    }

    @FXML
    private void acaoBotaoSair(ActionEvent event){
        Main.chamarJanela("../gui/guiDoGerente/funcionario/TelaFuncionario.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
