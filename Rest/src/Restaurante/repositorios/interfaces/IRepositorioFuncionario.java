package Restaurante.repositorios.interfaces;

import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;

/**
 *  Abaixo temos a interface a ser implementada no repositório de funcionários, com suas assinaturas de métodos.
 */
public interface IRepositorioFuncionario {

    void adicionarPessoa(Funcionario funcionarioQueSeraAdicionado); //OK
    double calcularFolhaDePagamento(); //OK
    boolean proucurarPessoa(String cpf); //OK
    void removerPessoa(Funcionario funcionarioQueSeraRemovido); //OK
    void mudarAtributosFuncionario(Funcionario funcionario, int index); //OK, TODO: INDEX inutil, mas nao causa problemas
    Funcionario pegarFuncionario(String cpf); //OK
    int pegarIdex(Funcionario funcionarioQueSeraPegoIdex); //INUTUL

}
