package EXCEPTIONS;

public class FalhaAoCriptografarSenhaException extends Exception {

    @Override
    public String getMessage() {
        return "Desculpe, houve um erro inesperado!\nPor favor, tente novamente mais tarde!";
    }
}
