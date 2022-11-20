package DAO.TEST.UNIT;

import DAO.TarefaDAO;
import DAO.UsuarioDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelApagarATarefaException;
import EXCEPTIONS.NaoFoiPossivelApagarOUsuarioException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelCriarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuario;
import EXCEPTIONS.NaoFoiPossivelListarOUsuarioException;
import EXCEPTIONS.NaoFoiPossivelSalvarAEdicaoDaTarefaException;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TarefaDAOTest {

    private TarefaDAO tarefaDAO;

    @Before
    public void init() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException {
        this.tarefaDAO = new TarefaDAO();

        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste TarefaDAO", "123", "tarefadao@email.com");
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        usuarioDAO.cadastrarUsuario(usuarioDTO);
    }

    @After
    public void tearDown() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarOUsuarioException, NaoFoiPossivelListarAsTarefasDoUsuario, NaoFoiPossivelApagarATarefaException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

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
    public void deveCriarUmaTarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario, NaoFoiPossivelCriarATarefaException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        TarefaDTO tarefaDTO = new TarefaDTO("Teste unitário", false);

        tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO);

        TarefaDTO tarefaSalva = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        tarefaDTO.setId(tarefaSalva.getId());
        tarefaDTO.setIdUsuario(usuarioDTO.getId());

        assertEquals(tarefaDTO, tarefaSalva);
    }

    @Test
    public void deveListarTodasAsTarefasDoUsuario() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelCriarATarefaException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        TarefaDTO tarefa1 = new TarefaDTO("Teste 1", false);
        TarefaDTO tarefa2 = new TarefaDTO("Teste 2", false);
        tarefaDAO.criarTarefa(tarefa1, usuarioDTO);
        tarefaDAO.criarTarefa(tarefa2, usuarioDTO);

        ArrayList<TarefaDTO> tarefasListadas = tarefaDAO.listarTarefas(usuarioDTO);

        ArrayList<TarefaDTO> tarefas = new ArrayList<>();
        tarefa1.setId(tarefasListadas.get(0).getId());
        tarefa2.setId(tarefasListadas.get(1).getId());
        tarefa1.setIdUsuario(usuarioDTO.getId());
        tarefa2.setIdUsuario(usuarioDTO.getId());
        tarefas.add(tarefa1);
        tarefas.add(tarefa2);

        assertEquals(tarefas, tarefasListadas);
    }
    
    @Test
    public void deveListarUmaTarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelCriarATarefaException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        TarefaDTO tarefaDTO = new TarefaDTO("Teste 1", false);
        tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO);
        
        TarefaDTO tarefaApoio = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        tarefaDTO.setId(tarefaApoio.getId());

        TarefaDTO tarefaListada = tarefaDAO.listarTarefa(tarefaDTO);
        
        tarefaDTO.setId(tarefaListada.getId());
        tarefaDTO.setIdUsuario(usuarioDTO.getId());
        
        assertEquals(tarefaDTO, tarefaListada);
    }
    
    @Test
    public void deveAtualizarUmaTarefaComSucesso() throws NaoFoiPossivelSalvarAEdicaoDaTarefaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelCriarATarefaException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        TarefaDTO tarefaDTO = new TarefaDTO("Teste 1", false);
        tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO);
        
        TarefaDTO tarefa = new TarefaDTO("Teste", false);
        TarefaDTO tarefaApoio = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        tarefa.setId(tarefaApoio.getId());
        tarefa.setIdUsuario(usuarioDTO.getId());
        
        tarefaDAO.atualizarTarefa(tarefa);

        TarefaDTO tarefaAtualizada = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        
        assertEquals(tarefa, tarefaAtualizada);
    }
    
    @Test
    public void deveApagarUmaTarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarATarefaException, NaoFoiPossivelListarAsTarefasDoUsuario, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelCriarATarefaException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        TarefaDTO tarefa = new TarefaDTO("Teste", false);
        tarefaDAO.criarTarefa(tarefa, usuarioDTO);
        
        TarefaDTO tarefaDTO = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        tarefa.setId(tarefaDTO.getId());

        tarefaDAO.apagarTarefa(tarefaDTO);
        
        TarefaDTO tarefaDTO2 = new TarefaDTO(0, null, null, 0);
        TarefaDTO tarefaApagada = tarefaDAO.listarTarefa(tarefa);
        
        assertEquals(tarefaDTO2, tarefaApagada);
    }

    private UsuarioDTO carregarUsuario() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste TarefaDAO", "123", "tarefadao@email.com");
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        UsuarioDTO usuarioRetornado = usuarioDAO.listarUsuario(usuarioDTO);

        return usuarioRetornado;
    }

    private TarefaDTO pegaAPrimeiraTarefaDoBanco(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario {
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
