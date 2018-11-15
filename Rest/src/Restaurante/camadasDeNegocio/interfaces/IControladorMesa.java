package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.excessoes.ObjetoExistencia.ObjetoEmUsoErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;

/**
 *  Abaixo temos a interface a ser implementada na camada "mesa", com suas assinaturas de métodos e exceções.
 */

public interface IControladorMesa {

    void removerMesas(Mesa mesaQueSeraRemovida) throws RepositorioVazioErro, ObjetosInsuficientesErro, ObjetoEmUsoErro;
    Mesa pegarMesa(int numeroDaMesa) throws RepositorioVazioErro, ObjetosInsuficientesErro;
    void adicionarMesas(Mesa mesa) throws ObjetoJaExisteErro;
    void editarMesa(Mesa novosAtributos, Mesa mesaAntiga) throws ObjetoNaoExisteErro;
    int qtdMesas();
}
