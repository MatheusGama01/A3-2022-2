package EXCEPTIONS;

public class NenhumaDescricaoInseridaException extends Exception {
    
    @Override
    public String getMessage() {
        return "Insira uma descrição para salvar a tarefa.";
    }
}
