package Restaurante.fachada.interfaceFachada;

import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.excessoes.ClienteJaReservouErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoEmUsoErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;

public interface IFachadaAtendente {
    void fazerNovaReserva(Reserva reservaQueSeraFeita) throws ClienteJaReservouErro, ObjetoJaExisteErro;
    void deletarUmaReserva(Reserva reservaQueSeraDeletada) throws ObjetoEmUsoErro, ObjetoNaoExisteErro;
    void mudarReserva(Reserva novosAtributos, Reserva antigaReserva) throws ObjetoJaExisteErro, ObjetoNaoExisteErro;
    Reserva buscarUmaReserva(Reserva reservaBuscada) throws ObjetoNaoExisteErro;
    Mesa buscarUmaMesa(int index) throws RepositorioVazioErro, ObjetosInsuficientesErro;
    void deletarMesasDoSistema(Mesa mesaQueSeraDeletada) throws RepositorioVazioErro, ObjetoEmUsoErro, ObjetosInsuficientesErro;
    void adicionarUmaMesa(Mesa mesaQueSeraAdicionada) throws ObjetoJaExisteErro;
    void editarMesa(Mesa novosAtributos, Mesa mesaAntiga) throws ObjetoNaoExisteErro;
    int qtdMesas();
}
