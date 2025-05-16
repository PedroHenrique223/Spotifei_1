package dao;

import model.Playlist;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    private Connection connection;

    public PlaylistDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean create(Playlist playlist) {
        String sql = "INSERT INTO playlist (nome, usuario_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, playlist.getNome());
            stmt.setInt(2, playlist.getUsuarioId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao criar playlist: " + e.getMessage());
            return false;
        }
    }

    public List<Playlist> read() {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT * FROM playlist";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Playlist playlist = new Playlist(rs.getInt("id"), rs.getString("nome"), rs.getInt("usuario_id"));
                playlists.add(playlist);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao ler playlists: " + e.getMessage());
        }
        return playlists;
    }
}
