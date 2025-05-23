package view;

import controller.UsuarioController;
import dao.Conexao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class RegisterFrame extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;

    public RegisterFrame() {
        // Configurações da Janela
        setTitle("Spotifei - Registro");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null); // Centraliza a janela

        //  Título 
        JLabel titleLabel = new JLabel("SPOTIFEI", SwingConstants.CENTER);
        titleLabel.setBounds(0, 40, 600, 50);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        titleLabel.setForeground(new Color(30, 215, 96));
        add(titleLabel);

        JLabel loginLabel = new JLabel("REGISTRO", SwingConstants.CENTER);
        loginLabel.setBounds(0, 100, 600, 40);
        loginLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        loginLabel.setForeground(new Color(30, 215, 96));
        add(loginLabel);

        //  Campos 
        nameField = new JTextField();
        nameField.setBounds(180, 180, 240, 35);
        nameField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        nameField.setForeground(Color.GRAY);
        nameField.setText("Nome");
        placeholder(nameField, "Nome");
        add(nameField);

        emailField = new JTextField();
        emailField.setBounds(180, 240, 240, 35);
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        emailField.setForeground(Color.GRAY);
        emailField.setText("Email");
        placeholder(emailField, "Email");
        add(emailField);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 300, 240, 35);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setForeground(Color.GRAY);
        passwordField.setEchoChar((char) 0);
        passwordField.setText("Senha");
        placeholder(passwordField, "Senha");
        add(passwordField);

        // ==== Botões ====
        registerButton = new JButton("Registrar");
        registerButton.setBounds(180, 370, 120, 35);
        registerButton.setBackground(new Color(30, 215, 96));
        registerButton.setForeground(Color.BLACK);
        add(registerButton);

        backButton = new JButton("Voltar");
        backButton.setBounds(320, 370, 100, 35);
        backButton.setBackground(new Color(30, 215, 96));
        backButton.setForeground(Color.BLACK);
        add(backButton);

        // Conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        UsuarioController usuarioController = new UsuarioController(conn);

        // Ação de Registrar
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nameField.getText();
                String email = emailField.getText();
                String senha = new String(passwordField.getPassword());

                if (usuarioController.cadastrarUsuario(nome, email, senha)) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    dispose();
                    new LoginFrame().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário.");
                }
            }
        });

        // Ação de Voltar
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Método para adicionar placeholder
    private void placeholder(JTextField field, String text) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegisterFrame().setVisible(true);
        });
    }
}
