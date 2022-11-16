package HELPER;

import EXCEPTIONS.FalhaAoCriptografarSenhaException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Criptografia {
    
    //Encripta a senha do usuário
    public static String encriptarSenha(String senha) throws FalhaAoCriptografarSenhaException{
        String senhaEncriptada = senha;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
            senhaEncriptada = hash.toString(12);
            return senhaEncriptada;
        } catch (Exception e) {
            System.out.println("Erro em encriptarSenha " + e);
            throw new FalhaAoCriptografarSenhaException();
        }
    }
}
