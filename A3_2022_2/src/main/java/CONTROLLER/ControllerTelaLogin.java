package CONTROLLER;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoAutenticarException;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.EmailOuSenhaIncorretosException;
import HELPER.Criptografia;
import VIEW.TelaCadastro;
import VIEW.TelaHome;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerTelaLogin {
    
    //Instancia TelaCadstro e a torna visível.
    public void navegarParaTelaCadastro(){
        TelaCadastro telaCadastro = new TelaCadastro();
        telaCadastro.setVisible(true);
    }
    
    /**
     * Realiza a verificação dos dados digitados pelo usuário e, caso estejam
     * corretos, navega para telaHome.
     */
    public Boolean logar(String email, String senha) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, EmailOuSenhaIncorretosException, FalhaAoAutenticarException, FalhaAoCriptografarSenhaException {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(Criptografia.encriptarSenha(senha), email);
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            ResultSet rs = usuarioDAO.autenticarUsuario(usuarioDTO);

            if (rs.next()) {
                System.out.println("Parabéns! você conseguiu acessar");
                UsuarioDTO usuario = new UsuarioDTO(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"), rs.getString("email"));      
                System.out.println(usuario.getId() + " " + usuario.getNome() + " " + usuario.getEmail() + " " + usuario.getSenha());
                
                TelaHome telaHome = new TelaHome(usuario);
                telaHome.setVisible(true);
                
                return true;
            } else {
                System.out.println("Não foi possível conectar");
                throw new EmailOuSenhaIncorretosException();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao tentar autenticar");
            throw new FalhaAoAutenticarException();
        }
    }
}
