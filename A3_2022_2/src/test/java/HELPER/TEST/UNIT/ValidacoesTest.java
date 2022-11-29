package HELPER.TEST.UNIT;

import EXCEPTIONS.EmailInvalidoException;
import EXCEPTIONS.NenhumDadoDeCadastroInseridoException;
import EXCEPTIONS.SenhasDiferentesException;
import HELPER.Validacoes;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidacoesTest {

    private Validacoes validacoes;

    @Before
    public void init() {
        this.validacoes = new Validacoes();
    }

    @Test
    public void deveRetornarTrueParaDadosInseridos() throws NenhumDadoDeCadastroInseridoException {
        String nome = "Teste";
        String email = "teste@email.com";
        String senha = "123";
        String confirmarSenha = "123";

        Boolean dadosInseridos = this.validacoes.dadosDeCadastroInseridos(nome, email, senha, confirmarSenha);

        assertTrue(dadosInseridos);
    }

    @Test
    public void deveRetornarExceptionParaDadosInseridos() throws NenhumDadoDeCadastroInseridoException {
        String nome = "Teste";
        String email = "";
        String senha = "123";
        String confirmarSenha = "123";

        NenhumDadoDeCadastroInseridoException NenhumDadoDeCadastroInseridoException = assertThrows(NenhumDadoDeCadastroInseridoException.class, () -> {
            this.validacoes.dadosDeCadastroInseridos(nome, email, senha, confirmarSenha);
        });

        assertEquals("Digite seus dados para realizar o cadastro.", NenhumDadoDeCadastroInseridoException.getMessage());
    }

    @Test
    public void deveRetornarTrueParaSenhasIguais() throws SenhasDiferentesException {
        String senha = "123";
        String confirmarSenha = "123";

        Boolean senhasIguais = this.validacoes.senhasIguais(senha, confirmarSenha);

        assertTrue(senhasIguais);
    }

    @Test
    public void deveRetornarExceptionParaSenhasIguais() throws SenhasDiferentesException {
        String senha = "123";
        String confirmarSenha = "321";

        SenhasDiferentesException SenhasDiferentesException = assertThrows(SenhasDiferentesException.class, () -> {
            this.validacoes.senhasIguais(senha, confirmarSenha);
        });

        assertEquals("As senhas são diferentes!\nDigite a mesma senha para realizar o cadastro.", SenhasDiferentesException.getMessage());
    }

    @Test
    public void deveRetornarTrueParaEmailValido() throws EmailInvalidoException {
        String email = "aaabbb@gmail.com";

        Boolean emailValido = this.validacoes.emailValido(email);

        assertTrue(emailValido);
    }

    @Test
    public void deveRetornarUmaExceptionQuandoEmailInvalido() throws EmailInvalidoException {
        String email = "inv@lido@com.br";

        EmailInvalidoException EmailInvalidoException = assertThrows(EmailInvalidoException.class, () -> {
            this.validacoes.emailValido(email);
        });

        assertEquals("O email digitado não é válido!\nPor favor, digite um email válido.", EmailInvalidoException.getMessage());
    }

    public void deveRetornarExceptionParaEmailSemArroba() throws EmailInvalidoException {
        String email = "invalido.com.br";

        EmailInvalidoException EmailInvalidoException = assertThrows(EmailInvalidoException.class, () -> {
            this.validacoes.emailValido(email);
        });

        assertEquals("O email digitado não é válido!\nPor favor, digite um email válido.", EmailInvalidoException.getMessage());
    }
    
    @Test
    public void deveRetornarExceptionParaEmailSemPonto() throws EmailInvalidoException {
        String email = "invalido@gmail";

        EmailInvalidoException EmailInvalidoException = assertThrows(EmailInvalidoException.class, () -> {
            this.validacoes.emailValido(email);
        });

        assertEquals("O email digitado não é válido!\nPor favor, digite um email válido.", EmailInvalidoException.getMessage());
    }

    @Test
    public void deveRetornarUmaExceptionQuandoEmailNaoDigitado() throws EmailInvalidoException {
        String email = "";

        EmailInvalidoException EmailInvalidoException = assertThrows(EmailInvalidoException.class, () -> {
            this.validacoes.emailValido(email);
        });

        assertEquals("O email digitado não é válido!\nPor favor, digite um email válido.", EmailInvalidoException.getMessage());
    }
}
