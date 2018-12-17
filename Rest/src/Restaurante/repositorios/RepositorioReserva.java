package Restaurante.repositorios;


import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.repositorios.interfaces.IRepositorioReserva;

/**
 * Abaixo temos a classe para o repositório de reservas, que serve para armazenar em um arraylist todas os dados
 * das reservas cadastradas; e a implementação da interface IRepositorioReserva.
 * Criação do arraylist para guardar os objetos do tipo "reserva".
 */

public class RepositorioReserva implements IRepositorioReserva{
    DBCenter dbCenter;
    private ArrayList<Reserva> reserva;
    
    public RepositorioReserva(){
        this.reserva = new ArrayList<>();
        this.dbCenter = new DBCenter();
    }


    /**
     * Método para adicionar uma nova reserva.
     * @param reserva Reserva que será adicionada.
     */
    @Override
    public void adicionarReserva(Reserva reserva) {

        if (this.verificarExistenciaReserva(reserva)) {
            return;
        }

        boolean flag = false;

        LocalDateTime dateTime = reserva.getDataHora();
        java.sql.Date sqlDate = java.sql.Date.valueOf(dateTime.toLocalDate());

        //TODO: como vou saber o cpf do atendente? via ficar no padrao(222.222.222-22), até q se tenha o login
        String sql =
                "INSERT INTO atendente_reserva_mesas VALUES (" +
                        "\"222.222.222-22\"" + "," + reserva.getMesa() + ",\"" + sqlDate + "\",\"" + reserva.getClienteQueReservou()+ "\""
                        +")";

        try {
            this.dbCenter.executarChamada(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  Método para verificar se uma reserva que será feita já existe no repositório.
     * @param reservaQueSeraComparada Reserva que será comparada.
     * @return Retorna se existe ou não.
     */
    @Override
    public boolean verificarExistenciaReserva(Reserva reservaQueSeraComparada){


        //TODO: da pra usar o exists e/ou o count mas n lembro como e n tem tempo
        boolean flag = false;

        LocalDateTime dateTime = reservaQueSeraComparada.getDataHora();
        java.sql.Date sqlDate = java.sql.Date.valueOf(dateTime.toLocalDate());

        String sql =
                "SELECT * FROM atendente_reserva_mesas WHERE (" +
                "numeroMesa=" + reservaQueSeraComparada.getMesa()  + " AND " +
                "cpfCliente=\"" + reservaQueSeraComparada.getClienteQueReservou()+ "\" " +
                ")";

        ResultSet resultado;

        int count = 0;
        try {
            resultado = this.dbCenter.executarChamada(sql);


            while(resultado.next()){
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }

        if (count > 0) {
            flag = true;
        }

        return flag;

    }

    /**
     * Método para mudar atributos (informações) de uma reserva.
     * @param novosDados Novos dados.
     * @param antiga Reserva que será alterada.
     */
    @Override
    public void mudarUmaReserva(Reserva novosDados, Reserva antiga) {
        if (this.verificarExistenciaReserva(antiga)) {
            String cpfCliente = novosDados.getClienteQueReservou();
            int numMesa = novosDados.getMesa();
            LocalDateTime dateTime = novosDados.getDataHora();
            java.sql.Date sqlDate = java.sql.Date.valueOf(dateTime.toLocalDate());



            String sql =
                    "UPDATE atendente_reserva_mesas " +
                            "SET cpfCliente =\"" + cpfCliente + "\" , numeroMesa=" + numMesa + ", data = \"" + sqlDate.toString() +"\"" +
                           " WHERE " +
                                    "numeroMesa=" + antiga.getMesa()  + " AND " +
                                            "cpfCliente=\"" + antiga.getClienteQueReservou()+ "\" " +
                                            ""

            ;

            try {
                this.dbCenter.executarChamada(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    /**]
     *  Método para saber o índice de uma reserva.
     * @param reservaQueSeDesejaSaberIdex Reserva que será fornecida o índice.
     * @return Retorna o índice da reserva.
     */
    @Override
    public int pegarIdex(Reserva reservaQueSeDesejaSaberIdex) {
        int idex  = 0;
        for (int i = 0; i < this.reserva.size(); i++){
            if (this.reserva.get(i).equals(reservaQueSeDesejaSaberIdex)){
                idex = this.reserva.indexOf(this.reserva.get(i));
                break;
            }
        }
        return idex;
    }

    /**
     * Método para pegar uma reserva de um determinado cliente.
     * @return Retorna a reserva que um determinado cliente reservou em determinada data
     */
    @Override
    public Reserva pegarReserva(Reserva reservaParam) {

        if (!this.verificarExistenciaReserva(reservaParam)) { return null; }

        String cpf = reservaParam.getClienteQueReservou();
        Reserva reserva = null;
        Mesa mesa = null;
        String cpfCliente;
        int numeroMesa = 0;
        LocalDate dataDB = null;

        String sql =
                "SELECT * FROM atendente_reserva_mesas WHERE (" +
                        "cpfCliente=\"" + cpf + "\" " +
                        ")";

        ResultSet resultado;

        try {
            resultado = this.dbCenter.executarChamada(sql);


            while(resultado.next()){
                numeroMesa = resultado.getInt("numeroMesa");
                dataDB = resultado.getDate("data").toLocalDate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return reserva;
        }


        String sqlMesa =
                "SELECT * FROM mesas WHERE (" +
                        "numero=\"" + numeroMesa + "\" " +
                        ")";

        try {
            boolean hasOne = false;
            ResultSet rs = this.dbCenter.executarChamada(sqlMesa);
            int numero = 0;
            boolean disp = true;
            while (rs.next()) {
                hasOne = true;
                disp = rs.getBoolean("disponibilidade");
                numero = rs.getInt("numero");
            }
            if (hasOne) {
                mesa = new Mesa(numero, disp ? "Livre" : "Oculpado");
            }

        }catch (Exception e) {
            e.printStackTrace();
            return reserva;
        }

        reserva = new Reserva(LocalDateTime.of(dataDB, LocalTime.of(0, 0)), cpf, mesa);

        return reserva;
    }

    /**
     * Método para deletar uma reserva.
     * @param reservaQueSeraDeletada Reserva que será deletada.
     */
    @Override
    public void deletarReserva(Reserva reservaQueSeraDeletada){
        if (this.verificarExistenciaReserva(reservaQueSeraDeletada)) {
            String sql =
                    "DELETE FROM atendente_reserva_mesas " +
                            " WHERE " +
                            "numeroMesa=" + reservaQueSeraDeletada.getMesa()  + " AND " +
                            "cpfCliente=\"" + reservaQueSeraDeletada.getClienteQueReservou()+ "\" " +
                            ""

                    ;

            try {
                this.dbCenter.executarChamada(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
     }


    @Override
    public Reserva pegarReserva(String cpf) {
        Reserva reserva = null;
        Mesa mesa = null;
        String cpfCliente;
        int numeroMesa = 0;
        LocalDate dataDB = null;


        if (!this.verificarExistenciaReserva(cpf)) { return null; }

        String sql =
                "SELECT * FROM atendente_reserva_mesas WHERE (" +
                        "cpfCliente=\"" + cpf + "\" " +
                        ")";

        ResultSet resultado;

        try {
            resultado = this.dbCenter.executarChamada(sql);


            while(resultado.next()){
                cpfCliente = resultado.getString("cpfCliente");
                numeroMesa = resultado.getInt("numeroMesa");
                dataDB = resultado.getDate("data").toLocalDate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return reserva;
        }


        String sqlMesa =
                "SELECT * FROM mesas WHERE (" +
                        "numero=\"" + numeroMesa + "\" " +
                        ")";

        try {
            boolean hasOne = false;
            ResultSet rs = this.dbCenter.executarChamada(sqlMesa);
            int numero = 0;
            boolean disp = true;
            while (rs.next()) {
                hasOne = true;
                disp = rs.getBoolean("disponibilidade");
                numero = rs.getInt("numero");
            }
            if (hasOne) {
                mesa = new Mesa(numero, disp ? "Livre" : "Oculpado");
            }

        }catch (Exception e) {
            e.printStackTrace();
            return reserva;
        }

        reserva = new Reserva(LocalDateTime.of(dataDB, LocalTime.of(0, 0)), cpf, mesa);

        return reserva;


    }

    @Override
    public boolean verificarExistenciaReserva(String cpf){
        //TODO: da pra usar o exists e/ou o count mas n lembro como e n tem tempo
        boolean flag = false;

        String sql =
                "SELECT * FROM atendente_reserva_mesas WHERE (" +
                        "cpfCliente=\"" + cpf + "\" " +
                        ")";

        ResultSet resultado;

        int count = 0;
        try {
            resultado = this.dbCenter.executarChamada(sql);


            while(resultado.next()){
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }

        if (count > 0) {
            flag = true;
        }

        return flag;
    }


}