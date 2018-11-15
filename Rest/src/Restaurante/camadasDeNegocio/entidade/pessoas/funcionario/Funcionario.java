package Restaurante.camadasDeNegocio.entidade.pessoas.funcionario;

import Restaurante.excessoes.*;
import Restaurante.excessoes.ParametroValidade.ParametroInvalidoErro;

/**
 * Classe modelo para os objetos do tipo "funcionário"; seus atributos e seu construtor.
 */

public class Funcionario {
    private String nome;
    private String cpf;
    private int idade;
    private String sexo;
    private String funcao;
    private double salario;

    public Funcionario(String nome, String cpf, int idade, String sexo, String funcao, double salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.sexo = sexo;
        this.funcao = funcao;
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    } //Adicionar regras de negocio

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void transicaoSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Método para verificar se as informações para criar um novo funcionário são válidas.
     * Verifica se há algum campo vazio, se o tamanho do cpf é o tamanho correto, se o salário é menor que 900,
     * se a pessoa é maior de idade e se o gênero foi selecionado. Se tudo estiver ok, então o funcionário é válido.
     * @return Retorna se é válido.
     * @throws ParametroInvalidoErro Parâmetro inválido.
     * @throws PessoaMenorDeIdadeErro Pessoa menor de idade.
     * @throws CampoVazioErro Campo de informação vazio.
     */

    public boolean eValido() throws ParametroInvalidoErro, PessoaMenorDeIdadeErro, CampoVazioErro{
        boolean pessoaValida = false;
        if (!this.nome.isEmpty() && !this.cpf.isEmpty() && !this.sexo.isEmpty() && !this.funcao.isEmpty()){
            char[] tamanhoCpf = this.cpf.toCharArray();
            if (tamanhoCpf.length == 14 || this.salario < 900.00){
                if (this.idade >= 18){
                    if (this.sexo.equals("Masculino") || this.sexo.equals("Feminino")){
                        pessoaValida = true;
                    }
                    else{
                        throw new ParametroInvalidoErro("Sexo");
                    }
                } else{
                    throw new PessoaMenorDeIdadeErro();
                }
            } else {
                throw new ParametroInvalidoErro("Cpf");
            }
        } else {
            throw new CampoVazioErro("Nome, Cpf ou Sexo");
        }
        return pessoaValida;
    }


}