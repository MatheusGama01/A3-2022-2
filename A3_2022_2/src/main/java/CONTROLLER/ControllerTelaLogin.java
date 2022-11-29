package CONTROLLER;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoAutenticarException;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.EmailOuSenhaIncorretosException;
import HELPER.Criptografia;

public class ControllerTelaLogin {

    private final UsuarioDAO usuarioDAO;

    public ControllerTelaLogin(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * Encripta a senha do usuário, chama o método autenticarUsuario e retorna o
     * usuário obtido, caso ele não esteja vazio.
     */
    public UsuarioDTO logar(String email, String senha) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, EmailOuSenhaIncorretosException, FalhaAoAutenticarException, FalhaAoCriptografarSenhaException {
        UsuarioDTO usuarioDTO = new UsuarioDTO(Criptografia.encriptarSenha(senha), email);
        UsuarioDTO usuarioVazio = new UsuarioDTO(0, null, null, null);

        UsuarioDTO usuarioLogado = usuarioDAO.autenticarUsuario(usuarioDTO);

        if (usuarioLogado.equals(usuarioVazio)) {
            throw new FalhaAoAutenticarException();
        } else {
            return usuarioLogado;
        }
    }
}
