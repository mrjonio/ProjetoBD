package Restaurante.repositorios.interfaces;

import Restaurante.entidade.concretos.PratoCardapio;

/**
 *  Abaixo temos a interface a ser implementada no repositório de cardápios, com suas assinaturas de métodos.
 */
public interface IRepositorioCardapio {

    boolean verificarExistenciaPrato(String nomeDoPrato);
    void adicionarPratoAoCardapio(PratoCardapio prato);
    PratoCardapio pegarPrato(String nomePrato);
    void removerPrato(PratoCardapio nomePrato);
    void alterarAtributoPrato(PratoCardapio nomePrato, int index);
    int pegarIdex(PratoCardapio pratoQueSeraPegoIdex);
    PratoCardapio[] criarVetorPratos();
    boolean verificarSeRepositorioEstaVazio();
}
