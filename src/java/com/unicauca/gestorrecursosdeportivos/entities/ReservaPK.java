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
public class ReservaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESESCID")
    private int resescid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESUSUID")
    private long resusuid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESFECHAINICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resfechainicio;

    public ReservaPK() {
    }

    public ReservaPK(int resescid, long resusuid, Date resfechainicio) {
        this.resescid = resescid;
        this.resusuid = resusuid;
        this.resfechainicio = resfechainicio;
    }

    public int getResescid() {
        return resescid;
    }

    public void setResescid(int resescid) {
        this.resescid = resescid;
    }

    public long getResusuid() {
        return resusuid;
    }

    public void setResusuid(long resusuid) {
        this.resusuid = resusuid;
    }

    public Date getResfechainicio() {
        return resfechainicio;
    }

    public void setResfechainicio(Date resfechainicio) {
        this.resfechainicio = resfechainicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) resescid;
        hash += (int) resusuid;
        hash += (resfechainicio != null ? resfechainicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaPK)) {
            return false;
        }
        ReservaPK other = (ReservaPK) object;
        if (this.resescid != other.resescid) {
            return false;
        }
        if (this.resusuid != other.resusuid) {
            return false;
        }
        if ((this.resfechainicio == null && other.resfechainicio != null) || (this.resfechainicio != null && !this.resfechainicio.equals(other.resfechainicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gestorrecursosdeportivos.entities.ReservaPK[ resescid=" + resescid + ", resusuid=" + resusuid + ", resfechainicio=" + resfechainicio + " ]";
    }
    
}
