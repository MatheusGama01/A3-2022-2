package CONTROLLER.TEST.INTEGRATION;

import CONTROLLER.ControllerTelaLogin;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.EmailOuSenhaIncorretosException;
import EXCEPTIONS.FalhaAoAutenticarException;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class ControllerTelaLoginTest {

    private ControllerTelaLogin controller;

    @Mock
    private UsuarioDAO usuarioDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.controller = new ControllerTelaLogin(usuarioDAO);
    }

    @Test
    public void verificaSeLogarRetornaUsuarioCorretamente() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, EmailOuSenhaIncorretosException, FalhaAoAutenticarException, FalhaAoCriptografarSenhaException {
        String email = "teste@email.com";
        String senha = "123";
        UsuarioDTO usuarioDTO = new UsuarioDTO(senha, email);

        when(usuarioDAO.autenticarUsuario(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        UsuarioDTO usuarioAutenticado = controller.logar(email, senha);

        assertEquals(usuarioDTO, usuarioAutenticado);
    }

    @Test(expected = FalhaAoAutenticarException.class)
    public void verificaSeLogarLancaExcecaoQuandoUsuarioRetornadoENulo() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, FalhaAoAutenticarException, EmailOuSenhaIncorretosException, FalhaAoCriptografarSenhaException {
        String email = "teste@email.com";
        String senha = "123";
        UsuarioDTO usuarioNulo = new UsuarioDTO(0, null, null, null);

        when(usuarioDAO.autenticarUsuario(any(UsuarioDTO.class))).thenReturn(usuarioNulo);

        UsuarioDTO usuarioAutenticado = controller.logar(email, senha);

        assertEquals(usuarioNulo, usuarioAutenticado);
    }

    @Test(expected = FalhaAoAutenticarException.class)
    public void verificaSeLogarLancaExcecaoQuandoNaoForPossivelAutenticarUsuario() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, FalhaAoAutenticarException, EmailOuSenhaIncorretosException, FalhaAoCriptografarSenhaException {
        String email = "teste@email.com";
        String senha = "123";

        when(usuarioDAO.autenticarUsuario(any(UsuarioDTO.class))).thenThrow(new FalhaAoAutenticarException());

        controller.logar(email, senha);
    }
}
