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
import static java.lang.System.setOut;

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
        //inclusive os que ainda nao foram cozinhados
        //TODO: mudar para gerarVetor
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
        ArrayList<Pedido> pedidosLocal = (ArrayList<Pedido>) this.gerarVetorPedido(dataInicial, dataFinal);
        for (Pedido pedidoFor : pedidosLocal){
            this.removerPedido(pedidoFor);
        }
    }


    /**
     * Método para adicionar um pedido.
     * @param pedidoQueSeraAdicionado Pedido que será adicionado.
     */
    @Override
    public void adicionarPedido(Pedido pedidoQueSeraAdicionado){
        //esse pedido já foi criado lá na mesa
        //TODO: arrumar quando tiver logado de fato
        String sql = "INSERT INTO conzinheiro_cozinha_pedido VALUES ('222.222.222-22', " + pedidoQueSeraAdicionado.getIdPedido() +" )";
        try {
            this.dbCenter.executarChamada(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerPedido(Pedido pedidoQueSeraRemovido) {
        System.out.println("asdas");


        String sqlCozinheiro = "DELETE FROM conzinheiro_cozinha_pedido WHERE idPedido=" + pedidoQueSeraRemovido.getIdPedido();
        String sqlMesas = "DELETE FROM mesas_faz_pedidos WHERE idPedido=" + pedidoQueSeraRemovido.getIdPedido();
        String sqlPratos = "DELETE FROM pedidos_tem_pratos WHERE idpedidos=" + pedidoQueSeraRemovido.getIdPedido();
        String sqlPedidos = "DELETE FROM pedidos WHERE idpedidos=" + pedidoQueSeraRemovido.getIdPedido();

        try {
            this.dbCenter.executarChamada(sqlCozinheiro);
            this.dbCenter.executarChamada(sqlMesas);
            this.dbCenter.executarChamada(sqlPratos);
            this.dbCenter.executarChamada(sqlPedidos);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Pedido buscarPedido(Pedido pedidoQueSeraBuscado) {
        Pedido pedido = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y-M-dd HH:mm:ss");
        String data = pedidoQueSeraBuscado.getDataDoPedido().format(formatter);


        String sqlf = "SELECT * FROM ( pedidosComMesas ) as pedidos2 WHERE pedidos2.data ='"+data+"' ";

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
        List<Pedido> temp2 = new ArrayList<>();

        //TODO: view de limpeza de pedidos que ja passou um dia e nao foram cozinhados


        String sql = "SELECT * FROM pedidosCozidosComMesas";

        try {
            ResultSet rs = this.dbCenter.executarChamada(sql);

            while(rs.next()) {
                int idMesa = rs.getInt("numeroMesa");
                String data = rs.getString("data");

                Mesa m = new Mesa(idMesa, "Oculpada");
                Pedido p = new Pedido(null, m);
                m.adicionarPedido(p);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy/HH/mm");

                LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(data.split(" ")[0].split("-")[0]), Integer.parseInt(data.split(" ")[0].split("-")[1]),Integer.parseInt(data.split(" ")[0].split("-")[2]),Integer.parseInt(data.split(" ")[1].split(":")[0]),Integer.parseInt(data.split(" ")[1].split(":")[1]),(int)Double.parseDouble(data.split(" ")[1].split(":")[2]));
                p.setDataDoPedido(dateTime);


                Pedido ret = this.buscarPedido(p);

                if (ret != null) {
                    temp2.add(ret);
                } else {
                    System.out.println("pedido nulo");
                    return temp;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return temp;
        }




        for (Pedido ped: temp2) {
            if(ped.getDataDoPedido().isAfter(inicio) && ped.getDataDoPedido().isBefore(fim)){
                temp.add(ped);
            }
        }
        return temp;
    }

}
