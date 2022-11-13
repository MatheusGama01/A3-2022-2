package CONTROLLER.TEST.UNIT;

import CONTROLLER.ControllerTelaTarefa;
import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelApagarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuario;
import EXCEPTIONS.NaoFoiPossivelSalvarAEdicaoDaTarefaException;
import EXCEPTIONS.TarefaNaoAlteradaException;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ControllerTelaTarefaTest {

    private ControllerTelaTarefa controller;
    private TarefaDAO tarefaDAO;

    @Before
    public void init() {
        this.controller = new ControllerTelaTarefa();
        this.tarefaDAO = new TarefaDAO();
    }

    @Test
    public void deveSalvarAEdicaoDaTarefaComSucesso() throws TarefaNaoAlteradaException, NaoFoiPossivelSalvarAEdicaoDaTarefaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario {
        UsuarioDTO usuarioDTO = new UsuarioDTO(6, "Teste ControllerTelaTarefa", "123", "testeControllerTelaTarefa@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(66, "Teste 2", true, 6);
        String descricao = "Teste";
        Boolean status = false;

        controller.salvarEdicao(tarefaDTO, descricao, status, usuarioDTO);

        TarefaDTO tarefaEditada = tarefaDAO.listarTarefa(tarefaDTO, usuarioDTO);

        assertEquals(tarefaDTO.getId(), tarefaEditada.getId());
        assertEquals(descricao, tarefaEditada.getDescricao());
        assertEquals(status, tarefaEditada.getStatus());
        assertEquals(tarefaDTO.getIdUsuario(), tarefaEditada.getIdUsuario());
    }

    @Test
    @DisplayName("O método salvarEdição deve lançar uma exceção quando não há nenhuma alteração na tarefa")
    public void verifcaSeSalvarEdicaoLancaException() throws TarefaNaoAlteradaException, NaoFoiPossivelSalvarAEdicaoDaTarefaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        UsuarioDTO usuarioDTO = new UsuarioDTO(6, "Teste ControllerTelaTarefa", "123", "testeControllerTelaTarefa@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(66, "Teste 2", true, 6);
        String descricao = "Teste 2";
        Boolean status = true;

        TarefaNaoAlteradaException TarefaNaoAlteradaException = assertThrows(TarefaNaoAlteradaException.class, () -> {
            controller.salvarEdicao(tarefaDTO, descricao, status, usuarioDTO);
        });
        
        assertEquals("A tarefa não foi alterada!\nFaça uma alteração para salvar a edição.", TarefaNaoAlteradaException.getMessage());
    }

    @Test
    public void deveApagarATarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarATarefaException, NaoFoiPossivelListarAsTarefasDoUsuario {
        UsuarioDTO usuarioDTO = new UsuarioDTO(6, "Teste ControllerTelaTarefa", "123", "testeControllerTelaTarefa@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(70, "Teste 1", false, 6);

        controller.apagarTarefa(tarefaDTO, usuarioDTO);

        TarefaDTO tarefaApagada = tarefaDAO.listarTarefa(tarefaDTO, usuarioDTO);
        assertEquals(0, tarefaApagada.getId());
    }
}
