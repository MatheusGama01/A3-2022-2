package CONTROLLER.TEST.UNIT;

import CONTROLLER.ControllerTelaHome;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuario;
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
    public void deveListarTodasAsTarefas() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario {
        UsuarioDTO usuarioDTO = new UsuarioDTO(4, "Teste ListarTarefas", "123", "listarTarefas@email.com");
        ArrayList<TarefaDTO> tarefas = new ArrayList<>();
        tarefas.add(new TarefaDTO(6, "Teste 1", false, 4));
        tarefas.add(new TarefaDTO(7, "Teste 2", true, 4));

        ArrayList<TarefaDTO> tarefasListadas = controller.listarTarefas(usuarioDTO);
        
        assertEquals(tarefas, tarefasListadas);
    }

    @Test
    public void deveListarTodasAsTarefasAFazer() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario {
        UsuarioDTO usuarioDTO = new UsuarioDTO(4, "Teste ListarTarefas", "123", "listarTarefas@email.com");
        ArrayList<TarefaDTO> tarefas = new ArrayList<>();
        tarefas.add(new TarefaDTO(6, "Teste 1", false, 4));

        ArrayList<TarefaDTO> tarefasListadas = controller.listarTarefasAFazer(usuarioDTO);

        assertEquals(tarefas, tarefasListadas);
    }

    @Test
    public void deveListarTodasAsTarefasFeitas() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario {
        UsuarioDTO usuarioDTO = new UsuarioDTO(4, "Teste ListarTarefas", "123", "listarTarefas@email.com");
        ArrayList<TarefaDTO> tarefas = new ArrayList<>();
        tarefas.add(new TarefaDTO(7, "Teste 2", true, 4));

        ArrayList<TarefaDTO> tarefasListadas = controller.listarTarefasFeitas(usuarioDTO);

        assertEquals(tarefas, tarefasListadas);
    }
}
