package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EstadisticaMMSE} entity.
 */
public class EstadisticaMMSEDTO implements Serializable {

    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    private Float media;

    @NotNull
    @DecimalMin(value = "0")
    private Float desviacionTipica;


    private Long codigoEstudioId;
    private String codigoEstudioNivelEstudios;

    private Long edadTipoPruebaId;
    private String edadTipoPruebaTipoPrueba;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMedia() {
        return media;
    }

    public void setMedia(Float media) {
        this.media = media;
    }

    public Float getDesviacionTipica() {
        return desviacionTipica;
    }

    public void setDesviacionTipica(Float desviacionTipica) {
        this.desviacionTipica = desviacionTipica;
    }

    public Long getCodigoEstudioId() {
        return codigoEstudioId;
    }

    public void setCodigoEstudioId(Long codigoEstudioId) {
        this.codigoEstudioId = codigoEstudioId;
    }

    public String getCodigoEstudioNivelEstudios() {
        return codigoEstudioNivelEstudios;
    }

    public void setCodigoEstudioNivelEstudios(String codigoEstudioNivelEstudios) {
        this.codigoEstudioNivelEstudios = codigoEstudioNivelEstudios;
    }

    public Long getEdadTipoPruebaId() {
        return edadTipoPruebaId;
    }

    public void setEdadTipoPruebaId(Long edadTipoPruebaId) {
        this.edadTipoPruebaId = edadTipoPruebaId;
    }

    public String getEdadTipoPruebaTipoPrueba() {
        return edadTipoPruebaTipoPrueba;
    }

    public void setEdadTipoPruebaTipoPrueba(String edadTipoPruebaTipoPrueba) {
        this.edadTipoPruebaTipoPrueba = edadTipoPruebaTipoPrueba;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EstadisticaMMSEDTO estadisticaMMSEDTO = (EstadisticaMMSEDTO) o;
        if (estadisticaMMSEDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), estadisticaMMSEDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EstadisticaMMSEDTO{" +
            "id=" + getId() +
            ", media=" + getMedia() +
            ", desviacionTipica=" + getDesviacionTipica() +
            ", codigoEstudioId=" + getCodigoEstudioId() +
            ", codigoEstudioNivelEstudios='" + getCodigoEstudioNivelEstudios() + "'" +
            ", edadTipoPruebaId=" + getEdadTipoPruebaId() +
            ", edadTipoPruebaTipoPrueba='" + getEdadTipoPruebaTipoPrueba() + "'" +
            "}";
    }
}
