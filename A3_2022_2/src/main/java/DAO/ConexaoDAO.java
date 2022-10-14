package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    Connection conn = null;

    //Inicializa a conexão com o banco de dados
    public Connection conectaBD() {
        try {
            String url = "jdbc:mysql://localhost:3306/app_tarefas?user_root&password=Vl030116@";
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println("Deu erro em conectaBD" + ex);
        }
        return conn;
    }
}
