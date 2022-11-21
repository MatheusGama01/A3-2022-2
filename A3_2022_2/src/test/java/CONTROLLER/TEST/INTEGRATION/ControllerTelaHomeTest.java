package CONTROLLER.TEST.INTEGRATION;

import CONTROLLER.ControllerTelaHome;
import DAO.TarefaDAO;
import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarAsTarefasDoUsuario;
import VIEW.TelaLogin;
import VIEW.TelaUsuario;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ControllerTelaHomeTest {

    private ControllerTelaHome controller;

    @Mock
    private TarefaDAO tarefaDAO;

    @Mock
    private TelaLogin telaLogin;

    @Mock
    private TelaUsuario telaUsuario;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.controller = new ControllerTelaHome();
    }

    //Integração entre ControllerTelaHome e TelaAdicionarTarefa.
    @Test
    public void deveTornarATelaAdicionarTarefaVisivel() {
        UsuarioDTO usuario = new UsuarioDTO(6, "Teste", "123", "teste@email.com");

        //controller.navegarParaTelaDeAdicionarTarefa(usuario);

        Mockito.verify(telaLogin).setVisible(true);
    }

    //Integração entre ControllerTelaHome e TelaUsuario.
    @Test
    public void deveTornarATelaUsuarioVisivel() {
        UsuarioDTO usuario = new UsuarioDTO(6, "Teste", "123", "teste@email.com");

        //controller.navegarParaTelaDeUsuario(usuario);

        Mockito.verify(telaUsuario).setVisible(true);
    }

    //Integração entre ControllerTelaHome e TarefaDAO.    
    @Test
    public void deveChamarOMetodoListarTarefas() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarAsTarefasDoUsuario {
        UsuarioDTO usuario = new UsuarioDTO(1, "Matheus", "123", "matheus@email.com");
        ArrayList<TarefaDTO> tarefas = new ArrayList<>();

        tarefas = controller.listarTarefas(usuario);

        Mockito.verify(tarefaDAO).listarTarefas(ArgumentMatchers.any());
    }
}
