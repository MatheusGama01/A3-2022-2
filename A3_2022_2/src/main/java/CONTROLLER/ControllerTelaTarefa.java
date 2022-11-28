package CONTROLLER;

import DTO.TarefaDTO;
import DAO.TarefaDAO;
import EXCEPTIONS.NaoFoiPossivelApagarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelSalvarAEdicaoDaTarefaException;
import EXCEPTIONS.TarefaNaoAlteradaException;
import java.util.Objects;

public class ControllerTelaTarefa {

    private final TarefaDAO tarefaDAO;

    public ControllerTelaTarefa(TarefaDAO tarefaDAO) {
        this.tarefaDAO = tarefaDAO;

    }

    /**
     * Verifica se há alteração na tarefa, caso sim chama o método
     * atualizarTarefa de TarefaDAO. Se não, lança uma exceção.
     */
    public Boolean salvarEdicao(TarefaDTO tarefa, String descricao, Boolean status) throws TarefaNaoAlteradaException, NaoFoiPossivelSalvarAEdicaoDaTarefaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        if (descricao.equals(tarefa.getDescricao()) && Objects.equals(status, tarefa.getStatus())) {
            throw new TarefaNaoAlteradaException();
        } else {
            System.out.println("Salvando alteração");

            tarefa.setDescricao(descricao);
            tarefa.setStatus(status);

            Boolean tarefaAtualizada = tarefaDAO.atualizarTarefa(tarefa);

            return tarefaAtualizada;
        }
    }

    //Chama o método de apagar tarefa em tarefaDAO.
    public Boolean apagarTarefa(TarefaDTO tarefa) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarATarefaException {
        Boolean apagouTarefa = tarefaDAO.apagarTarefa(tarefa);

        return apagouTarefa;
    }
}
