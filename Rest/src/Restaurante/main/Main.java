package Restaurante.main;

import Restaurante.camadasDeNegocio.entidade.pessoas.Pessoa;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../gui/objetos/TelaLogin.fxml"));
        primaryStage.setScene(new Scene(root, 780, 411));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Restaurante");
        primaryStage.show();
    }

    public static void chamarJanela(String nomeDaJanela, int width, int heigt) {
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(Main.class.getResource(nomeDaJanela));
            stage.setResizable(false);
            stage.setScene(new Scene(root, width, heigt));
            stage.setTitle("Restaurante");
            stage.show();
        } catch (IOException e) {

        }
    }


    public static void main(String[] args)  {
        IFachadaGerente fachada = Fachada.getInstance();
        Pessoa gerente = new Gerente("fasfasf", "111.111.111-11", 19, "Masculino", 1000, "alo");
        try {
            fachada.cadastrarUmFuncionario(gerente);
            launch(args);
        } catch (ObjetoJaExisteErro objetoJaExisteErro) {
            objetoJaExisteErro.printStackTrace();
        }

    }
}
