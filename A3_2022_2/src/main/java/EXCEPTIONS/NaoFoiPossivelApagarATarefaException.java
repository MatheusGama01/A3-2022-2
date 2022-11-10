package EXCEPTIONS;

public class NaoFoiPossivelApagarATarefaException extends Exception {

    @Override
    public String getMessage() {
        return "Desculpe, houve um erro inesperado e não conseguimos apagar a tarefa!\nPor favor, tente novamente mais tarde!";
    }
}
