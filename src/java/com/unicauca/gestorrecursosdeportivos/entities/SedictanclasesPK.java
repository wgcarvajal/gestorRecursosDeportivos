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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author geovanny
 */
@Embeddable
public class SedictanclasesPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESCID")
    private int escid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CURID")
    private long curid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainicio;

    public SedictanclasesPK() {
    }

    public SedictanclasesPK(int escid, long curid, Date fechainicio) {
        this.escid = escid;
        this.curid = curid;
        this.fechainicio = fechainicio;
    }

    public int getEscid() {
        return escid;
    }

    public void setEscid(int escid) {
        this.escid = escid;
    }

    public long getCurid() {
        return curid;
    }

    public void setCurid(long curid) {
        this.curid = curid;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) escid;
        hash += (int) curid;
        hash += (fechainicio != null ? fechainicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SedictanclasesPK)) {
            return false;
        }
        SedictanclasesPK other = (SedictanclasesPK) object;
        if (this.escid != other.escid) {
            return false;
        }
        if (this.curid != other.curid) {
            return false;
        }
        if ((this.fechainicio == null && other.fechainicio != null) || (this.fechainicio != null && !this.fechainicio.equals(other.fechainicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.prueba.entities.SedictanclasesPK[ escid=" + escid + ", curid=" + curid + ", fechainicio=" + fechainicio + " ]";
    }
    
}
