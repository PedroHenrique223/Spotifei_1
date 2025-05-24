package model;

/**
 * Classe que representa uma música no sistema Spotifei.
 * 
 * Contém informações como id, nome, artista e gênero.
 * Criado para testar a geração de Javadoc.
 */
public class Musica {
    private int id;
    private String nome;
    private String artista;
    private String genero;

    /**
     * Construtor da classe Musica.
     *
     * @param id identificador único da música
     * @param nome nome da música
     * @param artista nome do artista
     * @param genero gênero musical
     */
    public Musica(int id, String nome, String artista, String genero) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.genero = genero;
    }

    /**
     * Obtém o identificador da música.
     * 
     * @return id da música
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador da música.
     * 
     * @param id novo id da música
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome da música.
     * 
     * @return nome da música
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da música.
     * 
     * @param nome novo nome da música
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o nome do artista.
     * 
     * @return nome do artista
     */
    public String getArtista() {
        return artista;
    }

    /**
     * Define o nome do artista.
     * 
     * @param artista novo nome do artista
     */
    public void setArtista(String artista) {
        this.artista = artista;
    }

    /**
     * Obtém o gênero musical.
     * 
     * @return gênero da música
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Define o gênero musical.
     * 
     * @param genero novo gênero da música
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }
}
