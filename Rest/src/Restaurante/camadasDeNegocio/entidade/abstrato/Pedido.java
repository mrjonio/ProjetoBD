package Restaurante.camadasDeNegocio.entidade.abstrato;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import java.time.LocalDateTime;

/**
 * Classe modelo para os objetos do tipo "prato"; seus atributos e construtor.
 */
public class Pedido{
    private PratoCardapio pratoPedido;
    private LocalDateTime dataDoPedido;
    private Mesa mesaQuePediu;

    public Pedido(PratoCardapio pratoPedido, Mesa mesaQuePediu){
        this.dataDoPedido = LocalDateTime.now();
        this.pratoPedido = pratoPedido;
        this.mesaQuePediu = mesaQuePediu;
    }

    public PratoCardapio getPratoPedido() {
        return pratoPedido;
    }

    public LocalDateTime getDataDoPedido() {
        return dataDoPedido;
    }

    public Mesa getMesaQuePediu(){
        return mesaQuePediu;
    }



}
