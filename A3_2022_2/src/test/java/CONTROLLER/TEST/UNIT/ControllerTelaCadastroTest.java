package CONTROLLER.TEST.UNIT;

import CONTROLLER.ControllerTelaCadastro;
import DAO.ConexaoDAO;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoAutenticarException;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelApagarOUsuarioException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarOUsuarioException;
import HELPER.Criptografia;
import java.sql.SQLException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ControllerTelaCadastroTest {

    private ControllerTelaCadastro controller;
    private Criptografia criptografia;

    @Before
    public void init() {
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);
        this.controller = new ControllerTelaCadastro(usuarioDAO);
        this.criptografia = new Criptografia();
    }

    @After
    public void tearDown() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarOUsuarioException, NaoFoiPossivelListarOUsuarioException {
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);
        UsuarioDTO usuarioDTO = new UsuarioDTO("123", "controllerTelaCadastro@email.com");

        usuarioDAO.apagarUsuario(usuarioDTO);
        UsuarioDTO usuarioRetornado = usuarioDAO.listarUsuario(usuarioDTO);

        UsuarioDTO usuarioDTO2 = new UsuarioDTO(0, null, null, null);
        assertEquals(usuarioDTO2, usuarioRetornado);
    }

    @Test
    public void deveCadastrarOUsuarioComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException, FalhaAoCriptografarSenhaException, FalhaAoAutenticarException, SQLException, NaoFoiPossivelListarOUsuarioException {
        String nome = "ControllerTelaCadastro Teste";
        String senha = "123";
        String email = "controllerTelaCadastro@email.com";

        controller.cadastrarUsuario(nome, email, senha);
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);
        UsuarioDTO usuarioDTO = new UsuarioDTO(nome, senha, email);
        UsuarioDTO usuarioCadastrado = usuarioDAO.listarUsuario(usuarioDTO);
        usuarioDTO.setId(usuarioCadastrado.getId());
        usuarioDTO.setSenha(criptografia.encriptarSenha(senha));

        assertEquals(usuarioDTO, usuarioCadastrado);
    }
}
