package CONTROLLER.TEST.UNIT;

import CONTROLLER.ControllerTelaTarefa;
import DAO.ConexaoDAO;
import DAO.TarefaDAO;
import DAO.UsuarioDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelApagarATarefaException;
import EXCEPTIONS.NaoFoiPossivelApagarOUsuarioException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelCriarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuarioException;
import EXCEPTIONS.NaoFoiPossivelListarOUsuarioException;
import EXCEPTIONS.NaoFoiPossivelSalvarAEdicaoDaTarefaException;
import EXCEPTIONS.TarefaNaoAlteradaException;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ControllerTelaTarefaTest {

    private ControllerTelaTarefa controller;
    private TarefaDAO tarefaDAO;

    @Before
    public void init() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException, NaoFoiPossivelCriarATarefaException, NaoFoiPossivelListarOUsuarioException {
        
        
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        this.tarefaDAO = new TarefaDAO(conexaoDAO);
        this.controller = new ControllerTelaTarefa(tarefaDAO);

        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste ControllerTelaTarefa", "123", "testeControllerTelaTarefa@email.com");
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);
        usuarioDAO.cadastrarUsuario(usuarioDTO);
        UsuarioDTO usuarioDTO2 = carregarUsuario();
        
        TarefaDTO tarefaDTO = new TarefaDTO("Teste 1", false);
        tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO2);
    }
    
    @After
    public void tearDown() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelApagarATarefaException, NaoFoiPossivelApagarOUsuarioException{
        UsuarioDTO usuarioDTO = carregarUsuario();
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);
        
        ArrayList<TarefaDTO> tarefas = tarefaDAO.listarTarefas(usuarioDTO);
        
        for (TarefaDTO tarefa : tarefas) {
            tarefaDAO.apagarTarefa(tarefa);
        }
        
        usuarioDAO.apagarUsuario(usuarioDTO);
        UsuarioDTO usuarioRetornado = usuarioDAO.listarUsuario(usuarioDTO);
        
        UsuarioDTO usuarioDTO2 = new UsuarioDTO(0, null, null, null);
        assertEquals(usuarioDTO2, usuarioRetornado);
    }
    
    @Test
    public void deveSalvarAEdicaoDaTarefaComSucesso() throws TarefaNaoAlteradaException, NaoFoiPossivelSalvarAEdicaoDaTarefaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        TarefaDTO tarefaDTO = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        
        String descricao = "Teste";
        Boolean status = true;

        controller.salvarEdicao(tarefaDTO, descricao, status);

        TarefaDTO tarefaEditada = tarefaDAO.listarTarefa(tarefaDTO);

        assertEquals(tarefaDTO.getId(), tarefaEditada.getId());
        assertEquals(descricao, tarefaEditada.getDescricao());
        assertEquals(status, tarefaEditada.getStatus());
        assertEquals(tarefaDTO.getIdUsuario(), tarefaEditada.getIdUsuario());
    }
    
    @Test
    @DisplayName("O método salvarEdição deve lançar uma exceção quando não há nenhuma alteração na tarefa")
    public void verifcaSeSalvarEdicaoLancaException() throws TarefaNaoAlteradaException, NaoFoiPossivelSalvarAEdicaoDaTarefaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        TarefaDTO tarefaDTO = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        
        String descricao = "Teste 1";
        Boolean status = false;

        TarefaNaoAlteradaException TarefaNaoAlteradaException = assertThrows(TarefaNaoAlteradaException.class, () -> {
            controller.salvarEdicao(tarefaDTO, descricao, status);
        });

        assertEquals("A tarefa não foi alterada!\nFaça uma alteração para salvar a edição.", TarefaNaoAlteradaException.getMessage());
    }
    
    @Test
    public void deveApagarATarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarATarefaException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        TarefaDTO tarefaDTO = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        
        controller.apagarTarefa(tarefaDTO);

        TarefaDTO tarefaApagada = tarefaDAO.listarTarefa(tarefaDTO);
        TarefaDTO tarefaDTO2 = new TarefaDTO(0, null, null, 0);
        
        assertEquals(tarefaDTO2, tarefaApagada);
    }
    
    private UsuarioDTO carregarUsuario() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste ControllerTelaTarefa", "123", "testeControllerTelaTarefa@email.com");
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);

        UsuarioDTO usuarioRetornado = usuarioDAO.listarUsuario(usuarioDTO);

        return usuarioRetornado;
    }
    
    private TarefaDTO pegaAPrimeiraTarefaDoBanco(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
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
