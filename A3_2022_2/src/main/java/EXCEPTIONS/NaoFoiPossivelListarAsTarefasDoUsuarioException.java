package EXCEPTIONS;

public class NaoFoiPossivelListarAsTarefasDoUsuarioException extends Exception {

    @Override
    public String getMessage() {
        return "Desculpe, houve um erro inesperado e não conseguimos encontrar suas tarefas!";
    }
}
