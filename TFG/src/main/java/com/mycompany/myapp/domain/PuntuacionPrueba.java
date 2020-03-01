package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PuntuacionPrueba.
 */
@Entity
@Table(name = "puntuacion_prueba")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PuntuacionPrueba implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor")
    private Long valor;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("puntuacionPruebas")
    private ZScore zscore;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("puntuacionPruebas")
    private Evaluacion paciente;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValor() {
        return valor;
    }

    public PuntuacionPrueba valor(Long valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public ZScore getZscore() {
        return zscore;
    }

    public PuntuacionPrueba zscore(ZScore zScore) {
        this.zscore = zScore;
        return this;
    }

    public void setZscore(ZScore zScore) {
        this.zscore = zScore;
    }

    public Evaluacion getPaciente() {
        return paciente;
    }

    public PuntuacionPrueba paciente(Evaluacion evaluacion) {
        this.paciente = evaluacion;
        return this;
    }

    public void setPaciente(Evaluacion evaluacion) {
        this.paciente = evaluacion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PuntuacionPrueba)) {
            return false;
        }
        return id != null && id.equals(((PuntuacionPrueba) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PuntuacionPrueba{" +
            "id=" + getId() +
            ", valor=" + getValor() +
            "}";
    }
}
