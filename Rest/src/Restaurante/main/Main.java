package Restaurante.main;


import Restaurante.camadasDeNegocio.entidade.abstrato.Reserva;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.Ingrediente;
import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import Restaurante.camadasDeNegocio.entidade.pessoas.funcionario.Funcionario;
import Restaurante.excessoes.ObjetoExistencia.ObjetoJaExisteErro;
import Restaurante.fachada.Fachada;
import Restaurante.fachada.interfaceFachada.IFachadaAtendente;
import Restaurante.fachada.interfaceFachada.IFachadaCozinheiro;
import Restaurante.fachada.interfaceFachada.IFachadaGerente;
import Restaurante.fachada.interfaceFachada.IFachadaMesa;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {



    public static void main(String[] args)  {
        Fachada fachada =  Fachada.getInstance();

        ArrayList<Ingrediente> ingredientes = new ArrayList<>();

        Ingrediente a = new Ingrediente("Queijo", 500);
        Ingrediente b = new Ingrediente("Polvilho", 500);
        Ingrediente c = new Ingrediente("Leite", 500);

        ingredientes.add(a);
        ingredientes.add(b);
        ingredientes.add(c);

        Funcionario gerente =  new Funcionario("Carlos", "111.111.111-11", 18, "masc", "Gerente", 1999999.00);
        PratoCardapio prat0 = new PratoCardapio("Pão de Queijo", 20.00, ingredientes);
        Mesa mesa = new Mesa(0, "Oculpado");
        Mesa mesa2 = new Mesa(1, "Vazia");
        Reserva reserva = new Reserva(LocalDateTime.now(), "111.111.111-11", mesa2);


        try {
            fachada.cadastrarUmFuncionario(gerente);
            fachada.adicionarPratoAoCardapio(prat0);
            fachada.adicionarIngrediente(a);
            fachada.adicionarIngrediente(b);
            fachada.adicionarIngrediente(c);
            fachada.adicionarUmaMesa(mesa);
            fachada.adicionarUmaMesa(mesa2);
            fachada.fazerNovaReserva(reserva);

        } catch (Exception objetoJaExisteErro) {
        }
    }
}
