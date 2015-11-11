/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author geovanny
 */
@Entity
@Table(name = "SEDICTANCLASES", catalog = "recursosDeportivos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sedictanclases.findAll", query = "SELECT s FROM Sedictanclases s"),
    @NamedQuery(name = "Sedictanclases.findByEscid", query = "SELECT s FROM Sedictanclases s WHERE s.sedictanclasesPK.escid = :escid"),
    @NamedQuery(name = "Sedictanclases.findByCurid", query = "SELECT s FROM Sedictanclases s WHERE s.sedictanclasesPK.curid = :curid"),
    @NamedQuery(name = "Sedictanclases.findByFechainicio", query = "SELECT s FROM Sedictanclases s WHERE s.sedictanclasesPK.fechainicio = :fechainicio"),
    @NamedQuery(name = "Sedictanclases.findByFechafin", query = "SELECT s FROM Sedictanclases s WHERE s.fechafin = :fechafin")})
public class Sedictanclases implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SedictanclasesPK sedictanclasesPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafin;
    @JoinColumn(name = "CURID", referencedColumnName = "CURID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Curso curso;
    @JoinColumn(name = "ESCID", referencedColumnName = "ESCID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Escenario escenario;

    public Sedictanclases() {
    }

    public Sedictanclases(SedictanclasesPK sedictanclasesPK) {
        this.sedictanclasesPK = sedictanclasesPK;
    }

    public Sedictanclases(SedictanclasesPK sedictanclasesPK, Date fechafin) {
        this.sedictanclasesPK = sedictanclasesPK;
        this.fechafin = fechafin;
    }

    public Sedictanclases(int escid, long curid, Date fechainicio) {
        this.sedictanclasesPK = new SedictanclasesPK(escid, curid, fechainicio);
    }

    public SedictanclasesPK getSedictanclasesPK() {
        return sedictanclasesPK;
    }

    public void setSedictanclasesPK(SedictanclasesPK sedictanclasesPK) {
        this.sedictanclasesPK = sedictanclasesPK;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Escenario getEscenario() {
        return escenario;
    }

    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sedictanclasesPK != null ? sedictanclasesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sedictanclases)) {
            return false;
        }
        Sedictanclases other = (Sedictanclases) object;
        if ((this.sedictanclasesPK == null && other.sedictanclasesPK != null) || (this.sedictanclasesPK != null && !this.sedictanclasesPK.equals(other.sedictanclasesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.prueba.entities.Sedictanclases[ sedictanclasesPK=" + sedictanclasesPK + " ]";
    }
    
}
