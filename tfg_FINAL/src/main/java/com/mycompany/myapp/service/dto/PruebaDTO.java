package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.mycompany.myapp.domain.enumeration.TipoPrueba;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Prueba} entity.
 */
public class PruebaDTO implements Serializable {

    private Long id;

    @NotNull
    private TipoPrueba tipoPrueba;

    @NotNull
    private String nombre;

    @NotNull
    private String codigo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPrueba getTipoPrueba() {
        return tipoPrueba;
    }

    public void setTipoPrueba(TipoPrueba tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PruebaDTO pruebaDTO = (PruebaDTO) o;
        if (pruebaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pruebaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PruebaDTO{" +
            "id=" + getId() +
            ", tipoPrueba='" + getTipoPrueba() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", codigo='" + getCodigo() + "'" +
            "}";
    }
}
