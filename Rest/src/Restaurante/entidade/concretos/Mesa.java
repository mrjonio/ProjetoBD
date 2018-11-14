package Restaurante.entidade.concretos;

import Restaurante.entidade.abstrato.Pedido;
import Restaurante.repositorios.RepositorioPedidos;
import Restaurante.repositorios.interfaces.IRepositorioPedidos;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe modelo para os objetos do tipo "mesa".
 */

//Muita coisa precisa ser feita aqui, adicionar atributo de numero, atributo de pedidos
public class Mesa {
    private int numero;
    private boolean disponibilidade;
    private IRepositorioPedidos pedidos;

    public Mesa(int numero, boolean disponibilidade) {
        this.numero = numero;
        this.disponibilidade = disponibilidade;
        this.pedidos = new RepositorioPedidos(); //Tem que ver isso aqui
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
