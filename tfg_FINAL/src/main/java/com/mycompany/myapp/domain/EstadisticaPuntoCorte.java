package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EstadisticaPuntoCorte.
 */
@Entity
@Table(name = "estadistica_punto_corte")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EstadisticaPuntoCorte implements Serializable {
	
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 0)
    @Column(name = "punto_corte", nullable = false)
    private Integer puntoCorte;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estadisticaPuntoCortes")
    private Prueba prueba;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPuntoCorte() {
        return puntoCorte;
    }

    public EstadisticaPuntoCorte puntoCorte(Integer puntoCorte) {
        this.puntoCorte = puntoCorte;
        return this;
    }

    public void setPuntoCorte(Integer puntoCorte) {
        this.puntoCorte = puntoCorte;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public EstadisticaPuntoCorte prueba(Prueba prueba) {
        this.prueba = prueba;
        return this;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EstadisticaPuntoCorte)) {
            return false;
        }
        return id != null && id.equals(((EstadisticaPuntoCorte) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EstadisticaPuntoCorte{" +
            "id=" + getId() +
            ", puntoCorte=" + getPuntoCorte() +
            "}";
    }
}
