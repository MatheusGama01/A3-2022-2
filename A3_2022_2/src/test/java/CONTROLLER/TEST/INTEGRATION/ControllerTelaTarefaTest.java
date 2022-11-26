package CONTROLLER.TEST.INTEGRATION;

import CONTROLLER.ControllerTelaTarefa;
import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelSalvarAEdicaoDaTarefaException;
import EXCEPTIONS.TarefaNaoAlteradaException;
import VIEW.TelaHome;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

public class ControllerTelaTarefaTest {

    @Mock
    private ControllerTelaTarefa controller;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void verificaSeEdicaoFoiSalvaComSucesso() throws TarefaNaoAlteradaException, NaoFoiPossivelSalvarAEdicaoDaTarefaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        TarefaDTO tarefa = mock(TarefaDTO.class);
        UsuarioDTO usuario = mock(UsuarioDTO.class);
        TelaHome telaHome = mock(TelaHome.class);
        TarefaDAO tarefaDAO = mock(TarefaDAO.class);
        
        String descricao = "Teste";
        boolean status = true;

        controller.salvarEdicao(tarefa, descricao, status, usuario);
        
        verify(tarefaDAO).atualizarTarefa(tarefa);
        verify(telaHome).setVisible(true);
    }
}
