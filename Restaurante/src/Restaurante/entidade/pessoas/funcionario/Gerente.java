package Restaurante.entidade.pessoas.funcionario;

import Restaurante.excessoes.CampoVazioErro;
import Restaurante.excessoes.ParametroInvalidoErro;
import Restaurante.excessoes.PessoaMenorDeIdadeErro;

// Essa classe deixa de existir
public class Gerente extends Funcionario {
    private String senha;

    public Gerente(String nome, String cpf, int idade, String sexo, double salario, String senha) {
        super(nome, cpf, idade, sexo, "Gerente", salario);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gerente)) return false;
        if (!super.equals(o)) return false;

        Gerente gerente = (Gerente) o;

        return getSenha().equals(gerente.getSenha());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getSenha().hashCode();
        return result;
    }


    public boolean validarSenha(String senhaPassada) throws ParametroInvalidoErro {
        boolean valida = false;
        if (this.senha.equals(senhaPassada)){
            valida = true;
        } else {
            throw new ParametroInvalidoErro("Senha");
        }
        return valida;
    }
}
