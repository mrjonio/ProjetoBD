package Restaurante.gui.guiDoGerente.funcionario;

import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;
import Restaurante.excessoes.ObjetoExistencia.ExcecaoObjetoExistencia;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.excessoes.ParametroValidade.ExceptionParametro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
import Restaurante.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaMudancaFuncionario implements Initializable {
    private IFachadaGerente fachada;

    public ControleTelaMudancaFuncionario(){
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private ImageView imgTop;

    @FXML
    private Button btSendFuncionario, btSendGerente, btSair, btVtGerente;

    @FXML
    private Label lbNome, lbCpf, lbIdade, lbSexo, lbFuncao, lbSalario, lbCpfAntigo;

    @FXML
    private Pane pane;

    @FXML
    private TextField tfNome, tfCpf, tfSexo, tfFuncao, tfIdade, tfSalario, tfSenha, tfCpfAntigo;

    @FXML
    private Tooltip ttGerente, ttNormal;


    private void alternarVisibilidade(boolean a, boolean b){
        lbNome.setVisible(b);
        lbCpf.setVisible(b);
        lbIdade.setVisible(b);
        lbSexo.setVisible(b);
        lbSalario.setVisible(b);
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
    private void acaoBotaoSendFuncionario(ActionEvent event){
        String nome = tfNome.getText();
        String cpf = tfCpfAntigo.getText();
        String sexo = tfSexo.getText();
        String funcao = tfFuncao.getText();
        try{
            double salario = Double.parseDouble(tfSalario.getText());
            int idade = Integer.parseInt(tfIdade.getText());
            Funcionario funcionario = new Funcionario(nome, cpf, idade, sexo, funcao, salario);
            funcionario.eValido();
            this.fachada.alterarAtributosDeUmFuncionario(funcionario, tfCpfAntigo.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sucesso");
            alert.setContentText("Atualizado com sucesso Completo!");
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
    private void acaoBotaoCancelar(ActionEvent event){
        Main.chamarJanela("../gui/guiDoGerente/funcionario/TelaFuncionario.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
