package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    // dados de conexão com o banco
    private static final String URL = "jdbc:postgresql://localhost:5432/Spotifei_1";
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "admin"; 

    // método estático pra pegar a conexão
    public static Connection getConexao() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            if (conn != null && !conn.isClosed()) {
                System.out.println("conexão estabelecida com sucesso!");
            } else {
                System.out.println("falha ao estabelecer conexão!");
            }

            return conn;

        } catch (SQLException e) {
            System.out.println("erro ao conectar ao banco de dados:");
            e.printStackTrace();
            return null;
        }
    }

    // método de instância pra pegar a conexão
    public Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            if (conn != null && !conn.isClosed()) {
                System.out.println("conexão estabelecida com sucesso!");
            } else {
                System.out.println("falha ao estabelecer conexão!");
            }

            return conn;

        } catch (SQLException e) {
            System.out.println("erro ao conectar ao banco de dados:");
            e.printStackTrace();
            return null;
        }
    }
}
