package EXCEPTIONS;

public class NenhumDadoDeLoginInseridoException extends Exception {

    @Override
    public String getMessage() {
        return "Digite seu email e senha para realizar o login.";
    }
}
