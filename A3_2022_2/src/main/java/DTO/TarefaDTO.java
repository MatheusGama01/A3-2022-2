package DTO;

public class TarefaDTO {

    private int id;
    private String descricao;
    Boolean status;

    public TarefaDTO(String descricao, Boolean statuts) {
        this.descricao = descricao;
        this.status = status;
    }

    public TarefaDTO(int id, String descricao, Boolean statuts) {
        this.id = id;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
