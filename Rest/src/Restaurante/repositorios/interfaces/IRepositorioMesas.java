package Restaurante.repositorios.interfaces;

import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;

/**
 *  Abaixo temos a interface a ser implementada no repositório de mesas, com suas assinaturas de métodos.
 */
public interface IRepositorioMesas {

    void adicionarMesas(Mesa mesa);
    Mesa pegarMesa(int index);
    void removerMesas(Mesa mesaQueSeraRemovida);
    void alterarAtributosMesa(Mesa novosAtributos, int index);
    boolean taVazio();
    boolean indiceContemUmaMesa(int idex);
    int quantidadeDeMesas();
}
