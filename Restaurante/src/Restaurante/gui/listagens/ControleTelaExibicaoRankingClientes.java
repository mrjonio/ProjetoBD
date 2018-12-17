package Restaurante.gui.listagens;

import Restaurante.entidade.pessoas.cliente.Cliente;
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

public class ControleTelaExibicaoRankingClientes implements Initializable {
    private IFachada fachada;
    private Cliente primeiroLugar;
    private Cliente segundoLugar;
    private Cliente terceiroLugar;

    public ControleTelaExibicaoRankingClientes(){
        this.fachada = Fachada.getInstance();
    }

    @FXML
    private Pane pane;

    @FXML
    private Label lbNome, lbCpf, lbSexo, lbPreferencia, lbIdade, lbIdadeClienteRanking, lbNomeDoClienteDoRanking, lbCpfDoClienteDoRanking,lbSexoDoClienteDoRanking, lbPreferenciaDoClienteDoRanking;

    @FXML
    private ImageView imgTop, imgCenter;

    @FXML
    private Button btPrimeiroLugar, btSegundoLugar, btTerceiroLugar, btVoltar, btCancelar;

    private Stage tela;



    @FXML
    private void acaoDoBotaoPrimeiroLugar(ActionEvent event){
        Cliente[] clientes = calcularRaking();
        this.primeiroLugar = clientes[0];
        this.segundoLugar = clientes[1];
        this.terceiroLugar = clientes[2];
        try {
            this.lbNomeDoClienteDoRanking.setText(this.primeiroLugar.getNome());
            String idade = String.valueOf(this.primeiroLugar.getIdade());
            this.lbIdadeClienteRanking.setText(idade);
            this.lbCpfDoClienteDoRanking.setText(this.primeiroLugar.getCpf());
            this.lbSexoDoClienteDoRanking.setText(this.primeiroLugar.getSexo());
            this.lbPreferenciaDoClienteDoRanking.setText(this.primeiroLugar.getPreferencia());
            mudarVisibilidades(false, true);
        } catch (NullPointerException e){

        }
    }

    @FXML
    private void acaoDoBotaoSegundoLugar(ActionEvent event){
        Cliente[] clientes = calcularRaking();
        this.primeiroLugar = clientes[0];
        this.segundoLugar = clientes[1];
        this.terceiroLugar = clientes[2];
        try {
            this.lbNomeDoClienteDoRanking.setText(this.segundoLugar.getNome());
            String idade = String.valueOf(this.segundoLugar.getIdade());
            this.lbIdadeClienteRanking.setText(idade);
            this.lbCpfDoClienteDoRanking.setText(this.segundoLugar.getCpf());
            this.lbSexoDoClienteDoRanking.setText(this.segundoLugar.getSexo());
            this.lbPreferenciaDoClienteDoRanking.setText(this.segundoLugar.getPreferencia());
            mudarVisibilidades(false, true);
        } catch (NullPointerException e){

        }
    }

    @FXML
    private void acaoDoBotaoTerceiroLugar(ActionEvent event){
        Cliente[] clientes = calcularRaking();
        this.primeiroLugar = clientes[0];
        this.segundoLugar = clientes[1];
        this.terceiroLugar = clientes[2];
        try {
            this.lbNomeDoClienteDoRanking.setText(this.terceiroLugar.getNome());
            String idade = String.valueOf(this.terceiroLugar.getIdade());
            this.lbIdadeClienteRanking.setText(idade);
            this.lbCpfDoClienteDoRanking.setText(this.terceiroLugar.getCpf());
            this.lbSexoDoClienteDoRanking.setText(this.terceiroLugar.getSexo());
            this.lbPreferenciaDoClienteDoRanking.setText(this.terceiroLugar.getPreferencia());
            mudarVisibilidades(false, true);
        } catch (NullPointerException e){

        }
    }

    @FXML
    private void acaoDoBotaoCancelar(ActionEvent event){
        Main.chamarJanela("../gui/objetos/TelaCliente.fxml", 711, 480);
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
        this.lbCpf.setVisible(b);
        this.lbSexo.setVisible(b);
        this.lbIdade.setVisible(b);
        this.lbPreferencia.setVisible(b);
        this.lbNomeDoClienteDoRanking.setVisible(b);
        this.lbCpfDoClienteDoRanking.setVisible(b);
        this.lbSexoDoClienteDoRanking.setVisible(b);
        this.lbIdadeClienteRanking.setVisible(b);
        this.lbPreferenciaDoClienteDoRanking.setVisible(b);
    }

    private Cliente[] calcularRaking(){
        Cliente[] podium = new Cliente[3];
        try {
            podium = (Cliente[]) this.fachada.gerarVetorDeClientes();
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