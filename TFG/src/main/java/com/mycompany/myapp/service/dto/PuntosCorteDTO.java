package com.mycompany.myapp.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.PuntosCorte} entity.
 */
public class PuntosCorteDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private Instant limite;

    @NotNull
    private Boolean superarlo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Instant getLimite() {
        return limite;
    }

    public void setLimite(Instant limite) {
        this.limite = limite;
    }

    public Boolean isSuperarlo() {
        return superarlo;
    }

    public void setSuperarlo(Boolean superarlo) {
        this.superarlo = superarlo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PuntosCorteDTO puntosCorteDTO = (PuntosCorteDTO) o;
        if (puntosCorteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), puntosCorteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PuntosCorteDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", limite='" + getLimite() + "'" +
            ", superarlo='" + isSuperarlo() + "'" +
            "}";
    }
}
