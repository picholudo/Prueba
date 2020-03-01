package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.mycompany.myapp.domain.enumeration.Nivelestudio;
import com.mycompany.myapp.domain.enumeration.Sexo;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Estadisticas} entity.
 */
public class EstadisticasDTO implements Serializable {

    private Long id;

    @Min(value = 60)
    @Max(value = 120)
    private Integer edad;

    @NotNull
    private Nivelestudio estudios;

    @NotNull
    private Sexo sexo;

    @NotNull
    private String prueba;

    @NotNull
    private Long media;

    @NotNull
    private Long desviacion;


    private Long zscoreId;

    private String zscoreNombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Nivelestudio getEstudios() {
        return estudios;
    }

    public void setEstudios(Nivelestudio estudios) {
        this.estudios = estudios;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public Long getMedia() {
        return media;
    }

    public void setMedia(Long media) {
        this.media = media;
    }

    public Long getDesviacion() {
        return desviacion;
    }

    public void setDesviacion(Long desviacion) {
        this.desviacion = desviacion;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EstadisticasDTO estadisticasDTO = (EstadisticasDTO) o;
        if (estadisticasDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), estadisticasDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EstadisticasDTO{" +
            "id=" + getId() +
            ", edad=" + getEdad() +
            ", estudios='" + getEstudios() + "'" +
            ", sexo='" + getSexo() + "'" +
            ", prueba='" + getPrueba() + "'" +
            ", media=" + getMedia() +
            ", desviacion=" + getDesviacion() +
            ", zscoreId=" + getZscoreId() +
            ", zscoreNombre='" + getZscoreNombre() + "'" +
            "}";
    }
}
