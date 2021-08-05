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

import com.mycompany.myapp.domain.enumeration.SospechaClinica;
import com.mycompany.myapp.domain.enumeration.SospechaClinicaSugerida;

/**
 * A Informe.
 */
@Entity
@Table(name = "informe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Informe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "sospecha_clinica")
    private SospechaClinica sospechaClinica;

    @Enumerated(EnumType.STRING)
    @Column(name= "sospecha_clinica_sugerida")
    private SospechaClinicaSugerida sospechaClinicaSugerida;

    @NotNull
    @Column(name = "fecha_evaluacion", nullable = false)
    private LocalDate fechaEvaluacion;

    @Column(name = "motivo_consulta")
    private String motivoConsulta;

    @Column(name = "orientacion")
    private String orientacion;

    @Column(name = "memoria")
    private String memoria;

    @Column(name = "praxias")
    private String praxias;

    @Column(name = "lenguaje")
    private String lenguaje;

    @Column(name = "funciones_ejecutivas")
    private String funcionesEjecutivas;

    @Column(name = "conducta")
    private String conducta;

    @Column(name = "actividades_diarias")
    private String actividadesDiarias;

    @Column(name = "resumen")
    private String resumen;

    @OneToMany(mappedBy = "informe", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ResultadoPrueba> resultadoPruebas = new HashSet<>();

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @NotNull
    @JsonIgnoreProperties("informes")
    private Paciente paciente;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @NotNull
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SospechaClinica getSospechaClinica() {
        return sospechaClinica;
    }

    public SospechaClinicaSugerida getSospechaClinicaSugerida() {
        return sospechaClinicaSugerida;
    }

    public Informe sospechaClinica(SospechaClinica sospechaClinica) {
        this.sospechaClinica = sospechaClinica;
        return this;
    }

    public Informe sospechaClinicaSugerida(SospechaClinicaSugerida sospechaClinicaSugerida) {
        this.sospechaClinicaSugerida = sospechaClinicaSugerida;
        return this;
    }

    public void setSospechaClinicaSugerida(SospechaClinicaSugerida sospechaClinicaSugerida) {
        this.sospechaClinicaSugerida = sospechaClinicaSugerida;
    }

    public void setSospechaClinica(SospechaClinica sospechaClinica) {
        this.sospechaClinica = sospechaClinica;
    }

    public LocalDate getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public Informe fechaEvaluacion(LocalDate fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
        return this;
    }

    public void setFechaEvaluacion(LocalDate fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public Informe motivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
        return this;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public Informe orientacion(String orientacion) {
        this.orientacion = orientacion;
        return this;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public String getMemoria() {
        return memoria;
    }

    public Informe memoria(String memoria) {
        this.memoria = memoria;
        return this;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getPraxias() {
        return praxias;
    }

    public Informe praxias(String praxias) {
        this.praxias = praxias;
        return this;
    }

    public void setPraxias(String praxias) {
        this.praxias = praxias;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public Informe lenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
        return this;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getFuncionesEjecutivas() {
        return funcionesEjecutivas;
    }

    public Informe funcionesEjecutivas(String funcionesEjecutivas) {
        this.funcionesEjecutivas = funcionesEjecutivas;
        return this;
    }

    public void setFuncionesEjecutivas(String funcionesEjecutivas) {
        this.funcionesEjecutivas = funcionesEjecutivas;
    }

    public String getConducta() {
        return conducta;
    }

    public Informe conducta(String conducta) {
        this.conducta = conducta;
        return this;
    }

    public void setConducta(String conducta) {
        this.conducta = conducta;
    }

    public String getActividadesDiarias() {
        return actividadesDiarias;
    }

    public Informe actividadesDiarias(String actividadesDiarias) {
        this.actividadesDiarias = actividadesDiarias;
        return this;
    }

    public void setActividadesDiarias(String actividadesDiarias) {
        this.actividadesDiarias = actividadesDiarias;
    }

    public String getResumen() {
        return resumen;
    }

    public Informe resumen(String resumen) {
        this.resumen = resumen;
        return this;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Set<ResultadoPrueba> getResultadoPruebas() {
        return resultadoPruebas;
    }

    public Informe resultadoPruebas(Set<ResultadoPrueba> resultadoPruebas) {
        this.resultadoPruebas = resultadoPruebas;
        return this;
    }

    public Informe addResultadoPrueba(ResultadoPrueba resultadoPrueba) {
        this.resultadoPruebas.add(resultadoPrueba);
        resultadoPrueba.setInforme(this);
        return this;
    }

    public Informe removeResultadoPrueba(ResultadoPrueba resultadoPrueba) {
        this.resultadoPruebas.remove(resultadoPrueba);
        resultadoPrueba.setInforme(null);
        return this;
    }

    public void setResultadoPruebas(Set<ResultadoPrueba> resultadoPruebas) {
        this.resultadoPruebas = resultadoPruebas;
    }

    public User getUser() {
        return user;
    }

    public Informe setUser(User user) {
        this.user = user;
        return this;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Informe paciente(Paciente paciente) {
        this.paciente = paciente;
        return this;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    @Transient
    public String getNombre() {
    	
    	StringBuilder sb =  new StringBuilder()
    			.append(getId());
    	
    	if(getPaciente() != null) {
    		sb.append(" - ")
    		.append(getPaciente().getNhc())
    		.append(" (")
    		.append(getPaciente().getNombre())
    		.append(")");
    		
    	}
		return sb.toString();
    }
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Informe)) {
            return false;
        }
        return id != null && id.equals(((Informe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Informe{" +
            "id=" + getId() +
            ", sospechaClinica='" + getSospechaClinica() + "'" +
            ", sospechaClinicaSugerida='" + getSospechaClinicaSugerida() + "'" +
            ", fechaEvaluacion='" + getFechaEvaluacion() + "'" +
            ", motivoConsulta='" + getMotivoConsulta() + "'" +
            ", orientacion='" + getOrientacion() + "'" +
            ", memoria='" + getMemoria() + "'" +
            ", praxias='" + getPraxias() + "'" +
            ", lenguaje='" + getLenguaje() + "'" +
            ", funcionesEjecutivas='" + getFuncionesEjecutivas() + "'" +
            ", conducta='" + getConducta() + "'" +
            ", actividadesDiarias='" + getActividadesDiarias() + "'" +
            ", resumen='" + getResumen() + "'" +
            "}";
    }
}
