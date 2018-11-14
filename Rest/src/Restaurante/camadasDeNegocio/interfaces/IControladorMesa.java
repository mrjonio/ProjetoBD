package Restaurante.camadasDeNegocio.interfaces;

import Restaurante.entidade.concretos.Mesa;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;

/**
 *  Abaixo temos a interface a ser implementada na camada "mesa", com suas assinaturas de métodos e exceções.
 */

public interface IControladorMesa {

    void removerMesas(Mesa mesaQueSeraRemovida) throws RepositorioVazioErro;
    Mesa pegarMesa(int index) throws RepositorioVazioErro, ObjetosInsuficientesErro;
    void adicionarMesas(Mesa mesa);
    int qtdMesas();
}
