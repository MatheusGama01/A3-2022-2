package EXCEPTIONS;

public class NaoFoiPossivelListarAsTarefasDoUsuario extends Exception {

    @Override
    public String getMessage() {
        return "Desculpe, houve um erro inesperado e não conseguimos encontrar suas tarefas!";
    }
}
