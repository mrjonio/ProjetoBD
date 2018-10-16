package Restaurante.entidade.pessoas.cliente;


import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.excessoes.CampoVazioErro;
import Restaurante.excessoes.ParametroInvalidoErro;
import Restaurante.excessoes.PessoaMenorDeIdadeErro;

//Essa classe vai sumir
/**
 * Classe modelo para os objetos do tipo "cliente"; seus atributos, e seu construtor.
 */
public class Cliente extends Pessoa {
    private String preferencia;
    private boolean bandeiraVermelha;

    public Cliente(String nome, String cpf, int idade, String sexo, String preferencia) {
        super(nome, cpf, idade, sexo);
        this.preferencia = preferencia;
        this.bandeiraVermelha = false;
    }


    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }


    /**
     * Método para saber se as informações para a criação de um objeto do tipo "cliente" são válidas.
     * Verifica se o cliente é menor de idade, se há algum campo de informações vazio, se uma preferência foi selecionada e
     * se há algum parâmentro invpalido. Caso haja algo de errado ou faltando, então as exceções são lançadas.
     * @return Retorna se é válido.
     * @throws ParametroInvalidoErro Parâmetro inválido.
     * @throws PessoaMenorDeIdadeErro Pessoa menor de idade.
     * @throws CampoVazioErro Campo de informações vazio.
     */
    @Override
    public boolean eValido() throws ParametroInvalidoErro, PessoaMenorDeIdadeErro, CampoVazioErro {
        boolean valido;
        if (!this.nome.isEmpty() && !this.cpf.isEmpty() && !this.sexo.isEmpty() && !this.preferencia.isEmpty()){
            char[] arrayCpf = this.cpf.toCharArray();
            if (arrayCpf.length == 14){
                if (this.idade >= 18){
                    if (this.preferencia.equals("Vegano") || this.preferencia.equals("Carnivoro") || this.preferencia.equals("Vegetariano")){
                        if (this.sexo.equals("Masculino") || this.sexo.equals("Feminino")){
                            valido = true;
                        } else {
                            throw new ParametroInvalidoErro("Sexo");
                        }
                    } else {
                        throw new ParametroInvalidoErro("Preferencia");
                    }
                } else {
                    throw new PessoaMenorDeIdadeErro();
                }
            } else {
                throw new ParametroInvalidoErro("Cpf");
            }
        } else {
            throw new CampoVazioErro("Infos");
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        if (!super.equals(o)) return false;

        Cliente cliente = (Cliente) o;

        return getPreferencia().equals(cliente.getPreferencia());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getPreferencia().hashCode();
        return result;
    }

    /**
     * Método para a exibição do cliente, contendo o nome do cliente, a preferência culinária do cliente,
     * o cpf do cliente, a idade do cliente e o sexo do cliente.
     * @return Retorna o cliente.
     */
    @Override
    public String toString() {
        return "Cliente: " +
                "Nome= " + nome + '\'' +
                " || Preferencia culinaria =" + preferencia + '\'' +
                " || Cpf = " + cpf + '\'' +
                " || Idade= " + idade +
                " || sexo= " + sexo + '\'';
    }

    public boolean isBandeiraVermelha() {
        return bandeiraVermelha;
    }

    public void setBandeiraVermelha(boolean bandeiraVermelha) {
        this.bandeiraVermelha = bandeiraVermelha;
    }
}
