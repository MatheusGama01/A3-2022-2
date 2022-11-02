package HELPER;

import DTO.TarefaDTO;
import VIEW.TelaTarefa;

public class HelperControllerTelaTarefa {

    private final TelaTarefa view;

    public HelperControllerTelaTarefa(TelaTarefa view) {
        this.view = view;
    }

    //Captura os  dados do campo de texto e da check box.
    public TarefaDTO obterModelo() {
        String descricao = this.view.getTextAreaDescricaoDaTarefa().getText();
        Boolean status = this.view.getCheckBoxStatusDaTarefa().isSelected();

        TarefaDTO tarefa = new TarefaDTO(descricao, status);

        return tarefa;
    }

}
