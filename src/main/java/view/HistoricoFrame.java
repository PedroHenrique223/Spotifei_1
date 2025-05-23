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

    // construtor que recebe o id do usuário
    public HistoricoFrame(int usuarioId) {
        this.usuarioId = usuarioId;

        setTitle("Histórico");
        setSize(800, 600);
        setLayout(new GridLayout(2, 1));
        setLocationRelativeTo(null); // centraliza a janela

        // tabelas pra exibir as curtidas e buscas
        JTable curtidasTable = new JTable();
        JTable buscasTable = new JTable();

        // modelos das tabelas com colunas
        DefaultTableModel curtidasModel = new DefaultTableModel(new Object[]{"ID", "Música", "Data"}, 0);
        DefaultTableModel buscasModel = new DefaultTableModel(new Object[]{"ID", "Termo", "Data"}, 0);

        try {
            // cria a conexão
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            // passa a conexão pros DAOs
            CurtidaDAO curtidaDAO = new CurtidaDAO(conn);
            List<Curtida> curtidas = curtidaDAO.listarCurtidas(usuarioId);
            for (Curtida c : curtidas) {
                curtidasModel.addRow(new Object[]{c.getId(), c.getNomeMusica(), c.getDataCurtida()});
            }

            HistoricoBuscaDAO buscaDAO = new HistoricoBuscaDAO(conn);
            List<HistoricoBusca> buscas = buscaDAO.listarBuscas(usuarioId);
            for (HistoricoBusca b : buscas) {
                buscasModel.addRow(new Object[]{b.getId(), b.getTermoBusca(), b.getDataBusca()});
            }

            // fecha a conexão
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // define os modelos nas tabelas
        curtidasTable.setModel(curtidasModel);
        buscasTable.setModel(buscasModel);

        // adiciona as tabelas com scroll
        add(new JScrollPane(curtidasTable));
        add(new JScrollPane(buscasTable));

        setVisible(true);
    }
}
