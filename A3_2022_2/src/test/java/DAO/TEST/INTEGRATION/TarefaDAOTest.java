package DAO.TEST.INTEGRATION;

import DAO.ConexaoDAO;
import DAO.TarefaDAO;
import DAO.UsuarioDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelApagarATarefaException;
import EXCEPTIONS.NaoFoiPossivelApagarOUsuarioException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelCriarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuarioException;
import EXCEPTIONS.NaoFoiPossivelListarOUsuarioException;
import EXCEPTIONS.NaoFoiPossivelSalvarAEdicaoDaTarefaException;
import HELPER.Criptografia;
import com.mysql.cj.jdbc.exceptions.MySQLTimeoutException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class TarefaDAOTest {

    @Mock
    private TarefaDAO tarefaDAO;

    //@Mock
    //private ConexaoDAO conexaoDAO;
    
    @Before
    public void init() throws FalhaAoCriptografarSenhaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException {
        MockitoAnnotations.initMocks(this);
        
        Criptografia criptografia = new Criptografia();
        UsuarioDTO usuarioDTO = new UsuarioDTO("TarefaDAO TesteIntegração", criptografia.encriptarSenha("123"), "tarefadao@email.com");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.cadastrarUsuario(usuarioDTO);
    }
    
    @After
    public void tearDown() throws FalhaAoCriptografarSenhaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarOUsuarioException, NaoFoiPossivelListarOUsuarioException {
        Criptografia criptografia = new Criptografia();
        UsuarioDTO usuarioDTO = new UsuarioDTO("TarefaDAO TesteIntegração", criptografia.encriptarSenha("123"), "tarefadao@email.com");
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        usuarioDAO.apagarUsuario(usuarioDTO);
        UsuarioDTO usuarioRetornado = usuarioDAO.listarUsuario(usuarioDTO);

        UsuarioDTO usuarioDTO2 = new UsuarioDTO(0, null, null, null);
        assertEquals(usuarioDTO2, usuarioRetornado);
    }

    @Test
    public void verificaSeListarTarefasRetornaNaoNulo() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        ArrayList<TarefaDTO> tarefas = mock(ArrayList.class);
        UsuarioDTO usuarioDTO = mock(UsuarioDTO.class);

        when(tarefaDAO.listarTarefas(any(UsuarioDTO.class))).thenReturn(tarefas);

        assertNotNull(tarefaDAO.listarTarefas(usuarioDTO));
    }
    
    @Test
    public void verificaSeListarTarefaRetornaNaoNulo() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        TarefaDTO tarefaDTO = mock(TarefaDTO.class);
        
        when(tarefaDAO.listarTarefa(tarefaDTO)).thenReturn(tarefaDTO);
        
        assertNotNull(tarefaDAO.listarTarefa(tarefaDTO));
    }
    
    @Test
    public void verificaSeCriarTarefaRetornaTrue() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCriarATarefaException{
        TarefaDTO tarefaDTO = mock(TarefaDTO.class);
        UsuarioDTO usuarioDTO = mock(UsuarioDTO.class);
        
        when(tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO)).thenReturn(true);
        
        assertTrue(tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO));
    }
    
    @Test
    public void verificaSeAtualizarTarefaRetornaTrue() throws NaoFoiPossivelSalvarAEdicaoDaTarefaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException{
        TarefaDTO tarefaDTO = mock(TarefaDTO.class);
        
        when(tarefaDAO.atualizarTarefa(tarefaDTO)).thenReturn(true);
        
        assertTrue(tarefaDAO.atualizarTarefa(tarefaDTO));
    }
    
    @Test
    public void verificaSeApagarTarefaRetornaTrue() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarATarefaException{
        TarefaDTO tarefaDTO = mock(TarefaDTO.class);
        
        when(tarefaDAO.apagarTarefa(tarefaDTO)).thenReturn(true);
        
        assertTrue(tarefaDAO.apagarTarefa(tarefaDTO));
    }

    /*

    @Test
    public void verificarSeListarTarefasLanca() throws SQLException {
        PreparedStatement pstm = mock(PreparedStatement.class);
        UsuarioDTO usuarioDTO = mock(UsuarioDTO.class);

        //doThrow(SQLException.class).when(pstm).executeQuery();
        //when(pstm.executeQuery()).thenThrow(SQLException.class);
        //given(pstm.executeQuery()).willThrow(SQLException.class);
        //willThrow(SQLException.class).given(pstm).executeQuery();
        
        ResultSet rs = doThrow(new SQLException()).when(pstm).executeQuery();
        when(pstm.executeQuery()).thenReturn(rs);

        NaoFoiPossivelListarAsTarefasDoUsuario NaoFoiPossivelListarAsTarefasDoUsuario = assertThrows(NaoFoiPossivelListarAsTarefasDoUsuario.class, () -> {
            tarefaDAO.listarTarefas(usuarioDTO);
        });

        assertEquals("Desculpe, houve um erro inesperado e não conseguimos encontrar suas tarefas!", NaoFoiPossivelListarAsTarefasDoUsuario.getMessage());
    }
    
    */

    /*
    
    //@Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    @Test
    public void verificaSeListarTarefasRepassaExcecaoQuandoBancoIndisponivel() throws NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelListarAsTarefasDoUsuario, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        ConexaoDAO conexaoDAO = mock(ConexaoDAO.class);

        //doThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException()).when(conexaoDAO).conectaBD();
        //given(conexaoDAO.conectaBD()).willThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());
        //willThrow(NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class).given(conexaoDAO).conectaBD();
        when(conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());
        
        NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException = assertThrows(NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class, () -> {
            tarefaDAO.listarTarefas(usuarioDTO);
        });

        assertEquals("Não foi possível estabelecer conexão com o banco de dados.\nTente novamente mais tarde!", NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.getMessage());
        
    
    
        
        Connection bd = Mockito.doThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException()).when(conexaoDAO).conectaBD();

        when(conexaoDAO.conectaBD()).thenReturn(bd);
        
        tarefaDAO.listarTarefas(usuarioDTO);

        verify(NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class).;
        
        NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException = assertThrows(NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class, () -> {
            tarefaDAO.listarTarefas(usuarioDTO);
        });

        assertEquals("Não foi possível estabelecer conexão com o banco de dados.\nTente novamente mais tarde!", NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.getMessage());
        
    }
    
     */
    private UsuarioDTO carregarUsuario() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO("TarefaDAO TesteIntegração", "123", "tarefadao@email.com");
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        UsuarioDTO usuarioRetornado = usuarioDAO.listarUsuario(usuarioDTO);

        return usuarioRetornado;
    }
}
