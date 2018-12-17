package Restaurante.camadasDeNegocio;


import Restaurante.camadasDeNegocio.interfaces.ICamadaReservas;
import Restaurante.entidade.abstrato.Reserva;
import Restaurante.entidade.concretos.Mesa;
import Restaurante.entidade.pessoas.Pessoa;
import Restaurante.excessoes.*;
import Restaurante.main.MetodosOutros;
import Restaurante.repositorios.RepositorioReserva;
import Restaurante.repositorios.interfaces.IRepositorioReserva;
import java.time.LocalDateTime;

/**
 * Classe CamadaReservas, seus atributos e seu construtor.
 * Implementação da interface ICamadaReservas.
 */
public class CamadaReservas implements ICamadaReservas {
    private IRepositorioReserva repositorioReserva;
    private static ICamadaReservas instancia;

    private CamadaReservas(){
        this.repositorioReserva = new RepositorioReserva();
    }

    /**
     * Acesso à leitura da interface ICamadaReservas.
     * @return Retorna a própria camada.
     */
    public static ICamadaReservas getInstance(){
        if (instancia == null){
            instancia = new CamadaReservas();
        }
        return instancia;
    }

    /**
     * Método para criar uma nova reserva.
     * Verifica se a reserva a ser feita já existe no repositório de reservas. E verifica se o cliente que deseja fazer a reserva
     * tem alguma reserva pendente, pois caso ele tenha uma reserva pendente, não vai ser possível realizar uma nova.
     * Caso a reserva não exista no repositório, então ela é criada e adicionada ao repositório.
     * @param reservaQueSeraFeita Reserva que será feita.
     * @throws ClienteJaReservouErro Cliente já reservou.
     * @throws ObjetoJaExisteErro Objeto (reserva) já existe.
     */
    @Override
    public void fazerNovaReserva(Reserva reservaQueSeraFeita) throws ClienteJaReservouErro, ObjetoJaExisteErro {
        boolean reservaJaExiste = this.repositorioReserva.verificarExistenciaReserva(reservaQueSeraFeita);
        if (!reservaJaExiste){
            boolean clienteJaReservou = this.repositorioReserva.verificarExistenciaReserva(reservaQueSeraFeita.getClienteQueReservou());
            if (!clienteJaReservou){
                this.repositorioReserva.adicionarReserva(reservaQueSeraFeita);
            } else {
                throw new ClienteJaReservouErro(reservaQueSeraFeita.getClienteQueReservou().getCpf());
            }
        } else {
            throw new ObjetoJaExisteErro("Reserva");
        }
    }

    /**
     * Método para verificar se a mesa de uma reserva está disponível.
     * Verifica se há uma mesa disponível no repositório de mesas para ser disponibilizada no horário da reserva.
     * Caso haja uma mesa disponível, então ela será liberada para o uso.
     * @param mesa Mesa a ser verificada.
     * @param dataHoraDaReserva Hora da reserva a ser verificada.
     * @return Retorna a liberação da mesa.
     */
    @Override
    public boolean verificacaoDeDisponibilidadeDeUmaMesa(Mesa mesa, LocalDateTime dataHoraDaReserva) {
        boolean mesaJaFoiReservada = this.repositorioReserva.verficarSeUmaMesaJaFoiReservada(mesa);
        boolean podeSerUsada = false;
        if (mesaJaFoiReservada){
            boolean estaDisponivel = this.repositorioReserva.verficarDisponibilidadeDeUmaMesaReservada(mesa, dataHoraDaReserva);
            if (estaDisponivel){
                podeSerUsada = true;
            }
        } else {
            podeSerUsada = true;
        }
        return podeSerUsada;
    }

    /**
     * Método para deletar uma reserva do repositório de reservas.
     * Busca e seleciona a reserva desejada e verifica se ela está sendo usada.
     * Caso a reserva não esteja sendo usada, ela poderá ser removida, caso contrário, será verificado se a reserva foi confirmada há
     * pelomenos 4 horas antes da tentativa de remoção, se sim, será removida, do contrario a exceção será lançada.
     * Se a reserva não existir no repositório de reservas, então outra exceção será lançada.
     * @param clienteQueReservou Cliente que fez a reserva.
     * @throws ObjetoEmUsoErro Reserva em uso.
     * @throws ObjetoNaoExisteErro Objeto (reserva).
     */
    @Override
    public void deletarUmaReserva(Pessoa clienteQueReservou) throws ObjetoEmUsoErro, ObjetoNaoExisteErro {
        Reserva reservaQueSeraDeletada = buscarReserva(clienteQueReservou);
        if (!reservaQueSeraDeletada.isConfirmacaoDaReserva()){
            this.repositorioReserva.deletarReserva(reservaQueSeraDeletada);
        } else {
            if (reservaQueSeraDeletada.getDataHora().isBefore(MetodosOutros.saberDiaHoraAtualFormatado().minusHours(4))) {
                this.repositorioReserva.deletarReserva(reservaQueSeraDeletada);
            } else {
                throw new ObjetoEmUsoErro("Reserva");
            }
        }
    }

    /**
     * Método para a confirmação/uso da reserva.
     * Busca a reserva no respositório de reservas para ver se ela existe; verifica se o horário que o cliente chegou bate com
     * o horário marcado na reserva e verifica se a reserva já foi ou está sendo usada.
     * Verifica também se a reserva foi expirada. Se passar por esse passos, então a reserva poderá ser usada.
     * @param donoDaReserva Cliente que fez a reserva.
     * @throws ReservaJaConfirmadaErro Reserva já foi ou está sendo usada.
     * @throws ObjetoNaoExisteErro Objeto (reserva) não existe.
     * @throws ReservaExpiradaErro Reserva expirada.
     */
    @Override
    public void confirmarReserva(Pessoa donoDaReserva) throws ReservaJaConfirmadaErro, ObjetoNaoExisteErro, ReservaExpiradaErro {
        Reserva reservaQueSeraConfirmada = buscarReserva(donoDaReserva);
        LocalDateTime horaAtual = MetodosOutros.saberDiaHoraAtualFormatado();
        int idexReserva;
        if (reservaQueSeraConfirmada.getDataHora().isBefore(horaAtual.plusHours(1)) && reservaQueSeraConfirmada.getDataHora().isAfter(horaAtual.minusHours(1))) {
            if (!reservaQueSeraConfirmada.isConfirmacaoDaReserva()) {
                idexReserva = this.repositorioReserva.pegarIdex(reservaQueSeraConfirmada);
                this.repositorioReserva.confirmarReserva(idexReserva);
            } else {
                throw new ReservaJaConfirmadaErro(donoDaReserva.getCpf());
            }
        } else {
            throw new ReservaExpiradaErro();
        }
    }

    /**
     * Método para mudar informações em uma reserva.
     * Verifica se a reserva com os novos atributos já existe no repositório de reservas e verifica também o cliente que a fez.
     * Se a reserva não existir, então o índice da informação a ser alterada será selecionado e as alterações serão feitas,
     * caso contrário, a exceção será lançada.
     * @param novosAtributos Novos atributos.
     * @param donoDaReserva Cliente que fez a reserva.
     * @throws ObjetoJaExisteErro Objeto (reserva) com os novos atributos já existe.
     * @throws ObjetoNaoExisteErro Objeto (dono da reserva) não existe.
     */
    @Override
    public void mudarReserva(Reserva novosAtributos, Pessoa donoDaReserva) throws ObjetoJaExisteErro, ObjetoNaoExisteErro {
        boolean reservaJaExiste = this.repositorioReserva.verificarExistenciaReserva(novosAtributos);
        Reserva reservaAtual = buscarReserva(donoDaReserva);
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
     * @param donoDaReserva Cliente que fez a reserva.
     * @return Retorna a reserva (informações) selecionada.
     * @throws ObjetoNaoExisteErro Objeto (reserva) não existe.
     */
    @Override
    public Reserva buscarReserva(Pessoa donoDaReserva) throws ObjetoNaoExisteErro {
        boolean existeReserva = this.repositorioReserva.verificarExistenciaReserva(donoDaReserva);
        Reserva reservaQueSeraPega;
        if (existeReserva){
            reservaQueSeraPega = this.repositorioReserva.pegarReserva(donoDaReserva);
        } else {
            throw new ObjetoNaoExisteErro("Reserva");
        }
        return reservaQueSeraPega;
    }

    /**
     * Método para criar o vetor de reservas.
     * Verifica se o repositório de reservas está vazio.
     * Caso o repositório não esteja vazio, então o vetor será criado, caso contrário, uma exceção será lançada.
     * Se o vetor criado tiver tamanho menor que 3, então outra exceção será lançada.
     * @return Retorna o vetor gerado.
     * @throws ObjetosInsuficientesErro Objetos (atributos/informações da reserva) insuficientes.
     * @throws RepositorioVazioErro Repositório vazio.
     */
    @Override
    public Reserva[] gerarVetorDeReservas() throws ObjetosInsuficientesErro, RepositorioVazioErro {
        boolean taVazio = this.repositorioReserva.verificarSeRepositorioEstaVazio();
        Reserva[] vetorGerado;
        if (!taVazio) {
            vetorGerado = this.repositorioReserva.criarVetorDeReservas();
            if (vetorGerado.length < 3){
                throw new ObjetosInsuficientesErro("Reserva");
            }
        } else {
            throw new RepositorioVazioErro("Repositorio Reservas");
        }
        return vetorGerado;
    }

    /**
     * Método para verificar se uma reserva existe.
     * Verifica se o repositório de reservas está vazio, e caso ele não esteja, retorna o nome do dono
     * da reserva contida no repositório de reservas.
     * @param nomeDoDono Nome do cliente que fez a reserva.
     * @return Retorna o nome do dono da reserva existente
     */
    @Override
    public boolean verificarExistenciaDeUmaReserva(String nomeDoDono) {
        boolean estaVazia = this.repositorioReserva.verificarSeRepositorioEstaVazio();
        boolean retorno;
        if (estaVazia) {
            retorno = false;
        } else {
            retorno = this.verificarExistenciaDeUmaReserva(nomeDoDono);
        }
        return retorno;
    }

}



