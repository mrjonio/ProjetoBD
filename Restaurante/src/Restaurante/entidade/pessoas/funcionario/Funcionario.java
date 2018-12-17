package Restaurante.entidade.pessoas.funcionario;

import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.excessoes.CampoVazioErro;
import Restaurante.excessoes.ParametroInvalidoErro;
import Restaurante.excessoes.PessoaMenorDeIdadeErro;

/**
 * Classe modelo para os objetos do tipo "funcionário"; seus atributos e seu construtor.
 */
//Classe precisa ser revista, a funcao dos funcionarios vai ter um impacto maior no projeto

public class Funcionario extends Pessoa {
    protected String funcao;
    protected double salario; //isso sai

    public Funcionario(String nome , String cpf, int idade, String sexo, String funcao, double salario) { //Nome sai, idade sai e sexo sai
        super(nome, cpf, idade, sexo);
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

    /**
     * Método para verificar se as informações para criar um novo funcionário são válidas.
     * Verifica se há algum campo vazio, se o tamanho do cpf é o tamanho correto, se o salário é menor que 900,
     * se a pessoa é maior de idade e se o gênero foi selecionado. Se tudo estiver ok, então o funcionário é válido.
     * @return Retorna se é válido.
     * @throws ParametroInvalidoErro Parâmetro inválido.
     * @throws PessoaMenorDeIdadeErro Pessoa menor de idade.
     * @throws CampoVazioErro Campo de informação vazio.
     */
    @Override
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Funcionario)) return false;
        if (!super.equals(o)) return false;

        Funcionario that = (Funcionario) o;

        if (Double.compare(that.getSalario(), getSalario()) != 0) return false;
        return getFuncao().equals(that.getFuncao());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + getFuncao().hashCode();
        temp = Double.doubleToLongBits(getSalario());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}