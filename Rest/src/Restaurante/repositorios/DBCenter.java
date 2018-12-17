package Restaurante.repositorios;


import java.sql.*;

class DBCenter {

    private static final String driver = "org.mariadb.jdbc.Driver";
    private static final String database = "javadb";
    private static final String server = "jdbc:mariadb://localhost/" + database;
    private static final String user = "javadb";
    private static final String passwd = "javadb";

    private Connection conn = null;
    private Statement stmt = null;

    private void abrirConeccao() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conn = DriverManager.getConnection(server,user,passwd);
        stmt = conn.createStatement();
    }

    private void fecharConeccao() {

        try {

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet executarComando(String sql) throws SQLException {
        return stmt.executeQuery(sql);
    }

    public ResultSet executarChamada(String sql) throws ClassNotFoundException, SQLException {

        ResultSet resultado = null;

        this.abrirConeccao();
        resultado = this.executarComando(sql);
        this.fecharConeccao();

        return resultado;
    }
}
