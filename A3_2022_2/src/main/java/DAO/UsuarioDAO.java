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
     * Verifica se os dados passados pelo model usuarioDTO são semelhantes a
     * algum usuario no banco.
     */
    public ResultSet autenticarUsuario(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, FalhaAoAutenticarException {
        try {
            conn = conexaoDAO.conectaBD();
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

    //Inseri um usuário no banco de dados.
    public boolean cadastrarUsuario(UsuarioDTO objUsuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException {
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

    //Lista um usuário do banco.
    public UsuarioDTO listarUsuario(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException {
        try {
            UsuarioDTO usuarioRetornado = new UsuarioDTO();
            
            conn = conexaoDAO.conectaBD();
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM usuarios WHERE email=?");
            
            pstm.setString(1, usuarioDTO.getEmail());

            ResultSet rs = pstm.executeQuery();
            
            //Inseri o usuário retornado no Objeto usuarioRetornado.
            while (rs.next()) {
                
                usuarioRetornado.setId(rs.getInt("id"));
                usuarioRetornado.setNome(rs.getString("nome"));
                usuarioRetornado.setEmail(rs.getString("email"));
                usuarioRetornado.setSenha(rs.getString("senha"));
            }

            return usuarioRetornado;
        } catch (SQLException ex) {
            System.out.println("Erro na consulta para autenticação " + ex);
            throw new NaoFoiPossivelListarOUsuarioException();
        }
    }
    
    /* Não sei se precisa ter.
    
    //Atualiza um usuário do banco de dados.
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
    
    */

    //Apaga um usuário do banco de dados.
    public boolean apagarUsuario(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarOUsuarioException {
        try {
            conn = conexaoDAO.conectaBD();
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM usuarios WHERE email = ?");

            pstm.setString(1, usuarioDTO.getEmail());

            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("Deu errado ao deletar o usuário" + ex);
            throw new NaoFoiPossivelApagarOUsuarioException();
        }
    }
}
