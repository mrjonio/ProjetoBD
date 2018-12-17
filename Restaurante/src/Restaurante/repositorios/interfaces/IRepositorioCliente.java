package Restaurante.repositorios.interfaces;

import Restaurante.entidade.pessoas.Pessoa;

/**
 *  Abaixo temos a interface a ser implementada no repositório de clientes, com suas assinaturas de métodos.
 */
public interface IRepositorioCliente {


    void adicionarCliente(Pessoa objeto);
    boolean proucurarPessoa(String cpf);
    void removerPessoa(Pessoa cliente);
    void mudarAtributosDeCliente(Pessoa novosAtributos, int index);
    Pessoa pegarCliente(String cpfDoClienteQueSeraPego);
    int pegarIdex(Pessoa clienteQueSeraPegoIdex);
    Pessoa[] criarVetorDeClientes();
    boolean verificarSeRepositorioEstaVazio();

}
