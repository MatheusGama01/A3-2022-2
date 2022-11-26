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

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificaSeLancaErroAoListarTarefasQuandoBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        when(conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());

        UsuarioDTO usuarioDTO = new UsuarioDTO(1, "Teste", "123", "teste@email.com");

        tarefaDAO.listarTarefas(usuarioDTO);
    }

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificaSeLancaErroAoListarTarefaQuandoBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        when(conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());

        TarefaDTO tarefaDTO = new TarefaDTO(1, "Teste", false, 100);

        tarefaDAO.listarTarefa(tarefaDTO);
    }

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificaSeLancaErroAoCriarTarefaComBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCriarATarefaException {
        when(conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());

        UsuarioDTO usuarioDTO = new UsuarioDTO(1, "Teste", "123", "teste@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(1, "Teste", false, 1);

        tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO);
    }

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificaSeLancaErroAoAtualizarTarefaComBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelSalvarAEdicaoDaTarefaException {
        when(conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());

        TarefaDTO tarefaDTO = new TarefaDTO(1, "Teste", false, 1);

        tarefaDAO.atualizarTarefa(tarefaDTO);
    }

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificaSeLancaErroAoApagarTarefaComBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelApagarATarefaException, NaoFoiPossivelCriarATarefaException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        when(conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());

        TarefaDTO tarefaDTO = new TarefaDTO(4, "asdjbasdb", false, 100);

        tarefaDAO.apagarTarefa(tarefaDTO);
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
}
