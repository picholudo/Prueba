package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EstadisticaTAVEC.
 */
@Entity
@Table(name = "estadistica_tavec")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EstadisticaTAVEC implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "media", nullable = false)
    private Float media;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "desviacion_tipica", nullable = false)
    private Float desviacionTipica;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estadisticaTAVECS")
    private Prueba prueba;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estadisticaTAVECS")
    private EdadTipoPrueba edadTipoPrueba;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMedia() {
        return media;
    }

    public EstadisticaTAVEC media(Float media) {
        this.media = media;
        return this;
    }

    public void setMedia(Float media) {
        this.media = media;
    }

    public Float getDesviacionTipica() {
        return desviacionTipica;
    }

    public EstadisticaTAVEC desviacionTipica(Float desviacionTipica) {
        this.desviacionTipica = desviacionTipica;
        return this;
    }

    public void setDesviacionTipica(Float desviacionTipica) {
        this.desviacionTipica = desviacionTipica;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public EstadisticaTAVEC prueba(Prueba prueba) {
        this.prueba = prueba;
        return this;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public EdadTipoPrueba getEdadTipoPrueba() {
        return edadTipoPrueba;
    }

    public EstadisticaTAVEC edadTipoPrueba(EdadTipoPrueba edadTipoPrueba) {
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
        if (!(o instanceof EstadisticaTAVEC)) {
            return false;
        }
        return id != null && id.equals(((EstadisticaTAVEC) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EstadisticaTAVEC{" +
            "id=" + getId() +
            ", media=" + getMedia() +
            ", desviacionTipica=" + getDesviacionTipica() +
            "}";
    }
}
