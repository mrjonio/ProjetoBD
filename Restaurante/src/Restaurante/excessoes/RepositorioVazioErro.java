package Restaurante.excessoes;

/**
 * Classe para tratar o erro de repositório vazio; e seu construtor.
 * (Extensão da classe Exception (exceção)).
 */
public class RepositorioVazioErro extends Exception {
    private String nomeDoRepositorio;

    public RepositorioVazioErro(String nomeDoRepositorio){
        this.nomeDoRepositorio = nomeDoRepositorio;
    }
}
