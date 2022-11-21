package CONTROLLER;

import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import DAO.TarefaDAO;
import EXCEPTIONS.NaoFoiPossivelApagarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelSalvarAEdicaoDaTarefaException;
import EXCEPTIONS.TarefaNaoAlteradaException;
import VIEW.TelaHome;
import java.util.Objects;

public class ControllerTelaTarefa {
    
    /**
     * Verifica se há alteração na tarefa, caso sim chama o método
     * atualizarTarefa de TarefaDAO.
     */
    public void salvarEdicao(TarefaDTO tarefa, String descricao, Boolean status, UsuarioDTO usuario) throws TarefaNaoAlteradaException, NaoFoiPossivelSalvarAEdicaoDaTarefaException, NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException {
        if (descricao.equals(tarefa.getDescricao()) && Objects.equals(status, tarefa.getStatus())) {
            throw new TarefaNaoAlteradaException();
        } else {
            System.out.println("Salvando alteração");
            TarefaDAO tarefaDAO = new TarefaDAO();

            tarefa.setDescricao(descricao);
            tarefa.setStatus(status);

            tarefaDAO.atualizarTarefa(tarefa);
            TelaHome telaHome = new TelaHome(usuario);
            telaHome.setVisible(true);
        }
    }

    //Chama o método de apagar tarefa em tarefaDAO.
    public void apagarTarefa(TarefaDTO tarefa, UsuarioDTO usuario) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarATarefaException {
        TarefaDAO tarefaDAO = new TarefaDAO();

        Boolean apagouTarefa = tarefaDAO.apagarTarefa(tarefa);

        TelaHome telaHome = new TelaHome(usuario);
        telaHome.setVisible(true);
    }
}
