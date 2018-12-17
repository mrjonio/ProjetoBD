import java.sql.*;

public class DBMANAGE {
    private static final String driver = "org.mariadb.jdbc.Driver";
    private static final String server = "jdbc:mariadb://localhost/dbtest";

    private static final String user = "root";
    private static final String passwd = "root";

    void coisar() {

        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(server, user, passwd);
            stmt = conn.createStatement();

            String selecionar = "SELECT Descricao FROM produto p WHERE NOT EXISTS (SELECT CodProd FROM vendaitens v WHERE p.CodProd = CodProd)";
            String sql = "INSERT INTO vendaitens (CodVenda, CodProd, Qtd, ValorTotal) VALUES ('456', '124', '3', '36.00')";
            String cliente = "INSERT INTO cliente (CodVenda, CodProd, Qtd, ValorTotal) VALUES ('456', '124', '3', '36.00')";

            ResultSet rs = stmt.executeQuery(selecionar);

            while (rs.next()){
                System.out.println(rs.getString("Descricao"));
            }

            rs.close();
            stmt.close();
            conn.close();

        }
        catch (Exception e) {
            e.printStackTrace();

        } finally {

            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {e.printStackTrace();}

        }

    }


}
