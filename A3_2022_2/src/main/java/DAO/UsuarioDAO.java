package DAO;

import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoAutenticarException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    Connection conn;

    /**
     * Verifica se os dados passados pelo model usuarioDTO são semelhantes a
     * algum usuario no banco.
     */
    public ResultSet autenticarUsuario(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, FalhaAoAutenticarException {
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
            throw new FalhaAoAutenticarException();
            //return null;
        }
    }

    public Boolean cadastrarUsuario(UsuarioDTO objUsuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException {
        try {
            conn = new ConexaoDAO().conectaBD();
            String query = "INSERT INTO usuarios (nome, email, senha) VALUES (?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(query);

            pstm.setString(1, objUsuarioDTO.getNome());
            pstm.setString(2, objUsuarioDTO.getEmail());
            pstm.setString(3, objUsuarioDTO.getSenha());

            pstm.execute();
            pstm.close();
            System.out.println("Usuário inserido no banco de dados");
            
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro UsuarioDAO.cadastrarUsuario()" + ex);
            throw new NaoFoiPossivelCadastrarUsuarioException();
        }

    }

    public ResultSet listarUsuario(UsuarioDTO UsuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        try {
            conn = new ConexaoDAO().conectaBD();
            String query = "SELECT * FROM usuarios WHERE email=?";
            PreparedStatement pstm = conn.prepareStatement(query);

            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println("Erro na consulta para autenticação " + ex);
            return null;
        }
    }

    public void atualizarUsuario(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        try {
            conn = new ConexaoDAO().conectaBD();
            PreparedStatement pstm = conn.prepareStatement("UPDATE usuarios SET nome = ? , senha = ? WHERE  email = ?");

            pstm.setString(1, usuarioDTO.getNome());
            pstm.setString(2, usuarioDTO.getSenha());
            pstm.setString(3, usuarioDTO.getEmail());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar o usuário" + ex);
        }
    }

    public void apagarUsuario(UsuarioDTO objDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        try {
            conn = new ConexaoDAO().conectaBD();
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM usuarios WHERE email = ?");
            pstm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Deu errado ao deletar o usuário" + ex);
        }
    }
}
