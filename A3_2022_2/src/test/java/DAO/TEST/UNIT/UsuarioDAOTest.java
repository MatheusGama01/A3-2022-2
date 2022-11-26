package DAO.TEST.UNIT;

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
import java.sql.ResultSet;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UsuarioDAOTest {

    private UsuarioDAO usuarioDAO;

    @Before
    public void init() {
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        this.usuarioDAO = new UsuarioDAO(conexaoDAO);
        
    }

    @After
    public void tearDown() throws FalhaAoCriptografarSenhaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarOUsuarioException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste UsuárioDAO", Criptografia.encriptarSenha("123"), "usuariodao@email.com");

        usuarioDAO.apagarUsuario(usuarioDTO);
        UsuarioDTO usuarioRetornado = usuarioDAO.listarUsuario(usuarioDTO);

        UsuarioDTO usuarioDTO2 = new UsuarioDTO(0, null, null, null);
        assertEquals(usuarioDTO2, usuarioRetornado);
    }

    @Test
    public void deveCadastrarOUsuarioComSucesso() throws FalhaAoCriptografarSenhaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelApagarOUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste UsuárioDAO", Criptografia.encriptarSenha("123"), "usuariodao@email.com");

        usuarioDAO.cadastrarUsuario(usuarioDTO);

        UsuarioDTO usuarioCadastrado = usuarioDAO.listarUsuario(usuarioDTO);
        usuarioDTO.setId(usuarioCadastrado.getId());

        assertEquals(usuarioDTO, usuarioCadastrado);
    }

    @Test
    public void deveAutenticarOUsuarioComSucesso() throws FalhaAoCriptografarSenhaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, FalhaAoAutenticarException, NaoFoiPossivelCadastrarUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste UsuárioDAO", Criptografia.encriptarSenha("123"), "usuariodao@email.com");
        usuarioDAO.cadastrarUsuario(usuarioDTO);

        ResultSet usuarioAutenticado = usuarioDAO.autenticarUsuario(usuarioDTO);

        assertNotNull(usuarioAutenticado);
    }

    @Test
    public void deveListarOUsuarioComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException, FalhaAoCriptografarSenhaException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste UsuárioDAO", Criptografia.encriptarSenha("123"), "usuariodao@email.com");
        usuarioDAO.cadastrarUsuario(usuarioDTO);

        UsuarioDTO usuarioListado = usuarioDAO.listarUsuario(usuarioDTO);
        usuarioDTO.setId(usuarioListado.getId());

        assertEquals(usuarioDTO, usuarioListado);
    }

    @Test
    public void deveApagarUsuarioComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, FalhaAoCriptografarSenhaException, NaoFoiPossivelCadastrarUsuarioException, NaoFoiPossivelApagarOUsuarioException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioTeste = new UsuarioDTO(0, null, null, null);
        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste UsuárioDAO", Criptografia.encriptarSenha("123"), "usuariodao@email.com");
        usuarioDAO.cadastrarUsuario(usuarioDTO);

        usuarioDAO.apagarUsuario(usuarioDTO);

        UsuarioDTO usuarioApagado = usuarioDAO.listarUsuario(usuarioDTO);
        assertEquals(usuarioTeste, usuarioApagado);
    }
}
