package view;

import controller.MusicaController;
import dao.Conexao;
import dao.CurtidaDAO;
import dao.HistoricoBuscaDAO;
import model.Curtida;
import model.Musica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class MusicaFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JTextField nomeField;
    private JTextField artistaField;
    private JTextField generoField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton curtirButton;
    private JButton descurtirButton;
    private JButton verCurtidasButton;
    private MusicaController musicaController;

    private int usuarioId = 1; // id fixo, ajusta se quiser

    public MusicaFrame() {
        setTitle("Spotifei - Músicas");
        setSize(800, 600);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // conecta no banco
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        musicaController = new MusicaController(conn);

        // configura a tabela
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Artista");
        model.addColumn("Gênero");

        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(50, 50, 700, 300);
        add(pane);

        // campos de entrada
        nomeField = new JTextField();
        nomeField.setBounds(50, 370, 200, 30);
        placeholder(nomeField, "Nome");
        add(nomeField);

        artistaField = new JTextField();
        artistaField.setBounds(270, 370, 200, 30);
        placeholder(artistaField, "Artista");
        add(artistaField);

        generoField = new JTextField();
        generoField.setBounds(490, 370, 200, 30);
        placeholder(generoField, "Gênero");
        add(generoField);

        // botões
        addButton = new JButton("Adicionar");
        addButton.setBounds(50, 420, 150, 30);
        addButton.setBackground(new Color(30, 215, 96));
        add(addButton);

        deleteButton = new JButton("Remover");
        deleteButton.setBounds(220, 420, 150, 30);
        deleteButton.setBackground(new Color(215, 30, 96));
        add(deleteButton);

        curtirButton = new JButton("Curtir");
        curtirButton.setBounds(390, 420, 150, 30);
        curtirButton.setBackground(new Color(50, 150, 255));
        add(curtirButton);

        descurtirButton = new JButton("Descurtir");
        descurtirButton.setBounds(560, 420, 150, 30);
        descurtirButton.setBackground(new Color(255, 100, 100));
        add(descurtirButton);

        verCurtidasButton = new JButton("Ver Curtidas");
        verCurtidasButton.setBounds(50, 470, 150, 30);
        verCurtidasButton.setBackground(new Color(150, 100, 255));
        add(verCurtidasButton);

        // ação adicionar música
        addButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String artista = artistaField.getText();
            String genero = generoField.getText();

            if (musicaController.cadastrarMusica(nome, artista, genero)) {
                JOptionPane.showMessageDialog(null, "Música adicionada com sucesso!");
                listarMusicas();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar música.");
            }
        });

        // ação remover música
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int id = Integer.parseInt(model.getValueAt(row, 0).toString());

                int confirm = JOptionPane.showConfirmDialog(null,
                        "Deseja realmente remover a música selecionada?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    if (musicaController.removerMusica(id)) {
                        JOptionPane.showMessageDialog(null, "Música removida com sucesso!");
                        listarMusicas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao remover música.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma música para remover.");
            }
        });

        // ação curtir música
        curtirButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int idMusica = Integer.parseInt(model.getValueAt(row, 0).toString());

                try {
                    Conexao conexao1 = new Conexao();
                    Connection conn1 = conexao1.getConnection();

                    CurtidaDAO curtidaDAO = new CurtidaDAO(conn1);
                    curtidaDAO.curtirMusica(usuarioId, idMusica);

                    JOptionPane.showMessageDialog(null, "Música curtida com sucesso!");

                    conn1.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao curtir música.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma música para curtir.");
            }
        });

        // ação descurtir música
        descurtirButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int idMusica = Integer.parseInt(model.getValueAt(row, 0).toString());

                try {
                    Conexao conexao1 = new Conexao();
                    Connection conn1 = conexao1.getConnection();

                    CurtidaDAO curtidaDAO = new CurtidaDAO(conn1);
                    curtidaDAO.descurtirMusica(usuarioId, idMusica);

                    JOptionPane.showMessageDialog(null, "Música descurtida com sucesso!");

                    conn1.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao descurtir música.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma música para descurtir.");
            }
        });

        // ação ver curtidas
        verCurtidasButton.addActionListener(e -> {
            try {
                Conexao conexao1 = new Conexao();
                Connection conn1 = conexao1.getConnection();

                CurtidaDAO curtidaDAO = new CurtidaDAO(conn1);
                List<Curtida> curtidas = curtidaDAO.listarCurtidas(usuarioId);

                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");

                String[] colunas = {"Nome da Música", "Data da Curtida"};
                DefaultTableModel curtidasModel = new DefaultTableModel(colunas, 0);

                for (Curtida c : curtidas) {
                    String dataFormatada = sdf.format(c.getDataCurtida());
                    curtidasModel.addRow(new Object[]{c.getNomeMusica(), dataFormatada});
                }

                JTable curtidasTable = new JTable(curtidasModel);
                JScrollPane scrollPane = new JScrollPane(curtidasTable);
                scrollPane.setPreferredSize(new Dimension(500, 300));

                JOptionPane.showMessageDialog(null, scrollPane, "Minhas Curtidas", JOptionPane.PLAIN_MESSAGE);

                conn1.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao listar curtidas.");
            }
        });

        listarMusicas(); // carrega músicas ao abrir
    }

    // método pra placeholder no campo
    private void placeholder(JTextField field, String text) {
        field.setText(text);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (field.getText().equals(text)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(text);
                }
            }
        });
    }

    // lista todas as músicas e registra no histórico
    private void listarMusicas() {
        model.setRowCount(0);

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            HistoricoBuscaDAO historicoBuscaDAO = new HistoricoBuscaDAO(conn);
            historicoBuscaDAO.registrarBusca(usuarioId, "Listagem de todas as músicas");

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<Musica> musicas = musicaController.listarMusicas();
        for (Musica m : musicas) {
            model.addRow(new Object[]{m.getId(), m.getNome(), m.getArtista(), m.getGenero()});
        }
    }

    // main pra rodar a tela
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MusicaFrame().setVisible(true);
        });
    }
}
