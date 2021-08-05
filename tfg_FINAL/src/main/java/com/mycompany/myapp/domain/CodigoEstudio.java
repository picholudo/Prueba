package com.mycompany.myapp.domain;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.mycompany.myapp.domain.enumeration.NivelEstudios;

/**
 * A CodigoEstudio.
 */
@Entity
@Table(name = "codigo_estudio")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CodigoEstudio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_estudios", nullable = false, unique = true)
    private NivelEstudios nivelEstudios;

    @NotNull
    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NivelEstudios getNivelEstudios() {
        return nivelEstudios;
    }

    public CodigoEstudio nivelEstudios(NivelEstudios nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
        return this;
    }

    public void setNivelEstudios(NivelEstudios nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
    }

    public String getCodigo() {
        return codigo;
    }

    public CodigoEstudio codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Transient
    public String getNombre() {
    	StringBuilder sb = new StringBuilder()
    			.append(getCodigo())
    			.append(" - ")
    			.append(getNivelEstudios() != null ? getNivelEstudios() : "")
    			;
    	return sb.toString();
    }
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CodigoEstudio)) {
            return false;
        }
        return id != null && id.equals(((CodigoEstudio) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CodigoEstudio{" +
            "id=" + getId() +
            ", nivelEstudios='" + getNivelEstudios() + "'" +
            ", codigo='" + getCodigo() + "'" +
            "}";
    }
}
