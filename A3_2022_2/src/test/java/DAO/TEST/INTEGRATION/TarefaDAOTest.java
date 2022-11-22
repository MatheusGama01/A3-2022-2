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
import java.sql.Connection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class TarefaDAOTest {

    private TarefaDAO tarefaDAO;

    @Mock
    private ConexaoDAO ConexaoDAO;

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
    public void verificaSeListarTarefasRepassaExcecaoQuandoBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelListarAsTarefasDoUsuario {
        UsuarioDTO usuarioDTO = carregarUsuario();
        NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException = new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException();

        when(ConexaoDAO.conectaBD()).thenReturn( new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());
        
        NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException = assertThrows(NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class, () -> {
            tarefaDAO.listarTarefas(usuarioDTO);
        });

    }

    private UsuarioDTO carregarUsuario() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO("TarefaDAO TesteIntegração", "123", "tarefadao@email.com");
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        UsuarioDTO usuarioRetornado = usuarioDAO.listarUsuario(usuarioDTO);

        return usuarioRetornado;
    }
}
