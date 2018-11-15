package Restaurante.repositorios;


import java.util.ArrayList;
import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;
import Restaurante.repositorios.interfaces.IRepositorioReserva;

/**
 * Abaixo temos a classe para o repositório de reservas, que serve para armazenar em um arraylist todas os dados
 * das reservas cadastradas; e a implementação da interface IRepositorioReserva.
 * Criação do arraylist para guardar os objetos do tipo "reserva".
 */

public class RepositorioReserva implements IRepositorioReserva{
    private ArrayList<Reserva> reserva;
    
    public RepositorioReserva(){
        this.reserva = new ArrayList<>();
    }


    /**
     * Método para adicionar uma nova reserva.
     * @param reserva Reserva que será adicionada.
     */
    @Override
    public void adicionarReserva(Reserva reserva) {
            this.reserva.add(reserva);
    }

    /**
     *  Método para verificar se uma reserva que será feita já existe no repositório.
     * @param reservaQueSeraComparada Reserva que será comparada.
     * @return Retorna se existe ou não.
     */
    @Override
    public boolean verificarExistenciaReserva(Reserva reservaQueSeraComparada){
        boolean flag = false;
        for (Reserva aReserva : reserva) {
            if (reservaQueSeraComparada.equals(aReserva)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Método para mudar atributos (informações) de uma reserva.
     * @param novosDados Novos dados.
     * @param idexDaReserva Índice do atributo da reserva que será alterado.
     */
    @Override
    public void mudarUmaReserva(Reserva novosDados, int idexDaReserva) {
        this.reserva.set(idexDaReserva, novosDados);
    }


    /**]
     *  Método para saber o índice de uma reserva.
     * @param reservaQueSeDesejaSaberIdex Reserva que será fornecida o índice.
     * @return Retorna o índice da reserva.
     */
    @Override
    public int pegarIdex(Reserva reservaQueSeDesejaSaberIdex) {
        int idex  = 0;
        for (int i = 0; i < this.reserva.size(); i++){
            if (this.reserva.get(i).equals(reservaQueSeDesejaSaberIdex)){
                idex = this.reserva.indexOf(this.reserva.get(i));
                break;
            }
        }
        return idex;
    }

    /**
     * Método para pegar uma reserva de um determinado cliente.
     * @return Retorna a reserva que um determinado cliente reservou em determinada data
     */
    @Override
    public Reserva pegarReserva(Reserva reserva) {
        Reserva reservaQueSeraPega = null;
        for (Reserva reservaDoFor : this.reserva) {
            if (reservaDoFor.equals(reserva)) {
                reservaQueSeraPega = reservaDoFor;
                break;
            }
        }
        return reservaQueSeraPega;
    }

    /**
     * Método para deletar uma reserva.
     * @param reservaQueSeraDeletada Reserva que será deletada.
     */
    @Override
    public void deletarReserva(Reserva reservaQueSeraDeletada){
        this.reserva.remove(reservaQueSeraDeletada);
     }





}