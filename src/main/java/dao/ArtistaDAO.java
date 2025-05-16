package dao;

import model.Artista;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaDAO {

    private Connection connection;

    public ArtistaDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean create(Artista artista) {
    String sql = "INSERT INTO artista (nome, genero) VALUES (?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, artista.getNome());
        stmt.setString(2, artista.getGenero());
        stmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println("Erro ao criar artista: " + e.getMessage());
        return false;
    }
}

    public List<Artista> read() {
        List<Artista> artistas = new ArrayList<>();
        String sql = "SELECT * FROM artista";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Artista artista = new Artista(rs.getInt("id"), rs.getString("nome"), rs.getString("genero"));

                artistas.add(artista);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao ler artistas: " + e.getMessage());
        }
        return artistas;
    }
    
public boolean delete(int id) {
    String sql = "DELETE FROM artista WHERE id = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println("Erro ao remover artista: " + e.getMessage());
        return false;
    }
}



    
}
