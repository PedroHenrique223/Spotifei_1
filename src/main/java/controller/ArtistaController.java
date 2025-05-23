package controller;

import dao.ArtistaDAO;
import model.Artista;
import java.sql.Connection;
import java.util.List;

public class ArtistaController {
    private ArtistaDAO artistaDAO;

    // construtor que inicializa o DAO com a conexão
    public ArtistaController(Connection connection) {
        this.artistaDAO = new ArtistaDAO(connection);
    }

    // cadastra um novo artista usando o DAO
    public boolean cadastrarArtista(String nome, String genero) {
        Artista artista = new Artista(0, nome, genero); // cria um artista com id 0 (gerado no banco)
        return artistaDAO.create(artista);
    }

    // retorna a lista de todos os artistas cadastrados
    public List<Artista> listarArtistas() {
        return artistaDAO.read();
    }
    
    // remove um artista pelo id
    public boolean removerArtista(int id) {
        return artistaDAO.delete(id);
    }
}
