package Restaurante.gui.guiDoCozinheiro;

import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaCozinheiro;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Optional;

public class TelaCozinheiro {

    private IFachadaCozinheiro fachada;

    @FXML
    private void aumentarEstoque() {

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Ingrediente");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField nomeTXT = new TextField();
        nomeTXT.setPromptText("Nome do ingrediente");
        gridPane.add(nomeTXT, 0, 0);

        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> nomeTXT.requestFocus());


        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        nomeTXT.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return nomeTXT.getText();
            }
            return null;
        });

        dialog.showAndWait();

        String saida = dialog.getResult();

        if (saida == null) {
            return;
        }

        Ingrediente i;

        try {
            i = this.fachada.pegarUmIngrediente(saida);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Ingrediente invalido");
            alert.showAndWait();
            return;
        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Dialog<String> dialog2 = new Dialog<>();
        dialog2.setTitle("Ingrediente");
        dialog2.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane2 = new GridPane();
        gridPane2.setHgap(10);
        gridPane2.setVgap(10);
        gridPane2.setPadding(new Insets(20, 150, 10, 10));

        TextField qtdTXT2 = new TextField();
        qtdTXT2.setText(String.valueOf(i.getQtd()));
        qtdTXT2.setPromptText("Quantidade");
        gridPane2.add(qtdTXT2, 0, 1);

        dialog2.getDialogPane().setContent(gridPane2);
        Platform.runLater(() -> qtdTXT2.requestFocus());


        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton2 = dialog2.getDialogPane().lookupButton(loginButtonType);
        loginButton2.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        qtdTXT2.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton2.setDisable(
                    newValue.trim().isEmpty()
            );
        });

        qtdTXT2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    qtdTXT2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });



        dialog2.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return qtdTXT2.getText();
            }
            return null;
        });

        dialog2.showAndWait();

        String saida2 = dialog2.getResult();

        if (saida2 == null) {
            return;
        }


        int quantidade;
        try {
            quantidade = Integer.parseInt(saida2);
            if (quantidade <= 0){
                //sim, é de proposito
                int a = 1 / 0;
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Quantidade invalida");
            alert.showAndWait();
            return;
        }

        try {
            Ingrediente ing = new Ingrediente(i.getNome(), quantidade);
            this.fachada.aumentarEstoque(ing);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Ingrediente nao encontrado");
            alert.showAndWait();
            return;
        }

    }

    @FXML
    private void removerIngrediente() {

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Ingrediente");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField nomeTXT = new TextField();
        nomeTXT.setPromptText("Nome do ingrediente");
        gridPane.add(nomeTXT, 0, 0);

        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> nomeTXT.requestFocus());


        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        nomeTXT.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return nomeTXT.getText();
            }
            return null;
        });

        dialog.showAndWait();

        String saida = dialog.getResult();

        if (saida == null) {
            return;
        }


        try {
            this.fachada.removerIngrediente(saida);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Ingrediente removido");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Ingrediente invalido");
            alert.showAndWait();
        }

    }

    @FXML
    private void pegarUmIngrediente() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Ingrediente");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField nomeTXT = new TextField();
        nomeTXT.setPromptText("Nome do ingrediente");
        gridPane.add(nomeTXT, 0, 0);

        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> nomeTXT.requestFocus());


        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        nomeTXT.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return nomeTXT.getText();
            }
            return null;
        });

        dialog.showAndWait();

        String saida = dialog.getResult();

        if (saida == null) {
            return;
        }

        Ingrediente i;

        try {
            i = this.fachada.pegarUmIngrediente(saida);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Ingrediente invalido");
            alert.showAndWait();
            return;
        }


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Ingrediente " + i.getNome());
        alert.setContentText("Temos " + i.getQtd());
        alert.showAndWait();

    }

    @FXML
    private void diminuirQuantidadeDeIngrediente() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Ingrediente");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField nomeTXT = new TextField();
        nomeTXT.setPromptText("Nome do ingrediente");
        gridPane.add(nomeTXT, 0, 0);

        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> nomeTXT.requestFocus());


        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        nomeTXT.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return nomeTXT.getText();
            }
            return null;
        });

        dialog.showAndWait();

        String saida = dialog.getResult();

        if (saida == null) {
            return;
        }

        Ingrediente i;

        try {
            i = this.fachada.pegarUmIngrediente(saida);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Ingrediente invalido");
            alert.showAndWait();
            return;
        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Dialog<String> dialog2 = new Dialog<>();
        dialog2.setTitle("Ingrediente");
        dialog2.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane2 = new GridPane();
        gridPane2.setHgap(10);
        gridPane2.setVgap(10);
        gridPane2.setPadding(new Insets(20, 150, 10, 10));

        TextField qtdTXT2 = new TextField();
        qtdTXT2.setText(String.valueOf(i.getQtd()));
        qtdTXT2.setPromptText("Quantidade");
        gridPane2.add(qtdTXT2, 0, 1);

        dialog2.getDialogPane().setContent(gridPane2);
        Platform.runLater(() -> qtdTXT2.requestFocus());


        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton2 = dialog2.getDialogPane().lookupButton(loginButtonType);
        loginButton2.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        qtdTXT2.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton2.setDisable(
                    newValue.trim().isEmpty()
            );
        });

        qtdTXT2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    qtdTXT2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });



        dialog2.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return qtdTXT2.getText();
            }
            return null;
        });

        dialog2.showAndWait();

        String saida2 = dialog2.getResult();

        if (saida2 == null) {
            return;
        }


        int quantidade;
        try {
            quantidade = Integer.parseInt(saida2);
            if (quantidade <= 0){
                //sim, é de proposito
                int a = 1 / 0;
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Quantidade invalida");
            alert.showAndWait();
            return;
        }

        try {
            this.fachada.diminuirQuantidadeDeIngrediente(i, quantidade);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Ingrediente nao encontrado");
            alert.showAndWait();
            return;
        }
    }

    @FXML
    private void alterarAtributoDeUmIngrediente() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Ingrediente");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField nomeTXT = new TextField();
        nomeTXT.setPromptText("Nome do ingrediente");
        gridPane.add(nomeTXT, 0, 0);

        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> nomeTXT.requestFocus());


        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        nomeTXT.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return nomeTXT.getText();
            }
            return null;
        });

        dialog.showAndWait();

        String saida = dialog.getResult();

        if (saida == null) {
            return;
        }

        Ingrediente i;

        try {
            i = this.fachada.pegarUmIngrediente(saida);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Ingrediente invalido");
            alert.showAndWait();
            return;
        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Dialog<Pair<String, String>> dialog2 = new Dialog<>();
        dialog2.setTitle("Ingrediente");
        dialog2.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane2 = new GridPane();
        gridPane2.setHgap(10);
        gridPane2.setVgap(10);
        gridPane2.setPadding(new Insets(20, 150, 10, 10));

        TextField nomeTXT2 = new TextField();
        nomeTXT2.setText(i.getNome());
        nomeTXT2.setPromptText("Nome do ingrediente");
        gridPane2.add(nomeTXT2, 0, 0);


        TextField qtdTXT2 = new TextField();
        qtdTXT2.setText(String.valueOf(i.getQtd()));
        qtdTXT2.setPromptText("Quantidade");
        gridPane2.add(qtdTXT2, 0, 1);

        dialog2.getDialogPane().setContent(gridPane2);
        Platform.runLater(() -> nomeTXT2.requestFocus());


        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton2 = dialog2.getDialogPane().lookupButton(loginButtonType);
        loginButton2.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        qtdTXT2.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton2.setDisable(newValue.trim().isEmpty());
            nomeTXT2.textProperty().addListener((observable2, oldValue2, newValue2) -> {
                loginButton2.setDisable(newValue2.trim().isEmpty());
            });
        });

        nomeTXT2.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton2.setDisable(newValue.trim().isEmpty());
            qtdTXT2.textProperty().addListener((observable2, oldValue2, newValue2) -> {
                loginButton2.setDisable(newValue2.trim().isEmpty());
            });
        });


        dialog2.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(nomeTXT.getText(), qtdTXT2.getText());
            }
            return null;
        });

        dialog2.showAndWait();

        Pair<String, String> saida2 = dialog2.getResult();

        if (saida2 == null) {
            return;
        }


        Ingrediente ingredienteNovo;
        try {
            ingredienteNovo = new Ingrediente(saida2.getKey(), Integer.parseInt(saida2.getValue()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Quantidade invalida");
            alert.showAndWait();
            return;
        }

        try {
            this.fachada.alterarAtributoDeUmIngrediente(ingredienteNovo, i.getNome());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Ingrediente nao encontrado");
            alert.showAndWait();
            return;
        }

    }

    @FXML
    private void adicionarIngrediente() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Ingrediente");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField nomeTXT = new TextField();
        nomeTXT.setPromptText("Nome do ingrediente");
        gridPane.add(nomeTXT, 0, 0);


        TextField qtdTXT = new TextField();
        qtdTXT.setPromptText("Quantidade");
        gridPane.add(qtdTXT, 0, 1);

        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> nomeTXT.requestFocus());


        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        qtdTXT.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
            nomeTXT.textProperty().addListener((observable2, oldValue2, newValue2) -> {
                loginButton.setDisable(newValue2.trim().isEmpty());
            });
        });

        nomeTXT.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
            qtdTXT.textProperty().addListener((observable2, oldValue2, newValue2) -> {
                loginButton.setDisable(newValue2.trim().isEmpty());
            });
        });


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(nomeTXT.getText(), qtdTXT.getText());
            }
            return null;
        });

        dialog.showAndWait();

        Pair<String, String> saida = dialog.getResult();

        if (saida == null) {
            return;
        }


        int quantidade = 0;
        try {
             quantidade = Integer.parseInt(saida.getValue());
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Quantidade invalida");
            alert.showAndWait();
            return;
        }

        Ingrediente i = new Ingrediente(saida.getKey(), quantidade);

        try {
            this.fachada.adicionarIngrediente(i);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Ingrediente invalido");
            alert.showAndWait();
            return;
        }
    }







    @FXML
    private void adicionarPratoAoCardapio() {
        ArrayList<Ingrediente> ingredientes;

        //TODO: fazer na gui ao inves de escrever o nome

        // Create the custom dialog.
        Dialog<Pair<String, Pair<String, String>>> dialog = new Dialog<>();
        dialog.setTitle("Novo Prato");
        dialog.setHeaderText("Preencha os ingredientes com os nomes separados por virgula apenas");


        // Set the button types.
        ButtonType criarButtonType = new ButtonType("Criar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(criarButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nome = new TextField();
        nome.setPromptText("Nome");
        TextField preco = new TextField();
        preco.setPromptText("Preço");

        TextField ingredientesTxt = new TextField();
        ingredientesTxt.setPromptText("ingredientes");

        grid.add(new Label("Nome:"), 0, 0);
        grid.add(nome, 1, 0);

        grid.add(new Label("Preço:"), 0, 1);
        grid.add(preco, 1, 1);

        grid.add(new Label("ingredientes:"), 0, 2);
        grid.add(ingredientesTxt, 1, 2);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(criarButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        nome.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        preco.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        ingredientesTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> nome.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == criarButtonType) {
                return new Pair<>(nome.getText(), new Pair<>(preco.getText(), ingredientesTxt.getText()));
            }
            return null;
        });

        Optional<Pair<String, Pair<String, String>>> result = dialog.showAndWait();

        String nomePrato = "";
        double precoPrato = 0;
        String ingredientesStr = "";

        if (result.isPresent()) {
            nomePrato = result.get().getKey();
            Pair p = result.get().getValue();

            try {
                precoPrato = Double.parseDouble(p.getKey().toString());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Preco invalido");
                alert.showAndWait();
                return;
            }

            ingredientesStr = p.getValue().toString();

            ingredientes = this.pegarIngredientes(ingredientesStr);

            if (!ingredientes.isEmpty()) {
                PratoCardapio prato = new PratoCardapio(nomePrato, precoPrato, ingredientes);
                try {
                    this.fachada.adicionarPratoAoCardapio(prato);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Prato invalido");
                    alert.showAndWait();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Prato invalido");
                alert.showAndWait();
            }

        }
    }


    private ArrayList<Ingrediente> pegarIngredientes(String listaVirgulina) {
        String[] ings = listaVirgulina.split(",");
        ArrayList<Ingrediente> ret  = new ArrayList<>();
        Fachada f = Fachada.getInstance();

        for (String ingredienteStr : ings) {
            try {
                ret.add(f.pegarUmIngrediente(ingredienteStr));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Ingrediente " + ingredienteStr + " nao existe");
                alert.showAndWait();
                ret.clear();
                return ret;
            }
        }
        return ret;
    }

    @FXML
    private void cozinharPedido() {

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Pedido");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField nomeTXT = new TextField();
        nomeTXT.setPromptText("Numero da mesa");
        gridPane.add(nomeTXT, 0, 0);

        dialog.getDialogPane().setContent(gridPane);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        nomeTXT.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return nomeTXT.getText();
            }
            return null;
        });

        dialog.showAndWait();

        String saida = dialog.getResult();

        if (saida == null) {
            return;
        }

        int numeroMesa;

        try {
            numeroMesa = Integer.parseInt(saida);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Numero da mesa invalido");
            alert.showAndWait();
            return;
        }

        Mesa m;
        try {
            m = Fachada.getInstance().buscarUmaMesa(numeroMesa);
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Mesa nao encontrada");
            alert.showAndWait();
            return;
        }



        Dialog lista = new Dialog();
        dialog.setTitle("Pedidos");
        dialog.getDialogPane().getButtonTypes().remove(dialog.getDialogPane().getButtonTypes().size() - 1);
        GridPane gridPane2 = new GridPane();
        gridPane2.setHgap(10);
        gridPane2.setVgap(10);


        ListView<Pedido> listView = new ListView<>();
        listView.setPrefWidth(dialog.getWidth() - 10);
        listView.setItems(FXCollections.observableArrayList(m.getPedidos()));

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    Pedido selectedItem = ((Pedido)listView.getSelectionModel().getSelectedItem());
                    if (selectedItem == null) {return;}

                    if (verificarIngredientes(selectedItem)) {
                        fachada.armazenarUmPedido(selectedItem);
                        m.getPedidos().remove(selectedItem);
                        listView.setItems(FXCollections.observableArrayList(m.getPedidos()));
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Cozinhando " + selectedItem);
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Nao tem ingredientes suficientes");
                        alert.showAndWait();
                    }

                }
            }
        });

        gridPane2.add(listView, 0, 0);
        dialog.getDialogPane().setContent(gridPane2);
        dialog.showAndWait();

    }

    private boolean verificarIngredientes(Pedido p) {
        //TODO: o prato nao tem atributo que indica quantos ingredientes de cada tipo usa
        return true;
    }




    @FXML
    public void initialize() {
        this.fachada = Fachada.getInstance();
    }

}
