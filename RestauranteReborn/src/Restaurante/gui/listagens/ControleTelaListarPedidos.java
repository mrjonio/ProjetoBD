package Restaurante.gui.listagens;

import Restaurante.entidade.abstrato.Pedido;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachada;
import Restaurante.main.Main;
import Restaurante.main.MetodosOutros;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ControleTelaListarPedidos implements Initializable {
    private List listaObjetos;
    private ObservableList listaObservavel;
    private IFachada fachada;

    public ControleTelaListarPedidos() {
        this.listaObjetos = new ArrayList<>();
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private ListView<Pedido> listView;

    @FXML
    private Button botaoDeAcao, btVoltar, btCancelar;

    @FXML
    private Label labelDeAcao, lbDia, lbMes, lbAno, lbData, lbDiaFinal, lbMesFinal, lbAnoFinal, lbDataFinal;

    @FXML
    private ImageView imgTop;

    @FXML
    private Pane pane;

    @FXML
    private TextField tfDia, tfMes, tfAno, tfDiaFinal, tfMesFinal, tfAnoFinal;

    private void mudarVisibilidades(boolean a, boolean b){
        listView.setItems(listaObservavel);
        listView.setVisible(b);
        botaoDeAcao.setVisible(a);
        labelDeAcao.setVisible(a);
        btVoltar.setVisible(b);
        btCancelar.setVisible(a);
        lbDia.setVisible(a);
        lbMes.setVisible(a);
        lbAno.setVisible(a);
        lbDiaFinal.setVisible(a);
        lbMesFinal.setVisible(a);
        lbAnoFinal.setVisible(a);
        lbData.setVisible(a);
        lbDataFinal.setVisible(a);
        tfDia.setVisible(a);
        tfMes.setVisible(a);
        tfAno.setVisible(a);
        tfDiaFinal.setVisible(a);
        tfMesFinal.setVisible(a);
        tfAnoFinal.setVisible(a);
    }

    @FXML
    private void acaoBotaoListar(ActionEvent evento) {
        try {
            listarClientes();
            mudarVisibilidades(false, true);

        } catch (ObjetosInsuficientesErro objetosInsuficientesErro) {
            Main.chamarJanela("../gui/erros/TelaObjetosInsuficientesErro.fxml", 400, 150);
        } catch (RepositorioVazioErro repositorioVazioErro) {
            Main.chamarJanela("../gui/erros/TelaRepositorioVazioErro.fxml", 400, 150);
        }
    }

    private void listarClientes() throws ObjetosInsuficientesErro, RepositorioVazioErro {
        LocalDate dataInicial = MetodosOutros.retornaData(this.tfDia.getText(), this.tfMes.getText(), this.tfAno.getText());
        LocalDate dataFinal = MetodosOutros.retornaData(this.tfDiaFinal.getText(), this.tfMesFinal.getText(), this.tfAnoFinal.getText());
        Pedido[] pedidos = this.fachada.gerarVetorDePedidos(dataInicial, dataFinal);
        Collections.addAll(listaObjetos, pedidos);
        listaObservavel = FXCollections.observableArrayList(listaObjetos);
    }

    @FXML
    private void acaoBotaoVoltar(ActionEvent event){
        mudarVisibilidades(true, false);
        listaObjetos.clear();
    }

    @FXML
    private void acaoBotaoCancelar(ActionEvent event){
        Main.chamarJanela("../gui/objetos/TelaPedidos.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
