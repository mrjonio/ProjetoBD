package Restaurante.gui.cadastros;

import Restaurante.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.excessoes.ObjetoJaExisteErro;
import Restaurante.excessoes.ParametroInvalidoErro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachada;
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

public class ControleTelaAdicionarAoCardapio implements Initializable{
    private IFachada fachada;

    public ControleTelaAdicionarAoCardapio(){
        this.fachada = Fachada.getInstance();
    }

    private Stage tela;

    @FXML
    private Button btSend, btCancelar;

    @FXML
    private Label lbNome, lbPreco;

    @FXML
    private Pane pane;

    @FXML
    private TextField tfNome, tfPreco;

    @FXML
    private ImageView imgTop;


    @FXML
    private void acaoBotaoSend(ActionEvent event){
        String nomeDoPrstoQueSeraAdicionado = this.tfNome.getText();
        try{
            double precoDoPratoQueSeraAdicionado = Double.parseDouble(this.tfPreco.getText());
            PratoCardapio pratoQueSeraAdicionado = new PratoCardapio(nomeDoPrstoQueSeraAdicionado, precoDoPratoQueSeraAdicionado);
            boolean eValido = pratoQueSeraAdicionado.eValido();
            this.fachada.adicionarUmPratoAoCardapio(pratoQueSeraAdicionado);
            Main.chamarJanela("../gui/outros/TelaCadastradoComSucesso.fxml", 400, 150);
        } catch (RuntimeException erro) {
            Main.chamarJanela("../gui/erros/TelaLetraNoLugarDeNumeroErro.fxml", 400, 150);
        } catch (ParametroInvalidoErro parametroInvalidoErro) {
            Main.chamarJanela("../gui/erros/TelaParametroInvalidoErro.fxml", 400, 150);
        } catch (ObjetoJaExisteErro objetoJaExisteErro) {
            Main.chamarJanela("../gui/erros/TelaPratoJaExisteErro.fxml", 400, 150);
        }
    }

    @FXML
    private void acaoBotaoCancelar(ActionEvent event){
        Main.chamarJanela("../gui/objetos/TelaCardapio.fxml", 711, 480);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
