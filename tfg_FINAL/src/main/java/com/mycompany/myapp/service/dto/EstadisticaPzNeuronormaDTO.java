package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EstadisticaPzNeuronorma} entity.
 */
public class EstadisticaPzNeuronormaDTO implements Serializable {

    private Long id;

    @NotNull
    @Min(value = 0)
    private Integer ajusteEstudios;

    @NotNull
    private Float pz;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAjusteEstudios() {
        return ajusteEstudios;
    }

    public void setAjusteEstudios(Integer ajusteEstudios) {
        this.ajusteEstudios = ajusteEstudios;
    }

    public Float getPz() {
        return pz;
    }

    public void setPz(Float pz) {
        this.pz = pz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EstadisticaPzNeuronormaDTO estadisticaPzNeuronormaDTO = (EstadisticaPzNeuronormaDTO) o;
        if (estadisticaPzNeuronormaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), estadisticaPzNeuronormaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EstadisticaPzNeuronormaDTO{" +
            "id=" + getId() +
            ", ajusteEstudios=" + getAjusteEstudios() +
            ", pz=" + getPz() +
            "}";
    }
}
