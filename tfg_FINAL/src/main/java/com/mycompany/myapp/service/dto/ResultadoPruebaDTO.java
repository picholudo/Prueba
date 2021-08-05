package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;

import com.mycompany.myapp.domain.enumeration.TipoPrueba;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ResultadoPrueba} entity.
 */
public class ResultadoPruebaDTO implements Serializable {

    private Long id;

    @Min(value = 0)
    private Integer pd;

    private Float pz;


    private Long pruebaId;
    private String pruebaNombre;

    private Long informeId;
    private String informeNombre;
    
    private TipoPrueba tipoPrueba;
    private Boolean exitoPuntoCorte;

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

    public Float getPz() {
        return pz;
    }

    public void setPz(Float pz) {
        this.pz = pz;
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

    public Long getInformeId() {
        return informeId;
    }

    public void setInformeId(Long informeId) {
        this.informeId = informeId;
    }
    
    public String getInformeNombre() {
		return informeNombre;
	}
    
    public void setInformeNombre(String informeNombre) {
		this.informeNombre = informeNombre;
	}
    
    public TipoPrueba getTipoPrueba() {
		return tipoPrueba;
	}
    
    public void setTipoPrueba(TipoPrueba tipoPrueba) {
		this.tipoPrueba = tipoPrueba;
	}
    
    public Boolean getExitoPuntoCorte() {
		return exitoPuntoCorte;
	}
    
    public void setExitoPuntoCorte(Boolean exitoPuntoCorte) {
		this.exitoPuntoCorte = exitoPuntoCorte;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResultadoPruebaDTO resultadoPruebaDTO = (ResultadoPruebaDTO) o;
        if (resultadoPruebaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resultadoPruebaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResultadoPruebaDTO{" +
            "id=" + getId() +
            ", pd=" + getPd() +
            ", pz=" + getPz() +
            ", tipoPrueba=" + getTipoPrueba() +
            ", exitoPuntoCorte=" + getExitoPuntoCorte() +
            ", pruebaId=" + getPruebaId() +
            ", informeId=" + getInformeId() +
            "}";
    }
}
