package Restaurante.repositorios;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.repositorios.interfaces.IRepositorioIngrediente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioIngredientes implements IRepositorioIngrediente {
    private List<Ingrediente> ingredientes;
    private DBCenter dbCenter;

    public RepositorioIngredientes() {
        this.ingredientes = new ArrayList<>();
        this.dbCenter = new DBCenter();
    }

    @Override
    public void adicionarIngrediente(Ingrediente ingredienteQueSeraAdicionado) {
        String nome = ingredienteQueSeraAdicionado.getNome();
        String qtd = String.valueOf(ingredienteQueSeraAdicionado.getQtd());
        String sql = "INSERT INTO ingredientes (nome, qtd) VALUES ('" + nome + "', '" + qtd + "')";
        try {
            dbCenter.executarChamada(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        this.ingredientes.add(ingredienteQueSeraAdicionado);
    }

    @Override
    public boolean proucurarIngrediente(String nome) {
        boolean ingrediente = false;
        String sql = "SELECT * FROM ingredientes";
        try {
            ResultSet encontrou = dbCenter.executarChamada(sql);
            while (encontrou.next()){
                if (encontrou.getString("nome").equals(nome)){
                    ingrediente = true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return ingrediente;
    }

    @Override
    public void removerIngrediente(Ingrediente ingredienteQueSeraRemovido) {
        String sql = "DELETE * FROM ingredientes NATURAL JOIN pratos_possuem_ingredientes WHERE nome = '" + ingredienteQueSeraRemovido.getNome() + "'";
        try {
            this.dbCenter.executarChamada(sql);
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    @Override
    public void mudarAtributosIngrediente(Ingrediente ingrediente, int index) {
        String sql =  "UPDATE ingredientes NATURAL JOIN pratos_possuem_ingredientes" +
                "SET nome ='" + ingrediente.getNome() +"', quantidade ='"+ ingrediente.getQtd() +"'\n" +
                "WHERE nome = '" + ingrediente.getNome() + "'";
        try {
            this.dbCenter.executarChamada(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mudarAtributosIngrediente(int qtd, int index) {
        this.ingredientes.get(index).setQtd(qtd);
    }

    @Override
    public Ingrediente pegarIngrediente(String nome) {
        String sql = "SELECT * FROM ingredientes";
        try {
            ResultSet encontrou = dbCenter.executarChamada(sql);
            while (encontrou.next()) {
                if (encontrou.getString("nome").equals(nome)) {
                    return new Ingrediente(encontrou.getString("nome"), Integer.parseInt(encontrou.getString("quantidade")));
                }
            }
            return null;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    @Override
    public Ingrediente pegarIngrediente(int index) {
        return null;
    }

    @Override
    public int pegarIdex(Ingrediente ingredienteQueSeraPegoIdex) {
        return 0;
    }

}
