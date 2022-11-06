package DAO.TEST.UNIT;

import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TarefaDAOTest {

    private TarefaDAO tarefaDAO;

    @Before
    public void init() {
        this.tarefaDAO = new TarefaDAO();
    }

    @Test
    public void deveListarTodasAsTarefasDoUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO(4, "Teste ListarTarefas", "123", "listarTarefas@email.com");
        TarefaDTO tarefaDTO1 = new TarefaDTO(50, "Teste 1", false, 4);
        TarefaDTO tarefaDTO2 = new TarefaDTO(51, "Teste 2", true, 4);

        ArrayList<TarefaDTO> tarefasListadas = tarefaDAO.listarTarefas(usuarioDTO);

        for (TarefaDTO tarefa : tarefasListadas) {

            if (tarefa.getId() == 50) {
                assertEquals(tarefaDTO1.getId(), tarefa.getId());
                assertEquals(tarefaDTO1.getDescricao(), tarefa.getDescricao());
                assertEquals(tarefaDTO1.getStatus(), tarefa.getStatus());
                assertEquals(tarefaDTO1.getIdUsuario(), tarefa.getIdUsuario());
            } else {
                assertEquals(tarefaDTO2.getId(), tarefa.getId());
                assertEquals(tarefaDTO2.getDescricao(), tarefa.getDescricao());
                assertEquals(tarefaDTO2.getStatus(), tarefa.getStatus());
                assertEquals(tarefaDTO2.getIdUsuario(), tarefa.getIdUsuario());
            }
        }
    }

    @Test
    public void deveListarUmaTarefaComSucesso() {
        UsuarioDTO usuarioDTO = new UsuarioDTO(4, "Teste ListarTarefas", "123", "listarTarefas@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(50, "Teste 1", false, 4);

        TarefaDTO tarefaListada = tarefaDAO.listarTarefa(tarefaDTO, usuarioDTO);

        assertEquals(tarefaDTO.getId(), tarefaListada.getId());
        assertEquals(tarefaDTO.getDescricao(), tarefaListada.getDescricao());
        assertEquals(tarefaDTO.getStatus(), tarefaListada.getStatus());
        assertEquals(tarefaDTO.getIdUsuario(), tarefaListada.getIdUsuario());
    }

    @Test
    public void deveCriarUmaTarefaComSucesso() {
        UsuarioDTO usuarioDTO = new UsuarioDTO(3, "Teste TarefaDAO", "123", "tarefadao@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(65, "Teste unitário", false);

        tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO);

        TarefaDTO tarefaSalva = tarefaDAO.listarTarefa(tarefaDTO, usuarioDTO);

        assertEquals(tarefaDTO.getDescricao(), tarefaSalva.getDescricao());
        assertEquals(tarefaDTO.getStatus(), tarefaSalva.getStatus());
    }

    @Test
    public void deveAtualizarUmaTarefaComSucesso() {
        UsuarioDTO usuarioDTO = new UsuarioDTO(3, "Teste TarefaDAO", "123", "tarefadao@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(65, "Teste", false);

        tarefaDAO.atualizarTarefa(tarefaDTO);

        TarefaDTO tarefaAtualizada = tarefaDAO.listarTarefa(tarefaDTO, usuarioDTO);
        assertEquals(tarefaDTO.getDescricao(), tarefaAtualizada.getDescricao());
    }

    @Test
    public void deveApagarUmaTarefaComSucesso() {
        UsuarioDTO usuarioDTO = new UsuarioDTO(3, "Teste TarefaDAO", "123", "tarefadao@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(65, "Teste", false);

        tarefaDAO.apagarTarefa(tarefaDTO);

        ArrayList<TarefaDTO> tarefa = new ArrayList<>();
        assertEquals(tarefa, tarefaDAO.listarTarefas(usuarioDTO));
    }

    /*
    private TarefaDTO pegaAPrimeiraTarefaDoBanco(UsuarioDTO usuarioDTO) {
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
     */
}
