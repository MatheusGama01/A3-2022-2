package VIEW.TEST.INTEGRATION;

import CONTROLLER.ControllerTelaLogin;
import VIEW.TelaLogin;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TelaLoginTest {
    
    private TelaLogin telaLogin;
    
    @Mock
    private ControllerTelaLogin controller;
    
    @Before
    public void init() {
        this.telaLogin = new TelaLogin();
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void deveChamarOMetodoNavegarParaTelaCadastro(){
        
    }
}
