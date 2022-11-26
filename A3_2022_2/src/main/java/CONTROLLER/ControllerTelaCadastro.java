package CONTROLLER;

import DAO.ConexaoDAO;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import HELPER.Criptografia;
import VIEW.TelaLogin;

public class ControllerTelaCadastro {
    
    /**
     * Chama o método de encriptação de senha e posteriormente chama o método de
     * cadastrar usuário passando UsuarioDTO com nome, email e senha encriptada.
     */
    public void cadastrarUsuario(String nome, String email, String senha) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException, FalhaAoCriptografarSenhaException {
        String senhaEncriptada = Criptografia.encriptarSenha(senha);

        UsuarioDTO usuarioDTO = new UsuarioDTO(nome, senhaEncriptada, email);
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);
        

        usuarioDAO.cadastrarUsuario(usuarioDTO);

        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }
}
