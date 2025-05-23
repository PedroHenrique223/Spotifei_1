package view;

import controller.PlaylistController;
import dao.Conexao;
import dao.PlaylistMusicaDAO;
import model.Playlist;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class PlaylistFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JTextField nomeField;
    private JTextField usuarioField;
    private JButton addButton;
    private JButton deleteButton;
    private PlaylistController playlistController;

    // campos extras pra adicionar/remover músicas
    private JTextField musicaField;
    private JTextField playlistNomeField;
    private JButton addMusicaButton;
    private JButton removeMusicaButton;

    public PlaylistFrame() {
        setTitle("Spotifei - Playlists");
        setSize(800, 600);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // conexão e controller
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        playlistController = new PlaylistController(conn);
        PlaylistMusicaDAO musicaDAO = new PlaylistMusicaDAO(conn);

        // configuração da tabela
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("ID Usuário");

        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(50, 50, 700, 300);
        add(pane);

        // campos
        nomeField = new JTextField();
        nomeField.setBounds(50, 370, 300, 30);
        placeholder(nomeField, "Nome da Playlist");
        add(nomeField);

        usuarioField = new JTextField();
        usuarioField.setBounds(370, 370, 300, 30);
        placeholder(usuarioField, "ID do Usuário");
        add(usuarioField);

        // botões
        addButton = new JButton("Adicionar");
        addButton.setBounds(50, 420, 150, 30);
        addButton.setBackground(new Color(30, 215, 96));
        add(addButton);

        deleteButton = new JButton("Remover");
        deleteButton.setBounds(220, 420, 150, 30);
        deleteButton.setBackground(new Color(215, 30, 96));
        add(deleteButton);

        // campos extras
        musicaField = new JTextField();
        musicaField.setBounds(50, 470, 300, 30);
        placeholder(musicaField, "Nome da Música");
        add(musicaField);

        playlistNomeField = new JTextField();
        playlistNomeField.setBounds(370, 470, 300, 30);
        placeholder(playlistNomeField, "Nome da Playlist");
        add(playlistNomeField);

        // botões de música
        addMusicaButton = new JButton("Adicionar Música");
        addMusicaButton.setBounds(50, 520, 150, 30);
        addMusicaButton.setBackground(Color.GREEN);
        add(addMusicaButton);

        removeMusicaButton = new JButton("Remover Música");
        removeMusicaButton.setBounds(220, 520, 150, 30);
        removeMusicaButton.setBackground(Color.RED);
        add(removeMusicaButton);

        // ação adicionar playlist
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

        // ação remover playlist (só a mensagem, não implementa delete real)
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

        // ação adicionar música na playlist
        addMusicaButton.addActionListener(e -> {
            String nomeMusica = musicaField.getText();
            String nomePlaylist = playlistNomeField.getText();

            boolean sucesso = musicaDAO.adicionarMusicaNaPlaylist(nomeMusica, nomePlaylist);
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Música adicionada à playlist!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar música.");
            }
        });

        // ação remover música da playlist
        removeMusicaButton.addActionListener(e -> {
            String nomeMusica = musicaField.getText();
            String nomePlaylist = playlistNomeField.getText();

            boolean sucesso = musicaDAO.removerMusicaDaPlaylist(nomeMusica, nomePlaylist);
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Música removida da playlist!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao remover música.");
            }
        });

        listarPlaylists(); // carrega as playlists ao abrir
    }

    // método placeholder pro campo
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

    // carrega as playlists na tabela
    private void listarPlaylists() {
        model.setRowCount(0);
        List<Playlist> playlists = playlistController.listarPlaylists();
        for (Playlist p : playlists) {
            model.addRow(new Object[]{p.getId(), p.getNome(), p.getUsuarioId()});
        }
    }

    // main pra rodar
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PlaylistFrame().setVisible(true);
        });
    }
}
