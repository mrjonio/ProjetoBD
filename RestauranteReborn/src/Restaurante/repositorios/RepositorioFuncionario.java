package Restaurante.repositorios;

import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.entidade.pessoas.funcionario.Funcionario;
import Restaurante.repositorios.interfaces.IRepositorioFuncionario;


import java.util.ArrayList;

/**
 * Abaixo temos a classe para o repositório de funcionários, que serve para armazenar em um arraylist todas os dados
 * dos funcionários cadastrados; e a implementação da interface IRepositorioFuncionario.
 * Criação do arraylist para guardar os objetos do tipo "funcionário".
 */

public class RepositorioFuncionario implements IRepositorioFuncionario {
    private ArrayList<Pessoa> funcionario;
    
    public RepositorioFuncionario(){
        this.funcionario = new ArrayList<>();
    }


    /**
     * Método para adicionar um novo funcionário.
     * @param funcionario Informações do funcionário a ser adicionado.
     */
    @Override
    public void adicionarPessoa(Pessoa funcionario){
        this.funcionario.add(funcionario);
    }


    /**
     * Método para verificar se um funcionário está no repositório, através de seu cpf.
     * @param cpf Cpf do funcionário.
     * @return Retorna se está ou não no repositório.
     */
    @Override
    public boolean proucurarPessoa(String cpf) {
        boolean flag = false;
        for (Pessoa aFuncionario : funcionario) {
            if (cpf.equals(aFuncionario.getCpf())) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    /**
     * Método para remover um funcionário do repositório.
     * @param funcionarioQueSeraRemovido Funcionário que será removido
     */
    @Override
    public void removerPessoa(Pessoa funcionarioQueSeraRemovido){
        this.funcionario.remove(funcionarioQueSeraRemovido);
    }

    /**
     * Método para mudar atributos de um funcionário, através do índice desse atributo.
     * @param funcionario Funcionário que terá os atributos modificados.
     * @param index Índice do atributo que será modificado.
     */
    @Override
    public void mudarAtributosFuncionario(Pessoa funcionario, int index) {
        this.funcionario.set(index, funcionario);
    }

    /**
     * Método para pegar um funcionário no repositório, através de seu cpf.
     * @param cpf Cpf do funcionário a ser buscado.
     * @return Retorna o funcionário que será pego.
     */
    @Override
    public Pessoa pegarFuncionario(String cpf) {
        Pessoa funcionarioQueSeraPego = null;
        for (Pessoa aFuncionario : this.funcionario) {
            if (aFuncionario.getCpf().equals(cpf)) {
                funcionarioQueSeraPego = aFuncionario;
            }
        }
        return funcionarioQueSeraPego;
    }


    /**
     * Método para calcular a folha de pagamento.
     * Calcula o pagamento total do salário de todos os funcionários.
     * @return Retorna o total.
     */
    @Override
    public double calcularFolhaDePagamento(){
        double total = 0;
        for (Pessoa aFuncionario : this.funcionario) {
            Funcionario funcionarioQueSeraSomadoOSalario = (Funcionario) aFuncionario;
            total += funcionarioQueSeraSomadoOSalario.getSalario();
        }
        return total;
    }

    /**]
     * Método para pegar o índice de um cliente no repositório.
     * @param funcionarioQueSeraPegoIdex Funcionário que será pego pelo índice.
     * @return Retorna o índice do funcionário.
     */
    @Override
    public int pegarIdex(Pessoa funcionarioQueSeraPegoIdex){
        return this.funcionario.indexOf(funcionarioQueSeraPegoIdex);
    }

    /**
     * Método para criar um vetor de funcionários.
     * @return Retorna o vetor criado.
     */
    @Override
    public Pessoa[] gerarVetorDeFuncionarios() {
        Pessoa[] vetorQueSeraDevolvido = new Funcionario[this.funcionario.size()];
        for (int i = 0; i < vetorQueSeraDevolvido.length; i++){
            vetorQueSeraDevolvido[i] = this.funcionario.get(i);
        }
        return vetorQueSeraDevolvido;
    }

    /**
     * Método para verificar se o repositório está vazio.
     * @return Retorna se está vazio ou não.
     */
    @Override
    public boolean verificarSeRepositorioEstaVazio() {
        return this.funcionario.isEmpty();
    }
}
