package EXCEPTIONS;

public class NenhumDadoDeCadastroInseridoException extends Exception {

    @Override
    public String getMessage() {
        return "Digite seus dados para realizar o cadastro.";
    }
}
