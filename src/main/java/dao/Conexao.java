package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/Spotifei_1";
    private static final String USER = "postgres"; // Altere para o seu usuário
    private static final String PASSWORD = "admin"; // Altere para a sua senha

    public Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexão estabelecida com sucesso!");
            } else {
                System.out.println("Falha ao estabelecer conexão!");
            }

            return conn;

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados:");
            e.printStackTrace();
            return null;
        }
    }
}
