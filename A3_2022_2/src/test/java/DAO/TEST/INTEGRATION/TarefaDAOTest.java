package DAO.TEST.INTEGRATION;

import DAO.ConexaoDAO;
import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelApagarATarefaException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelCriarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuarioException;
import EXCEPTIONS.NaoFoiPossivelListarOUsuarioException;
import EXCEPTIONS.NaoFoiPossivelSalvarAEdicaoDaTarefaException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class TarefaDAOTest {

    private TarefaDAO tarefaDAO;

    @Mock
    private ConexaoDAO conexaoDAO;

    @Before
    public void init() throws FalhaAoCriptografarSenhaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException {
        MockitoAnnotations.initMocks(this);
        this.tarefaDAO = new TarefaDAO(this.conexaoDAO);
    }

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificaSeLancaErroAoListarTarefasQuandoBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        when(this.conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());

        UsuarioDTO usuarioDTO = new UsuarioDTO(1, "Teste", "123", "teste@email.com");

        this.tarefaDAO.listarTarefas(usuarioDTO);
    }

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificaSeLancaErroAoListarTarefaQuandoBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        when(this.conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());

        TarefaDTO tarefaDTO = new TarefaDTO(1, "Teste", false, 100);

        this.tarefaDAO.listarTarefa(tarefaDTO);
    }

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificaSeLancaErroAoCriarTarefaComBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCriarATarefaException {
        when(this.conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());

        UsuarioDTO usuarioDTO = new UsuarioDTO(1, "Teste", "123", "teste@email.com");
        TarefaDTO tarefaDTO = new TarefaDTO(1, "Teste", false, 1);

        this.tarefaDAO.criarTarefa(tarefaDTO, usuarioDTO);
    }

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificaSeLancaErroAoAtualizarTarefaComBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelSalvarAEdicaoDaTarefaException {
        when(this.conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());

        TarefaDTO tarefaDTO = new TarefaDTO(1, "Teste", false, 1);

        this.tarefaDAO.atualizarTarefa(tarefaDTO);
    }

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificaSeLancaErroAoApagarTarefaComBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException, NaoFoiPossivelApagarATarefaException, NaoFoiPossivelCriarATarefaException, NaoFoiPossivelListarAsTarefasDoUsuarioException {
        when(this.conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());

        TarefaDTO tarefaDTO = new TarefaDTO(4, "asdjbasdb", false, 100);

        this.tarefaDAO.apagarTarefa(tarefaDTO);
    }
}
