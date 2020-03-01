package com.mycompany.myapp.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.mycompany.myapp.domain.enumeration.Sospecha;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Evaluacion} entity.
 */
public class EvaluacionDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private Sospecha valoracion;


    private Long pacienteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Sospecha getValoracion() {
        return valoracion;
    }

    public void setValoracion(Sospecha valoracion) {
        this.valoracion = valoracion;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EvaluacionDTO evaluacionDTO = (EvaluacionDTO) o;
        if (evaluacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluacionDTO{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", valoracion='" + getValoracion() + "'" +
            ", pacienteId=" + getPacienteId() +
            "}";
    }
}
