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
import Restaurante.excessoes.ObjetoExistencia.ObjetosInsuficientesErro;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Abaixo temos a interface a ser implementada na fachada; contendo todas as assinaturas de método necessárias e suas exceções.
 */
public interface IFachadaGerente {


    //Funcionarios
    void cadastrarUmFuncionario(Funcionario funcionarioQueSeraCadastrado) throws ObjetoJaExisteErro;
    Funcionario buscarUmFuncionario(String cpfDoFuncionario) throws ObjetoNaoExisteErro;
    void alterarAtributosDeUmFuncionario(Funcionario atributosSubstituidos, String cpfAtualDoFuncionario) throws ObjetoNaoExisteErro;
    void deletarUmFuncionario(String cpfDoFuncionarioQueSeraRemovido) throws ObjetoNaoExisteErro;
    double calcularFolhaDePagamento();

    //Mesas
    void adicionarUmaMesa(Mesa mesaQueSeraAdicionada) throws ObjetoJaExisteErro;
    Mesa buscarUmaMesa(int index) throws RepositorioVazioErro, ObjetosInsuficientesErro;
    int qtdMesas();
    void deletarMesasDoSistema(Mesa mesaQueSeraDeletada) throws RepositorioVazioErro, ObjetoEmUsoErro, ObjetosInsuficientesErro;

    //Pratos
    PratoCardapio buscarUmPratoDoCardapio(String nomeDoPrato) throws ObjetoNaoExisteErro;
    public void verificarPratoPendente(String nomePrato) throws PratoPendenteErro;
    void retirarUmPratoDoCardapio(String nomeDoPratoQueSeraRetirado) throws ObjetoNaoExisteErro, Exception;
    void alterarAtributoDeUmPrato(PratoCardapio novoPrato, String nomeAtual) throws ObjetoNaoExisteErro;

    //Reserva
    Reserva buscarUmaReserva(Reserva reservaBuscada) throws ObjetoNaoExisteErro;
    Reserva buscarUmaReserva(String cpf) throws ObjetoNaoExisteErro;


    //Pedidos
    void deletarPedidosDeUmaDeterminadaEpoca(LocalDateTime dataInicialRemocao, LocalDateTime dataFinalRemocao) throws ObjetosInsuficientesErro;
    double calcularLucroGeradoPorPedidosEmDetermiadoPeriodoDeTempo(LocalDateTime inicioDoPeriodo, LocalDateTime finalDoPeriodo) throws NaoOuveLucroErro, ObjetosInsuficientesErro;
    List<Pedido> criarListaPedidosDeterminadoPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) throws ObjetosInsuficientesErro;
    void removerUmDeterminadopedido(Pedido pedidoQueSeraRemovido) throws ObjetoNaoExisteErro;

}
