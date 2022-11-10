package EXCEPTIONS;

public class TarefaNaoAlteradaException extends Exception {

    @Override
    public String getMessage() {
        return "A tarefa não foi alterada!\nFaça uma alteração para salvar a edição.";
    }
}
