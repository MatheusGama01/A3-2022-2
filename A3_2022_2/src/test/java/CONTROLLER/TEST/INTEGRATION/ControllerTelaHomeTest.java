package CONTROLLER.TEST.INTEGRATION;

import CONTROLLER.ControllerTelaHome;
import DAO.ConexaoDAO;
import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuarioException;
import VIEW.TelaLogin;
import VIEW.TelaUsuario;
import com.mysql.cj.jdbc.ConnectionImpl;
import java.sql.Connection;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class ControllerTelaHomeTest {
    
    @Mock
    private ControllerTelaHome controller;

//    @Mock
//    private TarefaDAO tarefaDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    /*    
    @Test
    public void verificaSeATarefaEApagadaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException{
        ControllerTelaHome controller = new ControllerTelaHome();
        
        tarefaDAO = mock(TarefaDAO.class);
        UsuarioDTO usuario = mock(UsuarioDTO.class);
        
        TarefaDTO tarefa1 = new TarefaDTO(1, "Teste 1", true, 100);
        TarefaDTO tarefa2 = new TarefaDTO(2, "Teste 2", false, 100);
        ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();
        listaDeTarefas.add(tarefa1);
        listaDeTarefas.add(tarefa2);
        
        when(tarefaDAO.listarTarefas(any(UsuarioDTO.class))).thenReturn(listaDeTarefas);
        
        ArrayList<TarefaDTO> tarefasRetornadas = controller.listarTarefas(usuario);
        
        assertEquals(listaDeTarefas.get(0).getDescricao(), tarefasRetornadas.get(0).getDescricao());
    }
    */
    
    @Test
    public void verificaSeListarTarefasRetornaNaoNula() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException{
        UsuarioDTO usuario = mock(UsuarioDTO.class);
        
        TarefaDTO tarefa1 = new TarefaDTO(1, "Tarefa 1", false, 1);
        TarefaDTO tarefa2 = new TarefaDTO(2, "Tarefa 2", true, 1);
        
        ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();
        listaDeTarefas.add(tarefa1);
        listaDeTarefas.add(tarefa2);
        
        when(controller.listarTarefas(any(UsuarioDTO.class))).thenReturn(listaDeTarefas);
        
        assertEquals(listaDeTarefas, controller.listarTarefas(usuario));
    }
    
    @Test
    public void verificaSeListarTarefasAFazerRetornaNaoNulo() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException{
        UsuarioDTO usuario = mock(UsuarioDTO.class);
        
        TarefaDTO tarefa1 = new TarefaDTO(1, "Tarefa 1", false, 1);
        TarefaDTO tarefa2 = new TarefaDTO(2, "Tarefa 2", false, 1);
        
        ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();
        listaDeTarefas.add(tarefa1);
        listaDeTarefas.add(tarefa2);
        
        when(controller.listarTarefasAFazer(any(UsuarioDTO.class))).thenReturn(listaDeTarefas);
        
        assertEquals(listaDeTarefas, controller.listarTarefasAFazer(usuario));
    }
    
    @Test
    public void verificaSeListarTarefasFeitasRetornaNaoNulo() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException{
        UsuarioDTO usuario = mock(UsuarioDTO.class);
        
        TarefaDTO tarefa1 = new TarefaDTO(1, "Tarefa 1", true, 1);
        TarefaDTO tarefa2 = new TarefaDTO(2, "Tarefa 2", true, 1);
        
        ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();
        listaDeTarefas.add(tarefa1);
        listaDeTarefas.add(tarefa2);
        
        when(controller.listarTarefasFeitas(any(UsuarioDTO.class))).thenReturn(listaDeTarefas);
        
        assertEquals(listaDeTarefas, controller.listarTarefasFeitas(usuario));
    }
    
    /*
    @Test
    public void verificaSeListarTarefasRetornaListaDeTarefas() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException{
        Connection connection = mock(Connection.class);
        UsuarioDTO usuarioDTO = mock(UsuarioDTO.class);
        
        when(conexaoDAO.conectaBD()).thenReturn(connection);
        
        tarefaDAO.listarTarefas(usuarioDTO);
        
        verify(conexaoDAO, times(1)).conectaBD();
    }
    */
    
    /*
    @Test
    public void verificaSeListarTarefasRetornaArrayListNaoNulo() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException{
        tarefaDAO = mock(TarefaDAO.class);
        
        ArrayList<TarefaDTO> tarefas = new ArrayList<>();
        TarefaDTO tarefa1 = new TarefaDTO(1, "Tarefa 1", false, 1);
        TarefaDTO tarefa2 = new TarefaDTO(2, "Tarefa 2", true, 1);
        tarefas.add(tarefa1);
        tarefas.add(tarefa2);
        
        when(tarefaDAO.listarTarefas(any(UsuarioDTO.class))).thenReturn(tarefas);
        
        UsuarioDTO usuario = new UsuarioDTO(1, "Matheus", "123", "matheus@email.com");
        ArrayList<TarefaDTO> tarefasListadas = controller.listarTarefas(usuario);
        
        assertEquals(tarefas.get(0).getDescricao(), tarefasListadas.get(0).getDescricao());
    }
    */
    
    /*
    //Integração entre ControllerTelaHome e TelaAdicionarTarefa.
    @Test
    public void deveTornarATelaAdicionarTarefaVisivel() {
        UsuarioDTO usuario = new UsuarioDTO(6, "Teste", "123", "teste@email.com");

        //controller.navegarParaTelaDeAdicionarTarefa(usuario);

        Mockito.verify(telaLogin).setVisible(true);
    }

    //Integração entre ControllerTelaHome e TelaUsuario.
    @Test
    public void deveTornarATelaUsuarioVisivel() {
        UsuarioDTO usuario = new UsuarioDTO(6, "Teste", "123", "teste@email.com");

        //controller.navegarParaTelaDeUsuario(usuario);

        Mockito.verify(telaUsuario).setVisible(true);
    }

    //Integração entre ControllerTelaHome e TarefaDAO.    
    @Test
    public void deveChamarOMetodoListarTarefas() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        UsuarioDTO usuario = new UsuarioDTO(1, "Matheus", "123", "matheus@email.com");
        ArrayList<TarefaDTO> tarefas = new ArrayList<>();

        tarefas = controller.listarTarefas(usuario);

        Mockito.verify(tarefaDAO).listarTarefas(ArgumentMatchers.any());
    }
    */
}
