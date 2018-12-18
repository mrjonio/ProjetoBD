package Restaurante.repositorios;


import Restaurante.camadasDeNegocio.entidade.abstrato.Pedido;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.repositorios.interfaces.IRepositorioPedidos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

/**
 * Abaixo temos a classe para o repositório de pedidos, que serve para armazenar em um arraylist todas os dados
 * dos pedidos cadastrados; e a implementação da interface IRepositorioPedido.
 * Criação do arraylist para guardar os objetos do tipo "pedido".
 */

public class RepositorioPedidos implements IRepositorioPedidos {
    private ArrayList<Pedido> pedidos;
    private DBCenter dbCenter;

    public RepositorioPedidos() {
        this.dbCenter = new DBCenter();
        this.pedidos = new ArrayList<>();
    }

    /**
     * Método para calcular o lucro de um determinado tempo.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     * @return Retorna o lucro de todos os pedidos de um determinado tempo.
     */
    @Override
    public double calcularLucro(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        double lucroDosPedidos = 0;


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y-M-dd");
        String dataF = dataFinal.format(formatter);
        String dataI = dataInicial.format(formatter);

        try {

            String sqlPratos = " SELECT * FROM pedidos_tem_pratos  AS pedidoCozinhado WHERE EXISTS ( SELECT * FROM pedidos AS todosPedidos WHERE ((pedidoCozinhado.idpedidos=todosPedidos.idpedidos) AND ( data BETWEEN '" + dataI + "' AND '"+ dataF +"'))) ";

            ResultSet resultSet = this.dbCenter.executarChamada(sqlPratos);


            while (resultSet.next()) {
                String nomePrato = resultSet.getString("nome_prato");
                String sqlTabelaPrato = "SELECT * FROM pratos WHERE nome='"+ nomePrato+ "'";
                ResultSet tabelaPrato = this.dbCenter.executarChamada(sqlTabelaPrato);

                while (tabelaPrato.next()) {
                    double preco = tabelaPrato.getDouble("preco");
                    lucroDosPedidos += preco;
                }

            }

        } catch (Exception e) {e.printStackTrace();}


        return lucroDosPedidos;
    }


    /**
     * Método para deletar pedidos.
     * @param dataInicial Data inicial.
     * @param dataFinal Data final.
     */
    @Override
    public void deletarPedidos(LocalDateTime dataInicial, LocalDateTime dataFinal){
        for (Pedido pedidoFor : pedidos){
            if (pedidoFor.getDataDoPedido().isAfter(dataInicial) && pedidoFor.getDataDoPedido().isBefore(dataFinal)){
                pedidos.remove(pedidoFor);
            }
        }
    }


    /**
     * Método para adicionar um pedido.
     * @param pedidoQueSeraAdicionado Pedido que será adicionado.
     */
    @Override
    public void adicionarPedido(Pedido pedidoQueSeraAdicionado){
        this.pedidos.add(pedidoQueSeraAdicionado);
    }

    @Override
    public void removerPedido(Pedido pedidoQueSeraRemovido) {
        //TODO: REMOVER PELO NUMERO DA MESA E PELA DATA
        this.pedidos.remove(pedidoQueSeraRemovido);
    }

    @Override
    public Pedido buscarPedido(Pedido pedidoQueSeraBuscado) {
        Pedido pedido = null;
        //TODO: ACHAR PELO NUMERO DA MESA E PELA DATA

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y-M-dd HH:mm:ss");
        String data = pedidoQueSeraBuscado.getDataDoPedido().format(formatter);

        String sql = "SELECT * FROM pedidos JOIN mesas_faz_pedidos ON pedidos.idpedidos=mesas_faz_pedidos.idPedido";// AND pedidos.data ='"+data+"' ";

        String sqlf = "SELECT * FROM ( "+sql+" ) as pedidos2 WHERE pedidos2.data ='"+data+"' ";

        //String sql = "SELECT * FROM pedido WHERE data ='"+data+"' ";
        Mesa mesa = (new  RepositorioMesas()).pegarMesa(pedidoQueSeraBuscado.getMesaQuePediu().getNumero());
        ArrayList<PratoCardapio> pratos = new ArrayList<>();
        if (mesa == null) return null;
        int idPedido;
        try {
            ResultSet pedidosDaData = this.dbCenter.executarChamada(sqlf);

            if(!pedidosDaData.first()) return null;

            idPedido = pedidosDaData.getInt("idpedidos");

            String sql2 = "SELECT * FROM pedidos_tem_pratos WHERE idpedidos="+ idPedido;

            ResultSet pratosIDs = this.dbCenter.executarChamada(sql2);

            while (pratosIDs.next()) {
                PratoCardapio p = this.pegarPrato(pratosIDs.getString("nome_prato"));
                pratos.add(p);
            }

            pedido = new Pedido(pratos, mesa);
            pedido.setIdPedido(idPedido);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pedido;
    }


    //TODO: dps pegar do repositorio

    private PratoCardapio pegarPrato(String nomePrato) {
        double precoPrato;
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        PratoCardapio pratoPego = null;

        String sql =
                "Select nome_ingrediente, quantidade " +
                        "FROM pratos_possuem_ingredientes " +
                        " WHERE " +
                        "nome_prato=\"" + nomePrato + "\"";
        try {
            ResultSet rs;
            rs = this.dbCenter.executarChamada(sql);
            while(rs.next()) {
                Ingrediente ing = new Ingrediente(rs.getString("nome_ingrediente"), rs.getInt("quantidade"));
                ingredientes.add(ing);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        String sql2 =
                "Select * " +
                        "FROM pratos " +
                        " WHERE " +
                        "nome=\"" + nomePrato + "\"";
        try {
            ResultSet rs;
            rs = this.dbCenter.executarChamada(sql2);
            rs.first();
            pratoPego = new PratoCardapio(rs.getString("nome"), rs.getFloat("preco"), ingredientes);
            return pratoPego;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return pratoPego;
    }

    @Override
    public List<Pedido> gerarVetorPedido(LocalDateTime inicio, LocalDateTime fim) {
        List<Pedido> temp = new ArrayList<>();
        for (Pedido ped: this.pedidos) {
            if(ped.getDataDoPedido().isAfter(inicio) && ped.getDataDoPedido().isBefore(fim)){
                temp.add(ped);
            }
        }
        return temp;
    }

    private int getFreeId() {
        int ret = -1;
        String sql = "select coalesce(min(t.idpedidos) + 1, 0)\n" +
                "     from pedidos t left outer join\n" +
                "     pedidos t2\n" +
                "     on t.idpedidos = t2.idpedidos - 1\n" +
                "     where t2.idpedidos is null;";

        try {
            ResultSet resultSet = this.dbCenter.executarChamada(sql);
            while (resultSet.next()) {
                ret = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }


}
