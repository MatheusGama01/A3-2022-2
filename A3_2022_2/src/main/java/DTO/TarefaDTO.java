package DTO;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TarefaDTO other = (TarefaDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }
}
