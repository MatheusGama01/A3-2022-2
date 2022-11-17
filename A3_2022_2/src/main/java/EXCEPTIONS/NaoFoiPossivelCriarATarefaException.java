package EXCEPTIONS;

public class NaoFoiPossivelCriarATarefaException extends Exception {

    @Override
    public String getMessage() {
        return "Desculpe, houve um erro inesperado e não conseguimos criar a tarefa!\nPor favor, tente novamente mais tarde!";
    }
}
