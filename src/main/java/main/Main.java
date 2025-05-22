package main;

import dao.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();

        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                System.out.println("Statement criado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao criar Statement:");
                e.printStackTrace();
            }
        } else {
            System.out.println("Conexão falhou. Verifique os logs acima.");
        }
    }
}
