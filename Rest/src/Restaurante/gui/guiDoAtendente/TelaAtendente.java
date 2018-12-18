package Restaurante.gui.guiDoAtendente;

import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaAtendente;
import Restaurante.main.MetodosOutros;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class TelaAtendente {

    /*
    POR ENQUANTO UM CPF SÓ PODE TER UMA RESERVA
     */

    private IFachadaAtendente fachada;

    @FXML
    private void mudarReserva() {
        Reserva reserva = this.pegarReserva();

        if (reserva == null) { return; }

        // Create the custom dialog.
        Dialog<Pair<String, Pair<String, String>>> dialog = new Dialog<>();
        dialog.setTitle("Mudar Reserva");
        dialog.setHeaderText("Cpf na forma xxx.xxx.xxx-xx");


        // Set the button types.
        ButtonType criarButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(criarButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nome = new TextField();
        nome.setText(reserva.getClienteQueReservou());
        nome.setPromptText("CPF");

        TextField preco = new TextField();
        preco.setText(String.valueOf(reserva.getMesa()));
        preco.setPromptText("Numero da mesa");

        TextField ingredientesTxt = new TextField();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy/HH/mm");
        ingredientesTxt.setText(reserva.getDataHora().format(formatter));
        ingredientesTxt.setPromptText("dd/mm/aaaa/hh/minmin");

        grid.add(nome, 1, 0);
        grid.add(preco, 1, 1);
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

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == criarButtonType) {
                return new Pair<>(nome.getText(), new Pair<>(preco.getText(), ingredientesTxt.getText()));
            }
            return null;
        });

        Optional<Pair<String, Pair<String, String>>> result = dialog.showAndWait();

        if (result == null || !result.isPresent()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Campos invalidos");
            alert.showAndWait();
            return;
        }

        String cpf = result.get().getKey();
        String numMesaStr = result.get().getValue().getKey();
        String strData = result.get().getValue().getValue();


        if(nullOrEmpty(cpf, numMesaStr, strData)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Campos invalidos");
            alert.showAndWait();
            return;
        }

        int numeroMesa;

        try {
            numeroMesa = Integer.parseInt(numMesaStr);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("numero da mesa invalido");
            alert.showAndWait();
            return;
        }

        Mesa mesa;
        try {
            mesa = this.fachada.buscarUmaMesa(numeroMesa);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("mesa invalida");
            alert.showAndWait();
            return;
        }


        LocalDateTime data;

        try {
            data = MetodosOutros.retornaDataHora(strData.split("/")[0], strData.split("/")[1], strData.split("/")[2], strData.split("/")[3], strData.split("/")[4]);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("data invalida");
            alert.showAndWait();
            return;
        }

        Reserva reservaNova = new Reserva(data, cpf, mesa);

        try {
            this.fachada.mudarReserva(reservaNova, reserva);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("reserva reservada k");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("reserva invalida");
            alert.showAndWait();
        }

    }

    @FXML
    private void apagarReserva() {
        String cpf = "";



        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Reserva");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField nomeTXT = new TextField();
        nomeTXT.setPromptText("CPF");
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

        cpf = saida;


        Reserva reserva;

        try {
            reserva = this.fachada.buscarUmaReserva(cpf);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Reserva nao encontrada");
            alert.showAndWait();
            return;
        }

        try {
            this.fachada.deletarUmaReserva(reserva);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Reserva deletada");
            alert.showAndWait();
            return;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Reserva nao encontrada");
            alert.showAndWait();
            return;
        }


    }

    @FXML
    private void verReserva() {
        Reserva reserva = this.pegarReserva();

        if (reserva != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Cpf " + reserva.getClienteQueReservou() + " mesa " + reserva.getMesa() + " para " + reserva.getDataHora().toString());
            alert.showAndWait();
        }

    }

    @FXML
    private void novaReserva() {
        // Create the custom dialog.
        Dialog<Pair<String, Pair<String, String>>> dialog = new Dialog<>();
        dialog.setTitle("Nova Reserva");
        dialog.setHeaderText("Cpf na forma xxx.xxx.xxx-xx");


        // Set the button types.
        ButtonType criarButtonType = new ButtonType("Criar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(criarButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nome = new TextField();
        nome.setPromptText("CPF");

        TextField preco = new TextField();
        preco.setPromptText("Numero da mesa");

        TextField ingredientesTxt = new TextField();
        ingredientesTxt.setPromptText("dd/mm/aaaa/hh/minmin");

        grid.add(nome, 1, 0);
        grid.add(preco, 1, 1);
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
        
        
        if (result == null || !result.isPresent()) {
            return;
        }


        String cpf = result.get().getKey();
        String numMesaStr = result.get().getValue().getKey();
        String strData = result.get().getValue().getValue();


        if(nullOrEmpty(cpf, numMesaStr, strData)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Campos invalidos");
            alert.showAndWait();
            return;
        }

        try {
            this.fachada.buscarUmaReserva(cpf);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("CPF já tem reserva");
            alert.showAndWait();
            return;
        } catch (Exception e) {}

        int numeroMesa;

        try {
            numeroMesa = Integer.parseInt(numMesaStr);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("numero da mesa invalido");
            alert.showAndWait();
            return;
        }

        Mesa mesa;
        try {
            mesa = this.fachada.buscarUmaMesa(numeroMesa);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("mesa invalida");
            alert.showAndWait();
            return;
        }


        LocalDateTime data;

        try {
            data = MetodosOutros.retornaDataHora(strData.split("/")[0], strData.split("/")[1], strData.split("/")[2], strData.split("/")[3], strData.split("/")[4]);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("data invalida");
            alert.showAndWait();
            return;
        }

        Reserva reserva = new Reserva(data, cpf, mesa);

        try {
            this.fachada.fazerNovaReserva(reserva);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("reserva reservada k");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("reserva invalida");
            alert.showAndWait();
        }


    }

    private boolean nullOrEmpty(String cpf, String numMesaStr, String strData) {
        return cpf == null || numMesaStr  == null || strData == null || cpf.isEmpty() || numMesaStr.isEmpty() || strData.isEmpty();
    }

    private Reserva pegarReserva() {
        String cpf = "";



        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Reserva");
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField nomeTXT = new TextField();
        nomeTXT.setPromptText("CPF");
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
            return null;
        }

        cpf = saida;

        Reserva reserva = null;

        if (cpf.isEmpty()) {return null;} else {

            try {
                reserva = this.fachada.buscarUmaReserva(cpf);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Reserva nao encontrada");
                alert.showAndWait();
            }
        }
        return reserva;
    }


    @FXML
    private void initialize() {
        this.fachada = Fachada.getInstance();
    }


}
