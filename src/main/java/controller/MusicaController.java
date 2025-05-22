package controller;

import dao.MusicaDAO;
import model.Musica;
import java.sql.Connection;
import java.util.List;

public class MusicaController {
    private MusicaDAO musicaDAO;

    public MusicaController(Connection connection) {
        this.musicaDAO = new MusicaDAO(connection);
    }

    public boolean cadastrarMusica(String nome, String artista, String genero) {
        Musica musica = new Musica(0, nome, artista, genero);
        return musicaDAO.create(musica);
    }

    public List<Musica> listarMusicas() {
        return musicaDAO.read();
    }
    
    public boolean removerMusica(int id) {
    return musicaDAO.delete(id);
    
    }
    
    

    
}
