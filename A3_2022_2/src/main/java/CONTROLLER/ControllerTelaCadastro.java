package CONTROLLER;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import HELPER.Criptografia;
import VIEW.TelaLogin;

public class ControllerTelaCadastro {
    
    //Instancia TelaCadstro e a torna visível.
    public void navegarParaTelaLogin() {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }

    public void cadastrarUsuario(String nome, String email, String senha) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException, FalhaAoCriptografarSenhaException {
        String senhaEncriptada = Criptografia.encriptarSenha(senha);
        
        UsuarioDTO usuarioDTO = new UsuarioDTO(nome, senhaEncriptada, email);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        usuarioDAO.cadastrarUsuario(usuarioDTO);
        
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }
}
