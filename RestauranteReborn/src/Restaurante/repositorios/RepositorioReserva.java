package Restaurante.repositorios;


import java.time.LocalDateTime;
import java.util.ArrayList;
import Restaurante.entidade.abstrato.Reserva;
import Restaurante.entidade.concretos.Mesa;
import Restaurante.entidade.pessoas.Pessoa;
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
     * Método para verificar se há uma reserva pendente para determinado cliente.
     * @param donoDaReserva Dono da reserva que será verificada.
     * @return Retorna se há ou não uma reserva pendente.
     */
    @Override
    public boolean verificarExistenciaReserva(Pessoa donoDaReserva){
        boolean flag = false;
        for (Reserva reservaFor : reserva) {
            if (donoDaReserva.equals(reservaFor.getClienteQueReservou())) {
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

    /**
     * Método para criar um vetor de reservas.
     * @return Retorna o vetor de reservas.
     */
    @Override
    public Reserva[] criarVetorDeReservas() {
        Reserva[] vetorReservas = new Reserva[this.reserva.size()];
        for (int i = 0; i < vetorReservas.length; i++){
            vetorReservas[i] = this.reserva.get(i);
        }
        return vetorReservas;
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
     * @param clienteQueReservou Cliente que fez a reserva.
     * @return Retorna a reserva que um determinado cliente reservou.
     */
    @Override
    public Reserva pegarReserva(Pessoa clienteQueReservou) {
        Reserva reservaQueSeraPega = null;
        for (Reserva reservaDoFor : this.reserva) {
            if (reservaDoFor.getClienteQueReservou().equals(clienteQueReservou)) {
                reservaQueSeraPega = reservaDoFor;
                break;
            }
        }
        return reservaQueSeraPega;
    }

    /**
     * Método para verificar se uma mesa já foi reservada.
     * @param mesa Mesa que será verificada.
     * @return Retorna se já foi reservada ou não.
     */
    @Override
    public boolean verficarSeUmaMesaJaFoiReservada(Mesa mesa) {
        boolean jaFoi = false;
        for (Reserva reservaFor : this.reserva) {
            if (mesa.equals(reservaFor.getMesa())) {
                jaFoi = true;
                break;
            }
        }
        return jaFoi;
    }

    /**
     * Método para verificar a disponibilidade de uma mesa reservada.
     * @param mesa Mesa que terá a disponibilidade verificada.
     * @param dataHora Data e hora da reserva da mesa.
     * @return Retorna se a mesa pode ser usada (está disponível) ou não.
     */
    @Override
    public boolean verficarDisponibilidadeDeUmaMesaReservada(Mesa mesa, LocalDateTime dataHora) {
        boolean podeSerUsada = false;
        for (Reserva reservaFor : this.reserva) {
            if (reservaFor.getMesa().equals(mesa)) {
                if (reservaFor.getDataHora().isAfter(dataHora.plusHours(2)) || reservaFor.getDataHora().isBefore(dataHora.minusHours(2))) {
                    podeSerUsada = true;
                    break;
                }
                break;
            } else {
                podeSerUsada = true;
            }
        }
        return podeSerUsada;
    }


    /**
     * Método para deletar uma reserva.
     * @param reservaQueSeraDeletada Reserva que será deletada.
     */
    @Override
    public void deletarReserva(Reserva reservaQueSeraDeletada){
        this.reserva.remove(reservaQueSeraDeletada);
     }

    /**
     * Método para confirmar/usar uma reserva.
     * @param index Índice de disponibilidade da reserva.
     */
     @Override
     public void confirmarReserva(int index){
        this.reserva.get(index).setConfirmacaoDaReserva(true);
     }

    /**
     * Método para verificar se o repositório de reserva está vazio.
     * @return Retorna se está vazio ou não.
     */
     @Override
    public boolean verificarSeRepositorioEstaVazio(){
         return this.reserva.isEmpty();
     }



}