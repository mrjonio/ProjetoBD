package Restaurante.gui.guiDoGerente;

import Restaurante.main.Main;

public class SelecaoGerenciamento {

    public void funcionarios() {
        Main.chamarJanela("../gui/guiDoGerente/funcionario/TelaFuncionario.fxml",710, 468);
    }

    public void mesas() {
        Main.chamarJanela("../gui/guiDoGerente/mesa/TelaMesa.fxml",710, 468);
    }

    public void pratos() {
        Main.chamarJanela("../gui/guiDoGerente/prato/TelaCardapio.fxml",710, 468);
    }

    public void reservas() {
        Main.chamarJanela("../gui/guiDoGerente/reserva/TelaReserva.fxml",710, 468);
    }

    public void pedidos() {
        Main.chamarJanela("../gui/guiDoGerente/pedido/TelaPedido.fxml",710, 468);
    }
}
