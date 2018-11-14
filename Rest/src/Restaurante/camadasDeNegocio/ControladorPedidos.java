package Restaurante.camadasDeNegocio;


import Restaurante.camadasDeNegocio.interfaces.IControladorPedidos;
import Restaurante.entidade.abstrato.Pedido;
import Restaurante.excessoes.NaoOuveLucroErro;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.repositorios.RepositorioPedidos;
import Restaurante.repositorios.interfaces.IRepositorioPedidos;

import java.time.LocalDate;

//Refazer
/**
 * Classe ControladorPedidos, seus atributos e construtor.
 * Implementação da interface IControladorPedidos.
 */
public class ControladorPedidos implements IControladorPedidos {
    private IRepositorioPedidos repositorioPedidos;
    private static IControladorPedidos instancia;

    private ControladorPedidos(){
        this.repositorioPedidos = new RepositorioPedidos();
    }


    /**
     * Acesso à leitura da interface IControladorPedidos.
     * @return Retorna a própria camada.
     */
    public static IControladorPedidos getInstance(){
        if (instancia == null){
            instancia = new ControladorPedidos();
        }
        return instancia;
    }

    /**
     * Método para remover um pedido de um determinado tempo.
     * Verifica se há ao menos um pedido no repositório de pedidos, se houver, então determinado pedido poderá ser removido,
     * caso contrário, a exceção é lançada e uma mensagem é exibida ao usuário, informando o que houve de errado.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     * @throws ObjetosInsuficientesErro Objetos (pedidos) insuficientes.
     */
    @Override
    public void removerPedidosDeUmPeriodoDeTempo(LocalDate dataInicial, LocalDate dataFinal) throws ObjetosInsuficientesErro {
        boolean existePeloMenosUmPedidoParaSerRemovido = this.repositorioPedidos.buscarPeloMenosUmPedido(dataInicial, dataFinal);
        if (existePeloMenosUmPedidoParaSerRemovido) {
            this.repositorioPedidos.deletarPedidos(dataInicial, dataFinal);
        } else {
            throw new ObjetosInsuficientesErro("Pedidos");
        }
    }

    /**
     * Método para calcular o lucro dos pedidos de um determinado tempo.
     * Caso não haja lucro ou pedidos para ser calculados, as exceções são lançadas.
     * Ambas as exceções exibem uma mensagem ao usuário, informando o que houve de errado.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     * @return Retorna o lucro dos pedidos.
     * @throws NaoOuveLucroErro Não houve lucros em determinado período de tempo.
     * @throws ObjetosInsuficientesErro Não houve pedidos para serem calculados em determinado período de tempo.
     */
    @Override
    public double calcularLucroDosPedidos(LocalDate dataInicial, LocalDate dataFinal) throws NaoOuveLucroErro, ObjetosInsuficientesErro {
        boolean existePeloMenosUmPratoParaCalcular = this.repositorioPedidos.buscarPeloMenosUmPedido(dataInicial, dataFinal);
        double lucroDosPratos;
        if (existePeloMenosUmPratoParaCalcular) {
            lucroDosPratos = this.repositorioPedidos.calcularLucro(dataInicial, dataFinal);
            if (lucroDosPratos <= 0) {
                throw new NaoOuveLucroErro();
            }
        } else {
            throw new ObjetosInsuficientesErro("Pedidos");
        }
        return lucroDosPratos;
    }



    /**
     * Método para armazenar um pedido.
     * @param pedidoQueSeraArmazenado Pedido que será armazenado.
     */
    @Override
    public void armazenarUmPedido(Pedido pedidoQueSeraArmazenado) {
        this.repositorioPedidos.adicionarPedido(pedidoQueSeraArmazenado);
    }


}
