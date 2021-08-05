package com.mycompany.myapp.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.mycompany.myapp.domain.enumeration.SospechaClinica;
import com.mycompany.myapp.domain.enumeration.SospechaClinicaSugerida;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Informe} entity.
 */
public class InformeDTO implements Serializable {

    private Long id;

    private SospechaClinica sospechaClinica;

    private SospechaClinicaSugerida sospechaClinicaSugerida;

    @NotNull
    private LocalDate fechaEvaluacion;

    private String motivoConsulta;

    private String orientacion;

    private String memoria;

    private String praxias;

    private String lenguaje;

    private String funcionesEjecutivas;

    private String conducta;

    private String actividadesDiarias;

    private String resumen;


    private Long userId;

    private String userEmail;

    private Long pacienteId;

    private String pacienteNombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SospechaClinica getSospechaClinica() {
        return sospechaClinica;
    }

    public void setSospechaClinica(SospechaClinica sospechaClinica) {
        this.sospechaClinica = sospechaClinica;
    }

    public SospechaClinicaSugerida getSospechaClinicaSugerida() {
        return sospechaClinicaSugerida;
    }

    public void setSospechaClinicaSugerida(SospechaClinicaSugerida sospechaClinicaSugerida) {
        this.sospechaClinicaSugerida = sospechaClinicaSugerida;
    }

    public LocalDate getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(LocalDate fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getPraxias() {
        return praxias;
    }

    public void setPraxias(String praxias) {
        this.praxias = praxias;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getFuncionesEjecutivas() {
        return funcionesEjecutivas;
    }

    public void setFuncionesEjecutivas(String funcionesEjecutivas) {
        this.funcionesEjecutivas = funcionesEjecutivas;
    }

    public String getConducta() {
        return conducta;
    }

    public void setConducta(String conducta) {
        this.conducta = conducta;
    }

    public String getActividadesDiarias() {
        return actividadesDiarias;
    }

    public void setActividadesDiarias(String actividadesDiarias) {
        this.actividadesDiarias = actividadesDiarias;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InformeDTO informeDTO = (InformeDTO) o;
        if (informeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), informeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InformeDTO{" +
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
            ", userId=" + getUserId() +
            ", userEmail='" + getUserEmail() + "'" +
            ", pacienteId=" + getPacienteId() +
            ", pacienteNombre='" + getPacienteNombre() + "'" +
            "}";
    }
}
