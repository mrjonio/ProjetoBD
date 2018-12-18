package Restaurante.gui.guiDoGerente.prato;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Optional;

public class TelaCardapio {


    private IFachadaGerente fachada;

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
    private void buscarPrato() {



        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Prato");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));
        TextField nomeDoPrato = new TextField();
        nomeDoPrato.setPromptText("Nome do prato");
        gridPane.add(nomeDoPrato, 0, 0);
        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> nomeDoPrato.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return nomeDoPrato.getText();
            }
            return null;
        });

        dialog.showAndWait();

        String saida = dialog.getResult();

        if (saida == null) {
            return;
        }

        if (!saida.equals("")) {

                try {
                    PratoCardapio p = fachada.buscarUmPratoDoCardapio(saida);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Prato: " + p.getNome());
                    ArrayList<String> ingredientes = new ArrayList<>();
                    p.getIngredientes().forEach(ingrediente -> ingredientes.add(ingrediente.getNome()));
                    alert.setContentText("Ingredientes: " + ingredientes.toString());
                    alert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Probleminhas");
                    alert.setContentText("Deu ruim");
                    alert.showAndWait();
                }
        } else {
            this.buscarPrato();
        }



    }

    @FXML
    private void removerPrato() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Prato");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));
        TextField nomeDoPrato = new TextField();
        nomeDoPrato.setPromptText("Nome do prato");
        gridPane.add(nomeDoPrato, 0, 0);
        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> nomeDoPrato.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return nomeDoPrato.getText();
            }
            return null;
        });

        dialog.showAndWait();

        String saida = dialog.getResult();

        if (saida == null) {
            return;
        }

        if (!saida.equals("")) {

            try {
                PratoCardapio p = fachada.buscarUmPratoDoCardapio(saida);
                fachada.retirarUmPratoDoCardapio(saida);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Prato: " + p.getNome() + " foi removido");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Probleminhas");
                alert.setContentText("Deu ruim");
                alert.showAndWait();
            }
        } else {
            this.buscarPrato();
        }
    }

    @FXML
    private void adicionarPrato() {
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
                Fachada f = Fachada.getInstance();
                try {
                    f.adicionarPratoAoCardapio(prato);
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

    @FXML
    private void alterarPrato() {


        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Prato");
        dialog.setHeaderText("Alterar prato");
        dialog.setContentText("Insira o nome do prato:");

        String nomeAtual;

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            nomeAtual = result.get();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Prato invalido");
            alert.showAndWait();
            return;
        }


        PratoCardapio pratoAtual;


        try {
            pratoAtual = this.fachada.buscarUmPratoDoCardapio(nomeAtual);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Prato nao encontrado");
            alert.showAndWait();
            return;
        }

        if (pratoAtual == null) {return;}


        String ingredientesVirgulados = "/";

        ArrayList<Ingrediente> ings = (ArrayList<Ingrediente>) pratoAtual.getIngredientes();

        for (Ingrediente i: ings) {
            ingredientesVirgulados += ",";
            ingredientesVirgulados += i.getNome();
        }

        ingredientesVirgulados = ingredientesVirgulados.split("/,")[1];

        System.out.println(ingredientesVirgulados);

        PratoCardapio pratoNovo = this.attPrato(pratoAtual,ingredientesVirgulados);

        if (pratoNovo != null) {
            try {
                this.fachada.alterarAtributoDeUmPrato(pratoNovo, nomeAtual);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Errou");
                alert.showAndWait();
                return;
            }

        }

    }


    private PratoCardapio attPrato(PratoCardapio pratoCardapio, String ingredientesVirgulados) {

        ArrayList<Ingrediente> ingredientes;
        PratoCardapio prato = null;

        // Create the custom dialog.
        Dialog<Pair<String, Pair<String, String>>> dialog = new Dialog<>();
        dialog.setTitle("Atualizar Prato");
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
        nome.setText(pratoCardapio.getNome());
        nome.setPromptText("Nome");
        nome.setDisable(true);

        TextField preco = new TextField();
        preco.setText(String.valueOf(pratoCardapio.getPreco()));
        preco.setPromptText("Preço");

        TextField ingredientesTxt = new TextField();
        ingredientesTxt.setText(ingredientesVirgulados);
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
                return null;
            }

            ingredientesStr = p.getValue().toString();

            ingredientes = this.pegarIngredientes(ingredientesStr);

            if (!ingredientes.isEmpty()) {
                prato = new PratoCardapio(nomePrato, precoPrato, ingredientes);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Prato invalido");
                alert.showAndWait();
            }

        }

        return prato;
    }


    @FXML
    public void initialize() {
        this.fachada = Fachada.getInstance();
    }

}
