package DAO.TEST.UNIT;

import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelApagarATarefaException;
import EXCEPTIONS.NaoFoiPossivelCriarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuario;
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
    public void deveListarTodasAsTarefasDoUsuario() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario {
        UsuarioDTO usuarioDTO = new UsuarioDTO(4, "Teste ListarTarefas", "123", "listarTarefas@email.com");
        ArrayList<TarefaDTO> tarefas = new ArrayList<>();
        tarefas.add(new TarefaDTO(6, "Teste 1", false, 4));
        tarefas.add(new TarefaDTO(7, "Teste 2", true, 4));

        ArrayList<TarefaDTO> tarefasListadas = tarefaDAO.listarTarefas(usuarioDTO);

        assertEquals(tarefas, tarefasListadas);
    }

    /*
    @Test
    @DisplayName("Deve lançar uma exceção caso o usuário passado não exista no banco de dados")
    public void listarTarefasDeveLancarExceptionSeOUsuarioForInvalido(){
        UsuarioDTO usuarioDTO = new UsuarioDTO(100, "Teste ListarTarefas", "123", "listarTarefas@email.com");
        
        NaoFoiPossivelListarAsTarefasDoUsuario NaoFoiPossivelListarAsTarefasDoUsuario = assertThrows(NaoFoiPossivelListarAsTarefasDoUsuario.class, () -> {
            tarefaDAO.listarTarefas(usuarioDTO);
        });
        
        assertEquals(NaoFoiPossivelListarAsTarefasDoUsuario.getMessage(), "Desculpe, houve um erro inesperado e não conseguimos encontrar suas tarefas!");
    }
     */
    @Test
    public void deveListarUmaTarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario {
        UsuarioDTO usuarioDTO = new UsuarioDTO(4, "Teste ListarTarefas", "123", "listarTarefas@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(6, "Teste 1", false, 4);

        TarefaDTO tarefaListada = tarefaDAO.listarTarefa(tarefaDTO, usuarioDTO);

        assertEquals(tarefaDTO, tarefaListada);
    }

    @Test
    public void deveCriarUmaTarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario, NaoFoiPossivelCriarATarefaException {
        UsuarioDTO usuarioDTO = new UsuarioDTO(3, "Teste TarefaDAO", "123", "tarefadao@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO("Teste unitário", false);

        tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO);

        //TarefaDTO tarefaSalva = tarefaDAO.listarTarefa(tarefaDTO, usuarioDTO);
        TarefaDTO tarefaSalva = pegaAPrimeiraTarefaDoBanco(usuarioDTO);

        assertEquals(tarefaDTO.getDescricao(), tarefaSalva.getDescricao());
        assertEquals(tarefaDTO.getStatus(), tarefaSalva.getStatus());
    }

    @Test
    public void deveAtualizarUmaTarefaComSucesso() throws NaoFoiPossivelSalvarAEdicaoDaTarefaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario {
        UsuarioDTO usuarioDTO = new UsuarioDTO(3, "Teste TarefaDAO", "123", "tarefadao@email.com");
        //TarefaDTO tarefaDTO = new TarefaDTO(62, "Teste", false);
        TarefaDTO tarefaDTO = pegaAPrimeiraTarefaDoBanco(usuarioDTO);

        tarefaDAO.atualizarTarefa(tarefaDTO);

        TarefaDTO tarefaAtualizada = tarefaDAO.listarTarefa(tarefaDTO, usuarioDTO);
        assertEquals(tarefaDTO.getDescricao(), tarefaAtualizada.getDescricao());
    }

    /*
    @Test
    @DisplayName("Deve lançar uma exceção se a tarefa passada para atualizarTarefa não existir no banco de dados")
    public void atualizarTarefaDeveLancarExceptionSeTarefaNaoExistir(){
        TarefaDTO tarefaDTO = new TarefaDTO(-1, "Teste", false);
        
        NaoFoiPossivelSalvarAEdicaoDaTarefaException NaoFoiPossivelSalvarAEdicaoDaTarefaException = assertThrows(NaoFoiPossivelSalvarAEdicaoDaTarefaException.class, () -> {
            tarefaDAO.atualizarTarefa(tarefaDTO);
        });
        
        assertEquals("Desculpe, houve um erro inesperado e não conseguimos salvar a edição da tarefa!\nPor favor, tente novamente mais tarde!", NaoFoiPossivelSalvarAEdicaoDaTarefaException.getMessage());
    }
     */
    @Test
    public void deveApagarUmaTarefaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarATarefaException, NaoFoiPossivelListarAsTarefasDoUsuario {
        UsuarioDTO usuarioDTO = new UsuarioDTO(3, "Teste TarefaDAO", "123", "tarefadao@email.com");
        //TarefaDTO tarefaDTO = new TarefaDTO(64, "Teste", false);
        TarefaDTO tarefaDTO = pegaAPrimeiraTarefaDoBanco(usuarioDTO);

        tarefaDAO.apagarTarefa(tarefaDTO);

        ArrayList<TarefaDTO> tarefa = new ArrayList<>();
        assertEquals(tarefa, tarefaDAO.listarTarefas(usuarioDTO));
    }

    private UsuarioDTO prepararUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Teste TarefaDAO", "123", "tarefadao@email.com");
        UsuarioDTO usuarioRetornado = new UsuarioDTO();
        
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
