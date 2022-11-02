package HELPER;

import DTO.TarefaDTO;
import VIEW.TelaHome;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HelperControllerTelaHome {

    private final TelaHome view;

    public HelperControllerTelaHome(TelaHome view) {
        this.view = view;
    }

    //Captura o status da combo box em telaHome.
    public String obterStatus() {
        String status = this.view.statusComboBox();
        return status;
    }

    //Inseri os dados do ArrayList tarefas na tabela em talaHome.
    public void preencherTabela(ArrayList<TarefaDTO> tarefas) {

        DefaultTableModel tabelaTarefas = (DefaultTableModel) this.view.getTblTarefas().getModel();
        tabelaTarefas.setNumRows(0);

        for (TarefaDTO tarefa : tarefas) {
            String status;

            if (tarefa.getStatus() == true) {
                status = "Feita";
            } else {
                status = "À fazer";
            }

            tabelaTarefas.addRow(new Object[]{
                tarefa.getId(),
                tarefa.getDescricao(),
                status
            });
        }
    }

    //Captura a tarefa que foi selecionada na tabela.
    public TarefaDTO obterTarefa() {
        int idTarefa = 0;
        String descricao = "";
        String statusTabela = "";
        Boolean status;

        JTable tabelaTarefas = (JTable) this.view.getTblTarefas();

        if (tabelaTarefas.getSelectedRow() != -1) {
            idTarefa = (int) tabelaTarefas.getValueAt(tabelaTarefas.getSelectedRow(), 0);
            descricao = tabelaTarefas.getValueAt(tabelaTarefas.getSelectedRow(), 1).toString();
            statusTabela = tabelaTarefas.getValueAt(tabelaTarefas.getSelectedRow(), 2).toString();
        }

        if (statusTabela == "Feita") {
            status = true;
        } else {
            status = false;
        }

        TarefaDTO tarefa = new TarefaDTO(idTarefa, descricao, status);

        return tarefa;
    }
}
