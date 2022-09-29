package DTO;

public class TarefaDTO {

    private int id;
    private String descricao, status;

    public TarefaDTO(String descricao, String statuts) {
        this.descricao = descricao;
        this.status = status;
    }

    public TarefaDTO() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
