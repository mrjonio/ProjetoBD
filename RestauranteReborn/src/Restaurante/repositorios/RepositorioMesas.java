package Restaurante.repositorios;

import Restaurante.entidade.concretos.Mesa;
import Restaurante.repositorios.interfaces.IRepositorioMesas;

//Mesas não podem ser deletadas, apenas criadas (max 101 mesas)

import java.util.ArrayList;

/**
 * Abaixo temos a classe para o repositório de mesas, que serve para armazenar em um arraylist todas os dados
 * das mesas cadastradas; e a implementação da interface IRepositorioMesas.
 * Criação do arraylist para guardar os objetos do tipo "mesas".
 */

public class RepositorioMesas implements IRepositorioMesas{
    private ArrayList<Mesa> mesas;


    public RepositorioMesas(){
        this.mesas = new ArrayList<>();
    }

    /**
     * Método para adicionar uma nova mesa.
     * @param mesa Mesa a ser adicionada.
     */
    @Override
    public void adicionarMesas(Mesa mesa){
        mesas.add(mesa);
    }


    /**
     * Método para pegar uma mesa, através de seu índice.
     * @param index Índice da mesa a ser pega.
     * @return Retorna o índice da mesa.
     */
    @Override
    public Mesa pegarMesa(int index){
        return mesas.get(index);
    }

    /**
     * Método para remover uma mesa.
     * @param mesaQueSeraRemovida Mesa que será removida.
     */
    @Override
    public void removerMesas(Mesa mesaQueSeraRemovida) {
        this.mesas.remove(mesaQueSeraRemovida);
        }

    /**]
     * Método para verificar se o repositório de mesas está vazio.
     * @return Retorna se o repositório está vazio ou não
     */
    @Override
    public boolean taVazio(){
        return this.mesas.isEmpty();
    }

    /**
     * Método para verificar se o índice fornecido contem uma mesa no repositório.
     * @param idex Índice fornecido.
     * @return Retorna se há ou não uma mesa.
     */
    @Override
    public boolean indiceContemUmaMesa(int idex) {
        boolean contemMesa = false;
        if (mesas.size() >= idex){
            contemMesa = true;
        }
        return contemMesa;
    }

    /**
     * Método para saber a quantidade de mesas no repositório.
     * @return Retorna a quantidade de mesas que há no repositório.
     */
    @Override
    public int quantidadeDeMesas() {
        return this.mesas.size();
    }
}


