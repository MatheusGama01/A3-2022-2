package DAO;

import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoAutenticarException;
import EXCEPTIONS.NaoFoiPossivelApagarOUsuarioException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarOUsuarioException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private final ConexaoDAO conexaoDAO;
    Connection conn;

    public UsuarioDAO(ConexaoDAO conexaoDAO) {
        this.conexaoDAO = conexaoDAO;
    }

    /**
     * Verifica se o email e senha passados são semelhantes ao email e senha de
     * algum usuário cadastrado no banco de dados.
     */
    public UsuarioDTO autenticarUsuario(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, FalhaAoAutenticarException {
        try {
            conn = conexaoDAO.conectaBD();
            String query = "SELECT * FROM usuarios WHERE email=? AND senha=?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, usuarioDTO.getEmail());
            pstm.setString(2, usuarioDTO.getSenha());
            ResultSet rs = pstm.executeQuery();

            UsuarioDTO usuarioLogado = new UsuarioDTO();

            while (rs.next()) {
                usuarioLogado.setId(rs.getInt("id"));
                usuarioLogado.setNome(rs.getString("nome"));
                usuarioLogado.setEmail(rs.getString("email"));
                usuarioLogado.setSenha(rs.getString("senha"));
            }

            pstm.close();

            return usuarioLogado;
        } catch (SQLException ex) {
            System.out.println("Erro na consulta para autenticação " + ex);
            throw new FalhaAoAutenticarException();
        }
    }

    //Insere um usuário no banco de dados.
    public Boolean cadastrarUsuario(UsuarioDTO objUsuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException {
        try {
            conn = conexaoDAO.conectaBD();
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

    //Busca um usuário específico no banco de dados.
    public UsuarioDTO listarUsuario(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException {
        try {
            UsuarioDTO usuarioRetornado = new UsuarioDTO();

            //Busca um usuário no banco de dados.
            conn = conexaoDAO.conectaBD();
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM usuarios WHERE email=?");
            pstm.setString(1, usuarioDTO.getEmail());
            ResultSet rs = pstm.executeQuery();

            //Insere o usuário retornado no objeto usuarioRetornado.
            while (rs.next()) {
                usuarioRetornado.setId(rs.getInt("id"));
                usuarioRetornado.setNome(rs.getString("nome"));
                usuarioRetornado.setEmail(rs.getString("email"));
                usuarioRetornado.setSenha(rs.getString("senha"));
            }

            pstm.close();

            return usuarioRetornado;
        } catch (SQLException ex) {
            System.out.println("Erro na consulta para autenticação " + ex);
            throw new NaoFoiPossivelListarOUsuarioException();
        }
    }

    //Apaga um usuário do banco de dados.
    public Boolean apagarUsuario(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarOUsuarioException {
        try {
            conn = conexaoDAO.conectaBD();
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM usuarios WHERE email = ?");
            pstm.setString(1, usuarioDTO.getEmail());
            pstm.executeUpdate();

            pstm.close();

            return true;
        } catch (SQLException ex) {
            System.out.println("Deu errado ao deletar o usuário" + ex);
            throw new NaoFoiPossivelApagarOUsuarioException();
        }
    }
}
