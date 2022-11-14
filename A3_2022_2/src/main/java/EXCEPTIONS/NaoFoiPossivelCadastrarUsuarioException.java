package EXCEPTIONS;

public class NaoFoiPossivelCadastrarUsuarioException extends Exception {

    @Override
    public String getMessage() {
        return "Desculpe, houve um erro inesperado e não conseguimos cadastrar o usuário!\nPor favor, tente novamente mais tarde!";
    }
}
