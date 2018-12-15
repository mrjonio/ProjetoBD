package Restaurante.repositorios;


import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.repositorios.interfaces.IRepositorioPedidos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Método para calcular o lucro de um determinado tempo.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     * @return Retorna o lucro de todos os pedidos de um determinado tempo.
     */
    @Override
    public double calcularLucro(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        double lucroDosPedidos = 0;
        for (Pedido pedido : this.pedidos) {
            if (pedido.getDataDoPedido().isAfter(dataInicial) && pedido.getDataDoPedido().isBefore(dataFinal)) {
                //TODO: arrumar isso aqui
                //lucroDosPedidos += pedido.getPratoPedido().getPreco();
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
    public void deletarPedidos(LocalDateTime dataInicial, LocalDateTime dataFinal){
        for (Pedido pedidoFor : pedidos){
            if (pedidoFor.getDataDoPedido().isAfter(dataInicial) && pedidoFor.getDataDoPedido().isBefore(dataFinal)){
                pedidos.remove(pedidoFor);
            }
        }
    }


    /**
     * Método para adicionar um pedido.
     * @param pedidoQueSeraAdicionado Pedido que será adicionado.
     */
    @Override
    public void adicionarPedido(Pedido pedidoQueSeraAdicionado){
        System.out.println("Pedido armazenado");
        this.pedidos.add(pedidoQueSeraAdicionado);
    }

    @Override
    public void removerPedido(Pedido pedidoQueSeraRemovido) {
        this.pedidos.remove(pedidoQueSeraRemovido);
    }

    @Override
    public Pedido buscarPedido(Pedido pedidoQueSeraBuscado) {
        Pedido pedido = null;
        for (Pedido ped: this.pedidos) {
            if(ped.equals(pedidoQueSeraBuscado)){
                pedido = ped;
            }
        }
        return pedido;
    }

    @Override
    public List<Pedido> gerarVetorPedido(LocalDateTime inicio, LocalDateTime fim) {
        List<Pedido> temp = new ArrayList<>();
        for (Pedido ped: this.pedidos) {
            if(ped.getDataDoPedido().isAfter(inicio) && ped.getDataDoPedido().isBefore(fim)){
                temp.add(ped);
            }
        }
        return temp;
    }


}
