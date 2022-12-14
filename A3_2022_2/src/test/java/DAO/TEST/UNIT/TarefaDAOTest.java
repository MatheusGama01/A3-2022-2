package DAO.TEST.UNIT;

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
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TarefaDAOTest {

    private TarefaDAO tarefaDAO;

    @Before
    public void init() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException {
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        this.tarefaDAO = new TarefaDAO(conexaoDAO);
        
        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste TarefaDAO", "123", "tarefadao@email.com");
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);

        usuarioDAO.cadastrarUsuario(usuarioDTO);
    }

    @After
    public void tearDown() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarOUsuarioException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelApagarATarefaException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);

        ArrayList<TarefaDTO> tarefas = this.tarefaDAO.listarTarefas(usuarioDTO);

        for (TarefaDTO tarefa : tarefas) {
            this.tarefaDAO.apagarTarefa(tarefa);
        }

        usuarioDAO.apagarUsuario(usuarioDTO);
        UsuarioDTO usuarioRetornado = usuarioDAO.listarUsuario(usuarioDTO);
        
        UsuarioDTO usuarioDTO2 = new UsuarioDTO(0, null, null, null);
        assertEquals(usuarioDTO2, usuarioRetornado);
    }

    @Test
    public void deveCriarUmaTarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelCriarATarefaException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        TarefaDTO tarefaDTO = new TarefaDTO("Teste unit??rio", false);

        this.tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO);

        TarefaDTO tarefaSalva = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        tarefaDTO.setId(tarefaSalva.getId());
        tarefaDTO.setIdUsuario(usuarioDTO.getId());

        assertEquals(tarefaDTO, tarefaSalva);
    }
    
    @Test
    public void deveListarTodasAsTarefasDoUsuario() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelCriarATarefaException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        TarefaDTO tarefa1 = new TarefaDTO("Teste 1", false);
        TarefaDTO tarefa2 = new TarefaDTO("Teste 2", false);
        this.tarefaDAO.criarTarefa(tarefa1, usuarioDTO);
        this.tarefaDAO.criarTarefa(tarefa2, usuarioDTO);

        ArrayList<TarefaDTO> tarefasListadas = this.tarefaDAO.listarTarefas(usuarioDTO);

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
    public void deveListarUmaTarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelCriarATarefaException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        TarefaDTO tarefaDTO = new TarefaDTO("Teste 1", false);
        this.tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO);
        
        TarefaDTO tarefaApoio = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        tarefaDTO.setId(tarefaApoio.getId());

        TarefaDTO tarefaListada = this.tarefaDAO.listarTarefa(tarefaDTO);
        
        tarefaDTO.setId(tarefaListada.getId());
        tarefaDTO.setIdUsuario(usuarioDTO.getId());
        
        assertEquals(tarefaDTO, tarefaListada);
    }
    
    @Test
    public void deveAtualizarUmaTarefaComSucesso() throws NaoFoiPossivelSalvarAEdicaoDaTarefaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelCriarATarefaException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        TarefaDTO tarefaDTO = new TarefaDTO("Teste 1", false);
        this.tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO);
        
        TarefaDTO tarefa = new TarefaDTO("Teste", false);
        TarefaDTO tarefaApoio = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        tarefa.setId(tarefaApoio.getId());
        tarefa.setIdUsuario(usuarioDTO.getId());
        
        this.tarefaDAO.atualizarTarefa(tarefa);

        TarefaDTO tarefaAtualizada = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        
        assertEquals(tarefa, tarefaAtualizada);
    }
    
    @Test
    public void deveApagarUmaTarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarATarefaException, NaoFoiPossivelListarAsTarefasDoUsuarioException, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelCriarATarefaException {
        UsuarioDTO usuarioDTO = carregarUsuario();
        TarefaDTO tarefa = new TarefaDTO("Teste", false);
        this.tarefaDAO.criarTarefa(tarefa, usuarioDTO);
        
        TarefaDTO tarefaDTO = pegaAPrimeiraTarefaDoBanco(usuarioDTO);
        tarefa.setId(tarefaDTO.getId());

        this.tarefaDAO.apagarTarefa(tarefaDTO);
        
        TarefaDTO tarefaDTO2 = new TarefaDTO(0, null, null, 0);
        TarefaDTO tarefaApagada = this.tarefaDAO.listarTarefa(tarefa);
        
        assertEquals(tarefaDTO2, tarefaApagada);
    }

    private UsuarioDTO carregarUsuario() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste TarefaDAO", "123", "tarefadao@email.com");
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexaoDAO);

        UsuarioDTO usuarioRetornado = usuarioDAO.listarUsuario(usuarioDTO);

        return usuarioRetornado;
    }

    private TarefaDTO pegaAPrimeiraTarefaDoBanco(UsuarioDTO usuarioDTO) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        ArrayList<TarefaDTO> tarefas = this.tarefaDAO.listarTarefas(usuarioDTO);
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
