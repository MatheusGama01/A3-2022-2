package DAO;

import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gabriele
 */
public class UsuarioDAO {

    Connection conn;

    /**
     * Verifica se os dados passados pelo model usuarioDTO são semelhantes a
     * algum usuario no banco.
     */
    public ResultSet autenticaUsuario(UsuarioDTO usuarioDTO) {
        try {
            conn = new ConexaoDAO().conectaBD();
            String query = "SELECT * FROM usuarios WHERE email=? AND senha=?";
            PreparedStatement pstm = conn.prepareStatement(query);

            pstm.setString(1, usuarioDTO.getEmail());
            pstm.setString(2, usuarioDTO.getSenha());

            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println("Erro na consulta para autenticação " + ex);
            return null;
        }
    }

    public void create(UsuarioDTO objUsuarioDTO) {
        try {
            conn = new ConexaoDAO().conectaBD();
            String query = "INSERT INTO usuario (Nome,Senha,Email,CPF,DataNasc,Telefone) VALUES (?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(query);

            pstm.setString(1, objUsuarioDTO.getNome());
            pstm.setString(2, objUsuarioDTO.getSenha());
            pstm.setString(3, objUsuarioDTO.getEmail());

            pstm.execute();
            pstm.close();
            System.out.println("Usuário inserido no banco de dados");
        } catch (SQLException ex) {
            System.out.println("Erro UsuarioDAO.create()" + ex);
        }

    }

    public ResultSet loadUser(UsuarioDTO UsuarioDTO) {
        try {
            conn = new ConexaoDAO().conectaBD();
            String query = "SELECT * FROM usuario WHERE CPF=?";
            PreparedStatement pstm = conn.prepareStatement(query);

            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println("Erro na consulta para autenticação " + ex);
            return null;
        }
    }

    public void update(UsuarioDTO usuarioDTO) {
        try {
            conn = new ConexaoDAO().conectaBD();
            PreparedStatement pstm = conn.prepareStatement("UPDATE usuario SET Email = ? , Nome = ?, Senha = ? WHERE  CPF = ?");

            pstm.setString(1, usuarioDTO.getEmail());
            pstm.setString(2, usuarioDTO.getNome());
            pstm.setString(3, usuarioDTO.getSenha());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar o usuário" + ex);
        }
    }

    public void delete(UsuarioDTO objDTO) {
        try {
            conn = new ConexaoDAO().conectaBD();
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM usuario WHERE CPF = ?");
            pstm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Deu errado ao deletar o usuário" + ex);
        }
    }
}
