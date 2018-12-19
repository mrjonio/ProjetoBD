package Restaurante.fachada;

import Restaurante.camadasDeNegocio.*;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.camadasDeNegocio.interfaces.*;
import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.excessoes.*;
import Restaurante.excessoes.ObjetoExistencia.ObjetoEmUsoErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetosInsuficientesErro;
import Restaurante.fachada.interfaceFachada.IFachadaAtendente;
import Restaurante.fachada.interfaceFachada.IFachadaCozinheiro;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;
import Restaurante.fachada.interfaceFachada.IFachadaMesa;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Abaixo temos a classe Fachada, seus atributos e seu construtor.
 * Implementação da interface IFachadaGerente.
 */
public class Fachada implements IFachadaGerente, IFachadaCozinheiro, IFachadaAtendente, IFachadaMesa {
    private IControladorPedidos camadaPedidos;
    private IControladorMesa camadaMesa;
    private IControladorReservas camadaReservas;
    private IControladorFuncionario camadaFuncionario;
    private IControladorCadapio camadaCadapio;
    private IControladorIngredientes camadaIngrediente;
    private static Fachada instancia;

    private Fachada() {
        this.camadaPedidos = ControladorPedidos.getInstance();
        this.camadaMesa = ControladorMesa.getInstance();
        this.camadaReservas = ControladorReservas.getInstance();
        this.camadaFuncionario = ControladorFuncionario.getInstance();
        this.camadaCadapio = ControladorCardapio.getInstance();
        this.camadaIngrediente = ControladorIngredientes.getInstance();
    }


    public static Fachada getInstance(){
        if (instancia == null){
            instancia = new Fachada();
        }
        return instancia;
    }


    @Override
    public int qtdMesas() {
        return this.camadaMesa.qtdMesas();
    }

    /**
     * Abaixo estão todas as assinaturas de método que são necessárias para compor a fachada e suas exceções.
     */



    @Override
    public void cadastrarUmFuncionario(Funcionario funcionarioQueSeraCadastrado) throws ObjetoJaExisteErro {
        this.camadaFuncionario.adicionarFuncionario(funcionarioQueSeraCadastrado);
    }

    @Override
    public void adicionarUmaMesa(Mesa mesaQueSeraAdicionada) throws ObjetoJaExisteErro {
        this.camadaMesa.adicionarMesas(mesaQueSeraAdicionada);
    }

    @Override
    public PratoCardapio pegarUmPrato(int indexDoPrato) throws ObjetoNaoExisteErro {
        return this.camadaCadapio.pegarUmPrato(indexDoPrato);
    }

    @Override
    public PratoCardapio pegarUmPrato(String nomeDoPrato) throws ObjetoNaoExisteErro {
        return this.camadaCadapio.pegarUmPrato(nomeDoPrato);
    }

    @Override
    public void editarMesa(Mesa novosAtributos, Mesa mesaAntiga) throws ObjetoNaoExisteErro {
        this.camadaMesa.editarMesa(novosAtributos, mesaAntiga);
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
    public Reserva buscarUmaReserva(String cpf) throws ObjetoNaoExisteErro {
        return this.camadaReservas.buscarReserva(cpf);
    }

    @Override
    public Mesa buscarUmaMesa(int index) throws RepositorioVazioErro, ObjetosInsuficientesErro {
        return this.camadaMesa.pegarMesa(index);
    }

    @Override
    public void alterarAtributosDeUmFuncionario(Funcionario atributosSubstituidos, String cpfAtualDoFuncionario) throws ObjetoNaoExisteErro {
        this.camadaFuncionario.mudarAtributosDeUmFuncionario(cpfAtualDoFuncionario, atributosSubstituidos);
    }

    @Override
    public void deletarUmFuncionario(String cpfDoFuncionarioQueSeraRemovido) throws ObjetoNaoExisteErro {
        this.camadaFuncionario.removerFuncionario(cpfDoFuncionarioQueSeraRemovido);
    }

    @Override
    public void deletarMesasDoSistema(Mesa mesaQueSeraDeletada) throws RepositorioVazioErro, ObjetoEmUsoErro, ObjetosInsuficientesErro {
        this.camadaMesa.removerMesas(mesaQueSeraDeletada);
    }

    @Override
    public void deletarPedidosDeUmaDeterminadaEpoca(LocalDateTime dataInicialRemocao, LocalDateTime dataFinalRemocao) throws ObjetosInsuficientesErro {
        this.camadaPedidos.removerPedidosDeUmPeriodoDeTempo(dataInicialRemocao, dataFinalRemocao);
    }

    @Override
    public void verificarPratoPendente(String nomePrato) throws PratoPendenteErro {
        this.camadaPedidos.verificarPratoPendente(nomePrato);
    }

    @Override
    public void retirarUmPratoDoCardapio(String nomeDoPratoQueSeraRetirado) throws ObjetoNaoExisteErro {
        this.camadaCadapio.removerPratoDoCardapio(nomeDoPratoQueSeraRetirado);
    }

    @Override
    public double calcularLucroGeradoPorPedidosEmDetermiadoPeriodoDeTempo(LocalDateTime inicioDoPeriodo, LocalDateTime finalDoPeriodo) throws NaoOuveLucroErro, ObjetosInsuficientesErro {
        return this.camadaPedidos.calcularLucroDosPedidos(inicioDoPeriodo, finalDoPeriodo);
    }

    @Override
    public double calcularFolhaDePagamento() {
        return this.camadaFuncionario.calcularFolhaDePagamento();
    }

    @Override
    public void alterarAtributoDeUmPrato(PratoCardapio novoPrato, String nomeAtual) throws ObjetoNaoExisteErro {
        this.camadaCadapio.alterarAtributoDeUmPrato(novoPrato);
    }

    @Override
    public List<Pedido> criarListaPedidosDeterminadoPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) throws ObjetosInsuficientesErro {
        return this.camadaPedidos.criarListaPedidosDeterminadoPeriodo(dataInicial, dataFinal);
    }

    @Override
    public void removerUmDeterminadopedido(Pedido pedidoQueSeraRemovido) throws ObjetoNaoExisteErro {
        this.camadaPedidos.removerUmDeterminadopedido(pedidoQueSeraRemovido);
    }


    @Override
    public void adicionarIngrediente(Ingrediente ingrediente) throws ObjetoJaExisteErro {
        this.camadaIngrediente.adicionarIngrediente(ingrediente);
    }

    @Override
    public void aumentarEstoque(Ingrediente ingrediente) throws ObjetoNaoExisteErro {
        this.camadaIngrediente.aumentarEstoque(ingrediente);
    }

    @Override
    public void removerIngrediente(String nomeDoIngrediente) throws ObjetoNaoExisteErro {
        this.camadaIngrediente.removerIngrediente(nomeDoIngrediente);
    }

    @Override
    public Ingrediente pegarUmIngrediente(String nomeDoIngrediente) throws ObjetoNaoExisteErro {
        return this.camadaIngrediente.pegarUmIngrediente(nomeDoIngrediente);
    }

    //@Override
    //public Ingrediente pegarUmIngrediente(int indexDoIngrediente) throws ObjetoNaoExisteErro {
    //    return this.camadaIngrediente.pegarUmIngrediente(indexDoIngrediente);
    //}

    @Override
    public void diminuirQuantidadeDeIngrediente(Ingrediente ingredienteQueSeraDiminuido, int qtd) throws ObjetoNaoExisteErro, ObjetosInsuficientesErro {
        this.camadaIngrediente.diminuirQuantidadeDeIngrediente(ingredienteQueSeraDiminuido, qtd);
    }

    @Override
    public void alterarAtributoDeUmIngrediente(Ingrediente novosAtributos, String nomeAtual) throws ObjetoNaoExisteErro {
        this.camadaIngrediente.alterarAtributoDeUmIngrediente(novosAtributos, nomeAtual);
    }

    @Override
    public boolean verificarQuantidadeIngredientes(Ingrediente nomeDoIngrediente, int qtdNecessaria) throws ObjetoNaoExisteErro {
        return this.camadaIngrediente.verificarQuantidade(nomeDoIngrediente, qtdNecessaria);
    }


    @Override
    public void armazenarUmPedido(Pedido pedidoQueSeraArmazenado) {
        this.camadaPedidos.armazenarUmPedido(pedidoQueSeraArmazenado);
    }

    @Override
    public void adicionarPratoAoCardapio(PratoCardapio prato) throws ObjetoJaExisteErro {
        this.camadaCadapio.adicionarPratoAoCardapio(prato);
    }

    @Override
    public void fazerNovaReserva(Reserva reservaQueSeraFeita) throws ClienteJaReservouErro, ObjetoJaExisteErro {
        this.camadaReservas.fazerNovaReserva(reservaQueSeraFeita);
    }

    @Override
    public void deletarUmaReserva(Reserva reservaQueSeraDeletada) throws ObjetoEmUsoErro, ObjetoNaoExisteErro {
        this.camadaReservas.deletarUmaReserva(reservaQueSeraDeletada);
    }

    @Override
    public void mudarReserva(Reserva novosAtributos, Reserva antigaReserva) throws ObjetoJaExisteErro, ObjetoNaoExisteErro {
        this.camadaReservas.mudarReserva(novosAtributos, antigaReserva);
    }

}
