package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;

/**
 *  Abaixo temos a interface a ser implementada na camada "cardápio", com suas assinaturas de métodos e exceções.
 */

public interface IControladorCadapio {

    void adicionarPratoAoCardapio(PratoCardapio prato) throws ObjetoJaExisteErro;
    void removerPratoDoCardapio(String nomePratoRemovido) throws ObjetoNaoExisteErro, Exception;
    PratoCardapio pegarUmPrato(String nomeDoPrato) throws ObjetoNaoExisteErro;
    PratoCardapio pegarUmPrato(int indexDoPrato) throws ObjetoNaoExisteErro;
    void alterarAtributoDeUmPrato(PratoCardapio novoPrato) throws ObjetoNaoExisteErro;
}
