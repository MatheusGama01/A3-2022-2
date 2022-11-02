package DTO;

public class TarefaDTO {

    private int id, idUsuario;
    private String descricao;
    Boolean status;

    public TarefaDTO(int id, String descricao, Boolean status) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
    }

    public TarefaDTO(int id, String descricao, Boolean status, int idUsuario) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.idUsuario = idUsuario;
    }

    public TarefaDTO(String descricao, Boolean status) {
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
