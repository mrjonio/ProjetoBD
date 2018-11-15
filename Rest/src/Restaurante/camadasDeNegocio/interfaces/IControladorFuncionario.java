package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;

/**
 * Abaixo temos a interface a ser implementada na camada "funcionário", com suas assinaturas de métodos e exceções.
 */

public interface IControladorFuncionario {

    void adicionarFuncionario(Funcionario funcionarioParaSerAdicionado) throws ObjetoJaExisteErro;
    void removerFuncionario(String cpfDoFuncionarioQueSeraRemovido) throws ObjetoNaoExisteErro;
    void mudarAtributosDeUmFuncionario(String cpf, Funcionario funcionarioComNovosParamentros) throws ObjetoNaoExisteErro;
    Funcionario buscarUmFuncionario(String cpfDoFuncionarioBuscado) throws ObjetoNaoExisteErro;
    double calcularFolhaDePagamento();
}
