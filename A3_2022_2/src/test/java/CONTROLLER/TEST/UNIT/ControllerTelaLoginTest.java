package CONTROLLER.TEST.UNIT;

import CONTROLLER.ControllerTelaLogin;
import DAO.TarefaDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoAutenticarException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.UsuarioOuSenhaIncorretosException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ControllerTelaLoginTest {

    private ControllerTelaLogin controller;
    private TarefaDAO tarefaDAO;

    @Before
    public void inti() {
        this.controller = new ControllerTelaLogin();
        this.tarefaDAO = new TarefaDAO();
    }

    @Test
    public void deveRealizarAutenticacaComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, UsuarioOuSenhaIncorretosException, FalhaAoAutenticarException {
        UsuarioDTO usuarioDTO = new UsuarioDTO(8, "Teste", "123", "teste@email.com");
        String email = usuarioDTO.getEmail();
        String senha = usuarioDTO.getSenha();

        Boolean login = controller.logar(email, senha);

        assertTrue(login);
    }

    @Test
    public void deveRetornarErroQuandoNenhumDadoEDigitado() {
        String email = "";
        String senha = "";

        UsuarioOuSenhaIncorretosException UsuarioOuSenhaIncorretosException = assertThrows(UsuarioOuSenhaIncorretosException.class, () -> {
            controller.logar(email, senha);
        });

        assertEquals("O usuário e/ou senha digitados estão incorretos.", UsuarioOuSenhaIncorretosException.getMessage());
    }
}
