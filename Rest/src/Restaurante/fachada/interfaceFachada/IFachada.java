package Restaurante.fachada.interfaceFachada;

import Restaurante.entidade.abstrato.Pedido;
import Restaurante.entidade.abstrato.Reserva;
import Restaurante.entidade.concretos.Mesa;
import Restaurante.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.entidade.pessoas.funcionario.Funcionario;
import Restaurante.excessoes.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Abaixo temos a interface a ser implementada na fachada; contendo todas as assinaturas de método necessárias e suas exceções.
 */
public interface IFachada {

    int qtdMesas();
    void cadastrarUmFuncionario(Funcionario funcionarioQueSeraCadastrado) throws ObjetoJaExisteErro;
    void adicionarUmPratoAoCardapio(PratoCardapio pratoQueSeraAdicionado) throws ObjetoJaExisteErro;
    void cadastrarUmaNovaReserva(Reserva reservaQueSeraFeita) throws ObjetoJaExisteErro, ClienteJaReservouErro;
    void adicionarUmaMesa(Mesa mesaQueSeraAdicionada);
    void armazenarUmPedido(Pedido pedidoQueSeraArmazenado);
    Funcionario buscarUmFuncionario(String cpfDoFuncionario) throws ObjetoNaoExisteErro;
    PratoCardapio buscarUmPratoDoCardapio(String nomeDoPrato) throws ObjetoNaoExisteErro;
    Reserva buscarUmaReserva(Reserva reservaBuscada) throws ObjetoNaoExisteErro;
    Mesa buscarUmaMesa(int index) throws RepositorioVazioErro, ObjetosInsuficientesErro;
    void alterarAtributosDeUmFuncionario(Funcionario atributosSubstituidos, String cpfAtualDoFuncionario) throws ObjetoNaoExisteErro;
    void alterarAtributosDeUmPratoDoCardapio(PratoCardapio novosAtributo, String nomeAtualDoPrato) throws ObjetoNaoExisteErro;
    void alterarAtributosDeUmaReserva(Reserva novosAtributos, Reserva reservaAntiga) throws ObjetoJaExisteErro, ObjetoNaoExisteErro;
    void deletarUmFuncionario(String cpfDoFuncionarioQueSeraRemovido) throws ObjetoNaoExisteErro;
    void retirarUmPratoDoCardapio(String nomeDoPratoQueSeraRetirado) throws ObjetoNaoExisteErro;
    void cancelarOuDeletarUmaReserva(Reserva reservaQueSeraDeletada) throws ObjetoNaoExisteErro, ObjetoEmUsoErro;
    void deletarMesasDoSistema(Mesa mesaQueSeraDeletada) throws RepositorioVazioErro;
    void deletarPedidosDeUmaDeterminadaEpoca(LocalDate dataInicialRemocao, LocalDate dataFinalRemocao) throws ObjetosInsuficientesErro;
    boolean verificarExistenciaDeUmaReserva(Reserva reservaBuscada) throws ObjetoNaoExisteErro;
    double calcularLucroGeradoPorPedidosEmDetermiadoPeriodoDeTempo(LocalDate inicioDoPeriodo, LocalDate finalDoPeriodo) throws NaoOuveLucroErro, ObjetosInsuficientesErro;
    double calcularFolhaDePagamento();

}
