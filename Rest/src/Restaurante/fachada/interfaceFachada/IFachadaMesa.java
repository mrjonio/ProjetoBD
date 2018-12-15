package Restaurante.fachada.interfaceFachada;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;

public interface IFachadaMesa {
    void adicionarUmaMesa(Mesa mesaQueSeraAdicionada) throws ObjetoJaExisteErro;
    PratoCardapio pegarUmPrato(int indexDoPrato) throws ObjetoNaoExisteErro;
    PratoCardapio pegarUmPrato(String nomeDoPrato) throws ObjetoNaoExisteErro;
    Mesa buscarUmaMesa(int index) throws RepositorioVazioErro, ObjetosInsuficientesErro;
    boolean verificarQuantidadeIngredientes(Ingrediente nomeDoIngrediente, int qtdNecessaria) throws ObjetoNaoExisteErro;
    void editarMesa(Mesa novosAtributos, Mesa mesaAntiga) throws ObjetoNaoExisteErro;

}
