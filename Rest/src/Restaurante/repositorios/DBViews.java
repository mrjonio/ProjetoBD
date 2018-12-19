package Restaurante.repositorios;


import java.sql.SQLException;

public class DBViews {
    private DBCenter dbCenter;

    private void funcionariosView(){
        String view = "CREATE VIEW funcRest" +
                "AS SELECT *" +
                "FROM funcionarios";
        try {
            dbCenter.executarChamada(view);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    private void MesasOculpadasView(){
        String view = "CREATE VIEW mesasOculpadas" +
                "AS SELECT numero, disponibilidade" +
                "FROM mesas" +
                "WHERE disponibilidade = 'Ocupado'";
        try {
            dbCenter.executarChamada(view);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void MesasReservadasView(){
        String view = "CREATE VIEW mesasReservadas" +
                "AS SELECT numero, disponibilidade" +
                "FROM mesas" +
                "WHERE disponibilidade = 'Reservado'";
        try {
            dbCenter.executarChamada(view);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void MesasVaziasView(){
        String view = "CREATE VIEW mesasVazias" +
                "AS SELECT numero, disponibilidade" +
                "FROM mesas" +
                "WHERE disponibilidade = 'Reservado'";
        try {
            dbCenter.executarChamada(view);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    private void IngredientesPratoView(){
        String view = "CREATE VIEW IngredientesPrato" +
                "AS SELECT *"+
                "FROM pratos_possuem_ingredientes" +
                "GROUP BY nome_prato";
        try {
            dbCenter.executarChamada(view);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void pratoPedidoView(){
        String view = "CREATE VIEW pratosPedidos" +
                "AS SELECT *"+
                "FROM pedidos_tem_pratos" +
                "GROUP BY idpedidos";
        try {
            dbCenter.executarChamada(view);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void pedidosMesaView(){
        String view = "CREATE VIEW pedidosMesa" +
                "AS SELECT *"+
                "FROM mesas_faz_pedidos" +
                "GROUP BY idpedidos";
        try {
            dbCenter.executarChamada(view);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////
    private void folhaPagamentoView(){
        String view = "CREATE VIEW folhaPagamento " +
                "AS SELECT SUM(salario) "+
                "FROM funcionarios";
        try {
            dbCenter.executarChamada(view);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void idPedidosCozidos(){
        String view = "CREATE VIEW idPedidosCozidos AS SELECT idpedidos AS cozidos FROM pedidos JOIN conzinheiro_cozinha_pedido ON pedidos.idpedidos=conzinheiro_cozinha_pedido.idPedido";
        try {
            dbCenter.executarChamada(view);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    private void pedidosCompletosCozidos(){
        String view = "CREATE VIEW pedidosCompletosCozidos AS SELECT * FROM idPedidosCozidos AS pedidosCozinhados JOIN pedidos AS todosPedidos WHERE pedidosCozinhados.cozidos=todosPedidos.idpedidos";
        try {
            dbCenter.executarChamada(view);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    private void pedidosCozidosComMesas(){
        String view = "CREATE VIEW pedidosCozidosComMesas AS SELECT * FROM pedidosCompletosCozidos AS pedidosQueForamCozinhados JOIN mesas_faz_pedidos AS mesasP ON mesasP.idPedido=pedidosQueForamCozinhados.idpedidos";
        try {
            dbCenter.executarChamada(view);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void pedidosComMesas(){
        String view = "CREATE VIEW pedidosComMesas AS SELECT * FROM pedidos JOIN mesas_faz_pedidos ON pedidos.idpedidos=mesas_faz_pedidos.idPedido";
        try {
            dbCenter.executarChamada(view);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void migrar() {
        this.dbCenter = new DBCenter();
        this.folhaPagamentoView();
        this.idPedidosCozidos();
        this.pedidosCompletosCozidos();
        this.pedidosCozidosComMesas();
        this.pedidosComMesas();
    }

}
