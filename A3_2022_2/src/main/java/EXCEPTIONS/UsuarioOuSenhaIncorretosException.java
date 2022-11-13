package EXCEPTIONS;

public class UsuarioOuSenhaIncorretosException extends Exception {

    @Override
    public String getMessage() {
        return "O usuário e/ou senha digitados estão incorretos.";
    }
}
