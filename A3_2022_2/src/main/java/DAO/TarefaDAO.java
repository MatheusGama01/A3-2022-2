package DAO;

import DTO.TarefaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TarefaDAO {

    PreparedStatement pstm;
    Connection conn;

    public ArrayList<TarefaDTO> listarTarefas() {
        try {
            ArrayList<TarefaDTO> listaDeTarefas = new ArrayList<>();
            String sql = "SELECT * FROM tarefa";
            conn = new ConexaoDAO().conectaBD();
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                TarefaDTO tarefa = new TarefaDTO();
                tarefa.setDescricao(rs.getString("descricao"));
                listaDeTarefas.add(tarefa);
            }

            pstm.close();
            return listaDeTarefas;
        } catch (SQLException ex) {
            System.out.println("Deu erro em listarTarefas" + ex);
            return null;
        }
    }

    public void criarTarefa(TarefaDTO tarefaDTO) {
        try {
            conn = new ConexaoDAO().conectaBD();
            pstm = conn.prepareStatement("INSERT INTO tarefa(descricao,status) values(?,?)");
            pstm.setString(1, tarefaDTO.getDescricao());
            pstm.setString(2, "a fazer");
            pstm.execute();
        } catch (SQLException ex) {
            System.out.println("Deu erro em criarTarefa" + ex);
        }
    }

    public void atualizarTarefa(TarefaDTO tarefaDTO) {
        try {
            conn = new ConexaoDAO().conectaBD();
            pstm = conn.prepareStatement("UPDATE tarefa SET descricao = ? , status = ? where  id = ?");
            pstm.setString(1, tarefaDTO.getDescricao());
            pstm.setString(2, tarefaDTO.getStatus());
            pstm.setInt(3, tarefaDTO.getId());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Deu erro em atualizarTarefa" + ex);
        }
    }
    
    public void apagarTarefa(TarefaDTO tarefaDTO){
        try{
            conn = new ConexaoDAO().conectaBD();
            pstm = conn.prepareStatement("DELETE FROM tarefa WHERE id = ?");
            pstm.setInt(1, tarefaDTO.getId());
            pstm.executeUpdate();
        } catch (SQLException ex){
            System.out.println("Deu erro em apagarTarefa" + ex);
        }
    }

}
