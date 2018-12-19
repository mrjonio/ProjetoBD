package Restaurante.repositorios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.excessoes.PratoPendenteErro;
import Restaurante.repositorios.interfaces.IRepositorioCardapio;

/**
 * Abaixo temos a classe para o repositório de cardápio que serve para armazenar em um arraylist todos os dados
 * dos pratos cadastrados; e a implementação da interface IRepositórioCardápio.
 * Criação do arraylist para guardar os objetos do tipo "prato".
 */
public class RepositorioCardapio implements IRepositorioCardapio{
    private DBCenter dbCenter;

    public RepositorioCardapio() {
        this.dbCenter = new DBCenter();
    }

    /**
     * Método para a verificação da existência de um prato no repositório através do seu nome.
     * @param nome Nome do prato a ser verificado.
     * @return Retorna se ele existe ou não.
     */
    @Override
    public boolean verificarExistenciaPrato(String nome){
        ResultSet rs;
        String sql =
                "SELECT nome" +
                " FROM pratos " +
                " WHERE " +
                        "nome=\"" + nome + "\"";
        try {
            rs = this.dbCenter.executarChamada(sql);
            while(rs.next()){ return true; }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Método para adicionar pratos ao cardápio.
     * @param prato Prato a ser adicionado.
     */
    @Override
    public void adicionarPratoAoCardapio(PratoCardapio prato) {
        String sql =
                "INSERT INTO pratos VALUES (" +
                        "\"" + prato.getNome() + "\", \"" + prato.getPreco() + "\", x'" + prato.getFotoBytes() + "')";

        try {
            this.dbCenter.executarChamada(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Add todos os ingredientes quem compoem o prato
        adicionarIngredientesPratos(prato);
    }

    /**
     * Método para alterar atributos de um prato. Seleciona o índice do atributo desejado e altera esse atributo.
     * @param novoPratoComNovosAtributos Prato com novos atributos.
     */
    @Override
    public void alterarAtributoPrato(PratoCardapio novoPratoComNovosAtributos){
    	String sql = " UPDATE pratos" +
                " SET preco=\"" + novoPratoComNovosAtributos.getPreco() + "\", imagem=x'" + novoPratoComNovosAtributos.getFotoBytes() + "'" +
                " WHERE nome=\"" + novoPratoComNovosAtributos.getNome() + "\"";
        try {
            this.dbCenter.executarChamada(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        //Atualiza ingredientes
        removerIngredientesPratos(novoPratoComNovosAtributos.getNome());
        adicionarIngredientesPratos(novoPratoComNovosAtributos);
    }

    //Código morto
    /**
     * Método para pegar o índice de um prato.
     * @param pratoQueSeraPegoidex Índice do prato a ser pego.
     * @return Retorna o índice do prato que foi pego.
     */
    @Override
    public int pegarIdex(PratoCardapio pratoQueSeraPegoidex) {
        return 0;
    }

    /**
     * Método para pegar um prato.
     * Se o prato solicitado tiver o mesmo nome de um prato no cardápio,
     * então esse prato será pego.
     * @param nomePrato Nome do prato solicitado.
     * @return Retorna o prato.
     */
    @Override
    public PratoCardapio pegarPrato(String nomePrato) {
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
            pratoPego.setNome(rs.getString("nome"));
            pratoPego.setPreco(rs.getFloat("preco"));
            pratoPego.setFoto(rs.getBytes("imagem"));
//          pratoPego.setFoto(rs.getBytes("imagem")); //Cata a imagem

            return pratoPego;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return pratoPego;
    }

    //Código morto
    @Override
    public PratoCardapio pegarPrato(int numeroPrato) {
        return null;
    }


    /**
     * Método para a remoção de um prato do cardápio.
     * @param pratoQueSeraRetiradoDoCardapio Prato que será removido.
     */
    @Override
    public void removerPrato(PratoCardapio pratoQueSeraRetiradoDoCardapio) throws PratoPendenteErro {
        ResultSet rs;

        //TODO: Botar isso no RepositorioPedidos
        //Verifica se o existe algum pedido pendente relacionado aquele prato
        String sql = "SELECT nome_prato " +
                " FROM pedidos_tem_pratos " +
                " WHERE (nome_prato=\"" + pratoQueSeraRetiradoDoCardapio.getNome() + "\")";
        try {
            rs = this.dbCenter.executarChamada(sql);
            while(rs.next()) {throw new PratoPendenteErro(pratoQueSeraRetiradoDoCardapio.getNome());}
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        //Deleta os ingredientes do prato
        removerIngredientesPratos(pratoQueSeraRetiradoDoCardapio.getNome());

        //Deleta os pratos
        String sql2 =
            "DELETE FROM pratos " +
                " WHERE " +
                "nome=\"" + pratoQueSeraRetiradoDoCardapio.getNome() + "\"";
        try {
            this.dbCenter.executarChamada(sql2);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adiciona todos os ingredientes que compoem o prato
     * @param prato Prato que é composto por os ingredientes
     */
    private void adicionarIngredientesPratos(PratoCardapio prato){
        for (Ingrediente ing : prato.getIngredientes()) {
            String sql2 =
                    "INSERT INTO pratos_possuem_ingredientes VALUES (" +
                            "\"" + prato.getNome() + "\", \"" + ing.getNome() + "\", \"" + ing.getQtd() + "\")";
            try {
                this.dbCenter.executarChamada(sql2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Remove todos os inngredientes que compoem aquele prato
     * @param nomePrato Nome do prato que é composto por os ingredientes
     */
    private void removerIngredientesPratos(String nomePrato) {
        String sql2 = "DELETE FROM pratos_possuem_ingredientes " +
                " WHERE " +
                "nome_prato=\"" + nomePrato + "\"";
        try {
            this.dbCenter.executarChamada(sql2);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Caso necessário alterar uma chave estrangeira
     * @param valor 'true' para não permitir a alteração; 'false' para permitir a alteração
     */
    private void checagemChaveEstrangeira(boolean valor) {
        String sql;
        if(valor == false) {
            sql = "SET foreign_key_checks = 0";
        } else {
            sql = "SET foreign_key_checks = 1";
        }
        try {
            this.dbCenter.executarChamada(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
