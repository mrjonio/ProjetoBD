package Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio;

/**
 * Aqui temos a classe modelo para os objetos do tipo "prato"; seus atributos e o seu construtor.
 */

import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PratoCardapio {
    private String nome;
    private double preco;
    private List<Ingrediente> ingredientes;
    private Image foto;

    public PratoCardapio(String nome, double preco, List ingredientes) {
        this.nome = nome;
        this.preco = preco;
        this.ingredientes = ingredientes;
        this.foto = new Image(this.getClass().getResource("alo.png").toString());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(float novoValor) { this.preco = novoValor; }

    public double getPreco() {
        return preco;
    }

    public Image getFoto() {
        return foto;
    }

    public String getFotoBytes() {
        try {
            BufferedImage bImage = SwingFXUtils.fromFXImage(this.foto, null);
            ByteArrayOutputStream s = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", s);
            byte[] res = s.toByteArray();
            s.close();

            char[] hexArray = "0123456789ABCDEF".toCharArray();
            char[] hexChars = new char[res.length * 2];
            for (int j = 0; j < res.length; j++) {
                int v = res[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }
            System.out.println(new String(hexChars));
            return new String(hexChars);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public void setFoto(byte[] bts) {
        BufferedImage fotaCrua;
        ByteArrayInputStream bais = new ByteArrayInputStream(bts);
        try {
            fotaCrua =  ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image foto = SwingFXUtils.toFXImage(fotaCrua, null);
        this.foto = foto;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void adicionarNovoIngrediente(Ingrediente novo) throws ObjetoJaExisteErro {
        boolean flag = true;
        for (Ingrediente ing: ingredientes) {
            if (ing.equals(novo)){
                flag = false;
                break;
            }
        }
        if (flag){
            this.ingredientes.add(novo);
        } else {
            throw new ObjetoJaExisteErro(novo.getNome());
        }
    }
}
