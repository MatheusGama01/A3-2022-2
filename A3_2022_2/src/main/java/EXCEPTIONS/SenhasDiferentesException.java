package EXCEPTIONS;

public class SenhasDiferentesException extends Exception {

    @Override
    public String getMessage() {
        return "As senhas são diferentes!\nDigite a mesma senha para realizar o cadastro.";
    }
}
