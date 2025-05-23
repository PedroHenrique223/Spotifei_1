package controller;

import dao.PlaylistDAO;
import model.Playlist;
import java.sql.Connection;
import java.util.List;

public class PlaylistController {
    private PlaylistDAO playlistDAO;

    // construtor que inicializa o DAO com a conexão
    public PlaylistController(Connection connection) {
        this.playlistDAO = new PlaylistDAO(connection);
    }

    // cadastra uma nova playlist no banco
    public boolean cadastrarPlaylist(String nome, int usuarioId) {
        Playlist playlist = new Playlist(0, nome, usuarioId); // cria a playlist com id 0 (gerado no banco)
        return playlistDAO.create(playlist);
    }

    // retorna a lista de todas as playlists cadastradas
    public List<Playlist> listarPlaylists() {
        return playlistDAO.read();
    }
}
