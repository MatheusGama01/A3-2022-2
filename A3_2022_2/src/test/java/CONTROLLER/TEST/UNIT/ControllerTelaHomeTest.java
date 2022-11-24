package CONTROLLER.TEST.UNIT;

import CONTROLLER.ControllerTelaHome;
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
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ControllerTelaHomeTest {

    private ControllerTelaHome controller;

    @Before
    public void init() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException {
        this.controller = new ControllerTelaHome();

        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste ControllerTelaHome", "123", "controller@email.com");
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        usuarioDAO.cadastrarUsuario(usuarioDTO);
    }

    @After
    public void tearDown() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarOUsuarioException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelApagarATarefaException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        TarefaDAO tarefaDAO = new TarefaDAO();
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
    public void deveListarTodasAsTarefas() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelCriarATarefaException {
        UsuarioDTO usuarioDTO = carregarUsuario();

        TarefaDTO tarefa1 = new TarefaDTO("Teste 1", false);
        TarefaDTO tarefa2 = new TarefaDTO("Teste 2", false);
        TarefaDAO tarefaDAO = new TarefaDAO();
        tarefaDAO.criarTarefa(tarefa1, usuarioDTO);
        tarefaDAO.criarTarefa(tarefa2, usuarioDTO);

        ArrayList<TarefaDTO> tarefasListadas = controller.listarTarefas(usuarioDTO);

        tarefa1.setIdUsuario(usuarioDTO.getId());
        tarefa2.setIdUsuario(usuarioDTO.getId());
        tarefa1.setId(tarefasListadas.get(0).getId());
        tarefa2.setId(tarefasListadas.get(1).getId());

        ArrayList<TarefaDTO> tarefas = new ArrayList<>();
        tarefas.add(tarefa1);
        tarefas.add(tarefa2);

        assertEquals(tarefas, tarefasListadas);
    }

    @Test
    public void deveListarTodasAsTarefasAFazer() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelCriarATarefaException, NaoFoiPossivelSalvarAEdicaoDaTarefaException {
        UsuarioDTO usuarioDTO = carregarUsuario();

        TarefaDTO tarefa1 = new TarefaDTO("Teste 1", false);
        TarefaDTO tarefa2 = new TarefaDTO("Teste 2", false);
        TarefaDAO tarefaDAO = new TarefaDAO();
        tarefaDAO.criarTarefa(tarefa1, usuarioDTO);
        tarefaDAO.criarTarefa(tarefa2, usuarioDTO);
        
        TarefaDTO tarefaDTO = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        tarefa1.setStatus(true);
        tarefa1.setId(tarefaDTO.getId());
        tarefaDAO.atualizarTarefa(tarefa1);

        ArrayList<TarefaDTO> tarefasListadas = controller.listarTarefasAFazer(usuarioDTO);

        tarefa2.setIdUsuario(usuarioDTO.getId());
        tarefa2.setId(tarefasListadas.get(0).getId());

        ArrayList<TarefaDTO> tarefas = new ArrayList<>();
        tarefas.add(tarefa2);

        assertEquals(tarefas, tarefasListadas);
    }
    
    @Test
    public void deveListarTodasAsTarefasFeitas() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelCriarATarefaException, NaoFoiPossivelSalvarAEdicaoDaTarefaException {
        UsuarioDTO usuarioDTO = carregarUsuario();

        TarefaDTO tarefa1 = new TarefaDTO("Teste 1", false);
        TarefaDTO tarefa2 = new TarefaDTO("Teste 2", false);
        TarefaDAO tarefaDAO = new TarefaDAO();
        tarefaDAO.criarTarefa(tarefa1, usuarioDTO);
        tarefaDAO.criarTarefa(tarefa2, usuarioDTO);
        
        TarefaDTO tarefaDTO = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        tarefa1.setStatus(true);
        tarefa1.setId(tarefaDTO.getId());
        tarefaDAO.atualizarTarefa(tarefa1);

        ArrayList<TarefaDTO> tarefasListadas = controller.listarTarefasFeitas(usuarioDTO);

        tarefa1.setIdUsuario(usuarioDTO.getId());
        tarefa1.setId(tarefasListadas.get(0).getId());

        ArrayList<TarefaDTO> tarefas = new ArrayList<>();
        tarefas.add(tarefa1);

        assertEquals(tarefas, tarefasListadas);
    }

    private UsuarioDTO carregarUsuario() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste ControllerTelaHome", "123", "controller@email.com");
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        UsuarioDTO usuarioRetornado = usuarioDAO.listarUsuario(usuarioDTO);

        return usuarioRetornado;
    }

    private TarefaDTO pegaAPrimeiraTarefaDoBanco(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
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
