package DAO.TEST.INTEGRATION;

import CONTROLLER.ControllerTelaHome;
import CONTROLLER.ControllerTelaTarefa;
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
import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.exceptions.MySQLTimeoutException;
import java.sql.Connection;
import java.sql.JDBCType;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class TarefaDAOTest {

    private TarefaDAO tarefaDAO;

    @Mock
    private ConexaoDAO conexaoDAO;
    
    @Before
    public void init() throws FalhaAoCriptografarSenhaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException {
        MockitoAnnotations.initMocks(this);
        this.tarefaDAO = new TarefaDAO(conexaoDAO);
    }
    
    /*
    @Test
    public void verificaSeListarTarefasRetornaNaoNulo() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        ArrayList<TarefaDTO> tarefas = mock(ArrayList.class);
        UsuarioDTO usuarioDTO = mock(UsuarioDTO.class);

        when(tarefaDAO.listarTarefas(any(UsuarioDTO.class))).thenReturn(tarefas);

        assertNotNull(tarefaDAO.listarTarefas(usuarioDTO));
        verify(tarefaDAO, times(1)).listarTarefas(usuarioDTO);
    }
    
    @Test
    public void verificaSeListarTarefaRetornaNaoNulo() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        TarefaDTO tarefaDTO = mock(TarefaDTO.class);
        
        when(tarefaDAO.listarTarefa(tarefaDTO)).thenReturn(tarefaDTO);
        
        assertNotNull(tarefaDAO.listarTarefa(tarefaDTO));
        verify(tarefaDAO, times(1)).listarTarefa(tarefaDTO);
    }
    
    @Test
    public void verificaSeCriarTarefaRetornaTrue() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCriarATarefaException{
        TarefaDTO tarefaDTO = mock(TarefaDTO.class);
        UsuarioDTO usuarioDTO = mock(UsuarioDTO.class);
        
        when(tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO)).thenReturn(true);
        
        assertTrue(tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO));
        verify(tarefaDAO, times(1)).criarTarefa(tarefaDTO, usuarioDTO);
    }
    
    @Test
    public void verificaSeAtualizarTarefaRetornaTrue() throws NaoFoiPossivelSalvarAEdicaoDaTarefaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException{
        UsuarioDTO usuario = carregarUsuario();
        TarefaDTO tarefaDTO = mock(TarefaDTO.class);
        
        when(tarefaDAO.atualizarTarefa(tarefaDTO)).thenReturn(true);
        
        assertTrue(tarefaDAO.atualizarTarefa(tarefaDTO));
        verify(tarefaDAO, times(1)).atualizarTarefa(tarefaDTO);
    }
    
    @Test
    public void verificaSeApagarTarefaRetornaTrue() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarATarefaException{
        TarefaDTO tarefaDTO = mock(TarefaDTO.class);
        
        when(tarefaDAO.apagarTarefa(tarefaDTO)).thenReturn(true);
        
        assertTrue(tarefaDAO.apagarTarefa(tarefaDTO));
        verify(tarefaDAO, times(1)).apagarTarefa(tarefaDTO);
    }
    */
    
    @Test
    public void verificaSeListarTarefasRetornaListaDeTarefas() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException{
        Connection connection = mock(Connection.class);
        UsuarioDTO usuarioDTO = mock(UsuarioDTO.class);
        
        when(conexaoDAO.conectaBD()).thenReturn(connection);
        
        tarefaDAO.listarTarefas(usuarioDTO);
        
        verify(conexaoDAO, times(1)).conectaBD();
    }

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificaSeLancaErroAoApagarTarefaComBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelApagarATarefaException, NaoFoiPossivelCriarATarefaException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        when(conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());

        TarefaDTO tarefaDTO2 = new TarefaDTO(4, "asdjbasdb", false, 100);
        tarefaDAO.apagarTarefa(tarefaDTO2);
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

    private TarefaDTO pegaAPrimeiraTarefaDoBanco(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        ArrayList<TarefaDTO> tarefas = tarefaDAO.listarTarefas(usuarioDTO);
        TarefaDTO tarefa = new TarefaDTO(0, "", Boolean.FALSE);

        for (TarefaDTO tarefa1 : tarefas) {

            tarefa.setId(tarefa1.getId());
            tarefa.setDescricao(tarefa1.getDescricao());
            tarefa.setStatus(tarefa1.getStatus());
            tarefa.setIdUsuario(tarefa1.getIdUsuario());

            break;
        }

        return tarefa;
    }
}
