package view;

import controller.UsuarioController;
import dao.Conexao;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame() {
        // Configurações da Janela
        setTitle("Spotifei - Login");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null); // Centraliza a janela

        // ==== Título ====
        JLabel titleLabel = new JLabel("SPOTIFEI", SwingConstants.CENTER);
        titleLabel.setBounds(0, 40, 600, 50);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        titleLabel.setForeground(new Color(30, 215, 96));
        add(titleLabel);

        JLabel loginLabel = new JLabel("LOGIN", SwingConstants.CENTER);
        loginLabel.setBounds(0, 100, 600, 40);
        loginLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        loginLabel.setForeground(new Color(30, 215, 96));
        add(loginLabel);

        // ==== Campos ====
        emailField = new JTextField();
        emailField.setBounds(180, 180, 240, 35);
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        emailField.setForeground(Color.GRAY);
        emailField.setText("Email");

        // Placeholder para "Email"
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (emailField.getText().equals("Email")) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (emailField.getText().isEmpty()) {
                    emailField.setForeground(Color.GRAY);
                    emailField.setText("Email");
                }
            }
        });

        add(emailField);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 240, 240, 35);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setForeground(Color.GRAY);
        passwordField.setEchoChar((char) 0); // Remover os asteriscos para o placeholder
        passwordField.setText("Senha");

        // Placeholder para "Senha"
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (String.valueOf(passwordField.getPassword()).equals("Senha")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('•'); // Voltar os asteriscos
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText("Senha");
                    passwordField.setEchoChar((char) 0); // Remover os asteriscos novamente
                }
            }
        });

        add(passwordField);

        // ==== Botões ====
        loginButton = new JButton("Login");
        loginButton.setBounds(180, 300, 100, 35);
        loginButton.setBackground(new Color(30, 215, 96));
        loginButton.setForeground(Color.BLACK);
        add(loginButton);

        registerButton = new JButton("Registrar");
        registerButton.setBounds(320, 300, 100, 35);
        registerButton.setBackground(new Color(30, 215, 96));
        registerButton.setForeground(Color.BLACK);
        add(registerButton);

        // Conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        UsuarioController usuarioController = new UsuarioController(conn);

        // Ação de Login
        loginButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        String senha = new String(passwordField.getPassword());

        List<Usuario> usuarios = usuarioController.listarUsuarios();
        Usuario usuarioLogado = usuarios.stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);

        if (usuarioLogado != null) {
            JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
            dispose(); // Fecha a tela de login
            new DashboardFrame(usuarioLogado.getId()).setVisible(true); // Passa o ID para o Dashboard
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
        }
    }
});


        // Ação de Registro
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Função de registro ainda não implementada.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
