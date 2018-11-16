package Restaurante.fachada.interfaceFachada;

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

/**
 * Abaixo temos a interface a ser implementada na fachada; contendo todas as assinaturas de método necessárias e suas exceções.
 */
public interface IFachadaGerente {

    int qtdMesas();
    void cadastrarUmFuncionario(Funcionario funcionarioQueSeraCadastrado) throws ObjetoJaExisteErro;
    void adicionarUmaMesa(Mesa mesaQueSeraAdicionada) throws ObjetoJaExisteErro;
    Funcionario buscarUmFuncionario(String cpfDoFuncionario) throws ObjetoNaoExisteErro;
    PratoCardapio buscarUmPratoDoCardapio(String nomeDoPrato) throws ObjetoNaoExisteErro;
    Reserva buscarUmaReserva(Reserva reservaBuscada) throws ObjetoNaoExisteErro;
    Mesa buscarUmaMesa(int index) throws RepositorioVazioErro, ObjetosInsuficientesErro;
    void alterarAtributosDeUmFuncionario(Funcionario atributosSubstituidos, String cpfAtualDoFuncionario) throws ObjetoNaoExisteErro;
    void deletarUmFuncionario(String cpfDoFuncionarioQueSeraRemovido) throws ObjetoNaoExisteErro;
    void retirarUmPratoDoCardapio(String nomeDoPratoQueSeraRetirado) throws ObjetoNaoExisteErro;
    void deletarMesasDoSistema(Mesa mesaQueSeraDeletada) throws RepositorioVazioErro, ObjetoEmUsoErro, ObjetosInsuficientesErro;
    void deletarPedidosDeUmaDeterminadaEpoca(LocalDateTime dataInicialRemocao, LocalDateTime dataFinalRemocao) throws ObjetosInsuficientesErro;
    double calcularLucroGeradoPorPedidosEmDetermiadoPeriodoDeTempo(LocalDateTime inicioDoPeriodo, LocalDateTime finalDoPeriodo) throws NaoOuveLucroErro, ObjetosInsuficientesErro;
    double calcularFolhaDePagamento();
    void alterarAtributoDeUmPrato(PratoCardapio novoPrato, String nomeAtual) throws ObjetoNaoExisteErro;



}
