package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EstadisticaTAVEC} entity.
 */
public class EstadisticaTAVECDTO implements Serializable {

    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    private Float media;

    @NotNull
    @DecimalMin(value = "0")
    private Float desviacionTipica;

    private Long pruebaId;
    private String pruebaNombre;

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

    public Long getPruebaId() {
        return pruebaId;
    }

    public void setPruebaId(Long pruebaId) {
        this.pruebaId = pruebaId;
    }

    public String getPruebaNombre() {
		return pruebaNombre;
	}
    
    public void setPruebaNombre(String pruebaNombre) {
		this.pruebaNombre = pruebaNombre;
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

        EstadisticaTAVECDTO estadisticaTAVECDTO = (EstadisticaTAVECDTO) o;
        if (estadisticaTAVECDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), estadisticaTAVECDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EstadisticaTAVECDTO{" +
            "id=" + getId() +
            ", media=" + getMedia() +
            ", desviacionTipica=" + getDesviacionTipica() +
            ", pruebaId=" + getPruebaId() +
            ", edadTipoPruebaId=" + getEdadTipoPruebaId() +
            "}";
    }
}
