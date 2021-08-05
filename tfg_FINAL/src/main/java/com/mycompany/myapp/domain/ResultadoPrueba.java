package com.mycompany.myapp.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.TipoPrueba;

/**
 * A ResultadoPrueba.
 */
@Entity
@Table(name = "resultado_prueba")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ResultadoPrueba implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static enum ResultadoPuntoCorte {
		MAYOR(1), MENOR(-1), IGUAL(0);
		
		private float valor; 
		
		private ResultadoPuntoCorte(float valor) {
			this.valor = valor;
		}
		
		public float getValor() {
			return valor;
		}
		
		public static ResultadoPuntoCorte lookup(float valor) {
			for(ResultadoPuntoCorte rpc : values()) {
				if(rpc.valor == valor) {
					return rpc;
				}
			}
			
			return null;
		}
	}
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0)
    @Column(name = "pd")
    private Integer pd;

    @Column(name = "pz")
    private Float pz;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("resultadoPruebas")
    private Prueba prueba;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("resultadoPruebas")
    private Informe informe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPd() {
        return pd;
    }

    public ResultadoPrueba pd(Integer pd) {
        this.pd = pd;
        return this;
    }

    public void setPd(Integer pd) {
        this.pd = pd;
    }

    public Float getPz() {
        return pz;
    }

    public ResultadoPrueba pz(Float pz) {
        this.pz = pz;
        return this;
    }

    public void setPz(Float pz) {
        this.pz = pz;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public ResultadoPrueba prueba(Prueba prueba) {
        this.prueba = prueba;
        return this;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public Informe getInforme() {
        return informe;
    }

    public ResultadoPrueba informe(Informe informe) {
        this.informe = informe;
        return this;
    }

    public void setInforme(Informe informe) {
        this.informe = informe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove
    
    
    @Transient
    public Boolean getExitoPuntoCorte() {
    	Boolean exito = null;
    	if(getPz() != null && getPrueba() != null && TipoPrueba.PUNTOS_DE_CORTE.equals(getPrueba().getTipoPrueba())) {
    		ResultadoPuntoCorte resultadoPuntoCorte = ResultadoPuntoCorte.lookup(getPz());
    		exito = !ResultadoPuntoCorte.MAYOR.equals(resultadoPuntoCorte);
    	}
    	return exito;
    } 
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResultadoPrueba)) {
            return false;
        }
        return id != null && id.equals(((ResultadoPrueba) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ResultadoPrueba{" +
            "id=" + getId() +
            ", pd=" + getPd() +
            ", pz=" + getPz() +
            "}";
    }
}
