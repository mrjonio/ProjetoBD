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


    private void cozinheiroCozinhou(){
        String trigger = "CREATE TRIGGER cozinheiroCozinhou \n" +
                "AFTER INSERT ON conzinheiro_cozinha_pedido \n" +
                "FOR EACH ROW \n" +
                "UPDATE cozinheiros SET cozinheiros.quatidadePratos = cozinheiros.quatidadePratos+1 WHERE cozinheiros.cpfCozinheiro=NEW.cpfCozinheiro;";
        try {
            dbCenter.executarChamada(trigger);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void migrar() {
        this.dbCenter = new DBCenter();
        this.cozinheiroCozinhou();
    }



}
