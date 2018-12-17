package Restaurante.fachada.interfaceFachada;

import Restaurante.entidade.abstrato.Pedido;
import Restaurante.entidade.abstrato.Reserva;
import Restaurante.entidade.concretos.Mesa;
import Restaurante.entidade.concretos.PratoCardapio;
import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.excessoes.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Abaixo temos a interface a ser implementada na fachada; contendo todas as assinaturas de método necessárias e suas exceções.
 */
public interface IFachada {

    int qtdMesas();
    void cadastrarUmCliente(Pessoa clienteQueSeraCadastrado) throws ObjetoJaExisteErro;
    void cadastrarUmFuncionario(Pessoa funcionarioQueSeraCadastrado) throws ObjetoJaExisteErro;
    void adicionarUmPratoAoCardapio(PratoCardapio pratoQueSeraAdicionado) throws ObjetoJaExisteErro;
    void cadastrarUmaNovaReserva(Reserva reservaQueSeraFeita) throws ObjetoJaExisteErro, ClienteJaReservouErro;
    void adicionarUmaMesa(Mesa mesaQueSeraAdicionada);
    void armazenarUmPedido(Pedido pedidoQueSeraArmazenado);
    Pessoa buscarUmFuncionario(String cpfDoFuncionario) throws ObjetoNaoExisteErro;
    Pessoa buscarUmCliente(String cpfDoCliente) throws ObjetoNaoExisteErro;
    PratoCardapio buscarUmPratoDoCardapio(String nomeDoPrato) throws ObjetoNaoExisteErro;
    Reserva buscarUmaReserva(Pessoa clienteQueReservou) throws ObjetoNaoExisteErro;
    Mesa buscarUmaMesa(int index) throws RepositorioVazioErro, ObjetosInsuficientesErro;
    void alterarAtributosDeUmCliente(Pessoa atributosSubstituidos, String cpfAtualDoCliente) throws ObjetoNaoExisteErro;
    void alterarAtributosDeUmFuncionario(Pessoa atributosSubstituidos, String cpfAtualDoFuncionario) throws ObjetoNaoExisteErro;
    void alterarAtributosDeUmPratoDoCardapio(PratoCardapio novosAtributo, String nomeAtualDoPrato) throws ObjetoNaoExisteErro;
    void alterarAtributosDeUmaReserva(Reserva novosAtributos, Pessoa donoDaReserva) throws ObjetoJaExisteErro, ObjetoNaoExisteErro;
    void deletarUmCliente(String cpfDoClienteQueSeraRemovido) throws ObjetoNaoExisteErro;
    void deletarUmFuncionario(String cpfDoFuncionarioQueSeraRemovido) throws ObjetoNaoExisteErro;
    void retirarUmPratoDoCardapio(String nomeDoPratoQueSeraRetirado) throws ObjetoNaoExisteErro;
    void cancelarOuDeletarUmaReserva(Pessoa donoDaReserva) throws ObjetoNaoExisteErro, ObjetoEmUsoErro;
    void deletarMesasDoSistema(Mesa mesaQueSeraDeletada) throws RepositorioVazioErro;
    void deletarPedidosDeUmaDeterminadaEpoca(LocalDate dataInicialRemocao, LocalDate dataFinalRemocao) throws ObjetosInsuficientesErro;
    void confirmarUmaReserva(Pessoa donoDaReserva) throws ReservaJaConfirmadaErro, ObjetoNaoExisteErro, ReservaExpiradaErro;
    boolean verificarSeUmaMesaEstaDisponivelEmDeterminadoMomento(Mesa mesaQueSeraVerificada, LocalDateTime momentoDesejado);
    boolean verificarExistenciaDeUmaReserva(String nomeDoDono);
    double calcularLucroGeradoPorPedidosEmDetermiadoPeriodoDeTempo(LocalDate inicioDoPeriodo, LocalDate finalDoPeriodo) throws NaoOuveLucroErro, ObjetosInsuficientesErro;
    double calcularLucroGeradoPorUmCliente(Pessoa clienteQueSeraPesquisado, LocalDate inicioDoPeriodo, LocalDate finalDoPeriodo) throws NaoOuveLucroErro, ObjetosInsuficientesErro;
    double calcularLucroGeradoPorUmCliente(Pessoa clienteQueSeraPesquisado) throws NaoOuveLucroErro, ObjetosInsuficientesErro;
    double calcularFolhaDePagamento();
    int[] criarRankingDosPratosMaisVendidos(PratoCardapio[] vetorDePratos, LocalDate inicioDoPeriodo, LocalDate finalDoPeriodo) throws ObjetosInsuficientesErro;
    int[] criarRankingDosClientesQueMaisGastam(Pessoa[] vetorDeCliente, LocalDate inicioDoPeriodo, LocalDate finalDoPeriodo) throws ObjetosInsuficientesErro;
    PratoCardapio[] gerarVetorDePratos() throws ObjetosInsuficientesErro, RepositorioVazioErro;
    Pessoa[] gerarVetorDeClientes() throws ObjetosInsuficientesErro, RepositorioVazioErro;
    Pessoa[] gerarVetorDeFuncionarios() throws ObjetosInsuficientesErro, RepositorioVazioErro;
    Reserva[] gerarVetorDeReservas() throws ObjetosInsuficientesErro, RepositorioVazioErro;
    Pedido[] gerarVetorDePedidos(LocalDate dataInicial, LocalDate dataFinal) throws ObjetosInsuficientesErro, RepositorioVazioErro;
}
