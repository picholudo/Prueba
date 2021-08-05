package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.mycompany.myapp.domain.enumeration.Sexo;
import com.mycompany.myapp.domain.enumeration.NivelEstudios;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Paciente} entity.
 */
public class PacienteDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer nhc;

    @NotNull
    private String nombre;

    @NotNull
    private Sexo sexo;

    private String profesion;

    @NotNull
    private NivelEstudios estudios;

    @NotNull
    @Min(value = 0)
    private Integer edad;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNhc() {
        return nhc;
    }

    public void setNhc(Integer nhc) {
        this.nhc = nhc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public NivelEstudios getEstudios() {
        return estudios;
    }

    public void setEstudios(NivelEstudios estudios) {
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
            ", nhc=" + getNhc() +
            ", nombre='" + getNombre() + "'" +
            ", sexo='" + getSexo() + "'" +
            ", profesion='" + getProfesion() + "'" +
            ", estudios='" + getEstudios() + "'" +
            ", edad=" + getEdad() +
            "}";
    }
}
