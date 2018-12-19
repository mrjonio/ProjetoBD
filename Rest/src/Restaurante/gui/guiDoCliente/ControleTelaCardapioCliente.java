package Restaurante.gui.guiDoCliente;

import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.fachada.Fachada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;

public class ControleTelaCardapioCliente {
    @FXML
    private Window tela;


    @FXML
    private ListView listView;
    private ObservableList observableList = FXCollections.observableArrayList();

    private ArrayList<PratoCardapio> pedidos;
    private Fachada fachadaMesa;

    public void verPedido() {
        System.out.println("ver pedido");
        //FXMLLoader f = Main.chamarJanelaLoader("../gui/objetos/PedidoConfirmacao.fxml", 600, 400);


        Stage stage = new Stage();
        Parent root;
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(ControleTelaCardapioCliente.class.getResource("PedidoConfirmacao.fxml"));
            loader.setController(new ControlePedido(this.pedidos));
            root = loader.load();
            stage.setResizable(false);
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("Restaurante");
            stage.show();
        } catch (IOException e) {

        }
    }

    public void limparPedido() {
        System.out.println(this.pedidos.size());
        this.pedidos.clear();
        System.out.println(this.pedidos.size());
    }

    public void confirmarPedido() {
        //TODO: arrumar isso aqui dps que ajustar o pedido como arraylist
        if (this.pedidos.size() > 0) {

            try {
                Mesa mesa = this.fachadaMesa.buscarUmaMesa(1);
                Pedido p = new Pedido(this.pedidos, mesa);
                mesa.adicionarPedido(p);
                this.fachadaMesa.editarMesa(mesa, mesa);
                this.limparPedido();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("TEM Q TER Q MESA 1 PRA TESTAR");
                return;
            }


            Pedido p = new Pedido(this.pedidos, new Mesa(1, "Vazia"));
            this.fachadaMesa.armazenarUmPedido(p);
            this.limparPedido();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Mas e cade os pratos?");
            alert.setContentText("????????");
            alert.showAndWait();
        }
    }

    private void adicionarPratoNoPedido(PratoCardapio prato) {
        pedidos.add(prato);
    }

    public void initialize() {

        fachadaMesa = Fachada.getInstance();
        pedidos = new ArrayList<>();


        try {
            ArrayList<PratoCardapio> cardapio = this.fachadaMesa.pegarTodosPratos();
            observableList.setAll(cardapio);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Problema no cardapio");
            alert.showAndWait();
            return;
        }

        listView.setItems(observableList);


        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() == 2) {
                    adicionarPratoNoPedido(((PratoCardapio)listView.getSelectionModel().getSelectedItem()));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Prato adicionado ao pedido");
                    alert.setContentText("Veja seu pedido para confirmar ou remover o prato");
                    alert.showAndWait();
                }
            }
        });


        listView.setCellFactory(new Callback<ListView<PratoCardapio>, ListCell<PratoCardapio>>() {
            @Override
            public ListCell<PratoCardapio> call(ListView<PratoCardapio> controleList) {
                return new ControleListCell();
            }
        });
    }
}
