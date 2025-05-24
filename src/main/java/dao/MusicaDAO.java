package dao;

import model.Musica;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicaDAO {

    private Connection connection;

    // construtor que recebe a conexão
    public MusicaDAO(Connection connection) {
        this.connection = connection;
    }

    // cria uma nova música no banco
    public boolean create(Musica musica) {
        String sql = "INSERT INTO musica (nome, artista, genero) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, musica.getNome()); // define o nome
            stmt.setString(2, musica.getArtista()); // define o artista
            stmt.setString(3, musica.getGenero()); // define o gênero
            stmt.executeUpdate(); // executa a inserção
            return true;
        } catch (SQLException e) {
            System.out.println("erro ao criar música: " + e.getMessage());
            return false;
        }
    }

    // lê todas as músicas cadastradas
    public List<Musica> read() {
        List<Musica> musicas = new ArrayList<>();
        String sql = "SELECT * FROM musica";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Musica musica = new Musica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("artista"),
                    rs.getString("genero")
                );
                musicas.add(musica); // adiciona na lista
            }
        } catch (SQLException e) {
            System.out.println("erro ao ler músicas: " + e.getMessage());
        }
        return musicas;
    }

    // remove uma música pelo id
    public boolean delete(int id) {
        String sql = "DELETE FROM musica WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id); // define o id da música a ser removida
            stmt.executeUpdate(); // executa a exclusão
            return true;
        } catch (SQLException e) {
            System.out.println("erro ao remover música: " + e.getMessage());
            return false;
        }
    }

    // busca músicas pelo termo (case insensitive com ILIKE)
    public List<Musica> buscarMusicas(String termoBusca) {
        List<Musica> musicas = new ArrayList<>();
        String sql = "SELECT * FROM musica WHERE nome ILIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + termoBusca + "%"); // adiciona wildcard pra buscar parcial
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Musica musica = new Musica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("artista"),
                    rs.getString("genero")
                );
                musicas.add(musica); // adiciona na lista
            }
        } catch (SQLException e) {
            System.out.println("erro ao buscar músicas: " + e.getMessage());
        }

        return musicas;
    }
}
