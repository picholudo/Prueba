package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.mycompany.myapp.domain.enumeration.TipoPrueba;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EdadTipoPrueba} entity.
 */
public class EdadTipoPruebaDTO implements Serializable {

    private Long id;

    @NotNull
    private String codigo;

    @NotNull
    private Integer edadMinima;

    @NotNull
    private Integer edadMaxima;

    @NotNull
    private TipoPrueba tipoPrueba;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }

    public Integer getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    public TipoPrueba getTipoPrueba() {
        return tipoPrueba;
    }

    public void setTipoPrueba(TipoPrueba tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EdadTipoPruebaDTO edadTipoPruebaDTO = (EdadTipoPruebaDTO) o;
        if (edadTipoPruebaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), edadTipoPruebaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EdadTipoPruebaDTO{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", edadMinima=" + getEdadMinima() +
            ", edadMaxima=" + getEdadMaxima() +
            ", tipoPrueba='" + getTipoPrueba() + "'" +
            "}";
    }
}
