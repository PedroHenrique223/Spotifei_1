package controller;

import dao.UsuarioDAO;
import model.Usuario;
import java.sql.Connection;
import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    // construtor que inicializa o DAO com a conexão
    public UsuarioController(Connection connection) {
        this.usuarioDAO = new UsuarioDAO(connection);
    }

    // cadastra um novo usuário no banco
    public boolean cadastrarUsuario(String nome, String email, String senha) {
        Usuario usuario = new Usuario(0, nome, email, senha); // cria o usuário com id 0 (gerado no banco)
        return usuarioDAO.create(usuario);
    }

    // retorna a lista de todos os usuários cadastrados
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.read();
    }
}
