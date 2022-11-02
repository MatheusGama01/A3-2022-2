package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/teste_a3";
    String usuario = "root";
    String senha = "@ServidorMySQL@";

    //Inicializa a conexão com o banco de dados
    public Connection conectaBD() {
        try {
            conn = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) {
            System.out.println("Deu erro em conectaBD " + ex);
        }
        return conn;
    }
}
