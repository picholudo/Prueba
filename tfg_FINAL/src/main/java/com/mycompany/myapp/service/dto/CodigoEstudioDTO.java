package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.mycompany.myapp.domain.enumeration.NivelEstudios;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.CodigoEstudio} entity.
 */
public class CodigoEstudioDTO implements Serializable {

    private Long id;

    @NotNull
    private NivelEstudios nivelEstudios;

    @NotNull
    private String codigo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NivelEstudios getNivelEstudios() {
        return nivelEstudios;
    }

    public void setNivelEstudios(NivelEstudios nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
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

        CodigoEstudioDTO codigoEstudioDTO = (CodigoEstudioDTO) o;
        if (codigoEstudioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), codigoEstudioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CodigoEstudioDTO{" +
            "id=" + getId() +
            ", nivelEstudios='" + getNivelEstudios() + "'" +
            ", codigo='" + getCodigo() + "'" +
            "}";
    }
}
