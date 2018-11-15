package Restaurante.camadasDeNegocio;

import Restaurante.camadasDeNegocio.interfaces.IControladorFuncionario;
import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.repositorios.RepositorioFuncionario;
import Restaurante.repositorios.interfaces.IRepositorioFuncionario;

/**
 * Classe CamadaFuncionário, seus atributos e construtor.
 * Implementação da interface ICamadaFuncionário.
 */
public class ControladorFuncionario implements IControladorFuncionario {
    private IRepositorioFuncionario repositorioFuncionario;
    private static IControladorFuncionario instancia;

    private ControladorFuncionario() {
        this.repositorioFuncionario = new RepositorioFuncionario();
    }

    /**
     * Acesso à leitura da interface ICamadaFuncionário.
     * @return Retorna a própria camada.
     */
    public static IControladorFuncionario getInstance(){
        if (instancia == null){
            instancia = new ControladorFuncionario();
        }
        return instancia;
    }

    /**
     * Método para adicionar um novo funcionário.
     * Verifica se o cpf do funcionário que será adicionado já existe no repositório de funcionários.
     * Caso o cpf ainda não exista no repositório, então o funcionário será adicionado ao repositório, caso contrário,
     * a exceção é lançada e uma mensagem é exibida ao usuário, informando o que houve de errado.
     * @param funcionarioParaSerAdicionado Informações do cliente que será adicionado.
     * @throws ObjetoJaExisteErro Objeto (cpf do funcionário) já existe.
     */
    @Override
    public void adicionarFuncionario(Funcionario funcionarioParaSerAdicionado) throws ObjetoJaExisteErro {
        boolean funcionarioJaExiste = this.repositorioFuncionario.proucurarPessoa(funcionarioParaSerAdicionado.getCpf());
        if (!funcionarioJaExiste){
            this.repositorioFuncionario.adicionarPessoa(funcionarioParaSerAdicionado);
        } else {
            throw new ObjetoJaExisteErro("Funcionario");
        }
    }

    /**
     * Método para remover um funcionário.
     * Busca e seleção do funcionário desejado, através do seu cpf.
     * Remoção do funcionário do repositório de funcionários.
     * @param cpfDoFuncionarioQueSeraRemovido Cpf do funcionário que será removido.
     * @throws ObjetoNaoExisteErro Objeto (cpf do cliente) não existe.
     */
    @Override
    public void removerFuncionario(String cpfDoFuncionarioQueSeraRemovido) throws ObjetoNaoExisteErro{
            Funcionario funcionarioQueSeraRemovido = buscarUmFuncionario(cpfDoFuncionarioQueSeraRemovido);
            this.repositorioFuncionario.removerPessoa(funcionarioQueSeraRemovido);
    }

    /**
     * Método para mudar atributos (informações) de um funcionário.
     * Busca e seleciona o funcionário que terá os atributos modificados, através do seu cpf.
     * Seleciona o índice do atributo que será alterado e o altera.
     * @param cpf Cpf do funcionário que terá os atributos alterados.
     * @param funcionarioComNovosParamentros Novas informações do funcionário.
     * @throws ObjetoNaoExisteErro Objeto (cpf do funcionário) mão existe.
     */
    @Override
    public void mudarAtributosDeUmFuncionario(String cpf, Funcionario funcionarioComNovosParamentros) throws ObjetoNaoExisteErro{
            Funcionario funcionarioQueSeraSubstituido = buscarUmFuncionario(cpf);
            int idexDoFuncionarioQueSeraSubstituido = this.repositorioFuncionario.pegarIdex(funcionarioQueSeraSubstituido);
            this.repositorioFuncionario.mudarAtributosFuncionario(funcionarioComNovosParamentros, idexDoFuncionarioQueSeraSubstituido);
    }

    /**
     * Método para a busca e exibição das informações de um funcionário.
     * Verifica se o funcionário existe no repositório de funcionário, através do seu cpf.
     * Caso o funcionário exista, então as informações do funcionário serão fornecidas; caso contrário,
     * a exceção é lançada e uma mensagem é exibida ao usuário, informando o que houve de errado.
     * @param cpfDoFuncionarioBuscado Cpf do funcionário que será buscado.
     * @return Retorna as informações do funcionário que foi solicitado.
     * @throws ObjetoNaoExisteErro Objeto (cpf do funcionário) não existe.
     */
    @Override
    public Funcionario buscarUmFuncionario(String cpfDoFuncionarioBuscado) throws ObjetoNaoExisteErro {
        boolean funcionarioExiste = this.repositorioFuncionario.proucurarPessoa(cpfDoFuncionarioBuscado);
        Funcionario funcionarioBuscado;
        if (funcionarioExiste){
            funcionarioBuscado = this.repositorioFuncionario.pegarFuncionario(cpfDoFuncionarioBuscado);
        } else{
            throw new ObjetoNaoExisteErro("Funcionario");
        }
        return  funcionarioBuscado;
    }


    /**
     * Método para calcular a folha de pagamento.
     * Calcula o valor de todos os salários dos funcionários.
     * @return Retorna o valor da folha de pagamento.
     */
    @Override
    public double calcularFolhaDePagamento() {
        final double folhaDePagamento;
        folhaDePagamento = this.repositorioFuncionario.calcularFolhaDePagamento();
        return folhaDePagamento;
    }
}

