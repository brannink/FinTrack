package fintrack.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Classe que estabelece a conexão com o banco de dados
 * Conexao
 */
public class Conexao {
    private static final String URL = "jdbc:sqlite:fintrack.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
