package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EstadisticaAjusteNeuronorma} entity.
 */
public class EstadisticaAjusteNeuronormaDTO implements Serializable {

    private Long id;

    @NotNull
    @Min(value = 0)
    private Integer scaledScore;

    @NotNull
    @Min(value = 0)
    private Integer ajusteEstudios;


    private Long pruebaId;
    private String pruebaNombre;

    private Long codigoEstudioId;
    private String codigoEstudioNivelEstudios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScaledScore() {
        return scaledScore;
    }

    public void setScaledScore(Integer scaledScore) {
        this.scaledScore = scaledScore;
    }

    public Integer getAjusteEstudios() {
        return ajusteEstudios;
    }

    public void setAjusteEstudios(Integer ajusteEstudios) {
        this.ajusteEstudios = ajusteEstudios;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EstadisticaAjusteNeuronormaDTO estadisticaAjusteNeuronormaDTO = (EstadisticaAjusteNeuronormaDTO) o;
        if (estadisticaAjusteNeuronormaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), estadisticaAjusteNeuronormaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EstadisticaAjusteNeuronormaDTO{" +
            "id=" + getId() +
            ", scaledScore=" + getScaledScore() +
            ", ajusteEstudios=" + getAjusteEstudios() +
            ", pruebaId=" + getPruebaId() +
            ", codigoEstudioId=" + getCodigoEstudioId() +
            "}";
    }
}
