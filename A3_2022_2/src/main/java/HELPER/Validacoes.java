package HELPER;

import EXCEPTIONS.NenhumDadoDeCadastroInseridoException;
import EXCEPTIONS.SenhasDiferentesException;

public class Validacoes {

    //Verifica se todos os campos de texto foram preenchidos.
    public Boolean dadosInseridos(String nome, String email, String senha, String confirmarSenha) throws NenhumDadoDeCadastroInseridoException {
        if (nome.equals("") || email.equals("") || senha.equals("") || confirmarSenha.equals("")) {
            throw new NenhumDadoDeCadastroInseridoException();
        } else {
            return true;
        }
    }

    //Verifica se os campos de senha e confirmação de senha são iguais.
    public Boolean senhasIguais(String senha, String confirmarSenha) throws SenhasDiferentesException {
        if (senha.equals(confirmarSenha)) {
            return true;
        } else {
            throw new SenhasDiferentesException();
        }
    }
}
