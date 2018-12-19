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

        boolean disp = !mesa.isDisponibilidade().equals("Oculpado");

        String sql = "INSERT INTO mesas VALUES ( "+ mesa.getNumero() + "," + disp +")";


        try {
            this.dbCenter.executarChamada(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.alterarAtributosMesa(mesa, mesa.getNumero());

    }


    /**
     * Método para pegar uma mesa, através de seu índice.
     *
     * @param index Índice da mesa a ser pega.
     * @return Retorna o índice da mesa.
     */
    @Override
    public Mesa pegarMesa(int index) {
        String sql = "SELECT * FROM mesas";
        Mesa mesa = null;
        try {
            ResultSet mesas = this.dbCenter.executarChamada(sql);
            while (mesas.next()) {

                if (Integer.parseInt(mesas.getString("numero")) == index) {
                    String disponibilidade = mesas.getString("disponibilidade");
                    mesa = new Mesa(index, disponibilidade.equals("0") ? "Oculpada" : "Vazia");



                    String sql2 = "SELECT * FROM mesas_faz_pedidos WHERE numeroMesa = '" + index + "'";
                    ResultSet pedidos = this.dbCenter.executarChamada(sql2);
                    ArrayList<Pedido> pedido = new ArrayList<>();
                    while (pedidos.next()){
                        String sql3 = "SELECT * FROM pedidos_tem_pratos WHERE idpedidos = '" + pedidos.getString("idPedido") + "'";
                        ResultSet pratos = this.dbCenter.executarChamada(sql3);
                        ArrayList<PratoCardapio> prato = new ArrayList<>();
                        while (pratos.next()){
                            String sql4 = "SELECT * FROM pratos WHERE nome = \""+ pratos.getString("nome_prato") +"\"";
                            ResultSet pratosResultado = this.dbCenter.executarChamada(sql4);
                            ArrayList<Ingrediente> ingredientes = new ArrayList<>();
                            while (pratosResultado.next()) {
                                String sql5 = "SELECT * FROM pratos_possuem_ingredientes WHERE nome_prato = \"" + pratosResultado.getString("nome") + "\"";
                                ResultSet ingredientesResultado = this.dbCenter.executarChamada(sql5);
                                while (ingredientesResultado.next()) {
                                    String sql6 = "SELECT * FROM ingredientes WHERE nome = +'" + ingredientesResultado.getString("nome_ingrediente") + "'";
                                    ResultSet ingredientesTAbela = this.dbCenter.executarChamada(sql6);
                                    while (ingredientesTAbela.next()) {
                                        Ingrediente i = new Ingrediente(ingredientesTAbela.getString("nome"), ingredientesTAbela.getInt("quantidade"));
                                        ingredientes.add(i);
                                    }
                                }
                            }
                            PratoCardapio p = new PratoCardapio(pratos.getString("nome_prato"), pratos.getDouble("preco"), ingredientes);
                            prato.add(p);
                        }
                        Pedido ped = new Pedido(prato, mesa);
                        pedido.add(ped);
                    }
                    for (Pedido p: pedido) {
                        mesa.adicionarPedido(p);
                    }
                }
            }
        } catch (Exception e) {
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
        //nao testei direito
        String sql = "DELETE FROM mesas WHERE numero = '" + mesaQueSeraRemovida.getNumero() + "'";
        String sql2 = "DELETE FROM mesas_faz_pedidos WHERE numeroMesa = '" + mesaQueSeraRemovida.getNumero() + "'";
        String sql3 = "DELETE FROM atendente_reserva_mesas WHERE numeroMesa = '" + mesaQueSeraRemovida.getNumero() + "'";
        try {
            this.dbCenter.executarChamada(sql3);
            this.dbCenter.executarChamada(sql2);
            this.dbCenter.executarChamada(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterarAtributosMesa(Mesa novosAtributos, int index) {

        //só nao atualiza a mesa pro atendente


        String sql =  "INSERT IGNORE INTO mesas\n" +"VALUES (" + novosAtributos.getNumero() +", disponibilidade ="+ novosAtributos.isDisponibilidade().equals("Livre") +")\n";

        try {
            Mesa mesa = this.pegarMesa(index);
            this.dbCenter.executarChamada(sql);
            for (int i = 0; i < novosAtributos.getPedidos().size(); i++) {
                Pedido p = novosAtributos.getPedidos().get(i);

                if (p.getIdPedido() == 0) {
                    //novo
                    p.setIdPedido(this.getFreeId());
                    System.out.println(this.getFreeId());
                    String sql3 = "INSERT INTO pedidos VALUES ("+ p.getIdPedido() + ",'"+ p.getDataDoPedido().toString() +"')";
                    this.dbCenter.executarChamada(sql3);
                    String sql4 = "INSERT INTO mesas_faz_pedidos VALUES ("+ novosAtributos.getNumero() + ","+ p.getIdPedido() +")";
                    this.dbCenter.executarChamada(sql4);

                    for (int j = 0; j < p.getPratoPedido().size(); j++) {
                        PratoCardapio pratoCardapio = p.getPratoPedido().get(j);
                        String sql6 = "INSERT INTO pedidos_tem_pratos VALUES ("+ p.getIdPedido() + ",'"+ pratoCardapio.getNome() + "', 0)";
                        this.dbCenter.executarChamada(sql6);
                    }
                    return;
                }

                String sql2 = "SELECT COUNT(*) FROM pedidos WHERE(idpedidos = "+ p.getIdPedido() +")";

                ResultSet attOUnovo = this.dbCenter.executarChamada(sql2);
                attOUnovo.first();

                if (attOUnovo.getInt(1) > 0) {
                    //atualiza
                    String sql5 = "UPDATE mesas_faz_pedidos SET numeroMesa=" + novosAtributos.getNumero() +" WHERE (numeroMesa = " + mesa.getNumero() +" AND idPedido = " + p.getIdPedido() + ")";
                    this.dbCenter.executarChamada(sql5);
                } else {
                    //novo
                    p.setIdPedido(this.getFreeId());
                    String sql3 = "INSERT INTO pedidos VALUES ("+ p.getIdPedido() + ",'"+ p.getDataDoPedido().toString() +"')";
                    this.dbCenter.executarChamada(sql3);
                    String sql4 = "INSERT INTO mesas_faz_pedidos VALUES ("+ novosAtributos.getNumero() + ","+ p.getIdPedido() +")";
                    this.dbCenter.executarChamada(sql4);


                    for (int j = 0; j < p.getPratoPedido().size(); j++) {
                        PratoCardapio pratoCardapio = p.getPratoPedido().get(j);
                        String sql6 = "INSERT INTO pedidos_tem_pratos VALUES ("+ p.getIdPedido() + ",'"+ pratoCardapio.getNome() + "', 0)";
                        this.dbCenter.executarChamada(sql6);
                    }
                }
            }
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

        String sql = "SELECT * FROM mesas";

        try {
            ResultSet resultSet = this.dbCenter.executarChamada(sql);
            int count = 0;

            while (resultSet.next()) {
                count++;
                break;
            }

            if (count > 0) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return  true;
        }

    }

    /**
     * Método para verificar se o índice fornecido contem uma mesa no repositório.
     * @param idex Índice fornecido.
     * @return Retorna se há ou não uma mesa.
     */
    @Override
    public boolean indiceContemUmaMesa(int idex) {
        String sql = "SELECT * FROM mesas";
        try {
            ResultSet mesas = this.dbCenter.executarChamada(sql);
            while (mesas.next()) {
                if (Integer.parseInt(mesas.getString("numero")) == idex) {
                    return  true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
        String sql = "SELECT COUNT(*) FROM mesas";
        try {
            ResultSet rs = this.dbCenter.executarChamada(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

    private int getFreeId() {
        int ret = -1;
        String sql = "SELECT MAX(idpedidos) FROM pedidos";

        try {
            ResultSet resultSet = this.dbCenter.executarChamada(sql);
            while (resultSet.next()) {
                ret = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret+1;
    }

}
