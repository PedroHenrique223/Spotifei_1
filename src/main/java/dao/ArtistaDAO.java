package dao;

import model.Artista;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaDAO {

    private Connection connection;

    // construtor que recebe a conexão com o banco
    public ArtistaDAO(Connection connection) {
        this.connection = connection;
    }

    // cria um novo artista no banco
    public boolean create(Artista artista) {
        String sql = "INSERT INTO artista (nome, genero) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, artista.getNome()); // define o nome
            stmt.setString(2, artista.getGenero()); // define o gênero
            stmt.executeUpdate(); // executa a inserção
            return true;
        } catch (SQLException e) {
            System.out.println("erro ao criar artista: " + e.getMessage());
            return false;
        }
    }

    // lê todos os artistas cadastrados no banco
    public List<Artista> read() {
        List<Artista> artistas = new ArrayList<>();
        String sql = "SELECT * FROM artista";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Artista artista = new Artista(rs.getInt("id"), rs.getString("nome"), rs.getString("genero"));
                artistas.add(artista); // adiciona o artista na lista
            }
        } catch (SQLException e) {
            System.out.println("erro ao ler artistas: " + e.getMessage());
        }
        return artistas;
    }

    // remove um artista pelo id
    public boolean delete(int id) {
        String sql = "DELETE FROM artista WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id); // define qual id será removido
            stmt.executeUpdate(); // executa a remoção
            return true;
        } catch (SQLException e) {
            System.out.println("erro ao remover artista: " + e.getMessage());
            return false;
        }
    }
}
