package CONTROLLER;

import DTO.TarefaDTO;
import DTO.UsuarioDTO;
import DAO.TarefaDAO;
import HELPER.HelperControllerTelaHome;
import VIEW.AdicionarTarefa;
import VIEW.TelaHome;
import VIEW.TelaTarefa;
import VIEW.TelaUsuario;
import java.util.ArrayList;

public class ControllerTelaHome {

    private final TelaHome telaHome;
    private final HelperControllerTelaHome helper;

    public ControllerTelaHome(TelaHome telaHome) {
        this.telaHome = telaHome;
        this.helper = new HelperControllerTelaHome(telaHome);
    }

    //Inseri os dados das tarefas na tabela em telaHome.
    public void inicializarTabela(UsuarioDTO usuario) {
        String statusSelecionado = this.helper.obterStatus();
        System.out.println("Em incializarTabela");
        System.out.println("Status selecionado: " + statusSelecionado);

        ArrayList<TarefaDTO> tarefas = new ArrayList<>();

        /**
         * A partir do status selecionado na combo box da TelaHome ele chama o
         * método mais adequado para trazer as tarefas do vanco de dados.
         */
        if (statusSelecionado.equals("À fazer")) {
            System.out.println("Em inicializarTabela, chamando controller.listarTarefasAFazer");
            tarefas = listarTarefasAFazer(usuario);
        } else if (statusSelecionado.equals("Feitas")) {
            System.out.println("Em inicializarTabela, chamando controller.listarTarefasFeitas");
            tarefas = listarTarefasFeitas(usuario);
        } else {
            System.out.println("Em inicializarTabela, chamando controller.listarTarefas");
            tarefas = listarTarefas(usuario);
        }

        helper.preencherTabela(tarefas);
    }

    //Instancia tela AdicionarTarefa e a torna visível.
    public void navegarParaTelaDeAdicionarTarefa(UsuarioDTO usuario) {
        AdicionarTarefa telaAdicionarTarefa = new AdicionarTarefa(usuario);
        telaAdicionarTarefa.setVisible(true);

        this.telaHome.dispose();
    }

    //Instancia TelaUsuario e a torna visível.
    public void navegarParaTelaDeUsuario(UsuarioDTO usuario) {
        TelaUsuario telaUsuario = new TelaUsuario(usuario);
        telaUsuario.setVisible(true);

        this.telaHome.dispose();
    }

    /**
     * Instancia TelaUsuario e a torna visível com os dados dados da tarefa que
     * foi seleciando.
     */
    public void navegarParaTelaTarefa(UsuarioDTO usuario) {
        TarefaDTO tarefa = this.helper.obterTarefa();
        System.out.println("Em controllerTelaHome, navegarParaTelaTarefa o status é: " + tarefa.getStatus() + " descrição: " + tarefa.getDescricao() + " id: " + tarefa.getId());

        TelaTarefa telaTarefa = new TelaTarefa(tarefa, usuario);
        telaTarefa.setVisible(true);
        telaTarefa.lblUsuario.setText(usuario.getNome());

        this.telaHome.dispose();
    }

    //Cria um ArrayList com todas as tarefas do usuário.
    private ArrayList<TarefaDTO> listarTarefas(UsuarioDTO usuario) {
        System.out.println("Em ControllerTelaHome o idUsuario é: " + usuario.getId());
        TarefaDAO tarefaDAO = new TarefaDAO();
        ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();

        //Inseri os dados retornados pelo banco em um ArrayList.
        for (TarefaDTO tarefa : tarefaDAO.listarTarefas(usuario)) {
            System.out.println("ControllerTelaHome.listarTarefas - Status da tarefa: " + tarefa.getStatus());
            listaDeTarefas.add(tarefa);
        }

        return listaDeTarefas;
    }

    //Cria um ArrayList apenas com as tarefas à fazer.
    private ArrayList listarTarefasAFazer(UsuarioDTO usuario) {
        System.out.println("Em ControllerTelaHome o idUsuario é: " + usuario.getId());
        TarefaDAO tarefaDAO = new TarefaDAO();
        ArrayList<TarefaDTO> listaDeTarefasAFazer = new ArrayList<>();

        /**
         * Verifica cada tarefa salva no banco e inseri na listaDeTarefasAFazer
         * apenas as que estão com status: false (à fazer).
         */
        for (TarefaDTO tarefa : tarefaDAO.listarTarefas(usuario)) {
            System.out.println("ControllerTelaHome.listarTarefas - Status da tarefa: " + tarefa.getStatus());
            if (tarefa.getStatus() == false) {
                listaDeTarefasAFazer.add(tarefa);
            }
        }

        return listaDeTarefasAFazer;
    }

    //Cria um ArrayList apenas com as tarefas feitas.
    private ArrayList listarTarefasFeitas(UsuarioDTO usuario) {
        System.out.println("Em ControllerTelaHome o idUsuario é: " + usuario.getId());
        TarefaDAO tarefaDAO = new TarefaDAO();
        ArrayList<TarefaDTO> listaDeTarefasFeitas = new ArrayList<>();

        /**
         * Verifica cada tarefa salva no banco e inseri na listaDeTarefasFeitas
         * apenas as que estão com status: true (feitas).
         */
        for (TarefaDTO tarefa : tarefaDAO.listarTarefas(usuario)) {
            System.out.println("ControllerTelaHome.listarTarefas - Status da tarefa: " + tarefa.getStatus());
            if (tarefa.getStatus() == true) {
                listaDeTarefasFeitas.add(tarefa);
            }
        }

        return listaDeTarefasFeitas;
    }
}
