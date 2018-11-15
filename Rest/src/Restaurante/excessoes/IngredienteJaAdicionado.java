package Restaurante.excessoes;

public class IngredienteJaAdicionado extends Exception {
    public String msg(){
        return "Ingrediente ja existe!!";
    }
}
