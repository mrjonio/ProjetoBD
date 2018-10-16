package Restaurante.fachada;

import Restaurante.camadasDeNegocio.*;
import Restaurante.camadasDeNegocio.interfaces.*;
import Restaurante.entidade.abstrato.Pedido;
import Restaurante.entidade.abstrato.Reserva;
import Restaurante.entidade.concretos.Mesa;
import Restaurante.entidade.concretos.PratoCardapio;
import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.excessoes.*;
import Restaurante.fachada.interfaceFachada.IFachada;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Abaixo temos a classe Fachada, seus atributos e seu construtor.
 * Implementação da interface IFachada.
 */
public class Fachada implements IFachada{
    private ICamadaPedidos camadaPedidos;
    private ICamadaMesa camadaMesa;
    private ICamadaReservas camadaReservas;
    private ICamadaFuncionario camadaFuncionario;
    private ICamadaCliente camadaCliente;
    private ICamadaCadapio camadaCadapio;
    private static IFachada instancia;

    private Fachada() {
        this.camadaPedidos = CamadaPedidos.getInstance();
        this.camadaMesa = CamadaMesa.getInstance();
        this.camadaReservas = CamadaReservas.getInstance();
        this.camadaFuncionario = CamadaFuncionario.getInstance();
        this.camadaCliente = CamadaCliente.getInstance();
        this.camadaCadapio = CamadaCardapio.getInstance();
    }


    public static IFachada getInstance(){
        if (instancia == null){
            instancia = new Fachada();
        }
        return instancia;
    }

    /**
     * Abaixo estão todas as assinaturas de método que são necessárias para compor a fachada e suas exceções.
     */


    @Override
    public void cadastrarUmCliente(Pessoa clienteQueSeraCadastrado) throws ObjetoJaExisteErro {
        this.camadaCliente.cadastrarNovoCliente(clienteQueSeraCadastrado);
    }

    @Override
    public void cadastrarUmFuncionario(Pessoa funcionarioQueSeraCadastrado) throws ObjetoJaExisteErro {
        this.camadaFuncionario.adicionarFuncionario(funcionarioQueSeraCadastrado);
    }

    @Override
    public void cadastrarUmaNovaReserva(Reserva reservaQueSeraFeita) throws ObjetoJaExisteErro, ClienteJaReservouErro {
        this.camadaReservas.fazerNovaReserva(reservaQueSeraFeita);
    }

    @Override
    public void adicionarUmPratoAoCardapio(PratoCardapio pratoQueSeraAdicionado) throws ObjetoJaExisteErro {
        this.camadaCadapio.adicionarPratoAoCardapio(pratoQueSeraAdicionado);
    }

    @Override
    public void adicionarUmaMesa(Mesa mesaQueSeraAdicionada){
        this.camadaMesa.adicionarMesas(mesaQueSeraAdicionada);
    }

    @Override
    public void armazenarUmPedido(Pedido pedidoQueSeraArmazenado){
        this.camadaPedidos.armazenarUmPedido(pedidoQueSeraArmazenado);
    }

    @Override
    public Pessoa buscarUmFuncionario(String cpfDoFuncionario) throws ObjetoNaoExisteErro {
        return this.camadaFuncionario.buscarUmFuncionario(cpfDoFuncionario);
    }

    @Override
    public Pessoa buscarUmCliente(String cpfDoCliente) throws ObjetoNaoExisteErro {
        return this.camadaCliente.buscarUmCliente(cpfDoCliente);
    }

    @Override
    public PratoCardapio buscarUmPratoDoCardapio(String nomeDoPrato) throws ObjetoNaoExisteErro {
        return this.camadaCadapio.pegarUmPrato(nomeDoPrato);
    }

    @Override
    public Reserva buscarUmaReserva(Pessoa clienteQueReservou) throws ObjetoNaoExisteErro {
        return this.camadaReservas.buscarReserva(clienteQueReservou);
    }

    @Override
    public Mesa buscarUmaMesa(int index) throws RepositorioVazioErro, ObjetosInsuficientesErro {
        return this.camadaMesa.pegarMesa(index);
    }

    @Override
    public int qtdMesas() {
        return this.camadaMesa.qtdMesas();
    }

    @Override
    public void alterarAtributosDeUmCliente(Pessoa atributosSubstituidos, String cpfAtualDoCliente) throws ObjetoNaoExisteErro {
        this.camadaCliente.mudarAtributosDeUmCliente(cpfAtualDoCliente, atributosSubstituidos);
    }

    @Override
    public void alterarAtributosDeUmFuncionario(Pessoa atributosSubstituidos, String cpfAtualDoFuncionario) throws ObjetoNaoExisteErro {
        this.camadaFuncionario.mudarAtributosDeUmFuncionario(cpfAtualDoFuncionario, atributosSubstituidos);
    }

    @Override
    public void alterarAtributosDeUmPratoDoCardapio(PratoCardapio novosAtributo, String nomeAtualDoPrato) throws ObjetoNaoExisteErro {
        this.camadaCadapio.alterarAtributoDeUmPrato(novosAtributo, nomeAtualDoPrato);
    }

    @Override
    public void alterarAtributosDeUmaReserva(Reserva novosAtributos, Pessoa donoDaReserva) throws ObjetoJaExisteErro, ObjetoNaoExisteErro {
        this.camadaReservas.mudarReserva(novosAtributos, donoDaReserva);
    }

    @Override
    public void deletarUmCliente(String cpfDoClienteQueSeraRemovido) throws ObjetoNaoExisteErro {
        this.camadaCliente.removerUmCliente(cpfDoClienteQueSeraRemovido);
    }

    @Override
    public void deletarUmFuncionario(String cpfDoFuncionarioQueSeraRemovido) throws ObjetoNaoExisteErro {
        this.camadaFuncionario.removerFuncionario(cpfDoFuncionarioQueSeraRemovido);
    }

    @Override
    public void deletarMesasDoSistema(Mesa mesaQueSeraDeletada) throws RepositorioVazioErro {
        this.camadaMesa.removerMesas(mesaQueSeraDeletada);
    }

    @Override
    public void deletarPedidosDeUmaDeterminadaEpoca(LocalDate dataInicialRemocao, LocalDate dataFinalRemocao) throws ObjetosInsuficientesErro {
        this.camadaPedidos.removerPedidosDeUmPeriodoDeTempo(dataInicialRemocao, dataFinalRemocao);
    }

    @Override
    public void retirarUmPratoDoCardapio(String nomeDoPratoQueSeraRetirado) throws ObjetoNaoExisteErro {
        this.camadaCadapio.removerPratoDoCardapio(nomeDoPratoQueSeraRetirado);
    }

    @Override
    public void cancelarOuDeletarUmaReserva(Pessoa donoDaReserva) throws ObjetoNaoExisteErro, ObjetoEmUsoErro {
        this.camadaReservas.deletarUmaReserva(donoDaReserva);
    }

    @Override
    public void confirmarUmaReserva(Pessoa donoDaReserva) throws ReservaJaConfirmadaErro, ObjetoNaoExisteErro, ReservaExpiradaErro {
        this.camadaReservas.confirmarReserva(donoDaReserva);
    }

    @Override
    public boolean verificarSeUmaMesaEstaDisponivelEmDeterminadoMomento(Mesa mesaQueSeraVerificada, LocalDateTime momentoDesejado){
        return this.camadaReservas.verificacaoDeDisponibilidadeDeUmaMesa(mesaQueSeraVerificada, momentoDesejado);
    }

    @Override
    public boolean verificarExistenciaDeUmaReserva(String nomeDoDono) {
        return this.camadaReservas.verificarExistenciaDeUmaReserva(nomeDoDono);
    }

    @Override
    public double calcularLucroGeradoPorPedidosEmDetermiadoPeriodoDeTempo(LocalDate inicioDoPeriodo, LocalDate finalDoPeriodo) throws NaoOuveLucroErro, ObjetosInsuficientesErro {
        return this.camadaPedidos.calcularLucroDosPedidos(inicioDoPeriodo, finalDoPeriodo);
    }

    @Override
    public double calcularLucroGeradoPorUmCliente(Pessoa clienteQueSeraPesquisado, LocalDate inicioDoPeriodo, LocalDate finalDoPeriodo) throws NaoOuveLucroErro, ObjetosInsuficientesErro {
        return this.camadaPedidos.calcularGastoDeUmCliente(clienteQueSeraPesquisado, inicioDoPeriodo, finalDoPeriodo);
    }

    @Override
    public double calcularLucroGeradoPorUmCliente(Pessoa clienteQueSeraPesquisado) throws NaoOuveLucroErro, ObjetosInsuficientesErro {
        return this.camadaPedidos.calcularGastoDeUmCliente(clienteQueSeraPesquisado);
    }

    @Override
    public double calcularFolhaDePagamento() {
        return this.camadaFuncionario.calcularFolhaDePagamento();
    }

    @Override
    public int[] criarRankingDosPratosMaisVendidos(PratoCardapio[] vetorDePratos, LocalDate inicioDoPeriodo, LocalDate finalDoPeriodo) throws ObjetosInsuficientesErro {
        return this.camadaPedidos.criarRankingPratosMaisVendidos(vetorDePratos, inicioDoPeriodo, finalDoPeriodo);
    }

    @Override
    public int[] criarRankingDosClientesQueMaisGastam(Pessoa[] vetorDeCliente, LocalDate inicioDoPeriodo, LocalDate finalDoPeriodo) throws ObjetosInsuficientesErro {
        return this.camadaPedidos.criarRankingClientesQueMaisConsomem(vetorDeCliente, inicioDoPeriodo, finalDoPeriodo);
    }

    @Override
    public PratoCardapio[] gerarVetorDePratos() throws ObjetosInsuficientesErro, RepositorioVazioErro {
        return this.camadaCadapio.fazerVetorDePratos();
    }

    @Override
    public Pessoa[] gerarVetorDeClientes() throws ObjetosInsuficientesErro, RepositorioVazioErro {
        return this.camadaCliente.criarVetorDeClientes();
    }

    @Override
    public Pessoa[] gerarVetorDeFuncionarios() throws ObjetosInsuficientesErro, RepositorioVazioErro {
        return this.camadaFuncionario.retornarVetorDeFuncionarios();
    }

    @Override
    public Reserva[] gerarVetorDeReservas() throws ObjetosInsuficientesErro, RepositorioVazioErro {
        return this.camadaReservas.gerarVetorDeReservas();
    }

    @Override
    public Pedido[] gerarVetorDePedidos(LocalDate dataInicial, LocalDate dataFinal) throws ObjetosInsuficientesErro, RepositorioVazioErro {
        return this.camadaPedidos.gerarVetorDePedidos(dataInicial, dataFinal);
    }


}
