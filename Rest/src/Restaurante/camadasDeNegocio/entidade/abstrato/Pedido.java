package Restaurante.camadasDeNegocio.entidade.abstrato;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Classe modelo para os objetos do tipo "prato"; seus atributos e construtor.
 */
public class Pedido{
    private ArrayList<PratoCardapio> pratoPedido;
    private LocalDateTime dataDoPedido;
    private Mesa mesaQuePediu;

    public Pedido(ArrayList<PratoCardapio> pratosPedido, Mesa mesaQuePediu){
        this.dataDoPedido = LocalDateTime.now();
        this.pratoPedido = pratosPedido;
        this.mesaQuePediu = mesaQuePediu;
    }

    public ArrayList<PratoCardapio> getPratoPedido() {
        return pratoPedido;
    }

    public LocalDateTime getDataDoPedido() {
        return dataDoPedido;
    }

    public Mesa getMesaQuePediu(){
        return mesaQuePediu;
    }



}
