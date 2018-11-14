package Restaurante.excessoes;

/**
 * Classe para tratar o erro de campo vazio; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class CampoVazioErro extends Exception {
    private String nomeDoCampo;

    public CampoVazioErro(String nomeCampo){
        this.nomeDoCampo = nomeCampo;
    }
}
