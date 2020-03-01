package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.Sexo;

import com.mycompany.myapp.domain.enumeration.Nivelestudio;

/**
 * A Paciente.
 */
@Entity
@Table(name = "paciente")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    private Sexo sexo;

    @Column(name = "profesion")
    private String profesion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estudios", nullable = false)
    private Nivelestudio estudios;

    @NotNull
    @Min(value = 60)
    @Max(value = 120)
    @Column(name = "edad", nullable = false)
    private Integer edad;

    @OneToMany(mappedBy = "paciente")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Evaluacion> evaluacions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Paciente sexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getProfesion() {
        return profesion;
    }

    public Paciente profesion(String profesion) {
        this.profesion = profesion;
        return this;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Nivelestudio getEstudios() {
        return estudios;
    }

    public Paciente estudios(Nivelestudio estudios) {
        this.estudios = estudios;
        return this;
    }

    public void setEstudios(Nivelestudio estudios) {
        this.estudios = estudios;
    }

    public Integer getEdad() {
        return edad;
    }

    public Paciente edad(Integer edad) {
        this.edad = edad;
        return this;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Set<Evaluacion> getEvaluacions() {
        return evaluacions;
    }

    public Paciente evaluacions(Set<Evaluacion> evaluacions) {
        this.evaluacions = evaluacions;
        return this;
    }

    public Paciente addEvaluacion(Evaluacion evaluacion) {
        this.evaluacions.add(evaluacion);
        evaluacion.setPaciente(this);
        return this;
    }

    public Paciente removeEvaluacion(Evaluacion evaluacion) {
        this.evaluacions.remove(evaluacion);
        evaluacion.setPaciente(null);
        return this;
    }

    public void setEvaluacions(Set<Evaluacion> evaluacions) {
        this.evaluacions = evaluacions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Paciente)) {
            return false;
        }
        return id != null && id.equals(((Paciente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Paciente{" +
            "id=" + getId() +
            ", sexo='" + getSexo() + "'" +
            ", profesion='" + getProfesion() + "'" +
            ", estudios='" + getEstudios() + "'" +
            ", edad=" + getEdad() +
            "}";
    }
}
