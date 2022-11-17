package DAO.TEST.UNIT;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import EXCEPTIONS.FalhaAoAutenticarException;
import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import EXCEPTIONS.NaoFoiPossivelCadastrarUsuarioException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import HELPER.Criptografia;
import java.sql.ResultSet;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UsuarioDAOTest {
    
    private UsuarioDAO usuarioDAO;
    
    @Before
    public void init(){
        this.usuarioDAO = new UsuarioDAO();
    }
    
    @Test
    public void deveAutenticarOUsuarioComSucesso() throws FalhaAoCriptografarSenhaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, FalhaAoAutenticarException{
        UsuarioDTO usuarioDTO = new UsuarioDTO(Criptografia.encriptarSenha("123"), "tarefadao@email.com");
        
        ResultSet usuarioAutenticado = usuarioDAO.autenticarUsuario(usuarioDTO);
        
        assertNotNull(usuarioAutenticado);
    }
    
    @Test
    public void deveCadastrarOUsuarioComSucesso() throws FalhaAoCriptografarSenhaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelCadastrarUsuarioException{
        UsuarioDTO usuarioDTO = new UsuarioDTO("UsuárioDAO Teste", Criptografia.encriptarSenha("123"), "usuariodaoTest@email.com");
        
        Boolean usuarioCadastrado = usuarioDAO.cadastrarUsuario(usuarioDTO);
        
        assertTrue(usuarioCadastrado);
    }
}
