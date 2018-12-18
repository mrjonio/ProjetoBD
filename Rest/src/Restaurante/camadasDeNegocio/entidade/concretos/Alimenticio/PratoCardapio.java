package Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio;

/**
 * Aqui temos a classe modelo para os objetos do tipo "prato"; seus atributos e o seu construtor.
 */

import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
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

    public void setPreco(float novoValor) { this.preco = novoValor; }

    public double getPreco() {
        return preco;
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
