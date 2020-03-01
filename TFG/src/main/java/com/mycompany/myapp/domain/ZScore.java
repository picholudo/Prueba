package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ZScore.
 */
@Entity
@Table(name = "z_score")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ZScore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "zscore")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Estadisticas> estadisticas = new HashSet<>();

    @OneToMany(mappedBy = "zscore")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PuntuacionPrueba> puntuacionPruebas = new HashSet<>();

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

    public ZScore nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Estadisticas> getEstadisticas() {
        return estadisticas;
    }

    public ZScore estadisticas(Set<Estadisticas> estadisticas) {
        this.estadisticas = estadisticas;
        return this;
    }

    public ZScore addEstadisticas(Estadisticas estadisticas) {
        this.estadisticas.add(estadisticas);
        estadisticas.setZscore(this);
        return this;
    }

    public ZScore removeEstadisticas(Estadisticas estadisticas) {
        this.estadisticas.remove(estadisticas);
        estadisticas.setZscore(null);
        return this;
    }

    public void setEstadisticas(Set<Estadisticas> estadisticas) {
        this.estadisticas = estadisticas;
    }

    public Set<PuntuacionPrueba> getPuntuacionPruebas() {
        return puntuacionPruebas;
    }

    public ZScore puntuacionPruebas(Set<PuntuacionPrueba> puntuacionPruebas) {
        this.puntuacionPruebas = puntuacionPruebas;
        return this;
    }

    public ZScore addPuntuacionPrueba(PuntuacionPrueba puntuacionPrueba) {
        this.puntuacionPruebas.add(puntuacionPrueba);
        puntuacionPrueba.setZscore(this);
        return this;
    }

    public ZScore removePuntuacionPrueba(PuntuacionPrueba puntuacionPrueba) {
        this.puntuacionPruebas.remove(puntuacionPrueba);
        puntuacionPrueba.setZscore(null);
        return this;
    }

    public void setPuntuacionPruebas(Set<PuntuacionPrueba> puntuacionPruebas) {
        this.puntuacionPruebas = puntuacionPruebas;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ZScore)) {
            return false;
        }
        return id != null && id.equals(((ZScore) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ZScore{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
