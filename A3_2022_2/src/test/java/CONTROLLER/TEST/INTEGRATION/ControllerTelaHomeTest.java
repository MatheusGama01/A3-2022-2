package CONTROLLER.TEST.INTEGRATION;

import CONTROLLER.ControllerTelaHome;
import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuarioException;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class ControllerTelaHomeTest {

    private ControllerTelaHome controller;

    @Mock
    private TarefaDAO tarefaDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.controller = new ControllerTelaHome(tarefaDAO);
    }

    @Test
    public void verificaSeListarTarefasRetornaCorretamenteAListaDeTarefas() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        UsuarioDTO usuario = new UsuarioDTO(100, "Teste", "123", "teste@email.com");

        TarefaDTO tarefa1 = new TarefaDTO(1, "Teste 1", true, 100);
        TarefaDTO tarefa2 = new TarefaDTO(2, "Teste 2", false, 100);
        ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();
        listaDeTarefas.add(tarefa1);
        listaDeTarefas.add(tarefa2);

        when(tarefaDAO.listarTarefas(any(UsuarioDTO.class))).thenReturn(listaDeTarefas);

        ArrayList<TarefaDTO> tarefasRetornadas = controller.listarTarefas(usuario);

        assertEquals(listaDeTarefas, tarefasRetornadas);
    }

    @Test(expected = NaoFoiPossivelListarAsTarefasDoUsuarioException.class)
    public void verificaSeListarTarefasLancaErroQuandoNaoForPossivelListarAsTarefas() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        when(tarefaDAO.listarTarefas(any(UsuarioDTO.class))).thenThrow(new NaoFoiPossivelListarAsTarefasDoUsuarioException());

        UsuarioDTO usuario = new UsuarioDTO(1, "Teste", "123", "teste@email.com");

        controller.listarTarefas(usuario);
    }

    @Test
    public void verificaSeListarTarefasAFazerRetornaCorretamenteAListaDeTarefas() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        UsuarioDTO usuario = new UsuarioDTO(100, "Teste", "123", "teste@email.com");

        TarefaDTO tarefa1 = new TarefaDTO(1, "Teste 1", true, 100);
        TarefaDTO tarefa2 = new TarefaDTO(2, "Teste 2", false, 100);
        ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();
        listaDeTarefas.add(tarefa1);
        listaDeTarefas.add(tarefa2);

        ArrayList<TarefaDTO> listaDeTarefasAFazer = new ArrayList<>();
        listaDeTarefasAFazer.add(tarefa2);

        when(tarefaDAO.listarTarefas(any(UsuarioDTO.class))).thenReturn(listaDeTarefas);

        ArrayList<TarefaDTO> tarefasRetornadas = controller.listarTarefasAFazer(usuario);

        assertEquals(listaDeTarefasAFazer, tarefasRetornadas);
    }

    @Test(expected = NaoFoiPossivelListarAsTarefasDoUsuarioException.class)
    public void verificaSeListarTarefasAFazerLancaErroQuandoNaoForPossivelListarAsTarefas() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        when(tarefaDAO.listarTarefas(any(UsuarioDTO.class))).thenThrow(new NaoFoiPossivelListarAsTarefasDoUsuarioException());

        UsuarioDTO usuario = new UsuarioDTO(1, "Teste", "123", "teste@email.com");

        controller.listarTarefasAFazer(usuario);
    }

    @Test
    public void verificaSeListarTarefasFeitasRetornaCorretamenteAListaDeTarefas() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        UsuarioDTO usuario = new UsuarioDTO(100, "Teste", "123", "teste@email.com");

        TarefaDTO tarefa1 = new TarefaDTO(1, "Teste 1", true, 100);
        TarefaDTO tarefa2 = new TarefaDTO(2, "Teste 2", false, 100);
        ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();
        listaDeTarefas.add(tarefa1);
        listaDeTarefas.add(tarefa2);

        ArrayList<TarefaDTO> listaDeTarefasFeitas = new ArrayList<>();
        listaDeTarefasFeitas.add(tarefa1);

        when(tarefaDAO.listarTarefas(any(UsuarioDTO.class))).thenReturn(listaDeTarefas);

        ArrayList<TarefaDTO> tarefasRetornadas = controller.listarTarefasFeitas(usuario);

        assertEquals(listaDeTarefasFeitas, tarefasRetornadas);
    }

    @Test(expected = NaoFoiPossivelListarAsTarefasDoUsuarioException.class)
    public void verificaSeListarTarefasFeitasLancaErroQuandoNaoForPossivelListarAsTarefas() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        when(tarefaDAO.listarTarefas(any(UsuarioDTO.class))).thenThrow(new NaoFoiPossivelListarAsTarefasDoUsuarioException());

        UsuarioDTO usuario = new UsuarioDTO(1, "Teste", "123", "teste@email.com");

        controller.listarTarefasFeitas(usuario);
    }
}
