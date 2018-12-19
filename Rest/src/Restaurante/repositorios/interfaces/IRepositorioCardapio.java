package Restaurante.repositorios.interfaces;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.excessoes.PratoPendenteErro;

/**
 *  Abaixo temos a interface a ser implementada no repositório de cardápios, com suas assinaturas de métodos.
 */
public interface IRepositorioCardapio {

    boolean verificarExistenciaPrato(String nomeDoPrato);
    void adicionarPratoAoCardapio(PratoCardapio prato);
    PratoCardapio pegarPrato(String nomePrato);
    PratoCardapio pegarPrato(int numeroPrato);
    void removerPrato(PratoCardapio nomePrato) throws PratoPendenteErro;
    void alterarAtributoPrato(PratoCardapio nomePrato);
    int pegarIdex(PratoCardapio pratoQueSeraPegoIdex);
}
