package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EstadisticaAjusteNeuronorma.
 */
@Entity
@Table(name = "estadistica_ajuste_neuronorma")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EstadisticaAjusteNeuronorma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 0)
    @Column(name = "scaled_score", nullable = false)
    private Integer scaledScore;

    @NotNull
    @Min(value = 0)
    @Column(name = "ajuste_estudios", nullable = false)
    private Integer ajusteEstudios;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estadisticaAjusteNeuronormas")
    private Prueba prueba;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estadisticaAjusteNeuronormas")
    private CodigoEstudio codigoEstudio;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScaledScore() {
        return scaledScore;
    }

    public EstadisticaAjusteNeuronorma scaledScore(Integer scaledScore) {
        this.scaledScore = scaledScore;
        return this;
    }

    public void setScaledScore(Integer scaledScore) {
        this.scaledScore = scaledScore;
    }

    public Integer getAjusteEstudios() {
        return ajusteEstudios;
    }

    public EstadisticaAjusteNeuronorma ajusteEstudios(Integer ajusteEstudios) {
        this.ajusteEstudios = ajusteEstudios;
        return this;
    }

    public void setAjusteEstudios(Integer ajusteEstudios) {
        this.ajusteEstudios = ajusteEstudios;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public EstadisticaAjusteNeuronorma prueba(Prueba prueba) {
        this.prueba = prueba;
        return this;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public CodigoEstudio getCodigoEstudio() {
        return codigoEstudio;
    }

    public EstadisticaAjusteNeuronorma codigoEstudio(CodigoEstudio codigoEstudio) {
        this.codigoEstudio = codigoEstudio;
        return this;
    }

    public void setCodigoEstudio(CodigoEstudio codigoEstudio) {
        this.codigoEstudio = codigoEstudio;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EstadisticaAjusteNeuronorma)) {
            return false;
        }
        return id != null && id.equals(((EstadisticaAjusteNeuronorma) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EstadisticaAjusteNeuronorma{" +
            "id=" + getId() +
            ", scaledScore=" + getScaledScore() +
            ", ajusteEstudios=" + getAjusteEstudios() +
            "}";
    }
}
