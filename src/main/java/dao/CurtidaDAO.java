package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
