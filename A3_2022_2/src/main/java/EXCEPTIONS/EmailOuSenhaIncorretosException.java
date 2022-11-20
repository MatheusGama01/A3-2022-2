package EXCEPTIONS;

public class EmailOuSenhaIncorretosException extends Exception {

    @Override
    public String getMessage() {
        return "O email e/ou senha digitados estão incorretos.";
    }
}
