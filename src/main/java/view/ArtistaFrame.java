package view;

import controller.ArtistaController;
import dao.Conexao;
import dao.Conexao;
import model.Artista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class ArtistaFrame extends JFrame {

    // Declaração das variáveis
    private JTable table;
    private DefaultTableModel model;
    private JTextField nomeField;
    private JTextField generoField;
    private JButton addButton;
    private JButton deleteButton;
    private ArtistaController artistaController;

    public ArtistaFrame() {
        // Configurações da Janela
        setTitle("Spotifei - Artistas");
        setSize(800, 600);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null); // Centraliza a janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        artistaController = new ArtistaController(conn);

        // ==== Tabela ====
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Gênero");

        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(50, 50, 700, 300);
        add(pane);

        // ==== Campos ====
        nomeField = new JTextField();
        nomeField.setBounds(50, 370, 300, 30);
        placeholder(nomeField, "Nome do Artista");
        add(nomeField);

        generoField = new JTextField();
        generoField.setBounds(370, 370, 300, 30);
        placeholder(generoField, "Gênero Musical");
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
            String genero = generoField.getText();

            if (!nome.isEmpty() && !genero.isEmpty()) {
            if (artistaController.cadastrarArtista(nome, genero)) {
                JOptionPane.showMessageDialog(null, "Artista adicionado com sucesso!");
            listarArtistas();
                nomeField.setText(""); // Limpa o campo
                generoField.setText(""); // Limpa o campo
            } else {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar artista.");
            }
            
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
            }
        });



deleteButton.addActionListener(e -> {
    int row = table.getSelectedRow();
    if (row != -1) {
        int id = Integer.parseInt(model.getValueAt(row, 0).toString());

        // Confirmação antes de excluir
        int confirm = JOptionPane.showConfirmDialog(null, 
            "Deseja realmente remover o artista selecionado?", 
            "Confirmação", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (artistaController.removerArtista(id)) {
                JOptionPane.showMessageDialog(null, "Artista removido com sucesso!");
                listarArtistas();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao remover artista.");
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Selecione um artista para remover.");
    }
});



        listarArtistas();
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

    private void listarArtistas() {
    model.setRowCount(0); // Limpa a tabela
    List<Artista> artistas = artistaController.listarArtistas();
    for (Artista a : artistas) {
        model.addRow(new Object[]{a.getId(), a.getNome(), a.getGenero()});
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ArtistaFrame().setVisible(true);
        });
    }
}
