package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.excessoes.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoNaoExisteErro;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;

/**
 * Abaixo temos a interface a ser implementada na camada "funcionário", com suas assinaturas de métodos e exceções.
 */

public interface ICamadaFuncionario {

    void adicionarFuncionario(Pessoa funcionarioParaSerAdicionado) throws ObjetoJaExisteErro;
    void removerFuncionario(String cpfDoFuncionarioQueSeraRemovido) throws ObjetoNaoExisteErro;
    void mudarAtributosDeUmFuncionario(String cpf, Pessoa funcionarioComNovosParamentros) throws ObjetoNaoExisteErro;
    Pessoa buscarUmFuncionario(String cpfDoFuncionarioBuscado) throws ObjetoNaoExisteErro;
    Pessoa[] retornarVetorDeFuncionarios() throws ObjetosInsuficientesErro, RepositorioVazioErro;
    double calcularFolhaDePagamento();
}
