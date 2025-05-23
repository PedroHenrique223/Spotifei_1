package dao;

import model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection connection;

    // construtor que recebe a conexão
    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    // cria um novo usuário no banco
    public boolean create(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome()); // define o nome
            stmt.setString(2, usuario.getEmail()); // define o email
            stmt.setString(3, usuario.getSenha()); // define a senha
            stmt.executeUpdate(); // executa a inserção
            return true;
        } catch (SQLException e) {
            System.out.println("erro ao criar usuário: " + e.getMessage());
            return false;
        }
    }

    // lê todos os usuários cadastrados
    public List<Usuario> read() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
                usuarios.add(usuario); // adiciona na lista
            }
        } catch (SQLException e) {
            System.out.println("erro ao ler usuários: " + e.getMessage());
        }
        return usuarios;
    }
}
