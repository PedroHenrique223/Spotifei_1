package controller;

import dao.PlaylistDAO;
import model.Playlist;
import java.sql.Connection;
import java.util.List;

public class PlaylistController {
    private PlaylistDAO playlistDAO;

    public PlaylistController(Connection connection) {
        this.playlistDAO = new PlaylistDAO(connection);
    }

    public boolean cadastrarPlaylist(String nome, int usuarioId) {
        Playlist playlist = new Playlist(0, nome, usuarioId);
        return playlistDAO.create(playlist);
    }

    public List<Playlist> listarPlaylists() {
        return playlistDAO.read();
    }
}
