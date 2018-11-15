package Restaurante.fachada;

import Restaurante.camadasDeNegocio.*;
import Restaurante.camadasDeNegocio.interfaces.*;
import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.excessoes.*;
import Restaurante.excessoes.ObjetoExistencia.ObjetoEmUsoErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.fachada.interfaceFachada.IFachada;
import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;
import java.time.LocalDate;

/**
 * Abaixo temos a classe Fachada, seus atributos e seu construtor.
 * Implementação da interface IFachada.
 */
public class Fachada implements IFachada{
    private IControladorPedidos camadaPedidos;
    private IControladorMesa camadaMesa;
    private IControladorReservas camadaReservas;
    private IControladorFuncionario camadaFuncionario;
    private IControladorCadapio camadaCadapio;
    private static IFachada instancia;

    private Fachada() {
        this.camadaPedidos = ControladorPedidos.getInstance();
        this.camadaMesa = ControladorMesa.getInstance();
        this.camadaReservas = ControladorReservas.getInstance();
        this.camadaFuncionario = ControladorFuncionario.getInstance();
        this.camadaCadapio = ControladorCardapio.getInstance();
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
    public void cadastrarUmFuncionario(Funcionario funcionarioQueSeraCadastrado) throws ObjetoJaExisteErro {
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
    public Funcionario buscarUmFuncionario(String cpfDoFuncionario) throws ObjetoNaoExisteErro {
        return this.camadaFuncionario.buscarUmFuncionario(cpfDoFuncionario);
    }

    @Override
    public PratoCardapio buscarUmPratoDoCardapio(String nomeDoPrato) throws ObjetoNaoExisteErro {
        return this.camadaCadapio.pegarUmPrato(nomeDoPrato);
    }

    @Override
    public Reserva buscarUmaReserva(Reserva reservaBuscada) throws ObjetoNaoExisteErro {
        return this.camadaReservas.buscarReserva(reservaBuscada);
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
    public void alterarAtributosDeUmFuncionario(Funcionario atributosSubstituidos, String cpfAtualDoFuncionario) throws ObjetoNaoExisteErro {
        this.camadaFuncionario.mudarAtributosDeUmFuncionario(cpfAtualDoFuncionario, atributosSubstituidos);
    }

    @Override
    public void alterarAtributosDeUmPratoDoCardapio(PratoCardapio novosAtributo, String nomeAtualDoPrato) throws ObjetoNaoExisteErro {
        this.camadaCadapio.alterarAtributoDeUmPrato(novosAtributo, nomeAtualDoPrato);
    }

    @Override
    public void alterarAtributosDeUmaReserva(Reserva novosAtributos, Reserva reservaAntiga) throws ObjetoJaExisteErro, ObjetoNaoExisteErro {
        this.camadaReservas.mudarReserva(novosAtributos, reservaAntiga);
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
    public void cancelarOuDeletarUmaReserva(Reserva reservaQueSeraDeletada) throws ObjetoNaoExisteErro, ObjetoEmUsoErro {
        this.camadaReservas.deletarUmaReserva(reservaQueSeraDeletada);
    }

    @Override
    public boolean verificarExistenciaDeUmaReserva(Reserva reservaBuscada) throws ObjetoNaoExisteErro {
        Reserva reserva =  this.camadaReservas.buscarReserva(reservaBuscada);
        return true;
    }

    @Override
    public double calcularLucroGeradoPorPedidosEmDetermiadoPeriodoDeTempo(LocalDate inicioDoPeriodo, LocalDate finalDoPeriodo) throws NaoOuveLucroErro, ObjetosInsuficientesErro {
        return this.camadaPedidos.calcularLucroDosPedidos(inicioDoPeriodo, finalDoPeriodo);
    }

    @Override
    public double calcularFolhaDePagamento() {
        return this.camadaFuncionario.calcularFolhaDePagamento();
    }



}
