package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A PuntosCorte.
 */
@Entity
@Table(name = "puntos_corte")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PuntosCorte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "limite", nullable = false)
    private Instant limite;

    @NotNull
    @Column(name = "superarlo", nullable = false)
    private Boolean superarlo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public PuntosCorte nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Instant getLimite() {
        return limite;
    }

    public PuntosCorte limite(Instant limite) {
        this.limite = limite;
        return this;
    }

    public void setLimite(Instant limite) {
        this.limite = limite;
    }

    public Boolean isSuperarlo() {
        return superarlo;
    }

    public PuntosCorte superarlo(Boolean superarlo) {
        this.superarlo = superarlo;
        return this;
    }

    public void setSuperarlo(Boolean superarlo) {
        this.superarlo = superarlo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PuntosCorte)) {
            return false;
        }
        return id != null && id.equals(((PuntosCorte) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PuntosCorte{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", limite='" + getLimite() + "'" +
            ", superarlo='" + isSuperarlo() + "'" +
            "}";
    }
}
