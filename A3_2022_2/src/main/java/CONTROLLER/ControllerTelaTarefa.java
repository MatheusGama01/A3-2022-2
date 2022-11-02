package CONTROLLER;

import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import DAO.TarefaDAO;
import HELPER.HelperControllerTelaTarefa;
import VIEW.TelaUsuario;
import VIEW.TelaHome;
import VIEW.TelaTarefa;
import javax.swing.JOptionPane;

public class ControllerTelaTarefa {

    private final TelaTarefa telaTarefa;
    private HelperControllerTelaTarefa helper;

    public ControllerTelaTarefa(TelaTarefa telaTarefa) {
        this.telaTarefa = telaTarefa;
        this.helper = new HelperControllerTelaTarefa(telaTarefa);
    }

    //Instancia TelaUsuario e a torna visível.
    public void navegarParaTelaDeUsuario(UsuarioDTO usuario) {
        TelaUsuario telaUsuario = new TelaUsuario(usuario);
        telaUsuario.setVisible(true);

        this.telaTarefa.dispose();
    }

    //Instancia TelaHome e a torna visível.
    public void navegarParaTelaHome(UsuarioDTO usuario) {
        TelaHome telaHome = new TelaHome(usuario);
        telaHome.setVisible(true);

        this.telaTarefa.dispose();
    }

    /**
     * Verifica se há alteração na tarefa, caso sim chama o método
     * atualizarTarefa de TarefaDAO.
     */
    public void salvarEdicao(TarefaDTO tarefa, UsuarioDTO usuario) {
        TarefaDTO tarerfaView = helper.obterModelo();

        if (tarerfaView.getDescricao().equals(tarefa.getDescricao()) && tarerfaView.getStatus() == tarefa.getStatus()) {
            navegarParaTelaHome(usuario);
            System.out.println("Nada foi alterado na tarefa");
        } else {
            System.out.println("Salvando alteração");
            TarefaDAO tarefaDAO = new TarefaDAO();

            tarefa.setDescricao(tarerfaView.getDescricao());
            tarefa.setStatus(tarerfaView.getStatus());

            tarefaDAO.atualizarTarefa(tarefa);
        }

        TelaHome telaHome = new TelaHome(usuario);
        telaHome.setVisible(true);

        this.telaTarefa.dispose();
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

                this.telaTarefa.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível apagar a tarefa! Tente novamente mais tarde!", "Atenção", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
