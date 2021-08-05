package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.mycompany.myapp.domain.enumeration.TipoPrueba;

/**
 * A EdadTipoPrueba.
 */
@Entity
@Table(name = "edad_tipo_prueba")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EdadTipoPrueba implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "codigo", nullable = false)
    private String codigo;

    @NotNull
    @Column(name = "edad_minima", nullable = false)
    private Integer edadMinima;

    @NotNull
    @Column(name = "edad_maxima", nullable = false)
    private Integer edadMaxima;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_prueba", nullable = false)
    private TipoPrueba tipoPrueba;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public EdadTipoPrueba codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getEdadMinima() {
        return edadMinima;
    }

    public EdadTipoPrueba edadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
        return this;
    }

    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }

    public Integer getEdadMaxima() {
        return edadMaxima;
    }

    public EdadTipoPrueba edadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
        return this;
    }

    public void setEdadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    public TipoPrueba getTipoPrueba() {
        return tipoPrueba;
    }

    public EdadTipoPrueba tipoPrueba(TipoPrueba tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
        return this;
    }

    public void setTipoPrueba(TipoPrueba tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove
    
    @Transient
    public String getNombre() {
    	StringBuilder sb = new StringBuilder()
    			.append(getCodigo())
    			.append(" - [")
    			.append(getEdadMinima())
    			.append(", ")
    			.append(getEdadMaxima())
    			.append("] (")
    			.append(getTipoPrueba() != null ? getTipoPrueba() : "")
    			.append(")")
    			;
    	return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EdadTipoPrueba)) {
            return false;
        }
        return id != null && id.equals(((EdadTipoPrueba) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EdadTipoPrueba{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", edadMinima=" + getEdadMinima() +
            ", edadMaxima=" + getEdadMaxima() +
            ", tipoPrueba='" + getTipoPrueba() + "'" +
            "}";
    }
}
