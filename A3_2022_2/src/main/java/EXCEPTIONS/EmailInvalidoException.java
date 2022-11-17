package EXCEPTIONS;

public class EmailInvalidoException extends Exception {

    @Override
    public String getMessage() {
        return "O email digitado não é válido!\nPor favor, digite um email válido.";
    }
}
