package CONTROLLER;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import VIEW.TelaLogin;

public class ControllerTelaCadastro {
    
    //Instancia TelaCadstro e a torna visível.
    public void navegarParaTelaLogin() {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }

    public void cadastrarUsuario(String nome, String email, String senha) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO(nome, senha, email);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        usuarioDAO.cadastrarUsuario(usuarioDTO);
        
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }
}
