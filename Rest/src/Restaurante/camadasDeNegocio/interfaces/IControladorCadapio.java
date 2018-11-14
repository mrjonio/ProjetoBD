package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.excessoes.*;

/**
 *  Abaixo temos a interface a ser implementada na camada "cardápio", com suas assinaturas de métodos e exceções.
 */

public interface IControladorCadapio {

    void adicionarPratoAoCardapio(PratoCardapio prato) throws ObjetoJaExisteErro;
    void removerPratoDoCardapio(String nomePratoRemovido) throws ObjetoNaoExisteErro;
    PratoCardapio pegarUmPrato(String nomeDoPrato) throws ObjetoNaoExisteErro;
    void alterarAtributoDeUmPrato(PratoCardapio novoPrato, String nomeAtual) throws ObjetoNaoExisteErro;
    PratoCardapio[] fazerVetorDePratos() throws ObjetosInsuficientesErro, RepositorioVazioErro;
}
