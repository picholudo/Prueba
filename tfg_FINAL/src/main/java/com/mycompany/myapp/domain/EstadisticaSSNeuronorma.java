package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EstadisticaSSNeuronorma.
 */
@Entity
@Table(name = "estadistica_ss_neuronorma")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EstadisticaSSNeuronorma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 0)
    @Column(name = "pd", nullable = false)
    private Integer pd;

    @NotNull
    @Min(value = 0)
    @Column(name = "scaled_score", nullable = false)
    private Integer scaledScore;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estadisticaSSNeuronormas")
    private Prueba prueba;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estadisticaSSNeuronormas")
    private EdadTipoPrueba edadTipoPrueba;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPd() {
        return pd;
    }

    public EstadisticaSSNeuronorma pd(Integer pd) {
        this.pd = pd;
        return this;
    }

    public void setPd(Integer pd) {
        this.pd = pd;
    }

    public Integer getScaledScore() {
        return scaledScore;
    }

    public EstadisticaSSNeuronorma scaledScore(Integer scaledScore) {
        this.scaledScore = scaledScore;
        return this;
    }

    public void setScaledScore(Integer scaledScore) {
        this.scaledScore = scaledScore;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public EstadisticaSSNeuronorma prueba(Prueba prueba) {
        this.prueba = prueba;
        return this;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public EdadTipoPrueba getEdadTipoPrueba() {
        return edadTipoPrueba;
    }

    public EstadisticaSSNeuronorma edadTipoPrueba(EdadTipoPrueba edadTipoPrueba) {
        this.edadTipoPrueba = edadTipoPrueba;
        return this;
    }

    public void setEdadTipoPrueba(EdadTipoPrueba edadTipoPrueba) {
        this.edadTipoPrueba = edadTipoPrueba;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EstadisticaSSNeuronorma)) {
            return false;
        }
        return id != null && id.equals(((EstadisticaSSNeuronorma) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EstadisticaSSNeuronorma{" +
            "id=" + getId() +
            ", pd=" + getPd() +
            ", scaledScore=" + getScaledScore() +
            "}";
    }
}
