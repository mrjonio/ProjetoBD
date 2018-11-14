package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.entidade.pessoas.funcionario.Funcionario;
import Restaurante.excessoes.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoNaoExisteErro;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;

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
