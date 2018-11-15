package Restaurante.repositorios.interfaces;

import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;

/**
 *  Abaixo temos a interface a ser implementada no repositório de funcionários, com suas assinaturas de métodos.
 */
public interface IRepositorioFuncionario {


    void adicionarPessoa(Funcionario funcionarioQueSeraAdicionado);
    double calcularFolhaDePagamento();
    boolean proucurarPessoa(String cpf);
    void removerPessoa(Funcionario funcionarioQueSeraRemovido);
    void mudarAtributosFuncionario(Funcionario funcionario, int index);
    Funcionario pegarFuncionario(String cpf);
    int pegarIdex(Funcionario funcionarioQueSeraPegoIdex);
    boolean verificarSeRepositorioEstaVazio();

}
