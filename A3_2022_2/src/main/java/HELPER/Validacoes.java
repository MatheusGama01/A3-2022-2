package HELPER;

import EXCEPTIONS.EmailInvalidoException;
import EXCEPTIONS.NenhumDadoDeCadastroInseridoException;
import EXCEPTIONS.NenhumDadoDeLoginInseridoException;
import EXCEPTIONS.SenhasDiferentesException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacoes {

    //Verifica se todos os campos de texto para cadastro foram preenchidos.
    public boolean dadosDeCadastroInseridos(String nome, String email, String senha, String confirmarSenha) throws NenhumDadoDeCadastroInseridoException {
        if (nome.equals("") || email.equals("") || senha.equals("") || confirmarSenha.equals("")) {
            throw new NenhumDadoDeCadastroInseridoException();
        } else {
            return true;
        }
    }

    //Verifica se todos os campos de texto para login foram preenchidos.
    public boolean dadosDeLoginInseridos(String email, String senha) throws NenhumDadoDeLoginInseridoException {
        if (email.equals("") || senha.equals("")) {
            throw new NenhumDadoDeLoginInseridoException();
        } else {
            return true;
        }
    }

    //Verifica se os campos de senha e confirmação de senha são iguais.
    public boolean senhasIguais(String senha, String confirmarSenha) throws SenhasDiferentesException {
        if (senha.equals(confirmarSenha)) {
            return true;
        } else {
            throw new SenhasDiferentesException();
        }
    }

    //Verifica se o email digitado é válido
    public boolean emailValido(String email) throws EmailInvalidoException {
        boolean emailValido = false;

        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);

            if (matcher.matches()) {
                emailValido = true;
            } else {
                throw new EmailInvalidoException();
            }
        } else {
            throw new EmailInvalidoException();
        }

        return emailValido;
    }
}
