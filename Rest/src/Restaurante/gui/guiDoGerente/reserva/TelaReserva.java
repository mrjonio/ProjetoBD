package Restaurante.gui.guiDoGerente.reserva;

import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class TelaReserva {


    private IFachadaGerente fachada;

    @FXML
    private TextField txtCPF;


    @FXML
    private void verReserva() {
        String cpf = this.txtCPF.getText();
        if (cpf.isEmpty()) {return;} else {
            this.fachada = Fachada.getInstance();

            try {
                Reserva reserva = this.fachada.buscarUmaReserva(cpf);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Reserva para para o dia " + reserva.getDataHora().toString() + " na mesa " + reserva.getMesa());
                alert.showAndWait();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Reserva nao encontrada");
                alert.showAndWait();
            }
        }
    }
}
