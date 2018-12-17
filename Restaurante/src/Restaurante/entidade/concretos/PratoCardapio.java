package Restaurante.entidade.concretos;

/**
 * Aqui temos a classe modelo para os objetos do tipo "prato"; seus atributos e o seu construtor.
 */

//Classe precisa ser reformulada, adicionar atributo Ingredientes (que será uma classe nova que deve ser criada)

import Restaurante.excessoes.ParametroInvalidoErro;

public class PratoCardapio {
    private String nome;
    private double preco;

    public PratoCardapio(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    /**
     * Método para verificar se as informações de um novo prato são válidas, caso não sejam, a exceção é lançada.
     * @return Retorna se é válido.
     * @throws ParametroInvalidoErro Parâmetro inválido.
     */
    public boolean eValido() throws ParametroInvalidoErro {
        boolean valido;
        if (!this.nome.isEmpty()){
            if (this.preco > 0){
                valido = true;
            } else {
                throw new ParametroInvalidoErro("Preco");
            }
        } else {
            throw new ParametroInvalidoErro("Nome");
        }
        return valido;
    }

    /**
     * Método para a exibição do prato, contendo o nome do prato e o preço do prato.
     * @return Retorna o prato.
     */
    @Override
    public String toString() {
        return "Prato: " +
                " Nome '" + nome + '\'' +
                " || Preco=" + preco ;
    }
}
