package Restaurante.camadasDeNegocio.entidade.abstrato;

import Restaurante.camadasDeNegocio.entidade.concretos.Alimenticio.PratoCardapio;
import Restaurante.camadasDeNegocio.entidade.concretos.Mesa;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Classe modelo para os objetos do tipo "prato"; seus atributos e construtor.
 */
public class Pedido{
    private ArrayList<PratoCardapio> pratoPedido;
    private LocalDateTime dataDoPedido;
    private Mesa mesaQuePediu;

    public Pedido(ArrayList<PratoCardapio> pratosPedido, Mesa mesaQuePediu){
        //Mudando a forma pradrao de tempo para evistar os segundos
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy/HH/mm");
        String data = LocalDateTime.now().format(formatter);
        this.dataDoPedido = LocalDateTime.parse(data, formatter);
        this.pratoPedido = pratosPedido;
        this.mesaQuePediu = mesaQuePediu;
    }

    public void setDataDoPedido(LocalDateTime dataNova) {
        this.dataDoPedido = dataNova;
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

    @Override
    public String toString() {
        return new String("Da mesa " +  this.mesaQuePediu.getNumero() + " os pratos " + this.pratoPedido.stream().map(PratoCardapio::getNome).collect(Collectors.toList()));
    }



}
