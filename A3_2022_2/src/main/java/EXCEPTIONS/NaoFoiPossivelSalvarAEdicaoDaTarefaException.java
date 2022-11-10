package EXCEPTIONS;

public class NaoFoiPossivelSalvarAEdicaoDaTarefaException extends Exception {

    @Override
    public String getMessage() {
        return "Desculpe, houve um erro inesperado e não conseguimos salvar a edição da tarefa!\nPor favor, tente novamente mais tarde!";
    }
}
