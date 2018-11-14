package Restaurante.entidade.abstrato;

import Restaurante.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.entidade.pessoas.Pessoa;

import java.time.LocalDate;

/**
 * Classe modelo para os objetos do tipo "prato"; seus atributos e construtor.
 */
public class Pedido{
    private Pessoa clienteQuePediu; //não tem mais
    private PratoCardapio pratoPedido;
    private LocalDate dataDoPedido;

    public Pedido(LocalDate dataDoPedido){
        this.dataDoPedido = dataDoPedido;
    } //reformular

    public PratoCardapio getPratoPedido() {
        return pratoPedido;
    }

    public Pessoa getClienteQuePediu() {
        return clienteQuePediu;
    } //Não tem mais

    public void setClienteQuePediu(Pessoa clienteQuePediu) {
        this.clienteQuePediu = clienteQuePediu;
    } //Não tem mais

    public LocalDate getDataDoPedido() {
        return dataDoPedido;
    }

    public void setDataDoPedido(LocalDate dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    } //Não tem mais

    public void setPratoPedido(PratoCardapio pratoPedido) {
        this.pratoPedido = pratoPedido;
    } //Não tem mais

    /**
     * Método para exibição do pedido, contendo o nome do cliente, cpf do cliente, nome do prato pedido e a data que o pedido foi feito.
     * @return Retorna o pedido.
     */
    @Override
    public String toString() {  //refazer
        return "Pedido: " +
                "Nome do Cliente =" + clienteQuePediu.getNome() +
                " || Cpf do Cliente = " + clienteQuePediu.getCpf() +
                ", || Prato pedido=" + pratoPedido.getNome() +
                ", || Data do Pedido=" + dataDoPedido +
                '}';
    }

    //Talvez seja necessário mais metodos aqui
}
