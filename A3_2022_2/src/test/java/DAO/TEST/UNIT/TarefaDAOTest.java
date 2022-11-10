package DAO.TEST.UNIT;

import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelApagarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelSalvarAEdicaoDaTarefaException;
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
    public void deveListarTodasAsTarefasDoUsuario() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        UsuarioDTO usuarioDTO = new UsuarioDTO(4, "Teste ListarTarefas", "123", "listarTarefas@email.com");
        ArrayList<TarefaDTO> tarefas = new ArrayList<>();
        tarefas.add(new TarefaDTO(50, "Teste 1", false, 4));
        tarefas.add(new TarefaDTO(51, "Teste 2", true, 4));

        ArrayList<TarefaDTO> tarefasListadas = tarefaDAO.listarTarefas(usuarioDTO);

        assertEquals(tarefas, tarefasListadas);
    }

    @Test
    public void deveListarUmaTarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        UsuarioDTO usuarioDTO = new UsuarioDTO(4, "Teste ListarTarefas", "123", "listarTarefas@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(50, "Teste 1", false, 4);

        TarefaDTO tarefaListada = tarefaDAO.listarTarefa(tarefaDTO, usuarioDTO);

        assertEquals(tarefaDTO, tarefaListada);
    }

    @Test
    public void deveCriarUmaTarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        UsuarioDTO usuarioDTO = new UsuarioDTO(3, "Teste TarefaDAO", "123", "tarefadao@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(64, "Teste unitário", false);

        tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO);

        TarefaDTO tarefaSalva = tarefaDAO.listarTarefa(tarefaDTO, usuarioDTO);

        assertEquals(tarefaDTO.getDescricao(), tarefaSalva.getDescricao());
        assertEquals(tarefaDTO.getStatus(), tarefaSalva.getStatus());
    }

    @Test
    public void deveAtualizarUmaTarefaComSucesso() throws NaoFoiPossivelSalvarAEdicaoDaTarefaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        UsuarioDTO usuarioDTO = new UsuarioDTO(3, "Teste TarefaDAO", "123", "tarefadao@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(62, "Teste", false);

        tarefaDAO.atualizarTarefa(tarefaDTO);

        TarefaDTO tarefaAtualizada = tarefaDAO.listarTarefa(tarefaDTO, usuarioDTO);
        assertEquals(tarefaDTO.getDescricao(), tarefaAtualizada.getDescricao());
    }

    @Test
    public void deveApagarUmaTarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarATarefaException {
        UsuarioDTO usuarioDTO = new UsuarioDTO(3, "Teste TarefaDAO", "123", "tarefadao@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(64, "Teste", false);

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
