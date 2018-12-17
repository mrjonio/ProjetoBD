package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.excessoes.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoNaoExisteErro;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;

/**
 *  Abaixo temos a interface a ser implementada na camada "cliente", com suas assinaturas de métodos e exceções.
 */

public interface ICamadaCliente {

    void cadastrarNovoCliente(Pessoa clienteQueSeraCadastrado) throws ObjetoJaExisteErro;
    void removerUmCliente(String cpfDoClienteQueSeraRemovido) throws ObjetoNaoExisteErro;
    void mudarAtributosDeUmCliente(String cpfAtual, Pessoa novosAtributos) throws ObjetoNaoExisteErro;
    Pessoa buscarUmCliente(String cpfDoClienteBuscado) throws ObjetoNaoExisteErro;
    Pessoa[] criarVetorDeClientes() throws ObjetosInsuficientesErro, RepositorioVazioErro;
}
