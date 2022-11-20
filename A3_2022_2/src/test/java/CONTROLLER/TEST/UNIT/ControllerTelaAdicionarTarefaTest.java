package CONTROLLER.TEST.UNIT;

import CONTROLLER.ControllerTelaAdicionarTarefa;
import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelCriarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuario;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ControllerTelaAdicionarTarefaTest {

    private ControllerTelaAdicionarTarefa controller;

    @Before
    public void init() {
        this.controller = new ControllerTelaAdicionarTarefa();
    }

    @Test
    public void deveAdicionarATarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCriarATarefaException, NaoFoiPossivelListarAsTarefasDoUsuario {
        UsuarioDTO usuarioDTO = new UsuarioDTO(7, "Controller", "123", "controller@email.com");
        TarefaDTO tarefaDTO = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        TarefaDAO tarefaDAO = new TarefaDAO();
        String descricao = "Teste";

        controller.criarTarefa(descricao, usuarioDTO);

        TarefaDTO tarefaListada = tarefaDAO.listarTarefa(tarefaDTO);

        assertEquals(tarefaDTO, tarefaListada);
    }

    private TarefaDTO pegaAPrimeiraTarefaDoBanco(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario {
        TarefaDAO tarefaDAO = new TarefaDAO();
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
