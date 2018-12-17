package Restaurante.gui.guiDoGerente.pedido;

import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.excessoes.NaoOuveLucroErro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
import Restaurante.main.MetodosOutros;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TelaPedido {


    private IFachadaGerente fachadaGerente;

    @FXML
    private void deletarPedidosDeUmaDeterminadaEpoca() {
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Pedidos");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField inicio = new TextField();
        inicio.setPromptText("Data de inicio");
        gridPane.add(inicio, 0, 0);

        TextField fim = new TextField();
        fim.setPromptText("Data de fim");
        gridPane.add(fim, 0, 1);

        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> inicio.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                String[] ret = new String[2];
                ret[0] = inicio.getText();
                ret[1] = fim.getText();
                return ret;
            }
            return null;
        });

        dialog.showAndWait();

        String[] saida = dialog.getResult();

        if (saida == null) {
            return;
        }


        String dataInicio = saida[0];
        String dataFim = saida[1];

        LocalDateTime dataI;
        LocalDateTime dataF;

        try {
            dataI = MetodosOutros.retornaDataHora(dataInicio.split("/")[0], dataInicio.split("/")[1], dataInicio.split("/")[2], "0", "0");
            dataF = MetodosOutros.retornaDataHora(dataFim.split("/")[0], dataFim.split("/")[1], dataFim.split("/")[2], "0", "0");
            this.fachadaGerente.deletarPedidosDeUmaDeterminadaEpoca(dataI, dataF);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Tudo ok");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Probleminhas");
            alert.setContentText("Deu ruim");
            alert.showAndWait();
            e.printStackTrace();
        }

    }

    @FXML
    private void calcularLucroGeradoPorPedidosEmDetermiadoPeriodoDeTempo() {
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Pedidos");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField inicio = new TextField();
        inicio.setPromptText("Data de inicio");
        gridPane.add(inicio, 0, 0);

        TextField fim = new TextField();
        fim.setPromptText("Data de fim");
        gridPane.add(fim, 0, 1);

        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> inicio.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                String[] ret = new String[2];
                ret[0] = inicio.getText();
                ret[1] = fim.getText();
                return ret;
            }
            return null;
        });

        dialog.showAndWait();

        String[] saida = dialog.getResult();

        if (saida == null) {
            return;
        }


        String dataInicio = saida[0];
        String dataFim = saida[1];

        LocalDateTime dataI;
        LocalDateTime dataF;

        try {
            dataI = MetodosOutros.retornaDataHora(dataInicio.split("/")[0], dataInicio.split("/")[1], dataInicio.split("/")[2], "0", "0");
            dataF = MetodosOutros.retornaDataHora(dataFim.split("/")[0], dataFim.split("/")[1], dataFim.split("/")[2], "0", "0");
            double lucros = this.fachadaGerente.calcularLucroGeradoPorPedidosEmDetermiadoPeriodoDeTempo(dataI, dataF);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Lucros");
            alert.setContentText("Lucros de " + lucros);
            alert.showAndWait();
        } catch (NaoOuveLucroErro err) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ta liso mermao");
            alert.showAndWait();
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Probleminhas");
            alert.setContentText("Deu ruim");
            alert.showAndWait();
            e.printStackTrace();
        }



    }

    @FXML
    private void criarListaPedidosDeterminadoPeriodo() {
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Pedidos");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField inicio = new TextField();
        inicio.setPromptText("Data de inicio");
        gridPane.add(inicio, 0, 0);

        TextField fim = new TextField();
        fim.setPromptText("Data de fim");
        gridPane.add(fim, 0, 1);

        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> inicio.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                String[] ret = new String[2];
                ret[0] = inicio.getText();
                ret[1] = fim.getText();
                return ret;
            }
            return null;
        });

        dialog.showAndWait();

        String[] saida = dialog.getResult();

        if (saida == null) {
            return;
        }


        String dataInicio = saida[0];
        String dataFim = saida[1];

        LocalDateTime dataI;
        LocalDateTime dataF;

        try {
            dataI = MetodosOutros.retornaDataHora(dataInicio.split("/")[0], dataInicio.split("/")[1], dataInicio.split("/")[2], "0", "0");
            dataF = MetodosOutros.retornaDataHora(dataFim.split("/")[0], dataFim.split("/")[1], dataFim.split("/")[2], "0", "0");
            List<Pedido> pedidos = this.fachadaGerente.criarListaPedidosDeterminadoPeriodo(dataI, dataF);


            Dialog lista = new Dialog();
            dialog.setTitle("Pedidos");
            dialog.getDialogPane().getButtonTypes().remove(dialog.getDialogPane().getButtonTypes().size() - 1);
            GridPane gridPane2 = new GridPane();
            gridPane2.setHgap(10);
            gridPane2.setVgap(10);
            gridPane2.setPadding(new Insets(20, 150, 10, 10));


            ListView<Pedido> listView = new ListView<>();
            listView.setPrefWidth(dialog.getWidth() - 10);
            listView.setItems(FXCollections.observableArrayList(pedidos));

            gridPane2.add(listView, 0, 0);


            dialog.getDialogPane().setContent(gridPane2);
            dialog.showAndWait();

        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Probleminhas");
            alert.setContentText("Deu ruim");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    private void removerUmDeterminadopedido() {
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Pedidos");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField data = new TextField();
        data.setPromptText("Data do pedido");
        gridPane.add(data, 0, 0);

        TextField mesa = new TextField();
        mesa.setPromptText("Mesa que pediu");
        gridPane.add(mesa, 0, 1);

        TextField pratos = new TextField();
        pratos.setPromptText("Pratos que pediu, separado por virgulas");
        gridPane.add(pratos, 0, 2);

        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> data.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                String[] ret = new String[3];
                ret[0] = data.getText();
                ret[1] = mesa.getText();
                ret[2] = pratos.getText();
                return ret;
            }
            return null;
        });

        dialog.showAndWait();

        String[] saida = dialog.getResult();

        if (saida == null) {
            return;
        }

        ArrayList<PratoCardapio> pratosLista = this.pegarPratos(saida[2]);

        LocalDateTime dataO = MetodosOutros.retornaDataHora(saida[0].split("/")[0], saida[0].split("/")[1], saida[0].split("/")[2], saida[0].split("/")[3], saida[0].split("/")[4]);

        Mesa m;
        try {
            m = this.fachadaGerente.buscarUmaMesa(Integer.parseInt(saida[1]));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Mesa nao existe");
            alert.showAndWait();
            return;
        }

        Pedido p = new Pedido(pratosLista, m);
        p.setDataDoPedido(dataO);
        System.out.println(p.getDataDoPedido().toString());

        try {
            this.fachadaGerente.removerUmDeterminadopedido(p);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Pedido nao existe");
            alert.showAndWait();
        }
    }


    //TODO: uma remoção simples de array list n deixa isso funcionar
    private ArrayList<PratoCardapio> pegarPratos(String pratosVirgiulados) {
        String[] pratos = pratosVirgiulados.split(",");
        ArrayList<PratoCardapio> ret  = new ArrayList<>();
        Fachada f = Fachada.getInstance();

        for (String pratoStr : pratos) {
            try {
                ret.add(f.pegarUmPrato(pratoStr));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Prato " + pratoStr + " nao existe");
                alert.showAndWait();
                ret.clear();
                return ret;
            }
        }
        return ret;
    }


    @FXML
    public void initialize() {this.fachadaGerente = Fachada.getInstance();}

}
