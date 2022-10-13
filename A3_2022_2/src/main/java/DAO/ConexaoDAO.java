package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    Connection conn = null;

    public Connection conectaBD() {
        try {
            String url = "colocar url do bd";
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println("Deu erro em conectaBD" + ex);
        }
        return conn;
    }
}
