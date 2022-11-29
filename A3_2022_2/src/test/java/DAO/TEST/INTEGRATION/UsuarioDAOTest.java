/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.TEST.INTEGRATION;

import DAO.ConexaoDAO;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoAutenticarException;
import EXCEPTIONS.NaoFoiPossivelApagarOUsuarioException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelListarOUsuarioException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Gabriele
 */
public class UsuarioDAOTest {

    private UsuarioDAO usuarioDAO;

    @Mock
    private ConexaoDAO conexaoDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.usuarioDAO = new UsuarioDAO(conexaoDAO);
    }

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificarSeLancaErroAoAutenticarUsuarioComBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, FalhaAoAutenticarException {
        when(conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());

        UsuarioDTO usuarioDTO1 = new UsuarioDTO(1, "jose", "1235", "jose44@email.com");

        usuarioDAO.autenticarUsuario(usuarioDTO1);
    }

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificarSeLancaErroAoCadastrarUsuarioComBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException {
        when(conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());
        
        UsuarioDTO usuarioDTOg = new UsuarioDTO("Lara", "9912", "Lara123@email.com");
        
        usuarioDAO.cadastrarUsuario(usuarioDTOg);
    }

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificarSeLancaErroAoListarUsuarioComBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelListarOUsuarioException {
        when(conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());
        
        UsuarioDTO usuarioDTOm = new UsuarioDTO("4433", "Matheus55@email.com");
        
        usuarioDAO.listarUsuario(usuarioDTOm);
    }

    @Test(expected = NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class)
    public void verificarSeLancaErroAoApagarUsuarioComBancoIndisponivel() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarOUsuarioException {
        when(conexaoDAO.conectaBD()).thenThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());
        
        UsuarioDTO usuarioDTOe = new UsuarioDTO("2613", "Eudes13@email.com");
        
        usuarioDAO.apagarUsuario(usuarioDTOe);
    }
}
