package Restaurante.gui.guiDoCliente;

import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.fachada.Fachada;
import Restaurante.gui.guiDoCliente.ControleListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;

public class ControlePedido {

    @FXML
    private Label totalLabel;

    @FXML
    private Button btLimparPedido, btConfirmarPedido;


    private Fachada fachadaMesa;


    @FXML
    private ListView listView;
    private ObservableList observableList = FXCollections.observableArrayList();

    private ArrayList<PratoCardapio> pedidos;

    public ControlePedido(ArrayList<PratoCardapio> pedidos) {
        this.pedidos = pedidos;
    }

    private void updateList() {
        observableList.setAll(this.pedidos);
        listView.setItems(observableList);
        totalLabel.setText(String.valueOf(this.pedidos.stream().mapToDouble(PratoCardapio::getPreco).sum()));
    }

    public void limparPedido() {
        this.pedidos.clear();
        this.updateList();
    }

    private void confirmarPedido() {
        //TODO: arrumar isso aqui dps que ajustar o pedido como arraylist
        if (this.pedidos.size() > 0) {

            Mesa essaMessa = null;

            try {
                essaMessa = this.fachadaMesa.buscarUmaMesa(0);
                Pedido p = new Pedido(this.pedidos, essaMessa);
                this.fachadaMesa.armazenarUmPedido(p);
                this.limparPedido();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Probleminhas c a mesa");
                alert.setContentText("????????");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Mas e cade os pratos?");
            alert.setContentText("????????");
            alert.showAndWait();
        }
    }

    @FXML
    public void initialize() {

        fachadaMesa = Fachada.getInstance();

        this.updateList();

        listView.setCellFactory(new Callback<ListView<PratoCardapio>, ListCell<PratoCardapio>>() {
            @Override
            public ListCell<PratoCardapio> call(ListView<PratoCardapio> controleList2) {
                return new ControleListCell();
            }
        });


        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() == 2) {
                    PratoCardapio p = ((PratoCardapio)listView.getSelectionModel().getSelectedItem());
                    pedidos.remove(p);
                    updateList();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Prato " + p.getNome() + " ao pedido");
                    alert.setContentText("Veja seu pedido para confirmar ou remover o prato");
                    alert.showAndWait();
                }
            }
        });

        this.btLimparPedido.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                limparPedido();
                ((Stage)((Node) event.getTarget()).getScene().getWindow()).close();
            }
        });

        this.btConfirmarPedido.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                confirmarPedido();
                limparPedido();
                ((Stage)((Node) event.getTarget()).getScene().getWindow()).close();
            }
        });

    }
}
