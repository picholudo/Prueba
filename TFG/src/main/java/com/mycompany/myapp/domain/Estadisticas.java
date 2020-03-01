package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.mycompany.myapp.domain.enumeration.Nivelestudio;

import com.mycompany.myapp.domain.enumeration.Sexo;

/**
 * A Estadisticas.
 */
@Entity
@Table(name = "estadisticas")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Estadisticas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 60)
    @Max(value = 120)
    @Column(name = "edad")
    private Integer edad;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estudios", nullable = false)
    private Nivelestudio estudios;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    private Sexo sexo;

    @NotNull
    @Column(name = "prueba", nullable = false)
    private String prueba;

    @NotNull
    @Column(name = "media", nullable = false)
    private Long media;

    @NotNull
    @Column(name = "desviacion", nullable = false)
    private Long desviacion;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estadisticas")
    private ZScore zscore;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEdad() {
        return edad;
    }

    public Estadisticas edad(Integer edad) {
        this.edad = edad;
        return this;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Nivelestudio getEstudios() {
        return estudios;
    }

    public Estadisticas estudios(Nivelestudio estudios) {
        this.estudios = estudios;
        return this;
    }

    public void setEstudios(Nivelestudio estudios) {
        this.estudios = estudios;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Estadisticas sexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getPrueba() {
        return prueba;
    }

    public Estadisticas prueba(String prueba) {
        this.prueba = prueba;
        return this;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public Long getMedia() {
        return media;
    }

    public Estadisticas media(Long media) {
        this.media = media;
        return this;
    }

    public void setMedia(Long media) {
        this.media = media;
    }

    public Long getDesviacion() {
        return desviacion;
    }

    public Estadisticas desviacion(Long desviacion) {
        this.desviacion = desviacion;
        return this;
    }

    public void setDesviacion(Long desviacion) {
        this.desviacion = desviacion;
    }

    public ZScore getZscore() {
        return zscore;
    }

    public Estadisticas zscore(ZScore zScore) {
        this.zscore = zScore;
        return this;
    }

    public void setZscore(ZScore zScore) {
        this.zscore = zScore;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Estadisticas)) {
            return false;
        }
        return id != null && id.equals(((Estadisticas) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Estadisticas{" +
            "id=" + getId() +
            ", edad=" + getEdad() +
            ", estudios='" + getEstudios() + "'" +
            ", sexo='" + getSexo() + "'" +
            ", prueba='" + getPrueba() + "'" +
            ", media=" + getMedia() +
            ", desviacion=" + getDesviacion() +
            "}";
    }
}
