package Restaurante.gui.listagens;

import Restaurante.entidade.concretos.PratoCardapio;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachada;
import Restaurante.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaExibicaoRankingPratos implements Initializable {
    private IFachada fachada;
    private PratoCardapio primeiroLugar;
    private PratoCardapio segundoLugar;
    private PratoCardapio terceiroLugar;

    public ControleTelaExibicaoRankingPratos(){
        this.fachada = Fachada.getInstance();
    }

    @FXML
    private Pane pane;

    @FXML
    private Label lbNome, lbPreco, lbNomeDoPratoDoRanking, lbPrecoDoPratoDoRanking;

    @FXML
    private ImageView imgTop, imgCenter;

    @FXML
    private Button btPrimeiroLugar, btSegundoLugar, btTerceiroLugar, btVoltar, btCancelar;

    private Stage tela;



    @FXML
    private void acaoDoBotaoPrimeiroLugar(ActionEvent event){
        PratoCardapio[] pratos = calcularRaking();
        this.primeiroLugar = pratos[0];
        this.segundoLugar = pratos[1];
        this.terceiroLugar = pratos[2];
        try {
            this.lbNomeDoPratoDoRanking.setText(this.primeiroLugar.getNome());
            String precoPrato = String.valueOf(this.primeiroLugar.getPreco());
            this.lbPrecoDoPratoDoRanking.setText(precoPrato);
            mudarVisibilidades(false, true);
        } catch (NullPointerException e){

        }
    }

    @FXML
    private void acaoDoBotaoSegundoLugar(ActionEvent event){
        PratoCardapio[] pratos = calcularRaking();
        this.primeiroLugar = pratos[0];
        this.segundoLugar = pratos[1];
        this.terceiroLugar = pratos[2];
        try {
            this.lbNomeDoPratoDoRanking.setText(this.segundoLugar.getNome());
            String precoPrato = String.valueOf(this.segundoLugar.getPreco());
            this.lbPrecoDoPratoDoRanking.setText(precoPrato);
            mudarVisibilidades(false, true);
        } catch (NullPointerException e){

        }
    }

    @FXML
    private void acaoDoBotaoTerceiroLugar(ActionEvent event){
        PratoCardapio[] pratos = calcularRaking();
        this.primeiroLugar = pratos[0];
        this.segundoLugar = pratos[1];
        this.terceiroLugar = pratos[2];
        try {
            this.lbNomeDoPratoDoRanking.setText(this.terceiroLugar.getNome());
            String precoPrato = String.valueOf(this.terceiroLugar.getPreco());
            this.lbPrecoDoPratoDoRanking.setText(precoPrato);
            mudarVisibilidades(false, true);
        } catch (NullPointerException e){

        }
    }

    @FXML
    private void acaoDoBotaoCancelar(ActionEvent event){
        Main.chamarJanela("../gui/objetos/TelaCardapio.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoVoltar(ActionEvent event){
        mudarVisibilidades(true, false);
    }

    private void mudarVisibilidades(boolean a, boolean b){
        this.btCancelar.setVisible(a);
        this.btPrimeiroLugar.setVisible(a);
        this.btTerceiroLugar.setVisible(a);
        this.btSegundoLugar.setVisible(a);
        this.btVoltar.setVisible(b);
        this.imgCenter.setVisible(b);
        this.lbNome.setVisible(b);
        this.lbPreco.setVisible(b);
        this.lbNomeDoPratoDoRanking.setVisible(b);
        this.lbPrecoDoPratoDoRanking.setVisible(b);
    }

    private PratoCardapio[] calcularRaking(){
        PratoCardapio[] podium = new PratoCardapio[3];
        try {
             podium = this.fachada.gerarVetorDePratos();
        } catch (ObjetosInsuficientesErro objetosInsuficientesErro) {
            Main.chamarJanela("../gui/erros/TelaObjetosInsuficientesErro.fxml", 400, 150);
        } catch (RepositorioVazioErro repositorioVazioErro) {
            Main.chamarJanela("../gui/erros/TelaRepositorioVazioErro.fxml", 400, 150);
        }
        return podium;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
