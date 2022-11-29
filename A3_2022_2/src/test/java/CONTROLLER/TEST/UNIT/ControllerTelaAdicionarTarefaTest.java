package CONTROLLER.TEST.UNIT;

import CONTROLLER.ControllerTelaAdicionarTarefa;
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
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ControllerTelaAdicionarTarefaTest {

    private ControllerTelaAdicionarTarefa controller;

    @Before
    public void init() {
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        TarefaDAO tarefaDAO = new TarefaDAO(conexaoDAO);
        this.controller = new ControllerTelaAdicionarTarefa(tarefaDAO);
    }

    @After
    public void tearDown() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarOUsuarioException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelApagarATarefaException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);
        
       
        TarefaDAO tarefaDAO = new TarefaDAO(conexaoDAO);
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
    public void deveAdicionarATarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCriarATarefaException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelCadastrarUsuarioException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste Controller", "123", "controller@email.com");
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);
        usuarioDAO.cadastrarUsuario(usuarioDTO);
        UsuarioDTO usuario = carregarUsuario();

        String descricao = "Teste";

        this.controller.criarTarefa(descricao, usuario);

        TarefaDTO tarefaSalva = pegaAPrimeiraTarefaDoBanco(usuario);

        assertEquals(descricao, tarefaSalva.getDescricao());
        assertFalse(tarefaSalva.getStatus());
        assertEquals(usuario.getId(), tarefaSalva.getIdUsuario());
    }

    private UsuarioDTO carregarUsuario() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste Controller", "123", "controller@email.com");
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);

        UsuarioDTO usuarioRetornado = usuarioDAO.listarUsuario(usuarioDTO);

        return usuarioRetornado;
    }

    private TarefaDTO pegaAPrimeiraTarefaDoBanco(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        TarefaDAO tarefaDAO = new TarefaDAO(conexaoDAO);
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
