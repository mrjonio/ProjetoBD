package Restaurante.gui.guiDoGerente.mesa;

import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.excessoes.ObjetoExistencia.ObjetoEmUsoErro;
import Restaurante.fachada.Fachada;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class TelaMesa {

    private Fachada fachada;
    private int qtdMesas;


    @FXML
    private Label totalCount, ocupadasCount, livresCount;


    @FXML
    private void novaMesa() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mesa");
        alert.setHeaderText("Adicionar nova mesa");
        alert.setContentText("Tem certeza?");

        Optional<ButtonType> result = alert.showAndWait();

        if (!result.isPresent()) {return;}

        if (result.get() == ButtonType.OK){
            Mesa nova = new Mesa(this.qtdMesas, "Vazia");


            try {
                this.fachada.adicionarUmaMesa(nova);
            } catch (Exception e) {
                Alert aler2 = new Alert(Alert.AlertType.WARNING);
                aler2.setHeaderText("Mesa com indice errado");
                aler2.setContentText("Consulte o universitaio com cara de acabado mais proximo");
                aler2.showAndWait();
            } finally {
                this.initialize();
            }
        }

    }

    @FXML
    private void apagarMesa() {

        //TODO: por enquanto tem meio que advinhar o numero da mesa, mas né

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Mesa");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));
        TextField idMesa = new TextField();
        idMesa.setPromptText("Numero da mesa");
        gridPane.add(idMesa, 0, 0);
        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> idMesa.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return idMesa.getText();
            }
            return null;
        });

        dialog.showAndWait();

        String saida = dialog.getResult();

        if (saida == null) {
            return;
        }

        if (!saida.equals("")) {

            int numeroMesa;

            try {
                numeroMesa = Integer.parseInt(saida);

                try {
                    fachada.deletarMesasDoSistema(fachada.buscarUmaMesa(numeroMesa));
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Mesa apagada");
                    alert.setContentText("Só foi");
                    alert.showAndWait();
                    this.initialize();
                } catch (ObjetoEmUsoErro e) {

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Mesa ocupada rapaaz");
                    alert.setContentText("Deu ruim");
                    alert.showAndWait();

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Probleminhas");
                    alert.setContentText("Deu ruim");
                    alert.showAndWait();
                }


            } catch (NumberFormatException e) {
                this.verMesa();
            }

        } else {
            this.apagarMesa();
        }

    }

    @FXML
    private void verMesa() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Mesa");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));
        TextField idMesa = new TextField();
        idMesa.setPromptText("Numero da mesa");
        gridPane.add(idMesa, 0, 0);
        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> idMesa.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return idMesa.getText();
            }
            return null;
        });

        dialog.showAndWait();

        String saida = dialog.getResult();

        if (saida == null) {
            return;
        }

        if (!saida.equals("")) {

            int numeroMesa;

            try {
                numeroMesa = Integer.parseInt(saida);

                try {
                    Mesa m = fachada.buscarUmaMesa(numeroMesa);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Mesa " + m.getNumero());
                    alert.setContentText("Dispinibilidade: " + m.isDisponibilidade());
                    alert.showAndWait();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Probleminhas");
                    alert.setContentText("Deu ruim");
                    alert.showAndWait();
                }


            } catch (NumberFormatException e) {
                this.verMesa();
            }
        } else {
            this.verMesa();
        }

    }

    @FXML
    public void initialize() {

        fachada = Fachada.getInstance();

        this.qtdMesas = fachada.qtdMesas();

        this.totalCount.setText(String.valueOf(this.qtdMesas));
        int ocupadas = 0;

        for (int i = 0; i < this.qtdMesas; i++) {
            try {
                Mesa m = fachada.buscarUmaMesa(i);
                if (m.isDisponibilidade().equals("Oculpado")) {
                    ocupadas++;
                }
            } catch (Exception e) {}
        }

        this.ocupadasCount.setText(String.valueOf(ocupadas));
        this.livresCount.setText(String.valueOf(this.qtdMesas - ocupadas));

    }
}
