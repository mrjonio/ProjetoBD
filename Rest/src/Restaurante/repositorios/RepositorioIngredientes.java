package Restaurante.repositorios;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.repositorios.interfaces.IRepositorioIngrediente;

import java.util.ArrayList;
import java.util.List;

public class RepositorioIngredientes implements IRepositorioIngrediente {
    private List<Ingrediente> ingredientes;

    public RepositorioIngredientes() {
        this.ingredientes = new ArrayList<>();
    }

    @Override
    public void adicionarIngrediente(Ingrediente ingredienteQueSeraAdicionado) {
        this.ingredientes.add(ingredienteQueSeraAdicionado);
    }

    @Override
    public boolean proucurarIngrediente(String nome) {
        boolean ingrediente = false;
        for (Ingrediente ing: this.ingredientes) {
            if(ing.getNome().equals(nome)){
                ingrediente = true;
                break;
            }
        }
        return ingrediente;
    }

    @Override
    public void removerIngrediente(Ingrediente ingredienteQueSeraRemovido) {
        this.ingredientes.remove(ingredienteQueSeraRemovido);
    }

    @Override
    public void mudarAtributosIngrediente(Ingrediente ingrediente, int index) {
        this.ingredientes.set(index, ingrediente);
    }

    @Override
    public void mudarAtributosIngrediente(int qtd, int index) {
        this.ingredientes.get(index).setQtd(qtd);
    }

    @Override
    public Ingrediente pegarIngrediente(String nome) {
        Ingrediente ingrediente = null;
        for (Ingrediente ing: this.ingredientes) {
            if(ing.getNome().equals(nome)){
                ingrediente = ing;
                break;
            }
        }
        return ingrediente;
    }

    @Override
    public Ingrediente pegarIngrediente(int index) {
        if(this.ingredientes.size() >= index) {
            return this.ingredientes.get(index);
        } else  {
            return null;
        }
    }

    @Override
    public int pegarIdex(Ingrediente ingredienteQueSeraPegoIdex) {
        return this.ingredientes.indexOf(ingredienteQueSeraPegoIdex);
    }

}
