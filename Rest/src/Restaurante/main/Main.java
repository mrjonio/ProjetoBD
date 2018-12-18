package Restaurante.main;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;

import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
import Restaurante.repositorios.DBTabelas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../gui/TelaSelecao.fxml"));
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

    public static FXMLLoader chamarJanelaLoader(String nomeDaJanela, int width, int heigt) {
      Stage stage = new Stage();
        Parent root;
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(Main.class.getResource(nomeDaJanela));
            root = loader.load();
            stage.setResizable(false);
            stage.setScene(new Scene(root, width, heigt));
            stage.setTitle("Restaurante");
            stage.show();
        } catch (IOException e) {

        }
        return loader;
    }


    public static void main(String[] args)  {

        Fachada fachada =  Fachada.getInstance();

        (new DBTabelas()).migrar();

        ArrayList<Ingrediente> ingredientes = new ArrayList<>();

        Ingrediente a = new Ingrediente("Queijo", 500);
        Ingrediente b = new Ingrediente("Polvilho", 500);
        Ingrediente c = new Ingrediente("Leite", 500);

        ingredientes.add(a);
        ingredientes.add(b);
        ingredientes.add(c);

        Funcionario gerente =  new Funcionario("Carlos", "111.111.111-11", 18, "masc", "Gerente", 1999999.00);
        PratoCardapio prat0 = new PratoCardapio("manga", 20.00, ingredientes);
        Mesa mesa2 = new Mesa(1, "Vazia");
        Mesa mesa = new Mesa(0, "Oculpado");
        Reserva reserva = new Reserva(LocalDateTime.now().plusDays(1), "111.111.111-11", mesa2);

        ArrayList<PratoCardapio> arr = new ArrayList<>();
        arr.add(prat0);

        Pedido p = new Pedido(arr, mesa);
        mesa.adicionarPedido(p);


        try {
            fachada.cadastrarUmFuncionario(gerente);
            fachada.adicionarPratoAoCardapio(prat0);
            fachada.adicionarIngrediente(a);
            fachada.adicionarIngrediente(b);
            fachada.adicionarIngrediente(c);
            fachada.adicionarUmaMesa(mesa2);
            fachada.adicionarUmaMesa(mesa);
            fachada.fazerNovaReserva(reserva);
            fachada.armazenarUmPedido(p);
//            fachada.adicionarPratoAoCardapio(prato)

        } catch (Exception objetoJaExisteErro) {
        }

        launch(args);
    }
}
