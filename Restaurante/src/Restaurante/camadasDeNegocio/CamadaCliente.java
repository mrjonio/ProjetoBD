package Restaurante.camadasDeNegocio;

import Restaurante.camadasDeNegocio.interfaces.ICamadaCliente;
import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.excessoes.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoNaoExisteErro;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;
import Restaurante.repositorios.RepositorioCliente;
import Restaurante.repositorios.interfaces.IRepositorioCliente;
//Deletar
/**
 * Classe CamadaCliente, seus atributos e construtor.
 * Implementação da interface ICamadaCliente.
 */
public class CamadaCliente implements ICamadaCliente{
    private IRepositorioCliente repositorioCliente;
    private static ICamadaCliente instancia;

    private CamadaCliente() {
        this.repositorioCliente = new RepositorioCliente();
    }

    /**
     * Acesso à leitura da interface ICamadaCliente.
     * @return Retorna a própria camada.
     */
    public static ICamadaCliente getInstance(){
        if (instancia == null){
            instancia = new CamadaCliente();
        }
        return instancia;
    }


    /**
     * Método para cadastrar um novo cliente. Verifica se o cpf do cliente a ser cadastrado já existe no repositório
     * de clientes. Caso não exista, o cliente será cadastrado e adicionado ao repositório de clientes; caso contrário,
     * a exceção é lançada e uma mensagem é exibida ao usuário, informando o que houve de errado.
     * @param clienteQueSeraCadastrado Informações do cliente que será cadastrado.
     * @throws ObjetoJaExisteErro Objeto (cpf do cliente) já existe.
     */
    @Override
    public void cadastrarNovoCliente(Pessoa clienteQueSeraCadastrado) throws ObjetoJaExisteErro {
        boolean clienteJaExiste = this.repositorioCliente.proucurarPessoa(clienteQueSeraCadastrado.getCpf());
        if (!clienteJaExiste){
            this.repositorioCliente.adicionarCliente(clienteQueSeraCadastrado);
        } else {
            throw new ObjetoJaExisteErro("Cliente");
        }
    }

    /**
     * Método para remover um cliente do repositório de clientes.
     * Busca, seleção e remoção das informações do cliente desejado.
     * @param cpfDoClienteQueSeraRemovido Cpf do cliente que será removido.
     * @throws ObjetoNaoExisteErro Objeto (cpf do cliente) não existe.
     */
    @Override
    public void removerUmCliente(String cpfDoClienteQueSeraRemovido) throws ObjetoNaoExisteErro{
               Pessoa clienteQueSeraRemovido = buscarUmCliente(cpfDoClienteQueSeraRemovido);
               this.repositorioCliente.removerPessoa(clienteQueSeraRemovido);
    }

    /**
     * Método para mudar os atributos (informações) de um cliente.
     * Busca o cliente através de seu cpf, seleciona o índice do atributo que será alterado e o altera.
     * @param cpfAtual Cpf do cliente que terá seu(s) atributo(s) modificado(s).
     * @param novosAtributos Novos atributos.
     * @throws ObjetoNaoExisteErro Objeto (cliente) não existe.
     */
    @Override
    public void mudarAtributosDeUmCliente(String cpfAtual, Pessoa novosAtributos) throws ObjetoNaoExisteErro{
            Pessoa clienteAtributosAtuais = buscarUmCliente(cpfAtual);
            int idex = this.repositorioCliente.pegarIdex(clienteAtributosAtuais);
            this.repositorioCliente.mudarAtributosDeCliente(novosAtributos, idex);

    }

    /**
     * Método para buscar as informações de um cliente. Verifica se o cpf solicitado está no repositório de clientes.
     * Caso o cpf esteja lá, então as informações do cliente são fornecidas; caso contrário, a exceção é lançada e
     * uma mensagem é exibida ao usuário, informando o que houve de errado.
     * @param cpfDoClienteBuscado Cpf do cliente que será buscado.
     * @return Retorna as informações do cliente.
     * @throws ObjetoNaoExisteErro Objeto (cpf do cliente) não existe.
     */
    @Override
    public Pessoa buscarUmCliente(String cpfDoClienteBuscado) throws ObjetoNaoExisteErro {
        Pessoa clienteBuscado;
        boolean clienteExiste = this.repositorioCliente.proucurarPessoa(cpfDoClienteBuscado);
        if (clienteExiste){
            clienteBuscado = this.repositorioCliente.pegarCliente(cpfDoClienteBuscado);
        }
        else {
            throw new ObjetoNaoExisteErro("Cliente");
        }
        return clienteBuscado;
    }

    /**
     * Método para a criação do vetor de clientes. Verifica se o repositório de clientes está vazio.
     * Caso o repositório não esteja vazio, então o vetor é gerado; caso contrário a exceção é lançada.
     * Caso o vetor gerado tenha tamanho menor que 3, então uma outra exceção é lançada.
     * Ambas as exceções exibem uma mensagem ao usuário, informando o que houve de errado.
     * @return O vetor criado.
     * @throws ObjetosInsuficientesErro Objetos (informações do cliente) insuficientes.
     * @throws RepositorioVazioErro Repositório Vazio.
     */
    @Override
    public Pessoa[] criarVetorDeClientes() throws ObjetosInsuficientesErro, RepositorioVazioErro {
        boolean taVazio = this.repositorioCliente.verificarSeRepositorioEstaVazio();
        Pessoa[] vetorGerado;
        if (!taVazio) {
            vetorGerado = this.repositorioCliente.criarVetorDeClientes();
            if (vetorGerado.length < 3){
                throw new ObjetosInsuficientesErro("Cliente");
            }
        } else {
            throw new RepositorioVazioErro("Repositorio Clientes");
        }
        return vetorGerado;
    }


}
