package DAO;

import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/teste_a3";
    String usuario = "root";
    String senha = "@ServidorMySQL@";

    //Inicializa a conexão com o banco de dados.
    public Connection conectaBD() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        try {
            this.conn = DriverManager.getConnection(this.url, this.usuario, this.senha);
            
            return this.conn;
        } catch (SQLException ex) {
            System.out.println("Deu erro em conectaBD " + ex);
            throw new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException();
        }
    }
}
