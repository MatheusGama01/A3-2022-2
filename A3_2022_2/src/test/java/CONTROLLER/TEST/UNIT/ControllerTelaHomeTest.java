package CONTROLLER.TEST.UNIT;

import CONTROLLER.ControllerTelaHome;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ControllerTelaHomeTest {

    private ControllerTelaHome controller;

    @Before
    public void init() {
        this.controller = new ControllerTelaHome();
    }

    @Test
    public void deveListarTodasAsTarefas() {
        UsuarioDTO usuarioDTO = new UsuarioDTO(4, "Teste ListarTarefas", "123", "listarTarefas@email.com");
        TarefaDTO tarefaDTO1 = new TarefaDTO(50, "Teste 1", false, 4);
        TarefaDTO tarefaDTO2 = new TarefaDTO(51, "Teste 2", true, 4);

        ArrayList<TarefaDTO> tarefasListadas = controller.listarTarefas(usuarioDTO);

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
    public void deveListarTodasAsTarefasAFazer() {
        UsuarioDTO usuarioDTO = new UsuarioDTO(4, "Teste ListarTarefas", "123", "listarTarefas@email.com");
        TarefaDTO tarefaDTO1 = new TarefaDTO(50, "Teste 1", false, 4);

        ArrayList<TarefaDTO> tarefasListadas = controller.listarTarefasAFazer(usuarioDTO);

        for (TarefaDTO tarefa : tarefasListadas) {
            assertEquals(tarefaDTO1.getId(), tarefa.getId());
            assertEquals(tarefaDTO1.getDescricao(), tarefa.getDescricao());
            assertEquals(tarefaDTO1.getStatus(), tarefa.getStatus());
            assertEquals(tarefaDTO1.getIdUsuario(), tarefa.getIdUsuario());
        }
    }

    @Test
    public void deveListarTodasAsTarefasFeitas() {
        UsuarioDTO usuarioDTO = new UsuarioDTO(4, "Teste ListarTarefas", "123", "listarTarefas@email.com");
        TarefaDTO tarefaDTO2 = new TarefaDTO(51, "Teste 2", true, 4);

        ArrayList<TarefaDTO> tarefasListadas = controller.listarTarefasFeitas(usuarioDTO);

        for (TarefaDTO tarefa : tarefasListadas) {
            assertEquals(tarefaDTO2.getId(), tarefa.getId());
            assertEquals(tarefaDTO2.getDescricao(), tarefa.getDescricao());
            assertEquals(tarefaDTO2.getStatus(), tarefa.getStatus());
            assertEquals(tarefaDTO2.getIdUsuario(), tarefa.getIdUsuario());
        }
    }
}
