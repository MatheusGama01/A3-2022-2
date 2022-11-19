package EXCEPTIONS;

public class NaoFoiPossivelApagarOUsuarioException extends Exception {

    @Override
    public String getMessage() {
        return "Desculpe, houve um erro inesperado e não conseguimos apagar o usuário!\nPor favor, tente novamente mais tarde!";
    }
}
