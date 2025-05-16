package model;

public class Artista {
    private int id;
    private String nome;
    private String genero;

    // Construtor padrão
    public Artista() {}

    // Construtor com parâmetros
    public Artista(int id, String nome, String genero) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
