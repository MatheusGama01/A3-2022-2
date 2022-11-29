package CONTROLLER;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import HELPER.Criptografia;

public class ControllerTelaCadastro {

    private final UsuarioDAO usuarioDAO;

    public ControllerTelaCadastro(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * Encripta a senha do usuário e posteriormente chama o método de cadastrar
     * usuário passando um UsuarioDTO com nome, email e senha encriptada.
     */
    public Boolean cadastrarUsuario(String nome, String email, String senha) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException, FalhaAoCriptografarSenhaException {
        String senhaEncriptada = Criptografia.encriptarSenha(senha);
        UsuarioDTO usuarioDTO = new UsuarioDTO(nome, senhaEncriptada, email);

        Boolean usuarioCadastrado = this.usuarioDAO.cadastrarUsuario(usuarioDTO);

        return usuarioCadastrado;
    }
}
