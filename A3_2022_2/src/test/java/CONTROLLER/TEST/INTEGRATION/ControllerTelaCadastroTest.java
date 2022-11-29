package CONTROLLER.TEST.INTEGRATION;

import CONTROLLER.ControllerTelaCadastro;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class ControllerTelaCadastroTest {

    private ControllerTelaCadastro controller;

    @Mock
    private UsuarioDAO usuarioDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.controller = new ControllerTelaCadastro(this.usuarioDAO);
    }

    @Test(expected = NaoFoiPossivelCadastrarUsuarioException.class)
    public void verificaSeCadastrarUsuarioLancaErroQuandoNaoForPossivelCadastrarUsuario() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException, FalhaAoCriptografarSenhaException {
        when(this.usuarioDAO.cadastrarUsuario(any(UsuarioDTO.class))).thenThrow(new NaoFoiPossivelCadastrarUsuarioException());

        String nome = "Teste";
        String email = "teste@email.com";
        String senha = "123";

        this.controller.cadastrarUsuario(nome, email, senha);
    }
}
