package Restaurante.repositorios;


import Restaurante.entidade.abstrato.Pedido;
import Restaurante.entidade.concretos.PratoCardapio;
import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.repositorios.interfaces.IRepositorioPedidos;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Abaixo temos a classe para o repositório de pedidos, que serve para armazenar em um arraylist todas os dados
 * dos pedidos cadastrados; e a implementação da interface IRepositorioPedido.
 * Criação do arraylist para guardar os objetos do tipo "pedido".
 */

public class RepositorioPedidos implements IRepositorioPedidos {
    private ArrayList<Pedido> pedidos;

    public RepositorioPedidos() {
        this.pedidos = new ArrayList<>();
    }


    /**]
     * Método para contar o número de aparições(pedidos) de um determinado prato.
     * @param pratoQueSeraContado Prato que será contado.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     * @return Retorna a quantidade de aparições de determinado pedido.
     */
    @Override
    public int contarNumeroDeAparicoesPrato(PratoCardapio pratoQueSeraContado, LocalDate dataInicial, LocalDate dataFinal){
        int quantidadeDeAparicoes = 0;
        for (Pedido pedido : this.pedidos) {
            if (pedido.getDataDoPedido().isAfter(dataInicial) && pedido.getDataDoPedido().isBefore(dataFinal)) {
                if (pedido.getPratoPedido().equals(pratoQueSeraContado)) {
                    quantidadeDeAparicoes++;
                }
            }
        }
        return quantidadeDeAparicoes;
    }

    /**
     * Método para contar o número de aparições de um prato que um determinado cliente pediu.
     * @param clienteQueSeraContado Cliente que será terá os pedidos contados.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     * @return Retorna o número de vezes que o cliente pediu determinado prato.
     */
    @Override
    public int contarNumeroDeAparicoesCliente(Pessoa clienteQueSeraContado, LocalDate dataInicial, LocalDate dataFinal){
        int quantidadeDeAparicoes = 0;
        for (Pedido pedido : this.pedidos) {
            if (pedido.getDataDoPedido().isAfter(dataInicial) && pedido.getDataDoPedido().isBefore(dataFinal)) {
                if (pedido.getClienteQuePediu().equals(clienteQueSeraContado)) {
                    quantidadeDeAparicoes++;
                }
            }
        }
        return quantidadeDeAparicoes;
    }

    /**
     * Método para calcular o lucro de um determinado tempo.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     * @return Retorna o lucro de todos os pedidos de um determinado tempo.
     */
    @Override
    public double calcularLucro(LocalDate dataInicial, LocalDate dataFinal) {
        double lucroDosPedidos = 0;
        for (Pedido pedido : this.pedidos) {
            if (pedido.getDataDoPedido().isAfter(dataInicial) && pedido.getDataDoPedido().isBefore(dataFinal)) {
                lucroDosPedidos += pedido.getPratoPedido().getPreco();
            }
        }
        return lucroDosPedidos;
    }


    /**
     * Método para deletar pedidos.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     */
    @Override
    public void deletarPedidos(LocalDate dataInicial, LocalDate dataFinal){
        for (Pedido pedidoFor : pedidos){
            if (pedidoFor.getDataDoPedido().isAfter(dataInicial) && pedidoFor.getDataDoPedido().isBefore(dataFinal)){
                pedidos.remove(pedidoFor);
            }
        }
    }


    /**
     * Método para calcular gastos de um cliente em um determinado tempo.
     * @param clienteQueSeraCalculado Cliente que terá os gastos calculados.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     * @return Retorna o valor dos gastos do cliente.
     */
    @Override
    public double calcularGastoDeUmCliente(Pessoa clienteQueSeraCalculado, LocalDate dataInicial, LocalDate dataFinal){
        double gastoTotal = 0;
        for (Pedido pedido : this.pedidos) {
            if (pedido.getDataDoPedido().isAfter(dataInicial) && pedido.getDataDoPedido().isBefore(dataFinal)) {
                if (pedido.getClienteQuePediu().equals(clienteQueSeraCalculado)) {
                    gastoTotal += pedido.getPratoPedido().getPreco();
                }
            }
        }
        return gastoTotal;
    }

    /**
     * Método para adicionar um pedido.
     * @param pedidoQueSeraAdicionado Pedido que será adicionado.
     */
    @Override
    public void adicionarPedido(Pedido pedidoQueSeraAdicionado){
        this.pedidos.add(pedidoQueSeraAdicionado);
    }

    /**
     * Método para verificar se há algum pedido no repositório.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     * @return Retorna se há ou não pedidos.
     */
    @Override
    public boolean buscarPeloMenosUmPedido(LocalDate dataInicial, LocalDate dataFinal) {
        boolean existePeloMenosUm = false;
        for (Pedido pedido : this.pedidos){
            if (pedido.getDataDoPedido().isAfter(dataInicial) && pedido.getDataDoPedido().isBefore(dataFinal)){
                existePeloMenosUm = true;
                break;
            }
        }
        return existePeloMenosUm;
    }

    /**
     * Método para verificar se há ao menos um pedido pendente para um determinado cliente.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     * @param cliente Cliente que terá os pedidos verificados.
     * @return Retorna se há algum pedido ou não.
     */
    @Override
    public boolean buscarPeloMenosUmPedido(LocalDate dataInicial, LocalDate dataFinal, Pessoa cliente) {
        boolean existePeloMenosUm = false;
        for (Pedido pedido : this.pedidos){
            if (pedido.getDataDoPedido().isAfter(dataInicial) && pedido.getDataDoPedido().isBefore(dataFinal) && pedido.getClienteQuePediu().equals(cliente)){
                existePeloMenosUm = true;
            }
        }
        return existePeloMenosUm;
    }

    /**
     * Método para verificar se o repositório de pedidos está vazio.
     * @return Retorna se o repositório está vazio ou não.
     */
    @Override
    public boolean estaVazio() {
        return this.pedidos.isEmpty();
    }

    /**
     * Método para criar um vetor para os pedidos.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     * @return Retorna o vetor criado.
     */
    @Override
    public Pedido[] gerarVetorPedidos(LocalDate dataInicial, LocalDate dataFinal) {
        ArrayList<Pedido> temp = new ArrayList<>();
        for (Pedido pedidoA : pedidos){
            if (pedidoA.getDataDoPedido().isAfter(dataInicial) && pedidoA.getDataDoPedido().isBefore(dataFinal)){
                temp.add(pedidoA);
            }
        }
        Pedido[] vetorDePedidos = new Pedido[temp.size()];
        for (int i = 0; i < temp.size(); i++){
            vetorDePedidos[i] = temp.get(i);
        }
        return vetorDePedidos;
    }


}
