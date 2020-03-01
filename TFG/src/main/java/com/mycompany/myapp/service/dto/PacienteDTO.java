package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.mycompany.myapp.domain.enumeration.Sexo;
import com.mycompany.myapp.domain.enumeration.Nivelestudio;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Paciente} entity.
 */
public class PacienteDTO implements Serializable {

    private Long id;

    @NotNull
    private Sexo sexo;

    private String profesion;

    @NotNull
    private Nivelestudio estudios;

    @NotNull
    @Min(value = 60)
    @Max(value = 120)
    private Integer edad;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Nivelestudio getEstudios() {
        return estudios;
    }

    public void setEstudios(Nivelestudio estudios) {
        this.estudios = estudios;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PacienteDTO pacienteDTO = (PacienteDTO) o;
        if (pacienteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pacienteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PacienteDTO{" +
            "id=" + getId() +
            ", sexo='" + getSexo() + "'" +
            ", profesion='" + getProfesion() + "'" +
            ", estudios='" + getEstudios() + "'" +
            ", edad=" + getEdad() +
            "}";
    }
}
