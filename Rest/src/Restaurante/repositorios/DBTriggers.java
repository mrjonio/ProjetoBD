package Restaurante.repositorios;

import java.sql.SQLException;

public class DBTriggers {
    private DBCenter dbCenter;

    private void idPedidoTrigger(){
        String view = "CREATE TRIGGER idIncrement" +
                "ON pedidos" +
                "FOR INSERT ";
        try {
            dbCenter.executarChamada(view);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
