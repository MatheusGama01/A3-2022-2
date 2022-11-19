package EXCEPTIONS;

public class NaoFoiPossivelListarOUsuarioException extends Exception{

    @Override
    public String getMessage() {
        return "Desculpe, houve um erro inesperado e não conseguimos carregar o usuário!\nPor favor, tente novamente mais tarde!";
    }
}
