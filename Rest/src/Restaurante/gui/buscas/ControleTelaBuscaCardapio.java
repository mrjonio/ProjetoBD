package Restaurante.gui.buscas;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.excessoes.ObjetoExistencia.ExcecaoObjetoExistencia;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
import Restaurante.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaBuscaCardapio implements Initializable {
    private IFachadaGerente fachada;

    public ControleTelaBuscaCardapio(){
        this.fachada = Fachada.getInstance();
    }

    @FXML
    private Label lbNome, lbPreco, lbNomePratoPego, lbPrecoPratoPego, lbNomeBuscado;

    @FXML
    private Button btSend, btCancelar, btVoltar;

    @FXML
    private ImageView imgPhoto, imgTop;

    @FXML
    private Pane pane;

    @FXML
    private TextField tfNomeBuscado;

    /*
    * Mudar visibilidades de componentes de tela quando clciar em um determinado botão, esse metodo funciona de forma igual
    * em todas as classes que ele é implementado
     */

    private void mudarVisibilidades(boolean a, boolean b){
        this.btCancelar.setVisible(a);
        this.lbNomeBuscado.setVisible(a);
        this.tfNomeBuscado.setVisible(a);
        this.btSend.setVisible(a);
        this.imgPhoto.setVisible(b);
        this.btVoltar.setVisible(b);
        this.lbNomePratoPego.setVisible(b);
        this.lbPrecoPratoPego.setVisible(b);
        this.lbNome.setVisible(b);
        this.lbPreco.setVisible(b);
    }

    @FXML
    private void acaoBotaoVoltar(ActionEvent event){
        mudarVisibilidades(true, false);
    }


    /*
    *Tratamento de eventod do botão de buscar um prato no cardapio
    * aqui são usados metodos definidos ao longo de todo o projeto, desde
    * o repositorio até a fachada, os erros também são tratados
     */

    @FXML
    private void acaoBotaoSend(ActionEvent event) {
        String nomeDoPratoBuscado = this.tfNomeBuscado.getText();
        try {
            PratoCardapio pratoPego = this.fachada.buscarUmPratoDoCardapio(nomeDoPratoBuscado);
            this.lbNomePratoPego.setText(pratoPego.getNome());
            String preco = String.valueOf(pratoPego.getPreco());
            this.lbPrecoPratoPego.setText(preco);
            this.imgPhoto.setImage(pratoPego.getFoto());
            mudarVisibilidades(false, true);
        } catch (ExcecaoObjetoExistencia e) {
            e.mostrarErro();
        }
    }

    /*
    *Botão de retorno para a tela anterior
     */
    @FXML
    private void acaoBotaoCancelar(ActionEvent event){
        Main.chamarJanela("../gui/objetos/TelaCardapio.fxml", 711, 480);
        Stage tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
