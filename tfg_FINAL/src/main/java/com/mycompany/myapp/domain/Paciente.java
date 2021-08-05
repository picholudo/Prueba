package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.Sexo;

import com.mycompany.myapp.domain.enumeration.NivelEstudios;

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
    @Column(name = "nhc", nullable = false, unique = true)
    private Integer nhc;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    private Sexo sexo;

    @Column(name = "profesion")
    private String profesion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estudios", nullable = false)
    private NivelEstudios estudios;

    @NotNull
    @Min(value = 0)
    @Column(name = "edad", nullable = false)
    private Integer edad;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Informe> informes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNhc() {
        return nhc;
    }

    public Paciente nhc(Integer nhc) {
        this.nhc = nhc;
        return this;
    }

    public void setNhc(Integer nhc) {
        this.nhc = nhc;
    }

    public String getNombre() {
        return nombre;
    }

    public Paciente nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public NivelEstudios getEstudios() {
        return estudios;
    }

    public Paciente estudios(NivelEstudios estudios) {
        this.estudios = estudios;
        return this;
    }

    public void setEstudios(NivelEstudios estudios) {
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

    public Set<Informe> getInformes() {
        return informes;
    }

    public Paciente informes(Set<Informe> informes) {
        this.informes = informes;
        return this;
    }

    public Paciente addInforme(Informe informe) {
        this.informes.add(informe);
        informe.setPaciente(this);
        return this;
    }

    public Paciente removeInforme(Informe informe) {
        this.informes.remove(informe);
        informe.setPaciente(null);
        return this;
    }

    public void setInformes(Set<Informe> informes) {
        this.informes = informes;
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
            ", nhc=" + getNhc() +
            ", nombre='" + getNombre() + "'" +
            ", sexo='" + getSexo() + "'" +
            ", profesion='" + getProfesion() + "'" +
            ", estudios='" + getEstudios() + "'" +
            ", edad=" + getEdad() +
            "}";
    }
}
