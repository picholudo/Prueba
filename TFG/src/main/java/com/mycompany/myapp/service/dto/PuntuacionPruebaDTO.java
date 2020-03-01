package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.PuntuacionPrueba} entity.
 */
public class PuntuacionPruebaDTO implements Serializable {

    private Long id;

    private Long valor;


    private Long zscoreId;

    private String zscoreNombre;

    private Long pacienteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Long getZscoreId() {
        return zscoreId;
    }

    public void setZscoreId(Long zScoreId) {
        this.zscoreId = zScoreId;
    }

    public String getZscoreNombre() {
        return zscoreNombre;
    }

    public void setZscoreNombre(String zScoreNombre) {
        this.zscoreNombre = zScoreNombre;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long evaluacionId) {
        this.pacienteId = evaluacionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PuntuacionPruebaDTO puntuacionPruebaDTO = (PuntuacionPruebaDTO) o;
        if (puntuacionPruebaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), puntuacionPruebaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PuntuacionPruebaDTO{" +
            "id=" + getId() +
            ", valor=" + getValor() +
            ", zscoreId=" + getZscoreId() +
            ", zscoreNombre='" + getZscoreNombre() + "'" +
            ", pacienteId=" + getPacienteId() +
            "}";
    }
}
