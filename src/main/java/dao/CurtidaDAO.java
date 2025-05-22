package dao;

import model.Curtida;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurtidaDAO {
    private Connection conexao;

    public CurtidaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void curtirMusica(int idUsuario, int idMusica) throws Exception {
        String sql = "INSERT INTO curtida (id_usuario, id_musica) VALUES (?, ?)";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.setInt(2, idMusica);
        ps.executeUpdate();
    }

    public void descurtirMusica(int idUsuario, int idMusica) throws Exception {
        String sql = "DELETE FROM curtida WHERE id_usuario = ? AND id_musica = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.setInt(2, idMusica);
        ps.executeUpdate();
    }

    public boolean verificarCurtida(int idUsuario, int idMusica) throws Exception {
        String sql = "SELECT * FROM curtida WHERE id_usuario = ? AND id_musica = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.setInt(2, idMusica);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public List<Curtida> listarCurtidas(int idUsuario) throws Exception {
        List<Curtida> lista = new ArrayList<>();
        String sql = "SELECT c.id, m.titulo, c.data_curtida FROM curtida c JOIN musica m ON c.id_musica = m.id WHERE c.id_usuario = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Curtida c = new Curtida();
            c.setId(rs.getInt("id"));
            c.setTituloMusica(rs.getString("titulo"));
            c.setDataCurtida(rs.getTimestamp("data_curtida"));
            lista.add(c);
        }
        return lista;
    }
}
