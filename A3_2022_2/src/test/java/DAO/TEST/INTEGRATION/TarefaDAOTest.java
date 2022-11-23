package DAO.TEST.INTEGRATION;

import DAO.ConexaoDAO;
import DAO.TarefaDAO;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuario;
import EXCEPTIONS.NaoFoiPossivelListarOUsuarioException;
import HELPER.Criptografia;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TarefaDAOTest {

    private TarefaDAO tarefaDAO;

    @Mock
    private ConexaoDAO conexaoDAO;

    @Before
    public void init() throws FalhaAoCriptografarSenhaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException {
        MockitoAnnotations.initMocks(this);
        this.tarefaDAO = new TarefaDAO();

        Criptografia criptografia = new Criptografia();
        UsuarioDTO usuarioDTO = new UsuarioDTO("TarefaDAO TesteIntegração", criptografia.encriptarSenha("123"), "tarefadao@email.com");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.cadastrarUsuario(usuarioDTO);
    }

    @Test
    public void verificaSeListarTarefasRepassaExcecaoQuandoBancoIndisponivel() throws NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelListarAsTarefasDoUsuario, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException = new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException();

        Mockito.doThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException()).when(conexaoDAO).conectaBD();

        NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException = tarefaDAO.listarTarefas(usuarioDTO);

        assertEquals("Não foi possível estabelecer conexão com o banco de dados.\nTente novamente mais tarde!", NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.getMessage());
    }

    private UsuarioDTO carregarUsuario() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO("TarefaDAO TesteIntegração", "123", "tarefadao@email.com");
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        UsuarioDTO usuarioRetornado = usuarioDAO.listarUsuario(usuarioDTO);

        return usuarioRetornado;
    }
}
