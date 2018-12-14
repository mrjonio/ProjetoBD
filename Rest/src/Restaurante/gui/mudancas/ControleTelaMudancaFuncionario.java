package Restaurante.gui.mudancas;

import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;
import Restaurante.excessoes.ObjetoExistencia.ExcecaoObjetoExistencia;
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

public class ControleTelaMudancaFuncionario implements Initializable {
    private IFachadaGerente fachada;

    public ControleTelaMudancaFuncionario(){
        this.fachada = Fachada.getInstance();
    }

    @FXML
    private ImageView imgTop;

    @FXML
    private Button btSendFuncionario, btSair, btVtNormal;

    @FXML
    private Label lbNome, lbCpf, lbIdade, lbSexo, lbFuncao, lbSalario, lbCpfAntigo;

    @FXML
    private Pane pane;

    @FXML
    private TextField tfNome, tfCpf, tfSexo, tfFuncao, tfIdade, tfSalario, tfCpfAntigo;




    @FXML
    private void acaoBotaoSendFuncionario(ActionEvent event){
        String nome = tfNome.getText();
        String cpf = tfCpf.getText();
        String sexo = tfSexo.getText();
        String funcao = tfFuncao.getText();
        try{
            double salario = Double.parseDouble(tfSalario.getText());
            int idade = Integer.parseInt(tfIdade.getText());
            Funcionario funcionario = new Funcionario(nome, cpf, idade, sexo, funcao, salario);
            this.fachada.alterarAtributosDeUmFuncionario(funcionario, tfCpfAntigo.getText());
            Main.chamarJanela("../gui/outros/TelaAlteradoComSucesso.fxml", 400, 150);
        } catch (RuntimeException erro) {
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
        } catch (ExcecaoObjetoExistencia objetoNaoExisteErro) {
            objetoNaoExisteErro.mostrarErro();
        }
    }


    @FXML
    private void acaoBotaoCancelar(ActionEvent event){
        Main.chamarJanela("../gui/objetos/TelaFuncionario.fxml", 711, 480);
        Stage tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
