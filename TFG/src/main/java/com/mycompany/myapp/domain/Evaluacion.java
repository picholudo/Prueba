package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.Sospecha;

/**
 * A Evaluacion.
 */
@Entity
@Table(name = "evaluacion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Evaluacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "valoracion", nullable = false)
    private Sospecha valoracion;

    @OneToMany(mappedBy = "paciente")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PuntuacionPrueba> puntuacionPruebas = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("evaluacions")
    private Paciente paciente;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Evaluacion fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Sospecha getValoracion() {
        return valoracion;
    }

    public Evaluacion valoracion(Sospecha valoracion) {
        this.valoracion = valoracion;
        return this;
    }

    public void setValoracion(Sospecha valoracion) {
        this.valoracion = valoracion;
    }

    public Set<PuntuacionPrueba> getPuntuacionPruebas() {
        return puntuacionPruebas;
    }

    public Evaluacion puntuacionPruebas(Set<PuntuacionPrueba> puntuacionPruebas) {
        this.puntuacionPruebas = puntuacionPruebas;
        return this;
    }

    public Evaluacion addPuntuacionPrueba(PuntuacionPrueba puntuacionPrueba) {
        this.puntuacionPruebas.add(puntuacionPrueba);
        puntuacionPrueba.setPaciente(this);
        return this;
    }

    public Evaluacion removePuntuacionPrueba(PuntuacionPrueba puntuacionPrueba) {
        this.puntuacionPruebas.remove(puntuacionPrueba);
        puntuacionPrueba.setPaciente(null);
        return this;
    }

    public void setPuntuacionPruebas(Set<PuntuacionPrueba> puntuacionPruebas) {
        this.puntuacionPruebas = puntuacionPruebas;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Evaluacion paciente(Paciente paciente) {
        this.paciente = paciente;
        return this;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Evaluacion)) {
            return false;
        }
        return id != null && id.equals(((Evaluacion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Evaluacion{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", valoracion='" + getValoracion() + "'" +
            "}";
    }
}
