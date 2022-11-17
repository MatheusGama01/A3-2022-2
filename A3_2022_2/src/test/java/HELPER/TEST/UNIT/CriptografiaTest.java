package HELPER.TEST.UNIT;

import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import HELPER.Criptografia;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CriptografiaTest {
    
    private Criptografia criptografia;
    
    @Before
    public void init(){
        this.criptografia = new Criptografia();
    }
    
    @Test
    public void deveEncriptarASenhaComSucesso() throws FalhaAoCriptografarSenhaException{
        String senha = "123";
        
        String senhaEncriptada = criptografia.encriptarSenha(senha);
        
        assertEquals("196a500b9911174047387a15004a27a6661231b233a904b457a99856833779928b67b2ab", senhaEncriptada);
    }
}
