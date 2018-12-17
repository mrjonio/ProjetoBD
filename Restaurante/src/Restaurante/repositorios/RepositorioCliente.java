package Restaurante.repositorios;


import java.util.ArrayList;
import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.entidade.pessoas.cliente.Cliente;
import Restaurante.repositorios.interfaces.IRepositorioCliente;

/**
 * Abaixo temos a classe para o repositório de clientes, que serve para armazenar em um arraylist os dados de
 * todos os clientes cadastrados; e a implementação da interface IRepositórioCliente.
 * Criação do arraylist para guardar os objetos do tipo "Pessoa (clientes)".
 */

public class RepositorioCliente implements IRepositorioCliente {

    private ArrayList<Pessoa> clientes;

    public RepositorioCliente() {
        this.clientes = new ArrayList<>();


    }

    /**
     * Método para adicionar um novo cliente.
     * @param cliente Cliente a ser adicionado.
     */
    @Override
    public void adicionarCliente(Pessoa cliente) {
            this.clientes.add(cliente);
    }


    /**
     * Método verificar se o cliente está no repositório.
     * @param cpf Cpf do cliente a ser procurado.
     * @return Retorna se está ou não.
     */
    @Override
    public boolean proucurarPessoa(String cpf){
        boolean frag = false;
        for (Pessoa cliente : this.clientes) {
            if (cpf.equals(cliente.getCpf())) {
                frag = true;
                break;
            }
        }
        return frag;
    }


    /**
     * Método para remover um cliente do repositório.
     * @param cliente Cliente a ser removido.
     */
    @Override
    public void removerPessoa(Pessoa cliente){
        this.clientes.remove(cliente);
    }

    /**
     * Método para mudar atributos de um cliente.
     * @param novosAtributos Novos atributos.
     * @param index Índice dos atributos a serem alterados.
     */
    @Override
    public void mudarAtributosDeCliente(Pessoa novosAtributos, int index) {
        this.clientes.set(index, novosAtributos);
    }

    /**
     * Método para pegar um cliente no repositório.
     * @param cpfDoClienteQueSeraPego Cpf do cliente que será pego.
     * @return Retorna o cliente que foi pego.
     */
    @Override
    public Pessoa pegarCliente(String cpfDoClienteQueSeraPego) {
        Pessoa clienteQueSeraPego = null;
        for (Pessoa cliente : this.clientes) {
            if (cpfDoClienteQueSeraPego.equals(cliente.getCpf())) {
                clienteQueSeraPego = cliente;
                break;
            }
        }
        return clienteQueSeraPego;
    }

    /**
     * Método para pegar o índice de um cliente no repositório.
     * @param clienteQueSeraPegoIdex Índice do cliente.
     * @return Retorna o índice.
     */
    @Override
    public int pegarIdex(Pessoa clienteQueSeraPegoIdex) {
        return this.clientes.indexOf(clienteQueSeraPegoIdex);
    }

    /**
     * Método para a criação do vetor de clientes.
     * @return Retorna o vetor criado.
     */
    @Override
    public Pessoa[] criarVetorDeClientes() {
        Pessoa[] vetorClientes = new Cliente[this.clientes.size()];
        for (int i = 0; i < vetorClientes.length; i++){
            vetorClientes[i] = clientes.get(i);
        }
        return vetorClientes;
    }

    /**
     * Método para verificar se o repositório está vazio.
     * @return Retorna se está vazio ou não.
     */
    @Override
    public boolean verificarSeRepositorioEstaVazio() {
        return this.clientes.isEmpty();
    }
}
