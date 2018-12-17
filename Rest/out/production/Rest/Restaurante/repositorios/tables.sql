-- -----------------------------------------------------
-- Tabela funcionarios
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS funcionarios (
  cpf VARCHAR(14) NOT NULL,
  nome TEXT NOT NULL,
  salario FLOAT UNSIGNED NOT NULL,
  idade INT UNSIGNED NOT NULL,
  PRIMARY KEY (cpf)
)
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Tabela pedidos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pedidos (
  idpedidos INT UNSIGNED NOT NULL,
  data DATETIME NOT NULL,
  PRIMARY KEY (idpedidos)
)
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Tabela cozinheiros
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cozinheiros (
  cpfCozinheiro VARCHAR(14) NOT NULL,
  PRIMARY KEY (cpfCozinheiro),
  CONSTRAINT FUNCIONARIO_COZINHEIRO
    FOREIGN KEY (cpfCozinheiro)
    REFERENCES funcionarios (cpf)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Tabela conzinheiro_cozinha_pedido
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS conzinheiro_cozinha_pedido (
  cpfCozinheiro VARCHAR(14) NOT NULL,
  idPedido INT UNSIGNED NOT NULL,
  PRIMARY KEY (cpfCozinheiro, idPedido),
  CONSTRAINT PEDIDO
    FOREIGN KEY (idPedido)
    REFERENCES pedidos (idpedidos)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT COZINHEIRO
    FOREIGN KEY (cpfCozinheiro)
    REFERENCES cozinheiros (cpfCozinheiro)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Tabela pratos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pratos (
  nome VARCHAR(50) NOT NULL,
  preco FLOAT NOT NULL,
  imagem TEXT NOT NULL,
  PRIMARY KEY (nome)
)
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Tabela ingredientes
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ingredientes (
  nome VARCHAR(50) NOT NULL,
  quantidade INT UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (nome)
)
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Tabela pratos_possuem_ingredientes
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pratos_possuem_ingredientes (
  nome_prato VARCHAR(50) NOT NULL,
  nome_ingrediente VARCHAR(50) NOT NULL,
  quantidade INT UNSIGNED ZEROFILL NULL,
  PRIMARY KEY (nome_prato, nome_ingrediente),
  CONSTRAINT NOME_PRATO
    FOREIGN KEY (nome_prato)
    REFERENCES pratos (nome)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT NOME_INGREDIENTE
    FOREIGN KEY (nome_ingrediente)
    REFERENCES ingredientes (nome)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Tabela pedidos_tem_pratos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pedidos_tem_pratos (
  idpedidos INT UNSIGNED NOT NULL,
  nome_prato VARCHAR(50) NOT NULL,
  PRIMARY KEY (idpedidos, nome_prato),
  CONSTRAINT PEDIDO_PRATO
    FOREIGN KEY (idpedidos)
    REFERENCES pedidos (idpedidos)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT PRATO
    FOREIGN KEY (nome_prato)
    REFERENCES pratos (nome)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Tabela mesas
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mesas (
  numero INT UNSIGNED NOT NULL,
  disponibilidade TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (numero)
)
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Tabela mesas_faz_pedidos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mesas_faz_pedidos (
  numeroMesa INT UNSIGNED NOT NULL,
  idPedido INT UNSIGNED NOT NULL,
  PRIMARY KEY (numeroMesa, idPedido),
  CONSTRAINT PEDIDO_MESA
    FOREIGN KEY (idPedido)
    REFERENCES pedidos (idpedidos)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT MESA_PEDINTE
    FOREIGN KEY (numeroMesa)
    REFERENCES mesas (numero)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Tabela atendentes
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS atendentes (
  cpfAtendente VARCHAR(14) NOT NULL,
  PRIMARY KEY (cpfAtendente),
  CONSTRAINT FUNCIONARIO_ATENDENTE
    FOREIGN KEY (cpfAtendente)
    REFERENCES funcionarios (cpf)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Tabela atendente_reserva_mesas
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS atendente_reserva_mesas (
  cpfAtendente VARCHAR(14) NOT NULL,
  numeroMesa INT UNSIGNED NOT NULL,
  PRIMARY KEY (cpfAtendente, numeroMesa),
  CONSTRAINT MESA_RESERVADA
    FOREIGN KEY (numeroMesa)
    REFERENCES mesas (numero)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT ATENDENTE
    FOREIGN KEY (cpfAtendente)
    REFERENCES atendentes (cpfAtendente)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Tabela gerentes
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS gerentes (
  cpfGerente VARCHAR(14) NOT NULL,
  PRIMARY KEY (cpfGerente),
  CONSTRAINT FUNCIONARIO_GERENTE
    FOREIGN KEY (cpfGerente)
    REFERENCES funcionarios (cpf)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
-- -----------------------------------------------------
