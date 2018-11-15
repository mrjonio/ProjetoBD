package Restaurante.fachada.interfaceFachada;

import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;
import Restaurante.excessoes.*;
import Restaurante.excessoes.ObjetoExistencia.ObjetoEmUsoErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;

import java.time.LocalDate;

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
