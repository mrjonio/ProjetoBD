package Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio;

/**
 * Aqui temos a classe modelo para os objetos do tipo "prato"; seus atributos e o seu construtor.
 */

import javafx.scene.image.Image;

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
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
