package Restaurante.camadasDeNegocio.entidade.pessoas.funcionario;

import Restaurante.excessoes.ParametroValidade.CampoVazioErro;
import Restaurante.excessoes.ParametroValidade.ParametroInvalidoErro;
import Restaurante.excessoes.ParametroValidade.PessoaMenorDeIdadeErro;

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

    public double getSalario() {
        return salario;
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

    public String getSexo() {
        return sexo;
    }


    /**
     * Método para verificar se as informações para criar um novo funcionário são válidas.
     * Verifica se há algum campo vazio, se o tamanho do cpf é o tamanho correto, se o salário é menor que 900,
     * se a pessoa é maior de idade e se o gênero foi selecionado. Se tudo estiver ok, então o funcionário é válido.
     * @throws ParametroInvalidoErro Parâmetro inválido.
     * @throws PessoaMenorDeIdadeErro Pessoa menor de idade.
     * @throws CampoVazioErro Campo de informação vazio.
     */

    public void eValido() throws ParametroInvalidoErro, PessoaMenorDeIdadeErro, CampoVazioErro{
        if (this.nome.isEmpty() || this.cpf.isEmpty() || this.sexo.isEmpty() || this.funcao.isEmpty()) {
            throw new CampoVazioErro("Nome, Cpf ou Sexo");
        } else {
            char[] tamanhoCpf = this.cpf.toCharArray();
            if (tamanhoCpf.length != 14 && !(this.salario < 900.00)) {
                throw new ParametroInvalidoErro("Cpf");
            } else {
                if (this.idade < 18) {
                    throw new PessoaMenorDeIdadeErro();
                } else {
                    if (!this.sexo.equals("Masculino") && !this.sexo.equals("Feminino")) {
                        throw new ParametroInvalidoErro("Sexo");
                    }
                }
            }
        }
    }


}