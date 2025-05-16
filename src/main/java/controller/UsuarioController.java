package controller;

import dao.UsuarioDAO;
import model.Usuario;
import java.sql.Connection;
import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController(Connection connection) {
        this.usuarioDAO = new UsuarioDAO(connection);
    }

    public boolean cadastrarUsuario(String nome, String email, String senha) {
        Usuario usuario = new Usuario(0, nome, email, senha);
        return usuarioDAO.create(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.read();
    }
}
