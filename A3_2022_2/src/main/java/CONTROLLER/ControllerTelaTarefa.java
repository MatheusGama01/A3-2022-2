package CONTROLLER;

import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import DAO.TarefaDAO;
import VIEW.TelaUsuario;
import VIEW.TelaHome;
import javax.swing.JOptionPane;

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
    public void salvarEdicao(TarefaDTO tarefa, String descricao, Boolean status, UsuarioDTO usuario) {

        if (descricao.equals(tarefa.getDescricao()) && status == tarefa.getStatus()) {
            navegarParaTelaHome(usuario);
            System.out.println("Nada foi alterado na tarefa");
        } else {
            System.out.println("Salvando alteração");
            TarefaDAO tarefaDAO = new TarefaDAO();

            tarefa.setDescricao(descricao);
            tarefa.setStatus(status);

            tarefaDAO.atualizarTarefa(tarefa);
        }

        TelaHome telaHome = new TelaHome(usuario);
        telaHome.setVisible(true);
    }

    /**
     * Verifica se o usuário realmente quer apagar a tarefa. Caso queira, a
     * tarefa é apagada, se não, volta para a Tela de edição da tarefa.
     */
    public void apagarTarefa(TarefaDTO tarefa, UsuarioDTO usuario) {
        TarefaDAO tarefaDAO = new TarefaDAO();

        //Confirma se o usuário realmente quer excluir a tarefa.
        int confirmarExclusao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar a tarefa?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        /**
         * Se o usuario confirmar a exclusão, chama o método apagarTarefa em
         * UsuarioDAO para que a tarefa seja apagada.
         */
        if (confirmarExclusao == JOptionPane.YES_OPTION) {
            Boolean apagouTarefa = tarefaDAO.apagarTarefa(tarefa);

            /**
             * Caso a tarefa seja apaga é mostrado uma menssagem que foi
             * apagada. Caso dê algum erro ao tentar apagar é mostrado uma
             * mesagem de erro.
             */
            if (apagouTarefa == true) {
                JOptionPane.showMessageDialog(null, "A tarefa foi apagada!");

                TelaHome telaHome = new TelaHome(usuario);
                telaHome.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível apagar a tarefa! Tente novamente mais tarde!", "Atenção", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
