package Restaurante.repositorios.interfaces;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;

public interface IRepositorioIngrediente {
    void adicionarIngrediente(Ingrediente ingredienteQueSeraAdicionado);
    boolean proucurarIngrediente(String nome);
    void removerIngrediente(Ingrediente ingredienteQueSeraRemovido);
    void mudarAtributosIngrediente(Ingrediente ingrediente, int index);
    void mudarAtributosIngrediente(int qtd, int index);
    Ingrediente pegarIngrediente(String nome);
    int pegarIdex(Ingrediente ingredienteQueSeraPegoIdex);
    boolean verificarSeRepositorioEstaVazio();
}
