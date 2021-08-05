package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EstadisticaTBA.
 */
@Entity
@Table(name = "estadistica_tba")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EstadisticaTBA implements Serializable {

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
    @JsonIgnoreProperties("estadisticaTBAS")
    private Prueba prueba;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estadisticaTBAS")
    private CodigoEstudio codigoEstudio;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estadisticaTBAS")
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

    public EstadisticaTBA media(Float media) {
        this.media = media;
        return this;
    }

    public void setMedia(Float media) {
        this.media = media;
    }

    public Float getDesviacionTipica() {
        return desviacionTipica;
    }

    public EstadisticaTBA desviacionTipica(Float desviacionTipica) {
        this.desviacionTipica = desviacionTipica;
        return this;
    }

    public void setDesviacionTipica(Float desviacionTipica) {
        this.desviacionTipica = desviacionTipica;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public EstadisticaTBA prueba(Prueba prueba) {
        this.prueba = prueba;
        return this;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public CodigoEstudio getCodigoEstudio() {
        return codigoEstudio;
    }

    public EstadisticaTBA codigoEstudio(CodigoEstudio codigoEstudio) {
        this.codigoEstudio = codigoEstudio;
        return this;
    }

    public void setCodigoEstudio(CodigoEstudio codigoEstudio) {
        this.codigoEstudio = codigoEstudio;
    }

    public EdadTipoPrueba getEdadTipoPrueba() {
        return edadTipoPrueba;
    }

    public EstadisticaTBA edadTipoPrueba(EdadTipoPrueba edadTipoPrueba) {
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
        if (!(o instanceof EstadisticaTBA)) {
            return false;
        }
        return id != null && id.equals(((EstadisticaTBA) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EstadisticaTBA{" +
            "id=" + getId() +
            ", media=" + getMedia() +
            ", desviacionTipica=" + getDesviacionTipica() +
            "}";
    }
}
