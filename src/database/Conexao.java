package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL =
            "jdbc:mysql://localhost:3306/estoque_db";

    private static final String USUARIO = "root";

    private static final String SENHA = "senha";

    public static Connection conectar() {

        try {
            return DriverManager.getConnection(
                    URL,
                    USUARIO,
                    SENHA
            );

        } catch (SQLException e) {

            System.out.println("Erro ao conectar com o banco de dados!");

            e.printStackTrace();

            return null;
        }
    }

    public static void main(String[] args) {

        Connection conexao = conectar();

        if (conexao != null) {
            System.out.println("Conectado ao MySQL com sucesso!");
        }

    }
}
