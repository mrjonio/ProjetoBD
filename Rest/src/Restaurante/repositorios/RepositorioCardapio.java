package Restaurante.repositorios;

import java.util.ArrayList;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.repositorios.interfaces.IRepositorioCardapio;

/**
 * Abaixo temos a classe para o repositório de cardápio que serve para armazenar em um arraylist todos os dados
 * dos pratos cadastrados; e a implementação da interface IRepositórioCardápio.
 * Criação do arraylist para guardar os objetos do tipo "prato".
 */
public class RepositorioCardapio implements IRepositorioCardapio{
	
    private ArrayList<PratoCardapio> pratos;


    public RepositorioCardapio() {
        pratos = new ArrayList<>();
    }


    /**
     * Método para a verificação da existência de um prato no repositório através do seu nome.
     * @param nome Nome do prato a ser verificado.
     * @return Retorna se ele existe ou não.
     */
    @Override
    public boolean verificarExistenciaPrato(String nome){
        boolean frag = false;
        for (PratoCardapio prato : pratos) {
            if (nome.equals(prato.getNome())) {
                frag = true;
                break;
            }
        }
        return frag;
    }

    /**
     * Método para adicionar pratos ao cardápio.
     * @param prato Prato a ser adicionado.
     */
    @Override
    public void adicionarPratoAoCardapio(PratoCardapio prato){
            pratos.add(prato);
    }

    /**
     * Método para alterar atributos de um prato. Seleciona o índice do atributo desejado e altera esse atributo.
     * @param novoPratoComNovosAtributos Prato com novos atributos.
     * @param index Índice do atributo a ser alterado.
     */
    @Override
    public void alterarAtributoPrato(PratoCardapio novoPratoComNovosAtributos, int index){
    	this.pratos.set(index, novoPratoComNovosAtributos);
    }

    /**
     * Método para pegar o índice de um prato.
     * @param pratoQueSeraPegoidex Índice do prato a ser pego.
     * @return Retorna o índice do prato que foi pego.
     */
    @Override
    public int pegarIdex(PratoCardapio pratoQueSeraPegoidex) {
        return this.pratos.indexOf(pratoQueSeraPegoidex);
    }

    /**
     * Método para pegar um prato.
     * Se o prato solicitado tiver o mesmo nome de um prato no cardápio,
     * então esse prato será pego.
     * @param nomePrato Nome do prato solicitado.
     * @return Retorna o prato.
     */
    @Override
    public PratoCardapio pegarPrato(String nomePrato){
        PratoCardapio pratoPego = null;
        for (PratoCardapio prato : this.pratos) {
            if (prato.getNome().equals(nomePrato)) {
                pratoPego = prato;
                break;
            }
        }
               return pratoPego;
        }

    @Override
    public PratoCardapio pegarPrato(int numeroPrato) {
        return null;
    }


    /**
     * Método para a remoção de um prato do cardápio.
     * @param pratoQueSeraRetiradoDoCardapio Prato que será removido.
     */
    @Override
    public void removerPrato(PratoCardapio pratoQueSeraRetiradoDoCardapio){
        this.pratos.remove(pratoQueSeraRetiradoDoCardapio);
    }




}









    
    
