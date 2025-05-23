package view;

import dao.Conexao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexaoFrame extends JFrame {
    private JButton testarConexaoButton;
    private JLabel statusLabel;

    public TesteConexaoFrame() {
        // Configurações da Janela
        setTitle("Spotifei - Teste de Conexão");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);

        //  Título 
        JLabel titleLabel = new JLabel("Teste de Conexão", SwingConstants.CENTER);
        titleLabel.setBounds(0, 20, 400, 30);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(30, 215, 96));
        add(titleLabel);

        //  Botão de Teste 
        testarConexaoButton = new JButton("Testar Conexão");
        testarConexaoButton.setBounds(125, 100, 150, 40);
        testarConexaoButton.setBackground(new Color(30, 215, 96));
        testarConexaoButton.setForeground(Color.BLACK);
        add(testarConexaoButton);

        //  Status 
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setBounds(0, 180, 400, 30);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        statusLabel.setForeground(Color.WHITE);
        add(statusLabel);

        // Ação do botão
        testarConexaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Iniciando tentativa de conexão...");

                    Conexao conexao = new Conexao();
                    System.out.println("Objeto Conexao criado.");

                    Connection conn = conexao.getConnection();
                    System.out.println("Tentando obter conexão...");

                    if (conn != null && !conn.isClosed()) {
                        statusLabel.setText("Conexão estabelecida com sucesso!");
                        statusLabel.setForeground(new Color(30, 215, 96));
                        System.out.println("Conexão bem-sucedida!");
                    } else {
                        statusLabel.setText("Falha na conexão.");
                        statusLabel.setForeground(Color.RED);
                        System.out.println("Falha na conexão.");
                    }
                } catch (SQLException ex) {
                    statusLabel.setText("Erro ao verificar conexão: " + ex.getMessage());
                    statusLabel.setForeground(Color.RED);
                    System.out.println("Erro SQL: " + ex.getMessage());
                    ex.printStackTrace();
                } catch (Exception ex) {
                    statusLabel.setText("Erro inesperado: " + ex.getMessage());
                    statusLabel.setForeground(Color.RED);
                    System.out.println("Erro inesperado: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TesteConexaoFrame().setVisible(true);
        });
    }
}
