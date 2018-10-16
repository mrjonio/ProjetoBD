package Restaurante.gui.listagens;

import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.entidade.pessoas.funcionario.Funcionario;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachada;
import Restaurante.main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ControleTelaListarFuncionarios implements Initializable {
    private List listaObjetos;
    private ObservableList listaObservavel;
    private IFachada fachada;

    public ControleTelaListarFuncionarios() {
        this.listaObjetos = new ArrayList<>();
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private ListView<Pessoa> listView;

    @FXML
    private Button botaoDeAcao, btVoltar, btCancelar;

    @FXML
    private Label labelDeAcao;

    @FXML
    private ImageView imgTop;

    @FXML
    private Pane pane;

    @FXML
    private void acaoBotaoListar(ActionEvent evento) {
        try {
            listarClientes();
            listView.setItems(listaObservavel);
            listView.setVisible(true);
            botaoDeAcao.setVisible(false);
            labelDeAcao.setVisible(false);
            btVoltar.setVisible(true);
            btCancelar.setVisible(false);
        } catch (ObjetosInsuficientesErro objetosInsuficientesErro) {
            Main.chamarJanela("../gui/erros/TelaObjetosInsuficientesErro.fxml", 400, 150);
        } catch (RepositorioVazioErro repositorioVazioErro) {
            Main.chamarJanela("../gui/erros/TelaRepositorioVazioErro.fxml", 400, 150);
        }
    }

    private void listarClientes() throws ObjetosInsuficientesErro, RepositorioVazioErro {
        Funcionario[] pessoas;
        pessoas = (Funcionario[]) this.fachada.gerarVetorDeFuncionarios();
        Collections.addAll(listaObjetos, pessoas);
        listaObservavel = FXCollections.observableArrayList(listaObjetos);
    }

    @FXML
    private void acaoBotaoVoltar(ActionEvent event){
        listView.setVisible(false);
        botaoDeAcao.setVisible(true);
        labelDeAcao.setVisible(true);
        btVoltar.setVisible(false);
        btCancelar.setVisible(true);
        listaObjetos.clear();
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