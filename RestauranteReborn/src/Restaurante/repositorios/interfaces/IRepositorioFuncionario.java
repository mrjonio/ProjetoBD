package Restaurante.repositorios.interfaces;

import Restaurante.entidade.pessoas.Pessoa;

/**
 *  Abaixo temos a interface a ser implementada no repositório de funcionários, com suas assinaturas de métodos.
 */
public interface IRepositorioFuncionario {


    void adicionarPessoa(Pessoa objeto);
    double calcularFolhaDePagamento();
    boolean proucurarPessoa(String cpf);
    void removerPessoa(Pessoa funcionarioQueSeraRemovido);
    void mudarAtributosFuncionario(Pessoa funcionario, int index);
    Pessoa pegarFuncionario(String cpf);
    int pegarIdex(Pessoa funcionarioQueSeraPegoIdex);
    Pessoa[] gerarVetorDeFuncionarios();
    boolean verificarSeRepositorioEstaVazio();

}
