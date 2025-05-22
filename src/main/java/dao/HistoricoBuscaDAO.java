package dao;

import model.HistoricoBusca;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoBuscaDAO {
    private Connection conexao;

    public HistoricoBuscaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void registrarBusca(int idUsuario, String termo) throws Exception {
        String sql = "INSERT INTO historico_busca (id_usuario, termo_busca) VALUES (?, ?)";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.setString(2, termo);
        ps.executeUpdate();
    }

    public List<HistoricoBusca> listarBuscas(int idUsuario) throws Exception {
        List<HistoricoBusca> lista = new ArrayList<>();
        String sql = "SELECT * FROM historico_busca WHERE id_usuario = ? ORDER BY data_busca DESC";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HistoricoBusca b = new HistoricoBusca();
            b.setId(rs.getInt("id"));
            b.setTermoBusca(rs.getString("termo_busca"));
            b.setDataBusca(rs.getTimestamp("data_busca"));
            lista.add(b);
        }
        return lista;
    }
}
