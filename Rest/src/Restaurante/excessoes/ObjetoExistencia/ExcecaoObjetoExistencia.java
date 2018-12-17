package Restaurante.excessoes.ObjetoExistencia;

import javax.swing.*;

public class ExcecaoObjetoExistencia extends Exception {
    protected String nomeObjeto;
    public ExcecaoObjetoExistencia(String nomeObjeto){
        this.nomeObjeto = nomeObjeto;
    }

    public void mostrarErro(){
        JOptionPane.showMessageDialog(null, this.nomeObjeto + "não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
