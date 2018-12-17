package Restaurante.repositorios;

import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;
import Restaurante.repositorios.interfaces.IRepositorioFuncionario;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Abaixo temos a classe para o repositório de funcionários, que serve para armazenar em um arraylist todas os dados
 * dos funcionários cadastrados; e a implementação da interface IRepositorioFuncionario.
 * Criação do arraylist para guardar os objetos do tipo "funcionário".
 */

public class RepositorioFuncionario implements IRepositorioFuncionario {
    private DBCenter dbCenter;

    public RepositorioFuncionario(){
        this.dbCenter = new DBCenter();
    }

    /**
     * Método para adicionar um novo funcionário.
     * @param funcionario Informações do funcionário a ser adicionado.
     */

    @Override
    public void adicionarPessoa(Funcionario funcionario) {
        String funcao = funcionario.getFuncao();
        String cpf = funcionario.getCpf();
        String idade = String.valueOf(funcionario.getIdade());
        String nome = funcionario.getNome();
        String salario = String.valueOf(funcionario.getSalario());
        String sexo = funcionario.getSexo();
        String sql = "INSERT INTO funcionarios (funcao, cpf, idade, nome, salario, sexo) VALUES ('" + funcao + "', '" +
                cpf + "', '" + idade + "', '" + nome + "', '" + salario + "', '" + "', '" + sexo + "')";
        try {
            dbCenter.executarChamada(sql);
            if (funcao.equals("Cozinheiro")) {
                String sql2 = "INSERT INTO cozinheiros (funcao, cpf, idade, nome, salario, sexo) VALUES ('" + funcao + "', '" +
                        cpf + "', '" + idade + "', '" + nome + "', '" + salario + "', '" + "', '" + sexo + "')";
                dbCenter.executarChamada(sql2);
            }
            if (funcao.equals("Gerente")) {
                String sql2 = "INSERT INTO gerentes (funcao, cpf, idade, nome, salario, sexo) VALUES ('" + funcao + "', '" +
                        cpf + "', '" + idade + "', '" + nome + "', '" + salario + "', '" + "', '" + sexo + "')";
                dbCenter.executarChamada(sql2);
            }
            if (funcao.equals("Atendente")) {
                String sql2 = "INSERT INTO atendentes (funcao, cpf, idade, nome, salario, sexo) VALUES ('" + funcao + "', '" +
                        cpf + "', '" + idade + "', '" + nome + "', '" + salario + "', '" + "', '" + sexo + "')";
                dbCenter.executarChamada(sql2);
            }
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Método para verificar se um funcionário está no repositório, através de seu cpf.
     * @param cpf Cpf do funcionário.
     * @return Retorna se está ou não no repositório.
     */
    @Override
    public boolean proucurarPessoa(String cpf) {
        String sql = "SELECT * FROM funcionarios WHERE cpf = '" + cpf + "'";
        try {
            ResultSet resultSet = dbCenter.executarChamada(sql);
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }


    /**
     * Método para remover um funcionário do repositório.
     * @param funcionarioQueSeraRemovido Funcionário que será removido
     */
    @Override
    public void removerPessoa(Funcionario funcionarioQueSeraRemovido){
        try {
            if (funcionarioQueSeraRemovido.getFuncao().equals("Cozinheiro")) {
                String sql = "DELETE * FROM funcionarios, cozinheiros WHERE cpf = '" + funcionarioQueSeraRemovido.getCpf() + "'";
                dbCenter.executarChamada(sql);
            }
            if (funcionarioQueSeraRemovido.getFuncao().equals("Gerente")) {
                String sql = "DELETE * FROM funcionarios, gerentes WHERE cpf = '" + funcionarioQueSeraRemovido.getCpf() + "'";
                dbCenter.executarChamada(sql);
            }
            if (funcionarioQueSeraRemovido.getFuncao().equals("Atendente")) {
                String sql = "DELETE * FROM funcionarios, atendentes WHERE cpf = '" + funcionarioQueSeraRemovido.getCpf() + "'";
                dbCenter.executarChamada(sql);
            } else {
                String sql = "DELETE * FROM funcionarios WHERE cpf = '" + funcionarioQueSeraRemovido.getCpf() + "'";
                dbCenter.executarChamada(sql);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Método para mudar atributos de um funcionário, através do índice desse atributo.
     * @param funcionario Funcionário que terá os atributos modificados.
     * @param index Índice do atributo que será modificado.
     */
    @Override
    public void mudarAtributosFuncionario(Funcionario funcionario, int index) {
        try{
        String sql = "UPDATE funcionarios NATURAL JOIN cozinheiros NATURAL JOIN gerentes NATURAL JOIN atendentes" +
                " SET nome ='" + funcionario.getNome() +"',  funcao='"+ funcionario.getFuncao() +"', sexo ='" + funcionario.getSexo() +"', salario= '" + funcionario.getSalario() +
                "', idade ='" + funcionario.getIdade() + "'" + "WHERE cpf = '" + funcionario.getCpf() + "'";
        dbCenter.executarChamada(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para pegar um funcionário no repositório, através de seu cpf.
     * @param cpf Cpf do funcionário a ser buscado.
     * @return Retorna o funcionário que será pego.
     */
    @Override
    public Funcionario pegarFuncionario(String cpf) {
        String sql = "SELECT * FROM funcionarios WHERE cpf = '" + cpf + "'";
        try {
            ResultSet resultSet = dbCenter.executarChamada(sql);
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String cpfB = resultSet.getString("cpf");
                String sexo = resultSet.getString("sexo");
                String funcao = resultSet.getString("funcao");
                int idade = Integer.parseInt(resultSet.getString("idade"));
                double salario = Double.parseDouble(resultSet.getString("salario"));
                return new Funcionario(nome, cpfB, idade, sexo, funcao, salario);
            } else {
                return null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
    }


    /**
     * Método para calcular a folha de pagamento.
     * Calcula o pagamento total do salário de todos os funcionários.
     * @return Retorna o total.
     */
    @Override
    public double calcularFolhaDePagamento(){
        String sql = "SELECT * FROM folhaPagamento";
        try {
            ResultSet resultSet = dbCenter.executarChamada(sql);
            if (resultSet.next()){
                return Double.parseDouble(resultSet.getString("salario"));
            }else{
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            return 0;
        }
    }

    /**]
     * Método para pegar o índice de um cliente no repositório.
     * @param funcionarioQueSeraPegoIdex Funcionário que será pego pelo índice.
     * @return Retorna o índice do funcionário.
     */
    @Override
    public int pegarIdex(Funcionario funcionarioQueSeraPegoIdex){
        return 0;
    }
}
