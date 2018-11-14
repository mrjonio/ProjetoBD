package Restaurante.camadasDeNegocio;

import Restaurante.camadasDeNegocio.interfaces.IControladorCadapio;
import Restaurante.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.excessoes.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoNaoExisteErro;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;
import Restaurante.repositorios.RepositorioCardapio;
import Restaurante.repositorios.interfaces.IRepositorioCardapio;

/**
 *  Classe CamadaCardápio, seus atributos e construtor.
 *  Implementação da interface IControladorCadapio.
 */
public class ControladorCardapio implements IControladorCadapio {
	
	private IRepositorioCardapio repositorioCardapio;
	private static IControladorCadapio instancia;

	private ControladorCardapio() {
		this.repositorioCardapio = new RepositorioCardapio();
	}

	/**
	 * Acesso à leitura da interface da camada cardápio.
	 * @return A própria camada é retornada.
	 */
	public static IControladorCadapio getInstance(){
		if (instancia == null){
			instancia = new ControladorCardapio();
		}
		return instancia;
	}

	/**
	 * Método para adicionar um novo prato ao cardápio. Verificação se o novo prato já existe no repositório de cardápio,
	 * caso o prato não exista, ele será adicionado ao repositório, caso ele exista no repositório, então a exceção será
	 * lançada e uma mensagem será exibida ao usuário informando o que houve de errado.
	 * @param prato Prato a ser adicionado.
	 * @throws ObjetoJaExisteErro Objeto (nome do prato) já existe.
	 */
	@Override
	public void adicionarPratoAoCardapio(PratoCardapio prato) throws ObjetoJaExisteErro{
		boolean pratoExiste = this.repositorioCardapio.verificarExistenciaPrato(prato.getNome());
		if (!pratoExiste) {
			this.repositorioCardapio.adicionarPratoAoCardapio(prato);
		}else {
			throw new ObjetoJaExisteErro("Prato do cardapio");
		}
	}


	/**
	 * Método para remover um prato do repositório de cardápio. Busca, seleção e remoção do prato desejado.
	 * @param nomePratoRemovido Nome do prato que será removido.
	 * @throws ObjetoNaoExisteErro Objeto (prato) não existe.
	 */
	@Override
	public void removerPratoDoCardapio(String nomePratoRemovido) throws ObjetoNaoExisteErro {
		PratoCardapio pratoQueSeraRemovido = pegarUmPrato(nomePratoRemovido);
		this.repositorioCardapio.removerPrato(pratoQueSeraRemovido);

	}

	/**
	 * Método para pegar um prato. Verifica a existência do prato no repositório de cardápio. Caso o prato exista,
	 * então ele será pego, caso o prato não exista no repositório, então a exceção é lançada e uma mensagem é exibida
	 * ao usuário, informando o que houve de errado.
	 * @param nomeDoPrato Nome do prato a ser pego.
	 * @return Retorno do prato desejado.
	 * @throws ObjetoNaoExisteErro Objeto (nome do prato) não existe.
	 */
	@Override
	public PratoCardapio pegarUmPrato(String nomeDoPrato) throws ObjetoNaoExisteErro{
		boolean pratoExiste = this.repositorioCardapio.verificarExistenciaPrato(nomeDoPrato);
		PratoCardapio prato;
		if (pratoExiste) {
			prato = this.repositorioCardapio.pegarPrato(nomeDoPrato);
		}
		else {
			throw new ObjetoNaoExisteErro("Prato");
		}
		return prato;
	}

	/**
	 * Método para alterar o atributo de um prato. Busca o prato desejado, seleciona o índice da informação que será
	 * alterada e a altera.
	 * @param novoPrato Nome do novo prato, depois da alteração de atributos (informações).
	 * @param nomeAtual Nome do prato que terá o(s) atributo(s) alterados.
	 * @throws ObjetoNaoExisteErro Objeto (prato) não existe.
	 */
	@Override
	public void alterarAtributoDeUmPrato(PratoCardapio novoPrato, String nomeAtual) throws ObjetoNaoExisteErro{
		PratoCardapio pratoAtual = pegarUmPrato(nomeAtual);
		int index = this.repositorioCardapio.pegarIdex(pratoAtual);
		this.repositorioCardapio.alterarAtributoPrato(novoPrato, index);
	}

	/**
	 * Método para a criação do vetor de pratos. Verifica se o repositório de cardápio está vazio.
	 * Caso o repositório não esteja vazio, então o vetor é gerado, caso contrário, o vetor não será gerado, e uma
	 * exceção será lançada. Se o tamanho do vetor for menor que 3, então outra exceção será lançada. Ambas as exceções
	 * vão exibir uma mensagem ao usuário, informando o que houve de errado.
	 * @return Vetor gerado (vetor de pratos).
	 * @throws ObjetosInsuficientesErro Objetos (informações do prato) insuficientes.
	 * @throws RepositorioVazioErro Repositório vazio.
	 */
	@Override
	public PratoCardapio[] fazerVetorDePratos() throws ObjetosInsuficientesErro, RepositorioVazioErro {
		boolean repositorioVazio = this.repositorioCardapio.verificarSeRepositorioEstaVazio();
		PratoCardapio[] vetorDePratos;
		if (!repositorioVazio){
			vetorDePratos = this.repositorioCardapio.criarVetorPratos();
			if (vetorDePratos.length < 3){
				throw new ObjetosInsuficientesErro("Pratos");
			}
		} else {
			throw new RepositorioVazioErro("Repositorio Pratos");
		}
		return vetorDePratos;
	}
}

	
	

