package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistMusicaDAO {

    private Connection connection;

    public PlaylistMusicaDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean adicionarMusicaNaPlaylist(String nomeMusica, String nomePlaylist) {
        try {
            // Buscar ID da música
            int idMusica = buscarIdMusicaPorNome(nomeMusica);
            int idPlaylist = buscarIdPlaylistPorNome(nomePlaylist);

            if (idMusica == -1 || idPlaylist == -1) {
                System.out.println("Música ou playlist não encontrada.");
                return false;
            }

            // Inserir na tabela de associação
            String sql = "INSERT INTO playlist_musica (id_playlist, id_musica) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idPlaylist);
                stmt.setInt(2, idMusica);
                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar música na playlist: " + e.getMessage());
            return false;
        }
    }

    public boolean removerMusicaDaPlaylist(String nomeMusica, String nomePlaylist) {
        try {
            int idMusica = buscarIdMusicaPorNome(nomeMusica);
            int idPlaylist = buscarIdPlaylistPorNome(nomePlaylist);

            if (idMusica == -1 || idPlaylist == -1) {
                System.out.println("Música ou playlist não encontrada.");
                return false;
            }

            String sql = "DELETE FROM playlist_musica WHERE id_playlist = ? AND id_musica = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idPlaylist);
                stmt.setInt(2, idMusica);
                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover música da playlist: " + e.getMessage());
            return false;
        }
    }

    private int buscarIdMusicaPorNome(String nomeMusica) throws SQLException {
        String sql = "SELECT id FROM musica WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomeMusica);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    private int buscarIdPlaylistPorNome(String nomePlaylist) throws SQLException {
        String sql = "SELECT id FROM playlist WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomePlaylist);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }
}
