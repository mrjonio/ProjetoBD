package Restaurante.gui.guiDoCliente;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ControleListCell extends ListCell<PratoCardapio> {

    /*
    TODO: TA FALTANDO A FOTA
     */


    @FXML
    private Label nome;

    @FXML
    private Label ingredientes;

    @FXML
    private Label preco;

    @FXML
    private GridPane gridPane;

    private FXMLLoader mLLoader;



    @Override
    protected void updateItem(PratoCardapio prato, boolean empty) {
        super.updateItem(prato, empty);

        if(empty || prato == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            nome.setText(prato.getNome());
            preco.setText(String.valueOf(prato.getPreco()));
            ingredientes.setText(prato.getIngredientes().get(0).getNome());

            setText(null);
            setGraphic(gridPane);
        }

    }


}
