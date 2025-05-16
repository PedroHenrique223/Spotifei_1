package view;

import controller.MusicaController;
import dao.Conexao;
import model.Musica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class MusicaFrame extends JFrame {

    // Declaração das variáveis
    private JTable table;
    private DefaultTableModel model;
    private JTextField nomeField;
    private JTextField artistaField;
    private JTextField generoField;
    private JButton addButton;
    private JButton deleteButton;
    private MusicaController musicaController;

    public MusicaFrame() {
        // Configurações da Janela
        setTitle("Spotifei - Músicas");
        setSize(800, 600);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null); // Centraliza a janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        musicaController = new MusicaController(conn);

        // ==== Tabela ====
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Artista");
        model.addColumn("Gênero");

        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(50, 50, 700, 300);
        add(pane);

        // ==== Campos ====
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
            String artista = artistaField.getText();
            String genero = generoField.getText();

            if (musicaController.cadastrarMusica(nome, artista, genero)) {
                JOptionPane.showMessageDialog(null, "Música adicionada com sucesso!");
                listarMusicas();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar música.");
            }
        });

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
                listarMusicas(); // Atualiza a lista
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao remover música.");
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Selecione uma música para remover.");
    }
});


        listarMusicas();
    }

    // Método para adicionar o Placeholder
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

    private void listarMusicas() {
        model.setRowCount(0); // Limpa a tabela
        List<Musica> musicas = musicaController.listarMusicas();
        for (Musica m : musicas) {
            model.addRow(new Object[]{m.getId(), m.getNome(), m.getArtista(), m.getGenero()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MusicaFrame().setVisible(true);
        });
    }
}
