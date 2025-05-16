package view;

import controller.PlaylistController;
import dao.Conexao;
import model.Playlist;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class PlaylistFrame extends JFrame {

    // Declaração das variáveis
    private JTable table;
    private DefaultTableModel model;
    private JTextField nomeField;
    private JTextField usuarioField;
    private JButton addButton;
    private JButton deleteButton;
    private PlaylistController playlistController;

    public PlaylistFrame() {
        // Configurações da Janela
        setTitle("Spotifei - Playlists");
        setSize(800, 600);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null); // Centraliza a janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        playlistController = new PlaylistController(conn);

        // ==== Tabela ====
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("ID Usuário");

        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(50, 50, 700, 300);
        add(pane);

        // ==== Campos ====
        nomeField = new JTextField();
        nomeField.setBounds(50, 370, 300, 30);
        placeholder(nomeField, "Nome da Playlist");
        add(nomeField);

        usuarioField = new JTextField();
        usuarioField.setBounds(370, 370, 300, 30);
        placeholder(usuarioField, "ID do Usuário");
        add(usuarioField);

        // ==== Botões ====
        addButton = new JButton("Adicionar");
        addButton.setBounds(50, 420, 150, 30);
        addButton.setBackground(new Color(30, 215, 96));
        add(addButton);

        deleteButton = new JButton("Remover");
        deleteButton.setBounds(220, 420, 150, 30);
        deleteButton.setBackground(new Color(215, 30, 96));
        add(deleteButton);

        // ==== Ações ====
        addButton.addActionListener(e -> {
            String nome = nomeField.getText();
            int usuarioId = Integer.parseInt(usuarioField.getText());

            if (playlistController.cadastrarPlaylist(nome, usuarioId)) {
                JOptionPane.showMessageDialog(null, "Playlist adicionada com sucesso!");
                listarPlaylists();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar playlist.");
            }
        });

        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int id = Integer.parseInt(model.getValueAt(row, 0).toString());
                JOptionPane.showMessageDialog(null, "Remover a playlist de ID: " + id);
                listarPlaylists();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma playlist para remover.");
            }
        });

        listarPlaylists();
    }

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

    private void listarPlaylists() {
        model.setRowCount(0); // Limpa a tabela
        List<Playlist> playlists = playlistController.listarPlaylists();
        for (Playlist p : playlists) {
            model.addRow(new Object[]{p.getId(), p.getNome(), p.getUsuarioId()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PlaylistFrame().setVisible(true);
        });
    }
}
