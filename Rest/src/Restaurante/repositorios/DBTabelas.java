package Restaurante.repositorios;


import java.lang.reflect.Field;
import java.util.ArrayList;

public class DBTabelas {

    private DBCenter dbCenter;

    // Nao mudar a ordem dessas string com tab no inicio, precisam estar nessa ordem pra
    // executar os drop table de forma dinamica

    private static final String tabGerentes = "gerentes";
    private static final String tabAtendenteReservaMesas = "atendente_reserva_mesas";
    private static final String tabAtendentes = "atendentes";
    private static final String tabMesasFazPedidos = "mesas_faz_pedidos";
    private static final String tabMesas = "mesas";
    private static final String tabPedidosTemPratos = "pedidos_tem_pratos";
    private static final String tabPratosPossuemIngredientes = "pratos_possuem_ingredientes";
    private static final String tabIngredientes = "ingredientes";
    private static final String tabPratos = "pratos";
    private static final String tabCozinheiroCozinhaPedido = "conzinheiro_cozinha_pedido";
    private static final String tabCozinheiros = "cozinheiros";
    private static final String tabPedidos = "pedidos";
    private static final String tabFuncionarios = "funcionarios";

    // Crio uma string generica onde posso substituir $chave$ por seu valor real
    private static final String criarCmd = "CREATE TABLE IF NOT EXISTS $tabela$ ( $atributos$ )";
    private static final String dropCmd = "DROP TABLE IF EXISTS $tabela$ CASCADE";

    private void executarCmd(String sql) {
        try {
            this.dbCenter.executarChamada(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void excluirTabelas() {

        Field[] campos = DBTabelas.class.getDeclaredFields();


        ArrayList<String> tabelas = new ArrayList<>();

        for (Field f: campos) {
            if(f.getName().startsWith("tab")) {
                String s;
                try {
                    f.setAccessible(true);
                    s = f.get(new Object()).toString();
                    tabelas.add(s);
                }catch (Exception e) {e.printStackTrace();}
            }
        }

        for (String tabela: tabelas) {
            String cmd = dropCmd.replace("$tabela$", tabela);
            this.executarCmd(cmd);
        }
    }

    private void criarTabFuncionarios() {
        String atributos = "cpf VARCHAR(14) NOT NULL," +
                "  nome TEXT NOT NULL," +
                "  salario FLOAT UNSIGNED NOT NULL," +
                "  idade INT UNSIGNED NOT NULL," +
                "  PRIMARY KEY (cpf)";

        String cmd = criarCmd.replace("$atributos$", atributos);
        cmd = cmd.replace("$tabela$", tabFuncionarios);
        this.executarCmd(cmd);
    }

    private void criarTabCozinheiros () {
        String atributos = "cpfCozinheiro VARCHAR(14) NOT NULL,\n" +
                "  PRIMARY KEY (cpfCozinheiro),\n" +
                "  CONSTRAINT FUNCIONARIO_COZINHEIRO\n" +
                "    FOREIGN KEY (cpfCozinheiro)\n" +
                "    REFERENCES funcionarios (cpf)";

        String cmd = criarCmd.replace("$atributos$", atributos);
        cmd = cmd.replace("$tabela$", tabCozinheiros);
        this.executarCmd(cmd);
    }

    private void criarTabGerentes () {
        String atributos = "cpfGerente VARCHAR(14) NOT NULL,\n" +
                "  PRIMARY KEY (cpfGerente),\n" +
                "  CONSTRAINT FUNCIONARIO_GERENTE\n" +
                "    FOREIGN KEY (cpfGerente)\n" +
                "    REFERENCES funcionarios (cpf)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION";

        String cmd = criarCmd.replace("$atributos$", atributos);
        cmd = cmd.replace("$tabela$", tabGerentes);
        this.executarCmd(cmd);
    }

    private void criarTabAtendentes () {
        String atributos = "cpfAtendente VARCHAR(14) NOT NULL,\n" +
                "  PRIMARY KEY (cpfAtendente),\n" +
                "  CONSTRAINT FUNCIONARIO_ATENDENTE\n" +
                "    FOREIGN KEY (cpfAtendente)\n" +
                "    REFERENCES funcionarios (cpf)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION";

        String cmd = criarCmd.replace("$atributos$", atributos);
        cmd = cmd.replace("$tabela$", tabAtendentes);
        this.executarCmd(cmd);
    }

    private void criarTabPedidos () {
        String atributos = "idpedidos INT UNSIGNED NOT NULL,\n" +
                "  data DATETIME NOT NULL,\n" +
                "  PRIMARY KEY (idpedidos)";

        String cmd = criarCmd.replace("$atributos$", atributos);
        cmd = cmd.replace("$tabela$", tabPedidos);
        this.executarCmd(cmd);
    }

    private void criarTabPratos () {
        String atributos = "nome VARCHAR(50) NOT NULL,\n" +
                "  preco FLOAT NOT NULL,\n" +
                "  imagem TEXT NOT NULL,\n" +
                "  PRIMARY KEY (nome)";

        String cmd = criarCmd.replace("$atributos$", atributos);
        cmd = cmd.replace("$tabela$", tabPratos);
        this.executarCmd(cmd);
    }

    private void criarTabIngredientes () {
        String atributos = "nome VARCHAR(50) NOT NULL,\n" +
                "  quantidade INT UNSIGNED ZEROFILL NOT NULL,\n" +
                "  PRIMARY KEY (nome)";

        String cmd = criarCmd.replace("$atributos$", atributos);
        cmd = cmd.replace("$tabela$", tabIngredientes);
        this.executarCmd(cmd);
    }

    private void criarTabMesas () {
        String atributos = "numero INT UNSIGNED NOT NULL,\n" +
                "  disponibilidade TINYINT NOT NULL DEFAULT 1,\n" +
                "  PRIMARY KEY (numero)";

        String cmd = criarCmd.replace("$atributos$", atributos);
        cmd = cmd.replace("$tabela$", tabMesas);
        this.executarCmd(cmd);
    }

    private void criarTabCozinheiroCozinhaPedido () {
        String atributos = "cpfCozinheiro VARCHAR(14) NOT NULL,\n" +
                "  idPedido INT UNSIGNED NOT NULL,\n" +
                "  PRIMARY KEY (cpfCozinheiro, idPedido),\n" +
                "  CONSTRAINT PEDIDO\n" +
                "    FOREIGN KEY (idPedido)\n" +
                "    REFERENCES pedidos (idpedidos)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT COZINHEIRO\n" +
                "    FOREIGN KEY (cpfCozinheiro)\n" +
                "    REFERENCES cozinheiros (cpfCozinheiro)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION";

        String cmd = criarCmd.replace("$atributos$", atributos);
        cmd = cmd.replace("$tabela$", tabCozinheiroCozinhaPedido);
        this.executarCmd(cmd);
    }

    private void criarTabPratosPossuemIngredientes () {
        String atributos = "nome_prato VARCHAR(50) NOT NULL,\n" +
                "  nome_ingrediente VARCHAR(50) NOT NULL,\n" +
                "  quantidade INT UNSIGNED ZEROFILL NULL,\n" +
                "  PRIMARY KEY (nome_prato, nome_ingrediente),\n" +
                "  CONSTRAINT NOME_PRATO\n" +
                "    FOREIGN KEY (nome_prato)\n" +
                "    REFERENCES pratos (nome)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT NOME_INGREDIENTE\n" +
                "    FOREIGN KEY (nome_ingrediente)\n" +
                "    REFERENCES ingredientes (nome)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION";

        String cmd = criarCmd.replace("$atributos$", atributos);
        cmd = cmd.replace("$tabela$", tabPratosPossuemIngredientes);
        this.executarCmd(cmd);
    }

    private void criarTabPedidosTemPratos () {
        String atributos = "idpedidos INT UNSIGNED NOT NULL,\n" +
                "  nome_prato VARCHAR(50) NOT NULL,\n" +
                "  PRIMARY KEY (idpedidos, nome_prato),\n" +
                "  CONSTRAINT PEDIDO_FEITO\n" +
                "    FOREIGN KEY (idpedidos)\n" +
                "    REFERENCES pedidos (idpedidos)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT PRATO\n" +
                "    FOREIGN KEY (nome_prato)\n" +
                "    REFERENCES pratos (nome)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION";

        String cmd = criarCmd.replace("$atributos$", atributos);
        cmd = cmd.replace("$tabela$", tabPedidosTemPratos);
        this.executarCmd(cmd);
    }

    private void criarTabMesasFazPedidos () {
        String atributos = "numeroMesa INT UNSIGNED NOT NULL,\n" +
                "  idPedido INT UNSIGNED NOT NULL,\n" +
                "  PRIMARY KEY (numeroMesa, idPedido),\n" +
                "  CONSTRAINT PEDIDO_MESA\n" +
                "    FOREIGN KEY (idPedido)\n" +
                "    REFERENCES pedidos (idpedidos)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT MESA_PEDINTE\n" +
                "    FOREIGN KEY (numeroMesa)\n" +
                "    REFERENCES mesas (numero)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION";

        String cmd = criarCmd.replace("$atributos$", atributos);
        cmd = cmd.replace("$tabela$", tabMesasFazPedidos);
        this.executarCmd(cmd);
    }

    private void criarTabAtendenteReservaMesas () {
        String atributos = "cpfAtendente VARCHAR(14) NOT NULL,\n" +
                "  numeroMesa INT UNSIGNED NOT NULL,\n" +
                "  PRIMARY KEY (cpfAtendente, numeroMesa),\n" +
                "  CONSTRAINT MESA_RESERVADA\n" +
                "    FOREIGN KEY (numeroMesa)\n" +
                "    REFERENCES mesas (numero)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT ATENDENTE\n" +
                "    FOREIGN KEY (cpfAtendente)\n" +
                "    REFERENCES atendentes (cpfAtendente)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION";

        String cmd = criarCmd.replace("$atributos$", atributos);
        cmd = cmd.replace("$tabela$", tabAtendenteReservaMesas);
        this.executarCmd(cmd);
    }

    private void criarTabelas() {
        this.criarTabFuncionarios();
        this.criarTabPedidos();
        this.criarTabCozinheiros();
        this.criarTabCozinheiroCozinhaPedido();
        this.criarTabPratos();
        this.criarTabIngredientes();
        this.criarTabPratosPossuemIngredientes();
        this.criarTabPedidosTemPratos();
        this.criarTabMesas();
        this.criarTabMesasFazPedidos();
        this.criarTabAtendentes();
        this.criarTabAtendenteReservaMesas();
        this.criarTabGerentes();
    }

    public void migrar() {
        this.dbCenter = new DBCenter();
        this.excluirTabelas();
        this.criarTabelas();
    }


}
