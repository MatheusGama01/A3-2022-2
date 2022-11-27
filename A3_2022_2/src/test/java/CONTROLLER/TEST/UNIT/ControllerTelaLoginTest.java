package CONTROLLER.TEST.UNIT;

import CONTROLLER.ControllerTelaLogin;
import DAO.ConexaoDAO;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoAutenticarException;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelApagarOUsuarioException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarOUsuarioException;
import EXCEPTIONS.EmailOuSenhaIncorretosException;
import HELPER.Criptografia;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ControllerTelaLoginTest {

    private ControllerTelaLogin controller;
    private Criptografia criptgrafia;

    @Before
    public void init() {
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);
        this.controller = new ControllerTelaLogin(usuarioDAO);
        this.criptgrafia = new Criptografia();
    }
    
    @After
    public void tearDown() throws FalhaAoCriptografarSenhaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarOUsuarioException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste ControllerTelaLogin", Criptografia.encriptarSenha("123"), "controller@email.com");
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);
        
        usuarioDAO.apagarUsuario(usuarioDTO);
        UsuarioDTO usuarioRetornado = usuarioDAO.listarUsuario(usuarioDTO);

        UsuarioDTO usuarioDTO2 = new UsuarioDTO(0, null, null, null);
        assertEquals(usuarioDTO2, usuarioRetornado);
    }

    @Test
    public void deveRealizarAutenticacaoComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, EmailOuSenhaIncorretosException, FalhaAoAutenticarException, FalhaAoCriptografarSenhaException, NaoFoiPossivelCadastrarUsuarioException {
        UsuarioDTO usuario = new UsuarioDTO("Teste ControllerTelaLogin", criptgrafia.encriptarSenha("123"), "controller@email.com");
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);
        usuarioDAO.cadastrarUsuario(usuario);
        
        usuario.setSenha("123");
        String email = usuario.getEmail();
        String senha = usuario.getSenha();

        Boolean login = controller.logar(email, senha);

        assertTrue(login);
    }

    @Test
    public void deveRetornarErroQuandoNenhumDadoEDigitado() {
        String email = "";
        String senha = "";

        EmailOuSenhaIncorretosException UsuarioOuSenhaIncorretosException = assertThrows(EmailOuSenhaIncorretosException.class, () -> {
            controller.logar(email, senha);
        });

        assertEquals("O email e/ou senha digitados estão incorretos.", UsuarioOuSenhaIncorretosException.getMessage());
    }
}
