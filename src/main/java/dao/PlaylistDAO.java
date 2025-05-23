package dao;

import model.Playlist;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    private Connection connection;

    // construtor que recebe a conexão
    public PlaylistDAO(Connection connection) {
        this.connection = connection;
    }

    // cria uma nova playlist no banco
    public boolean create(Playlist playlist) {
        String sql = "INSERT INTO playlist (nome, usuario_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, playlist.getNome()); // define o nome da playlist
            stmt.setInt(2, playlist.getUsuarioId()); // define o id do usuário dono da playlist
            stmt.executeUpdate(); // executa a inserção
            return true;
        } catch (SQLException e) {
            System.out.println("erro ao criar playlist: " + e.getMessage());
            return false;
        }
    }

    // lê todas as playlists cadastradas
    public List<Playlist> read() {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT * FROM playlist";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Playlist playlist = new Playlist(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("usuario_id")
                );
                playlists.add(playlist); // adiciona na lista
            }
        } catch (SQLException e) {
            System.out.println("erro ao ler playlists: " + e.getMessage());
        }
        return playlists;
    }
}
