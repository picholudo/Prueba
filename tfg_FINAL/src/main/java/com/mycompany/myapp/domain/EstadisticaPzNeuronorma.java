package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EstadisticaPzNeuronorma.
 */
@Entity
@Table(name = "estadistica_pz_neuronorma")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EstadisticaPzNeuronorma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 0)
    @Column(name = "ajuste_estudios", nullable = false)
    private Integer ajusteEstudios;

    @NotNull
    @Column(name = "pz", nullable = false)
    private Float pz;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAjusteEstudios() {
        return ajusteEstudios;
    }

    public EstadisticaPzNeuronorma ajusteEstudios(Integer ajusteEstudios) {
        this.ajusteEstudios = ajusteEstudios;
        return this;
    }

    public void setAjusteEstudios(Integer ajusteEstudios) {
        this.ajusteEstudios = ajusteEstudios;
    }

    public Float getPz() {
        return pz;
    }

    public EstadisticaPzNeuronorma pz(Float pz) {
        this.pz = pz;
        return this;
    }

    public void setPz(Float pz) {
        this.pz = pz;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EstadisticaPzNeuronorma)) {
            return false;
        }
        return id != null && id.equals(((EstadisticaPzNeuronorma) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EstadisticaPzNeuronorma{" +
            "id=" + getId() +
            ", ajusteEstudios=" + getAjusteEstudios() +
            ", pz=" + getPz() +
            "}";
    }
}
