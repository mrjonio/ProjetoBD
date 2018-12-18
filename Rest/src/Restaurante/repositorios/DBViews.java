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


    public void migrar() {
        this.dbCenter = new DBCenter();
        this.folhaPagamentoView();
    }

}
