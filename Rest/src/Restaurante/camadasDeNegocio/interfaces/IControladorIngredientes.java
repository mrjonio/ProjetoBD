package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetosInsuficientesErro;

public interface IControladorIngredientes {
    void adicionarIngrediente(Ingrediente ingrediente) throws ObjetoJaExisteErro;
    void aumentarEstoque(Ingrediente ingrediente) throws ObjetoNaoExisteErro;
    void removerIngrediente(String nomeDoIngrediente) throws ObjetoNaoExisteErro;
    Ingrediente pegarUmIngrediente(String nomeDoIngrediente) throws ObjetoNaoExisteErro;
    //Ingrediente pegarUmIngrediente(int indexDoIngrediente) throws ObjetoNaoExisteErro;
    void diminuirQuantidadeDeIngrediente(Ingrediente ingredienteQueSeraDiminuido, int qtd) throws ObjetoNaoExisteErro, ObjetosInsuficientesErro;
    void alterarAtributoDeUmIngrediente(Ingrediente novosAtributos, String nomeAtual) throws ObjetoNaoExisteErro;
    boolean verificarQuantidade(Ingrediente nomeDoIngrediente, int qtdNecessaria) throws ObjetoNaoExisteErro;
}
