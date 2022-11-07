package CONTROLLER.TEST.UNIT;

import CONTROLLER.ControllerTelaTarefa;
import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ControllerTelaTarefaTest {

    private ControllerTelaTarefa controller;
    private TarefaDAO tarefaDAO;

    @Before
    public void init() {
        this.controller = new ControllerTelaTarefa();
        tarefaDAO = new TarefaDAO();
    }

    @Test
    public void deveSalvarAEdicaoDaTarefaComSucesso() {
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
    public void deveApagarATarefaComSucesso(){
        UsuarioDTO usuarioDTO = new UsuarioDTO(6, "Teste ControllerTelaTarefa", "123", "testeControllerTelaTarefa@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(70, "Teste 1", false, 6);
        
        controller.apagarTarefa(tarefaDTO, usuarioDTO);
        
        TarefaDTO tarefaApagada = tarefaDAO.listarTarefa(tarefaDTO, usuarioDTO);
        assertEquals(0, tarefaApagada.getId());
    }
}