package Restaurante.gui.listagens;

import Restaurante.entidade.abstrato.Reserva;
import Restaurante.entidade.pessoas.Pessoa;
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

public class ControleTelaListarReservas implements Initializable {
    private List listaObjetos;
    private ObservableList listaObservavel;
    private IFachada fachada;

    public ControleTelaListarReservas() {
        this.listaObjetos = new ArrayList<>();
        this.fachada = Fachada.getInstance();
    }

    @FXML
    private ListView<Pessoa> listView;

    @FXML
    private Button botaoDeAcao, btVoltar;

    @FXML
    private Label labelDeAcao;

    @FXML
    private ImageView imgTop;

    @FXML
    private Pane pane;

    private Stage tela;

    @FXML
    private void acaoBotaoListarReservas(ActionEvent evento) {
        try {
            listarReservas();
            listView.setItems(listaObservavel);
            listView.setVisible(true);
            botaoDeAcao.setVisible(false);
            labelDeAcao.setVisible(false);
        } catch (ObjetosInsuficientesErro objetosInsuficientesErro) {
            Main.chamarJanela("../gui/erros/TelaObjetosInsuficientesErro.fxml", 400, 150);
        } catch (RepositorioVazioErro repositorioVazioErro) {
            Main.chamarJanela("../gui/erros/TelaRepositorioVazioErro.fxml", 400, 150);
        }
    }

    private void listarReservas() throws ObjetosInsuficientesErro, RepositorioVazioErro {
        Reserva[] reservas;
        reservas = this.fachada.gerarVetorDeReservas();
        Collections.addAll(listaObjetos, reservas);
        listaObservavel = FXCollections.observableArrayList(listaObjetos);
    }
    @FXML
    private void acaoBotaoCancelar(ActionEvent event){
        Main.chamarJanela("../gui/objetos/TelaReservas.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}