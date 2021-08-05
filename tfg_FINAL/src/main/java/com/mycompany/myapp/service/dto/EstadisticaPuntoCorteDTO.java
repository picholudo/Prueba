package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EstadisticaPuntoCorte} entity.
 */
public class EstadisticaPuntoCorteDTO implements Serializable {

    private Long id;

    @NotNull
    @Min(value = 0)
    private Integer puntoCorte;


    private Long pruebaId;
    private String pruebaNombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPuntoCorte() {
        return puntoCorte;
    }

    public void setPuntoCorte(Integer puntoCorte) {
        this.puntoCorte = puntoCorte;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EstadisticaPuntoCorteDTO estadisticaPuntoCorteDTO = (EstadisticaPuntoCorteDTO) o;
        if (estadisticaPuntoCorteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), estadisticaPuntoCorteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EstadisticaPuntoCorteDTO{" +
            "id=" + getId() +
            ", puntoCorte=" + getPuntoCorte() +
            ", pruebaId=" + getPruebaId() +
            "}";
    }
}
