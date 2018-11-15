package Restaurante.camadasDeNegocio;


import Restaurante.camadasDeNegocio.interfaces.IControladorReservas;
import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.repositorios.RepositorioReserva;
import Restaurante.repositorios.interfaces.IRepositorioReserva;

/**
 * Classe ControladorReservas, seus atributos e seu construtor.
 * Implementação da interface IControladorReservas.
 */
public class ControladorReservas implements IControladorReservas {
    private IRepositorioReserva repositorioReserva;
    private static IControladorReservas instancia;

    private ControladorReservas(){
        this.repositorioReserva = new RepositorioReserva();
    }

    /**
     * Acesso à leitura da interface IControladorReservas.
     * @return Retorna a própria camada.
     */

    public static IControladorReservas getInstance(){
        if (instancia == null){
            instancia = new ControladorReservas();
        }
        return instancia;
    }

    /**
     * Método para criar uma nova reserva.
     * Verifica se a reserva a ser feita já existe no repositório de reservas. E verifica se o cliente que deseja fazer a reserva
     * tem alguma reserva pendente, pois caso ele tenha uma reserva pendente, não vai ser possível realizar uma nova.
     * Caso a reserva não exista no repositório, então ela é criada e adicionada ao repositório.
     * @param reservaQueSeraFeita Reserva que será feita.
     * @throws ObjetoJaExisteErro Objeto (reserva) já existe.
     */
    @Override
    public void fazerNovaReserva(Reserva reservaQueSeraFeita) throws ObjetoJaExisteErro {
        boolean reservaJaExiste = this.repositorioReserva.verificarExistenciaReserva(reservaQueSeraFeita);
        if (!reservaJaExiste){
            this.repositorioReserva.adicionarReserva(reservaQueSeraFeita);
        } else {
            throw new ObjetoJaExisteErro("Reserva");
        }
    }

    /**
     * Método para deletar uma reserva do repositório de reservas.
     * Se a reserva não existir no repositório de reservas, então uma exceção será lançada..
     * @throws ObjetoNaoExisteErro Objeto (reserva).
     */
    @Override
    public void deletarUmaReserva(Reserva reservaQueSeraDeletada) throws ObjetoNaoExisteErro {
        Reserva existe = buscarReserva(reservaQueSeraDeletada);
        this.repositorioReserva.deletarReserva(existe);
    }


    /**
     * Método para mudar informações em uma reserva.
     * Verifica se a reserva com os novos atributos já existe no repositório de reservas e verifica também o cliente que a fez.
     * Se a reserva não existir, então o índice da informação a ser alterada será selecionado e as alterações serão feitas,
     * caso contrário, a exceção será lançada.
     * @param novosAtributos Novos atributos.
     * @param reservaAntiga Reserva com os atributos antigos.
     * @throws ObjetoJaExisteErro Objeto (reserva) com os novos atributos já existe.
     * @throws ObjetoNaoExisteErro Objeto (dono da reserva) não existe.
     */
    @Override
    public void mudarReserva(Reserva novosAtributos, Reserva reservaAntiga) throws ObjetoJaExisteErro, ObjetoNaoExisteErro {
        boolean reservaJaExiste = this.repositorioReserva.verificarExistenciaReserva(novosAtributos);
        Reserva reservaAtual = buscarReserva(reservaAntiga);
        if (!reservaJaExiste){
            int idexDaReserva = this.repositorioReserva.pegarIdex(reservaAtual);
            this.repositorioReserva.mudarUmaReserva(novosAtributos, idexDaReserva);
        } else {
            throw new ObjetoJaExisteErro("Reserva");
        }
    }

    /**
     * Método para buscar as informações de uma reserva.
     * Verifica se a reserva existe no repositório de reservas e se foi feita pelo cliente solicitado.
     * Se a reserva existir, então suas informações serão fornecidas, caso contrário, a exceção será lançada.
     * @param reservaQueSeraBuscada Cliente que fez a reserva.
     * @return Retorna a reserva (informações) selecionada.
     * @throws ObjetoNaoExisteErro Objeto (reserva) não existe.
     */
    @Override
    public Reserva buscarReserva(Reserva reservaQueSeraBuscada) throws ObjetoNaoExisteErro {
        boolean existeReserva = this.repositorioReserva.verificarExistenciaReserva(reservaQueSeraBuscada);
        if (existeReserva){
            return reservaQueSeraBuscada;
        } else {
            throw new ObjetoNaoExisteErro("Reserva");
        }
    }

}



