package Restaurante.repositorios.interfaces;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;

public interface IRepositorioIngrediente {
    void adicionarIngrediente(Ingrediente ingredienteQueSeraAdicionado); //OK
    boolean proucurarIngrediente(String nome); //OK
    void removerIngrediente(Ingrediente ingredienteQueSeraRemovido); //OK
    void mudarAtributosIngrediente(Ingrediente ingrediente, Ingrediente antigo); //OK
    //void mudarAtributosIngrediente(int qtd, int index);
    Ingrediente pegarIngrediente(String nome); //OK
    //Ingrediente pegarIngrediente(int index);
    //int pegarIdex(Ingrediente ingredienteQueSeraPegoIdex);
}
