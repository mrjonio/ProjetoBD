package Restaurante.camadasDeNegocio;


import Restaurante.camadasDeNegocio.interfaces.ICamadaPedidos;
import Restaurante.entidade.abstrato.Pedido;
import Restaurante.entidade.concretos.PratoCardapio;
import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.excessoes.NaoOuveLucroErro;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;
import Restaurante.repositorios.RepositorioPedidos;
import Restaurante.repositorios.interfaces.IRepositorioPedidos;

import java.time.LocalDate;

//Refazer
/**
 * Classe CamadaPedidos, seus atributos e construtor.
 * Implementação da interface ICamadaPedidos.
 */
public class CamadaPedidos implements ICamadaPedidos {
    private IRepositorioPedidos repositorioPedidos;
    private static ICamadaPedidos instancia;

    private CamadaPedidos(){
        this.repositorioPedidos = new RepositorioPedidos();
    }


    /**
     * Acesso à leitura da interface ICamadaPedidos.
     * @return Retorna a própria camada.
     */
    public static ICamadaPedidos getInstance(){
        if (instancia == null){
            instancia = new CamadaPedidos();
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
     * Método para calcular os gastos de um determinado cliente.
     * Caso o cliente não gaste nada ou não haja pedidos de determinado cliente, as exceções serão lançadas.
     * Ambas as exceções exibem uma mensagem ao usuário, informando o que houve de errado.
     * @param cliente Cliente que terá os gastos calculados.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     * @return Retorna o total dos gastos de determinado cliente.
     * @throws NaoOuveLucroErro Não houve lucro.
     * @throws ObjetosInsuficientesErro Objetos (pedidos) insuficientes.
     */
    @Override
    public double calcularGastoDeUmCliente(Pessoa cliente, LocalDate dataInicial, LocalDate dataFinal) throws NaoOuveLucroErro, ObjetosInsuficientesErro {
        boolean existePeloMenosUmPedidoParaCalcular = this.repositorioPedidos.buscarPeloMenosUmPedido(dataInicial, dataFinal, cliente);
        double gastoTotalPeriodoPedido;
        if (existePeloMenosUmPedidoParaCalcular) {
            gastoTotalPeriodoPedido = this.repositorioPedidos.calcularGastoDeUmCliente(cliente, dataInicial, dataFinal);
            if (gastoTotalPeriodoPedido <= 0) {
                throw new NaoOuveLucroErro();
            }
        } else {
            throw new ObjetosInsuficientesErro("Pedidos");
        }
        return gastoTotalPeriodoPedido;
    }


    /*
    *Metodo usado exlusivamente pela propria camada, para que se possa saber quais foram os 3 "melhores" de um vetor que será passado
    * como parametro.
     */

    private int[] saberIdexDoRanking(int[] vetorGeradoPeloRanking){
        int[] idexDosVencedores = new int[3];
        idexDosVencedores[0] = 0;
        int[] comparador = new int[3];
        comparador[0] = 0;
        for (int i = 0; i < vetorGeradoPeloRanking.length; i++) {
            if (vetorGeradoPeloRanking[i] >= comparador[0]){
                comparador[2] = comparador[1];
                idexDosVencedores[2] = idexDosVencedores[1];
                comparador[1] = comparador[0];
                idexDosVencedores[1] = idexDosVencedores[0];
                comparador[0] = vetorGeradoPeloRanking[i];
                idexDosVencedores[0] = i;
            } else {
                if (vetorGeradoPeloRanking[i] >= comparador[1]){
                    comparador[2] = comparador[1];
                    idexDosVencedores[2] = idexDosVencedores[1];
                    comparador[1] = vetorGeradoPeloRanking[i];
                    idexDosVencedores[1] = i;
                } else {
                    comparador[2] = vetorGeradoPeloRanking[i];
                    idexDosVencedores[2] = i;
                }
            }
        }
        return idexDosVencedores;
    }


    /*
    * Metodo que calcula os pratos que foram mais vendidos em um determinado periodo de tempo
    * um vetor de pratos deve ser passado como parametro, para que sejam considerados apenas pratos que ainda
    * existem no cardapio, os 3 pratos mais vendidos serão exibidos na gui.
    * Caso o vetor passado, ou o repositorio pedidos, possua menos de 3 objetos ou esteja vazio
    * um erro deverá ser mandado para o usuario
     */
    @Override
    public int[] criarRankingPratosMaisVendidos(PratoCardapio[] vetorDePratos, LocalDate dataInicial, LocalDate dataFinal) throws ObjetosInsuficientesErro {
        int[] contagemDeAparicoesDePratos = new int[vetorDePratos.length];
        int[] idexDosVencedores;
        boolean existePeloMenosUmPedido = this.repositorioPedidos.buscarPeloMenosUmPedido(dataInicial, dataFinal);
        if (existePeloMenosUmPedido){
            for (int i = 0; i < vetorDePratos.length; i++){
                contagemDeAparicoesDePratos[i] = this.repositorioPedidos.contarNumeroDeAparicoesPrato(vetorDePratos[i], dataInicial, dataFinal);
            }
            idexDosVencedores = saberIdexDoRanking(contagemDeAparicoesDePratos);
        } else {
            throw new ObjetosInsuficientesErro("Pedidos");
        }
        return idexDosVencedores;
    }

    /*
    * Metodo que calcula os clientes que mais consumiram em um determinado periodo de tempo
    * um vetor de clientes deve ser passado como parametro, para que sejam considerados apenas pessoas que ainda
    * existem no sistema, os 3 clientes que mais compraram serão exibidos na gui.
    * Caso o vetor passado, ou o repositorio pedidos, possua menos de 3 objetos ou esteja vazio
    * um erro deverá ser mandado para o usuario
    */
    @Override
    public int[] criarRankingClientesQueMaisConsomem(Pessoa[] vetorClientes, LocalDate dataInicial, LocalDate dataFinal) throws ObjetosInsuficientesErro {
        int[] contagemDeAparicoesDeClientes = new int[vetorClientes.length];
        int[] idexDosVencedores;
        boolean existePeloMenosUmPedido = this.repositorioPedidos.buscarPeloMenosUmPedido(dataInicial, dataFinal);
        if (existePeloMenosUmPedido){
            for (int i = 0; i < vetorClientes.length; i++){
                contagemDeAparicoesDeClientes[i] = this.repositorioPedidos.contarNumeroDeAparicoesCliente(vetorClientes[i], dataInicial, dataFinal);
            }
            idexDosVencedores = saberIdexDoRanking(contagemDeAparicoesDeClientes);
        } else {
            throw new ObjetosInsuficientesErro("Pedidos");
        }
        return idexDosVencedores;
    }

    /**
     * Método para armazenar um pedido.
     * @param pedidoQueSeraArmazenado Pedido que será armazenado.
     */
    @Override
    public void armazenarUmPedido(Pedido pedidoQueSeraArmazenado) {
        this.repositorioPedidos.adicionarPedido(pedidoQueSeraArmazenado);
    }

    /**
     * Método para a criação do vetor de pedidos.
     * Verifica se o repositório de pedidos está vazio.
     * Caso o repositório não esteja vazio, o vetor será criado.
     * Se o repositório estiver vazio então a exceção será criada e uma mensagem será
     * exibida ao usuário, informando o que houve de errado.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     * @return Retorna o vetor criado.
     * @throws ObjetosInsuficientesErro Objetos (pedidos) insuficientes.
     * @throws RepositorioVazioErro Repositório de pedidos vazio.
     */
    @Override
    public Pedido[] gerarVetorDePedidos(LocalDate dataInicial, LocalDate dataFinal) throws ObjetosInsuficientesErro, RepositorioVazioErro {
        boolean estaVazio = this.repositorioPedidos.estaVazio();
        Pedido[] vetorGerado;
        if (!estaVazio) {
            boolean existePeloMenosUmPedido = this.repositorioPedidos.buscarPeloMenosUmPedido(dataInicial, dataFinal);
            if (existePeloMenosUmPedido) {
                vetorGerado = this.repositorioPedidos.gerarVetorPedidos(dataInicial, dataFinal);
            } else {
                throw new ObjetosInsuficientesErro("Pedido");
            }
        } else {
            throw new RepositorioVazioErro("Pedidos");
        }
        return vetorGerado;
    }

    /**
     * Métodos para calcular gastos de um determinado cliente em um determinado tempo.
     * @param cliente Cliente que terá os gastos calculados.
     * @return Retorna os gastos do cliente.
     * @throws NaoOuveLucroErro Não houve lucro.
     * @throws ObjetosInsuficientesErro Objetos (pedidos) insuficientes.
     */
    @Override
    public double calcularGastoDeUmCliente(Pessoa cliente) throws NaoOuveLucroErro, ObjetosInsuficientesErro {
        LocalDate inicio =LocalDate.of(1900, 1, 1);
        LocalDate fim = LocalDate.of(2900, 1, 1);
        return calcularGastoDeUmCliente(cliente, inicio, fim);
    }
}
