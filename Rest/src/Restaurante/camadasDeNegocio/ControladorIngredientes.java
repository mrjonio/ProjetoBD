package Restaurante.camadasDeNegocio;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.camadasDeNegocio.interfaces.IControladorIngredientes;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetoNaoExisteErro;
import Restaurante.excessoes.ObjetoExistencia.ObjetosInsuficientesErro;
import Restaurante.repositorios.RepositorioIngredientes;
import Restaurante.repositorios.interfaces.IRepositorioIngrediente;

public class ControladorIngredientes implements IControladorIngredientes {
    private IRepositorioIngrediente repositorioIngrediente;
    private static IControladorIngredientes instancia;

    private ControladorIngredientes() {
        this.repositorioIngrediente = new RepositorioIngredientes();
    }

    /**
     * Acesso à leitura da interface da camada ingredientes.
     * @return A própria camada é retornada.
     */
    public static IControladorIngredientes getInstance(){
        if (instancia == null){
            instancia = new ControladorIngredientes();
        }
        return instancia;
    }

    @Override
    public void adicionarIngrediente(Ingrediente ingrediente) throws ObjetoJaExisteErro {
        boolean existe = this.repositorioIngrediente.proucurarIngrediente(ingrediente.getNome());
        if(!existe){
            this.repositorioIngrediente.adicionarIngrediente(ingrediente);
        } else {
            throw new ObjetoJaExisteErro("Ingrediente a ser adicionado");
        }
    }

    @Override
    public void aumentarEstoque(Ingrediente ingrediente) throws ObjetoNaoExisteErro {
        boolean existe =  this.repositorioIngrediente.proucurarIngrediente(ingrediente.getNome());
        if(existe){
            Ingrediente buscado = this.pegarUmIngrediente(ingrediente.getNome());
            int indexBuscado = this.repositorioIngrediente.pegarIdex(buscado);
            this.repositorioIngrediente.mudarAtributosIngrediente(ingrediente.getQtd(), indexBuscado);
        } else{
            throw new ObjetoNaoExisteErro("Ingrediente a ser aumentado");
        }

    }

    @Override
    public void removerIngrediente(String nomeDoIngrediente) throws ObjetoNaoExisteErro {
        boolean existe =  this.repositorioIngrediente.proucurarIngrediente(nomeDoIngrediente);
        if(existe){
            Ingrediente buscado = this.pegarUmIngrediente(nomeDoIngrediente);
            this.repositorioIngrediente.removerIngrediente(buscado);
        } else{
            throw new ObjetoNaoExisteErro("Ingrediente a ser aumentado");
        }
    }

    @Override
    public Ingrediente pegarUmIngrediente(String nomeDoIngrediente) throws ObjetoNaoExisteErro {
        boolean existe =  this.repositorioIngrediente.proucurarIngrediente(nomeDoIngrediente);
        if(existe){
            return this.repositorioIngrediente.pegarIngrediente(nomeDoIngrediente);
        } else{
            throw new ObjetoNaoExisteErro("Ingrediente buscado");
        }
    }

    @Override
    public Ingrediente pegarUmIngrediente(int indexDoIngrediente) throws ObjetoNaoExisteErro {
            Ingrediente buscado = this.repositorioIngrediente.pegarIngrediente(indexDoIngrediente);
            if (buscado != null){
                return buscado;
            } else {
                throw new ObjetoNaoExisteErro("Ingrediente buscado");
            }

    }

    @Override
    public void diminuirQuantidadeDeIngrediente(Ingrediente ingredienteQueSeraDiminuido, int qtd) throws ObjetoNaoExisteErro, ObjetosInsuficientesErro {
        Ingrediente buscado = this.pegarUmIngrediente(ingredienteQueSeraDiminuido.getNome());
        if(buscado != null){
            if (buscado.getQtd() >= qtd){
                //setQtd soma, entao se quero diminuir tenho somar c um negativo
                buscado.setQtd(( (-1) * qtd));
            } else{
                throw new ObjetosInsuficientesErro("Ingrediente a ser usado");
            }
        } else{
            throw new ObjetoNaoExisteErro("Ingrediente a ser usado");
        }
    }

    @Override
    public void alterarAtributoDeUmIngrediente(Ingrediente novosAtributos, String nomeAtual) throws ObjetoNaoExisteErro {
        Ingrediente buscado = this.pegarUmIngrediente(nomeAtual);
        if(buscado != null){
            int index = this.repositorioIngrediente.pegarIdex(buscado);
            this.repositorioIngrediente.mudarAtributosIngrediente(novosAtributos, index);
        } else{
            throw new ObjetoNaoExisteErro("Ingrediente a ser usado");
        }
    }

    @Override
    public boolean verificarQuantidade(Ingrediente nomeDoIngrediente, int qtdNecessaria) throws ObjetoNaoExisteErro {
        Ingrediente buscado = this.pegarUmIngrediente(nomeDoIngrediente.getNome());
        if(buscado != null){
            if (buscado.getQtd() >= qtdNecessaria){
                return true;
            } else{
                return false;
            }
        } else {
            throw new ObjetoNaoExisteErro("Ingrediente a ser usado");
        }
    }
}
