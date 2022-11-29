package CONTROLLER.TEST.INTEGRATION;

import CONTROLLER.ControllerTelaAdicionarTarefa;
import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelCriarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class ControllerTelaAdicionarTarefaTest {

    private ControllerTelaAdicionarTarefa controller;

    @Mock
    private TarefaDAO tarefaDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.controller = new ControllerTelaAdicionarTarefa(tarefaDAO);
    }

    @Test(expected = NaoFoiPossivelCriarATarefaException.class)
    public void verificaSeCriarTarefaLacaExcecaoQuandoNaoForPossivelCriarUmaTarefa() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCriarATarefaException {
        when(tarefaDAO.criarTarefa(any(TarefaDTO.class), any(UsuarioDTO.class))).thenThrow(new NaoFoiPossivelCriarATarefaException());

        UsuarioDTO usuario = new UsuarioDTO(100, "Teste", "123", "teste@email.com");
        String descricao = "Teste";

        controller.criarTarefa(descricao, usuario);
    }
}
