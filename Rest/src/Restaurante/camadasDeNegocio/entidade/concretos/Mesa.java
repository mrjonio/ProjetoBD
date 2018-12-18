package Restaurante.camadasDeNegocio.entidade.concretos;


import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.excessoes.ParametroValidade.ParametroInvalidoErro;
import Restaurante.excessoes.ParametroValidade.ParametrosIguaisErro;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe modelo para os objetos do tipo "mesa".
 */

public class Mesa {
    private int numero;
    private String disponibilidade;
    private List<Pedido> pedidos;

    public Mesa(int numero, String disponibilidade) {
        this.numero = numero;
        this.disponibilidade = disponibilidade;
        this.pedidos = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String isDisponibilidade() {
        return disponibilidade;
    }

    public void mudaDisponibilidade(String disponibilidade) throws ParametroInvalidoErro, ParametrosIguaisErro {
        if(!this.disponibilidade.equals(disponibilidade)){
            if(disponibilidade.equals("Oculpado") || disponibilidade.equals("Reservado") || disponibilidade.equals("Vazia")){
                this.disponibilidade = disponibilidade;
            } else{
                throw new ParametroInvalidoErro(disponibilidade);
            }
        } else{
            throw new ParametrosIguaisErro(disponibilidade);
        }
    }

    public void adicionarPedido(Pedido novoPedido){
        novoPedido.setIdPedido(0);
        this.pedidos.add(novoPedido);
    }

    public void retirarPedido(Pedido pedidoQueSeraRetirado) throws ObjetoNaoExisteErro {
        Pedido pedidoBuscado = null;
        for (Pedido p: this.pedidos) {
            if (p.equals(pedidoQueSeraRetirado)){
                pedidoBuscado = p;
                break;
            }
        }
        if (pedidoBuscado != null){
            this.pedidos.remove(pedidoQueSeraRetirado);
        } else{
            throw new ObjetoNaoExisteErro("Pedido buscado");
        }

    }

    public List<Pedido> getPedidos(){
        return this.pedidos;
    }

    public boolean acabaramOsPedidos(){
        return this.pedidos.isEmpty();
    }

}
