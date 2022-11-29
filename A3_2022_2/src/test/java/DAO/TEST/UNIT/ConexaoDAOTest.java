package DAO.TEST.UNIT;

import DAO.ConexaoDAO;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import java.sql.Connection;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ConexaoDAOTest {
    
    private ConexaoDAO conexaoDAO;
    
    @Before
    public void init(){
        this.conexaoDAO = new ConexaoDAO();
    }
    
    @Test
    public void deveConectarAoBancoComSucesso() throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException{
        
        Connection conn = this.conexaoDAO.conectaBD();
        
        assertNotNull(conn);
    }
}
