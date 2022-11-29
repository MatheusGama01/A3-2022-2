package CONTROLLER.TEST.INTEGRATION;

import CONTROLLER.ControllerTelaTarefa;
import DAO.TarefaDAO;
import DTO.TarefaDTO;
import EXCEPTIONS.NaoFoiPossivelApagarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelSalvarAEdicaoDaTarefaException;
import EXCEPTIONS.TarefaNaoAlteradaException;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class ControllerTelaTarefaTest {

    private ControllerTelaTarefa controller;

    @Mock
    private TarefaDAO tarefaDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.controller = new ControllerTelaTarefa(this.tarefaDAO);
    }

    @Test(expected = NaoFoiPossivelSalvarAEdicaoDaTarefaException.class)
    public void verificaSeSalvarEdicaoLancaErroQuandoNaoFoiSalvaAEdicao() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarATarefaException, TarefaNaoAlteradaException, NaoFoiPossivelSalvarAEdicaoDaTarefaException {
        when(this.tarefaDAO.atualizarTarefa(any(TarefaDTO.class))).thenThrow(new NaoFoiPossivelSalvarAEdicaoDaTarefaException());

        TarefaDTO tarefa = new TarefaDTO(1, "Teste", false, 100);
        String descricao = "Teste 1";
        Boolean status = true;

        this.controller.salvarEdicao(tarefa, descricao, status);
    }

    @Test(expected = NaoFoiPossivelApagarATarefaException.class)
    public void verificaSeApagarTarefaLancaErroQuandoNaoFoiApagadaATarefa() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarATarefaException {
        when(this.tarefaDAO.apagarTarefa(any(TarefaDTO.class))).thenThrow(new NaoFoiPossivelApagarATarefaException());

        TarefaDTO tarefa = new TarefaDTO(1, "Teste", false, 100);

        this.controller.apagarTarefa(tarefa);
    }
}
