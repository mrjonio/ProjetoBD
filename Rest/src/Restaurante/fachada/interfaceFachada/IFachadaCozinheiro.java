package Restaurante.fachada.interfaceFachada;

import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetosInsuficientesErro;


public interface IFachadaCozinheiro {
    void adicionarIngrediente(Ingrediente ingrediente) throws ObjetoJaExisteErro;
    void aumentarEstoque(Ingrediente ingrediente) throws ObjetoNaoExisteErro;
    void removerIngrediente(String nomeDoIngrediente) throws ObjetoNaoExisteErro;
    Ingrediente pegarUmIngrediente(String nomeDoIngrediente) throws ObjetoNaoExisteErro;
    Ingrediente pegarUmIngrediente(int indexDoIngrediente) throws ObjetoNaoExisteErro;
    void diminuirQuantidadeDeIngrediente(Ingrediente ingredienteQueSeraDiminuido, int qtd) throws ObjetoNaoExisteErro, ObjetosInsuficientesErro;
    void alterarAtributoDeUmIngrediente(Ingrediente novosAtributos, String nomeAtual) throws ObjetoNaoExisteErro;
    boolean verificarQuantidadeIngredientes(Ingrediente nomeDoIngrediente, int qtdNecessaria) throws ObjetoNaoExisteErro;
    void armazenarUmPedido(Pedido pedidoQueSeraArmazenado);
    void adicionarPratoAoCardapio(PratoCardapio prato) throws ObjetoJaExisteErro;

}
