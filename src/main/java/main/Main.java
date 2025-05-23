package main;

import dao.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Conexao conexao = new Conexao(); // cria uma instância de conexão
        Connection conn = conexao.getConnection(); // tenta pegar a conexão

        if (conn != null) {
            try {
                Statement stmt = conn.createStatement(); // cria um statement pra executar comandos SQL
                System.out.println("statement criado com sucesso!");
            } catch (SQLException e) {
                System.out.println("erro ao criar statement:");
                e.printStackTrace();
            }
        } else {
            System.out.println("conexão falhou. verifique os logs acima.");
        }
    }
}
