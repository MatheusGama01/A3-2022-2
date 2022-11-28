package CONTROLLER;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoAutenticarException;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.EmailOuSenhaIncorretosException;
import HELPER.Criptografia;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerTelaLogin {

    private final UsuarioDAO usuarioDAO;

    public ControllerTelaLogin(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * Encripta a senha do usuário, chama o método autenticarUsuario e retorna o
     * usuário obtido.
     */
    public UsuarioDTO logar(String email, String senha) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, EmailOuSenhaIncorretosException, FalhaAoAutenticarException, FalhaAoCriptografarSenhaException {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(Criptografia.encriptarSenha(senha), email);

            ResultSet rs = usuarioDAO.autenticarUsuario(usuarioDTO);

            if (rs.next()) {
                System.out.println("Parabéns! você conseguiu acessar");
                UsuarioDTO usuario = new UsuarioDTO(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"), rs.getString("email"));
                System.out.println(usuario.getId() + " " + usuario.getNome() + " " + usuario.getEmail() + " " + usuario.getSenha());

                return usuarioDTO;
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
