package Restaurante.repositorios;

import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.repositorios.interfaces.IRepositorioMesas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Abaixo temos a classe para o repositório de mesas, que serve para armazenar em um arraylist todas os dados
 * das mesas cadastradas; e a implementação da interface IRepositorioMesas.
 * Criação do arraylist para guardar os objetos do tipo "mesas".
 */

public class RepositorioMesas implements IRepositorioMesas {
    private ArrayList<Mesa> mesas;
    private DBCenter dbCenter;


    public RepositorioMesas() {
        this.mesas = new ArrayList<>();
        this.dbCenter = new DBCenter();
    }

    /**
     * Método para adicionar uma nova mesa.
     *
     * @param mesa Mesa a ser adicionada.
     */
    @Override
    public void adicionarMesas(Mesa mesa) {
        try {
            for (Pedido p : mesa.getPedidos()) {
                for (PratoCardapio c : p.getPratoPedido()) {
                    for (Ingrediente i : c.getIngredientes()) {
                        String pratoIng = "INSERT INTO pratos_possuem_ingredientes (nome_prato, nome_ingrediente, quantidade) VALUES" +
                                "('" + c.getNome() + "', '" + i.getNome() + "', '" + i.getQtd() + "')";
                        dbCenter.executarChamada(pratoIng);
                    }
                    String prato = "INSERT INTO pedidos_tem_pratos (nome_prato, preco, idPedidos) VALUES" +
                            "('" + c.getNome() + "', '" + c.getPreco() + "', '" + p.getIdPedido() + "')";
                    dbCenter.executarChamada(prato);

                }
                String pedido = "INSERT INTO mesas_faz_pedidos (numeroMesa, idPedido) VALUES " +
                        "('" + mesa.getNumero() + "', '" + p.getIdPedido() + "')";
                dbCenter.executarChamada(pedido);
            }
            String mess = "INSERT INTO mesas (numero, disponibilidade) VALUES ('" +
                    mesa.getNumero() + "', '" + mesa.isDisponibilidade() + "')";
            dbCenter.executarChamada(mess);
        }  catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Método para pegar uma mesa, através de seu índice.
     *
     * @param index Índice da mesa a ser pega.
     * @return Retorna o índice da mesa.
     */
    @Override
    public Mesa pegarMesa(int index) {
        String sql = "SELECT * FROM mesasOculpadas, mesasReservadas";
        Mesa mesa = null;
        try {
            ResultSet mesas = this.dbCenter.executarChamada(sql);
            while (mesas.next()) {
                if (Integer.parseInt(mesas.getString("numero")) == index) {
                    String disponibilidade = mesas.getString("disponibilidade");
                    mesa = new Mesa(index, disponibilidade);
                    String sql2 = "SELECT * FROM pedidosMesa WHERE numeroMesa = '" + index + "'";
                    ResultSet pedidos = this.dbCenter.executarChamada(sql2);
                    ArrayList<Pedido> pedido = new ArrayList<>();
                    while (pedidos.next()){
                        String sql3 = "SELECT * FROM pratosPedidos WHERE idPedido = '" + pedidos.getString("idPedido") + "'";
                        ResultSet pratos = this.dbCenter.executarChamada(sql3);
                        ArrayList<PratoCardapio> prato = new ArrayList<>();
                        while (pratos.next()){
                            String sql4 = "SELECT * FROM ingredientesPrato WHERE nome_prato = '" + pratos.getString("nome_prato") + "'";
                            ResultSet ingrediente = this.dbCenter.executarChamada(sql3);
                            ArrayList<Ingrediente> ingredientes = new ArrayList<>();
                            while (ingrediente.next()){
                                Ingrediente ing = new Ingrediente(ingrediente.getString("nome_ingrediente"), Integer.parseInt(ingrediente.getString("quantidade")));
                                ingredientes.add(ing);
                            }
                            PratoCardapio prat = new PratoCardapio(pratos.getString("nome_prato"), Float.parseFloat(pratos.getString("preco_prato")), ingredientes);
                            prato.add(prat);
                        }
                        Pedido ped = new Pedido(prato, mesa);
                        pedido.add(ped);
                    }
                    for (Pedido p: pedido) {
                        mesa.adicionarPedido(p);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return mesa;
    }


    /**
     * Método para remover uma mesa.
     * @param mesaQueSeraRemovida Mesa que será removida.
     */
    @Override
    public void removerMesas(Mesa mesaQueSeraRemovida) {
        String sql = "DELETE * FROM mesas NATURAL JOIN mesas_faz_pedidos NATURAL JOIN pedidos_tem_pratos WHERE numero = '" + mesaQueSeraRemovida.getNumero() + "'";
        try {
            this.dbCenter.executarChamada(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterarAtributosMesa(Mesa novosAtributos, int index) {
        String sql =  "UPDATE mesas\n" +
                "SET numero ='" + novosAtributos.getNumero() +"', disponibilidade ='"+ novosAtributos.isDisponibilidade() +"'\n" +
                "WHERE numero = '" + index + "'";
        try {
            this.dbCenter.executarChamada(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**]
     * Método para verificar se o repositório de mesas está vazio.
     * @return Retorna se o repositório está vazio ou não
     */
    @Override
    public boolean taVazio(){
        return this.mesas.isEmpty();
    }

    /**
     * Método para verificar se o índice fornecido contem uma mesa no repositório.
     * @param idex Índice fornecido.
     * @return Retorna se há ou não uma mesa.
     */
    @Override
    public boolean indiceContemUmaMesa(int idex) {
        boolean contemMesa = false;
        for (Mesa mes: this.mesas) {
            if (mes.getNumero() == idex){
                contemMesa = true;
                break;
            }
        } return contemMesa;
    }

    @Override
    public int pegarIndex(Mesa mesa) {
         return this.mesas.indexOf(mesa);
    }

    /**
     * Método para saber a quantidade de mesas no repositório.
     * @return Retorna a quantidade de mesas que há no repositório.
     */
    @Override
    public int quantidadeDeMesas() {
        return this.mesas.size();
    }
}


