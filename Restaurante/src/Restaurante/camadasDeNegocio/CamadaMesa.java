package Restaurante.camadasDeNegocio;

import Restaurante.camadasDeNegocio.interfaces.ICamadaMesa;
import Restaurante.entidade.concretos.Mesa;
import Restaurante.excessoes.ObjetosInsuficientesErro;
import Restaurante.excessoes.RepositorioVazioErro;
import Restaurante.repositorios.RepositorioMesas;
import Restaurante.repositorios.interfaces.IRepositorioMesas;

//Refazer
/**
 * Classe CamadaMesa, seus atributos e construtor.
 * Implementação da interface ICamadaMesa.
 */
public class CamadaMesa implements ICamadaMesa {
    private IRepositorioMesas repositorioMesas;
    private static ICamadaMesa instancia;


    private CamadaMesa() {
        this.repositorioMesas = new RepositorioMesas();
    }

    /**
     * Acesso à leitura da interface ICamadaMesa.
     * @return Retorna a própria camada.
     */
    public static ICamadaMesa getInstance(){
        if (instancia == null){
            instancia = new CamadaMesa();
        }
        return instancia;
    }

    /**
     * Método para a adição de mesas.
     * @param mesa Mesa que será adicionada.
     */
    @Override
    public void adicionarMesas(Mesa mesa){
        this.repositorioMesas.adicionarMesas(mesa);
    }

    /**
     * Método para saber a quantidade de mesas que há no repositório.
     * @return Retorna a quantidade de mesas que há no repositório.
     */
    @Override
    public int qtdMesas() {
        return this.repositorioMesas.quantidadeDeMesas();
    }

    /**
     * Método para a remoção de mesas.
     * @param mesaQueSeraRemovida Mesa que será removida.
     * @throws RepositorioVazioErro Repositório de mesas vazio.
     */
    @Override
    public void removerMesas(Mesa mesaQueSeraRemovida) throws RepositorioVazioErro {
        this.repositorioMesas.removerMesas(mesaQueSeraRemovida);
    }

    /**]
     * Método para selecionar uma mesa no repositório de mesas.
     * Caso a mesa não exista no repositório de mesas, a exceção será lançada.
     * @param index Índice da mesa que será pega.
     * @return Retorna a mesa selecionada.
     * @throws RepositorioVazioErro Repositório de mesas vazio.
     * @throws ObjetosInsuficientesErro Objetos (mesas) insuficientes.
     */
    @Override
    public Mesa pegarMesa(int index) throws  RepositorioVazioErro, ObjetosInsuficientesErro {
        boolean repositorioVazio = this.repositorioMesas.taVazio();
        Mesa mesaQueSeraPega;
        if (!repositorioVazio) {
            boolean temMesaNoIndex = this.repositorioMesas.indiceContemUmaMesa(index);
            if (temMesaNoIndex) {
                mesaQueSeraPega = this.repositorioMesas.pegarMesa(index);
            } else {
                throw new ObjetosInsuficientesErro("Mesas");
            }
        } else {
            throw new RepositorioVazioErro("Mesas");
        }
        return mesaQueSeraPega;
    }

}