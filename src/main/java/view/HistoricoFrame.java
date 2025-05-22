package view;

import dao.Conexao;
import dao.CurtidaDAO;
import dao.HistoricoBuscaDAO;
import model.Curtida;
import model.HistoricoBusca;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class HistoricoFrame extends JFrame {

    private int usuarioId;

    public HistoricoFrame(int usuarioId) {
        this.usuarioId = usuarioId;

        setTitle("Histórico");
        setSize(800, 600);
        setLayout(new GridLayout(2, 1));
        setLocationRelativeTo(null);

        JTable curtidasTable = new JTable();
        JTable buscasTable = new JTable();

        DefaultTableModel curtidasModel = new DefaultTableModel(new Object[]{"ID", "Música", "Data"}, 0);
        DefaultTableModel buscasModel = new DefaultTableModel(new Object[]{"ID", "Termo", "Data"}, 0);

        try {
            // priar a conexão
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            // passar a conexão para os DAOs
            CurtidaDAO curtidaDAO = new CurtidaDAO(conn);
            List<Curtida> curtidas = curtidaDAO.listarCurtidas(usuarioId);
            for (Curtida c : curtidas) {
                curtidasModel.addRow(new Object[]{c.getId(), c.getTituloMusica(), c.getDataCurtida()});
            }

            HistoricoBuscaDAO buscaDAO = new HistoricoBuscaDAO(conn);
            List<HistoricoBusca> buscas = buscaDAO.listarBuscas(usuarioId);
            for (HistoricoBusca b : buscas) {
                buscasModel.addRow(new Object[]{b.getId(), b.getTermoBusca(), b.getDataBusca()});
            }

            
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        curtidasTable.setModel(curtidasModel);
        buscasTable.setModel(buscasModel);

        add(new JScrollPane(curtidasTable));
        add(new JScrollPane(buscasTable));

        setVisible(true);
    }
}
