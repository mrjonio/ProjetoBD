package Restaurante.repositorios.interfaces;

import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;

/**
 *  Abaixo temos a interface a ser implementada no repositório de mesas, com suas assinaturas de métodos.
 */
public interface IRepositorioMesas {

    void adicionarMesas(Mesa mesa); //OK, chamando alterar atributos para fazer o attach
    Mesa pegarMesa(int index); //OK
    void removerMesas(Mesa mesaQueSeraRemovida); //OK
    void alterarAtributosMesa(Mesa novosAtributos, int index); //OK
    boolean taVazio(); //OK
    boolean indiceContemUmaMesa(int idex); //OK
    int pegarIndex(Mesa mesa); //NAO USADO
    int quantidadeDeMesas(); //OK
}
