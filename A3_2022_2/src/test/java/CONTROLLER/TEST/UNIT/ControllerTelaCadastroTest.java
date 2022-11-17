package CONTROLLER.TEST.UNIT;

import CONTROLLER.ControllerTelaCadastro;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoAutenticarException;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import HELPER.Criptografia;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ControllerTelaCadastroTest {

    private ControllerTelaCadastro controller;

    @Before
    public void init() {
        this.controller = new ControllerTelaCadastro();
    }

    @Test
    public void deveCadastrarOUsuarioComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException, FalhaAoCriptografarSenhaException, FalhaAoAutenticarException, SQLException {
        String nome = "ControllerTelaCadastro Teste";
        String senha = "123";
        String email = "controllerTelaCadastro@email.com";

        controller.cadastrarUsuario(nome, email, senha);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioDTO usuarioDTO = new UsuarioDTO(Criptografia.encriptarSenha(senha), email);
        ResultSet usuarioAutenticado = usuarioDAO.autenticarUsuario(usuarioDTO);

        assertNotNull(usuarioAutenticado);
    }
}
