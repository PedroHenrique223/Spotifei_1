package model;

public class Musica {
    private int id;
    private String nome;
    private String artista;
    private String genero;

    // construtor que inicializa todos os atributos
    public Musica(int id, String nome, String artista, String genero) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.genero = genero;
    }

    // getters e setters
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

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
