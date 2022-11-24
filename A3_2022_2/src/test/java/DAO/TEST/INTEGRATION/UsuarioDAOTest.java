/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.TEST.INTEGRATION;

import DAO.ConexaoDAO;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoAutenticarException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import java.sql.ResultSet;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Gabriele
 */
public class UsuarioDAOTest {

    @Mock
    private UsuarioDAO usuarioDAO;

    @Mock
    private ConexaoDAO conexaoDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void verificaSeAutenticarUsuarioRetornaNaoNulo() throws FalhaAoAutenticarException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {

        // arrange
        ResultSet rs = mock(ResultSet.class);
        UsuarioDTO user = mock(UsuarioDTO.class);
        // action
        when(usuarioDAO.autenticarUsuario(any(UsuarioDTO.class))).thenReturn(rs);
        // assert 
        assertNotNull(usuarioDAO.autenticarUsuario(user));

    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void verificaSeQuandoBancoIndisponivelAutenticarUsuarioLancaExcecao() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, FalhaAoAutenticarException {

        UsuarioDTO user = mock(UsuarioDTO.class);
        Mockito.doThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException()).when(conexaoDAO).conectaBD();

        expectedException.expect(NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.class);
        expectedException.expectMessage("Não foi possível estabelecer conexão com o banco de dados.\n Tente novamente mais tarde!");

        given(usuarioDAO.autenticarUsuario(user)).willThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException());

        //arrange 
//        Mockito.doThrow(new NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException()).when(conexaoDAO).conectaBD();
//        //action
//        usuarioDAO.autenticarUsuario(user);
//        //assert 
//        assertEquals("Não foi possível estabelecer conexão com o banco de dados.\nTente novamente mais tarde!", NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException.getMessage());
//     
    }
}
