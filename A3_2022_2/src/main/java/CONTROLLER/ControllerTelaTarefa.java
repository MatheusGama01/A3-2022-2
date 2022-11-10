package CONTROLLER;

import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import DAO.TarefaDAO;
import EXCEPTIONS.NaoFoiPossivelApagarATarefaException;
import EXCEPTIONS.NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException;
import EXCEPTIONS.NaoFoiPossivelSalvarAEdicaoDaTarefaException;
import EXCEPTIONS.TarefaNaoAlteradaException;
import VIEW.TelaUsuario;
import VIEW.TelaHome;
import java.util.Objects;

public class ControllerTelaTarefa {

    //Instancia TelaUsuario e a torna visível.
    public void navegarParaTelaDeUsuario(UsuarioDTO usuario) {
        TelaUsuario telaUsuario = new TelaUsuario(usuario);
        telaUsuario.setVisible(true);
    }

    //Instancia TelaHome e a torna visível.
    public void navegarParaTelaHome(UsuarioDTO usuario) {
        TelaHome telaHome = new TelaHome(usuario);
        telaHome.setVisible(true);
    }

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

    /**
     * Verifica se o usuário realmente quer apagar a tarefa. Caso queira, a
     * tarefa é apagada, se não, volta para a Tela de edição da tarefa.
     */
    public void apagarTarefa(TarefaDTO tarefa, UsuarioDTO usuario) throws NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException, NaoFoiPossivelApagarATarefaException {
        TarefaDAO tarefaDAO = new TarefaDAO();

        Boolean apagouTarefa = tarefaDAO.apagarTarefa(tarefa);

        TelaHome telaHome = new TelaHome(usuario);
        telaHome.setVisible(true);
    }
}
