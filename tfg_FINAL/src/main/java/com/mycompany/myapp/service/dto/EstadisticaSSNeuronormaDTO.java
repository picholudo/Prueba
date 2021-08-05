package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EstadisticaSSNeuronorma} entity.
 */
public class EstadisticaSSNeuronormaDTO implements Serializable {

    private Long id;

    @NotNull
    @Min(value = 0)
    private Integer pd;

    @NotNull
    @Min(value = 0)
    private Integer scaledScore;


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

    public Integer getPd() {
        return pd;
    }

    public void setPd(Integer pd) {
        this.pd = pd;
    }

    public Integer getScaledScore() {
        return scaledScore;
    }

    public void setScaledScore(Integer scaledScore) {
        this.scaledScore = scaledScore;
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

        EstadisticaSSNeuronormaDTO estadisticaSSNeuronormaDTO = (EstadisticaSSNeuronormaDTO) o;
        if (estadisticaSSNeuronormaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), estadisticaSSNeuronormaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EstadisticaSSNeuronormaDTO{" +
            "id=" + getId() +
            ", pd=" + getPd() +
            ", scaledScore=" + getScaledScore() +
            ", pruebaId=" + getPruebaId() +
            ", edadTipoPruebaId=" + getEdadTipoPruebaId() +
            "}";
    }
}
