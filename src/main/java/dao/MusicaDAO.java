package dao;

import model.Musica;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicaDAO {

    private Connection connection;

    public MusicaDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean create(Musica musica) {
        String sql = "INSERT INTO musica (nome, artista, genero) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, musica.getNome());
            stmt.setString(2, musica.getArtista());
            stmt.setString(3, musica.getGenero());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao criar música: " + e.getMessage());
            return false;
        }
    }

    public List<Musica> read() {
        List<Musica> musicas = new ArrayList<>();
        String sql = "SELECT * FROM musica";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Musica musica = new Musica(rs.getInt("id"), rs.getString("nome"), rs.getString("artista"), rs.getString("genero"));
                musicas.add(musica);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao ler músicas: " + e.getMessage());
        }
        return musicas;
    }
    
    public boolean delete(int id) {
    String sql = "DELETE FROM musica WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println("Erro ao remover música: " + e.getMessage());
        return false;
    }
}

    
    
}
