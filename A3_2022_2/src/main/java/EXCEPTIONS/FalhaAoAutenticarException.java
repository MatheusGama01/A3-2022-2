package EXCEPTIONS;

public class FalhaAoAutenticarException extends Exception {

    @Override
    public String getMessage() {
        return "Desculpe, houve um erro inesperado e não conseguimos fazer a autenticação!\nPor favor, tente novamente mais tarde!";
    }
}
