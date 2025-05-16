package controller;

import dao.ArtistaDAO;
import model.Artista;
import java.sql.Connection;
import java.util.List;

public class ArtistaController {
    private ArtistaDAO artistaDAO;

    public ArtistaController(Connection connection) {
        this.artistaDAO = new ArtistaDAO(connection);
    }

    public boolean cadastrarArtista(String nome, String genero) {
        Artista artista = new Artista(0, nome, genero); 
            return artistaDAO.create(artista);
    }


    public List<Artista> listarArtistas() {
        return artistaDAO.read();
    }
    
    public boolean removerArtista(int id) {
        return artistaDAO.delete(id);
    }

    
    
    
}

