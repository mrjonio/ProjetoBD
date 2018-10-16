package Restaurante.entidade.pessoas;

//Essa classe vai sumir
import Restaurante.excessoes.CampoVazioErro;
import Restaurante.excessoes.ParametroInvalidoErro;
import Restaurante.excessoes.PessoaMenorDeIdadeErro;

/**
 * (Super) Classe modelo para os objetos do tipo "pessoa"; seus atributos e seu construtor.
 */
public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected int idade;
    protected String sexo;


    public Pessoa(String nome, String cpf, int idade, String sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.sexo = sexo;

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

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;

        Pessoa pessoa = (Pessoa) o;

        if (getIdade() != pessoa.getIdade()) return false;
        if (!getNome().equals(pessoa.getNome())) return false;
        if (!getCpf().equals(pessoa.getCpf())) return false;
        return getSexo().equals(pessoa.getSexo());
    }

    @Override
    public int hashCode() {
        int result = getNome().hashCode();
        result = 31 * result + getCpf().hashCode();
        result = 31 * result + getIdade();
        result = 31 * result + getSexo().hashCode();
        return result;
    }

    /**
     * Método para a exibição da pessoa; contendo o nome da pessoa, o cpf da pessoa, a idade da pessoa,
     * e o sexo da pessoa.
     * @return Retorna a pessoa.
     */
    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", idade=" + idade +
                ", sexo='" + sexo + '\'' +
                '}';
    }

    /**
     * Método para verificar se as informações passadas para a criação de um novo objeto "pessoa" estão todas corretas.
     * Verifica se o nome não está vazio, se o tamanho do cpf é o tamanho correto, se o gênero da pessoa foi selecionado e
     * se a pessoa é maior de idade. Caso haja algum erro, as exceções serão lançadas.
     * @return Retorna se é válido.
     * @throws PessoaMenorDeIdadeErro Pessoa menor de idade.
     * @throws ParametroInvalidoErro Parâmetro inválido.
     * @throws CampoVazioErro Campo de informações vazio.
     */
    public boolean eValido() throws PessoaMenorDeIdadeErro, ParametroInvalidoErro, CampoVazioErro {
        boolean valido = false;
        if (this.nome != null){
            char[] letrasDoCpf = this.cpf.toCharArray();
            if (letrasDoCpf.length < 14 && this.cpf != null){
                if (this.sexo == "Masculino" || this.sexo == "Feminino"){
                    if (this.idade >= 18){
                        valido = true;
                    } else{
                        throw new PessoaMenorDeIdadeErro();
                    }
                } else{
                    throw new ParametroInvalidoErro("Sexo");
                }
            } else {
                throw new ParametroInvalidoErro("Cpf");
            }
        } else {
            throw new ParametroInvalidoErro("Nome");
        }
        return valido;
    }
}
