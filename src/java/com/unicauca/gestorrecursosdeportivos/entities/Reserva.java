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
@Table(name = "RESERVA", catalog = "recursosDeportivos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
    @NamedQuery(name = "Reserva.findByResescid", query = "SELECT r FROM Reserva r WHERE r.reservaPK.resescid = :resescid"),
    @NamedQuery(name = "Reserva.findByResusuid", query = "SELECT r FROM Reserva r WHERE r.reservaPK.resusuid = :resusuid"),
    @NamedQuery(name = "Reserva.findByResfechainicio", query = "SELECT r FROM Reserva r WHERE r.reservaPK.resfechainicio = :resfechainicio"),
    @NamedQuery(name = "Reserva.findByResfechafin", query = "SELECT r FROM Reserva r WHERE r.resfechafin = :resfechafin"),
    @NamedQuery(name = "Reserva.findByResescidRangoFecha", query = "SELECT r FROM Reserva r WHERE r.reservaPK.resescid = :resescid AND r.reservaPK.resfechainicio BETWEEN :fechainicio AND :fechafin"),
    @NamedQuery(name = "Reserva.findByResescidFechainicio", query = "SELECT r FROM Reserva r WHERE r.reservaPK.resescid = :resescid AND r.reservaPK.resfechainicio=:resfechainicio"),
    @NamedQuery(name = "Reserva.findByResescidFechaIntermedia", query = "SELECT r FROM Reserva r WHERE r.reservaPK.resescid = :resescid AND :fechaintermedia BETWEEN r.reservaPK.resfechainicio AND r.resfechafin "),
    @NamedQuery(name = "Reserva.findByReservadaFeha", query = "SELECT r FROM Reserva r WHERE r.reservaPK.resescid = :resescid AND (r.reservaPK.resfechainicio=:resfechainicio OR :fechaintermedia BETWEEN r.reservaPK.resfechainicio AND r.resfechafin )"),
    @NamedQuery(name = "Reserva.findByContarReservasUsuarioFecha", query = "SELECT COUNT(r) FROM Reserva r WHERE r.reservaPK.resusuid = :resusuid AND r.escenario.espid.espid = :espid AND r.reservaPK.resfechainicio BETWEEN :resfechainicio AND :resfechafin"),
    @NamedQuery(name = "Reserva.findByContarReservasEscenarioUsuarioFecha", query = "SELECT COUNT(r) FROM Reserva r WHERE r.reservaPK.resescid = :resescid AND r.reservaPK.resusuid = :resusuid AND r.reservaPK.resfechainicio BETWEEN :resfechainicio AND :resfechafin")})
    
public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservaPK reservaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESFECHAFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resfechafin;
    @JoinColumn(name = "RESUSUID", referencedColumnName = "USUID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "RESESCID", referencedColumnName = "ESCID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Escenario escenario;

    public Reserva() {
    }

    public Reserva(ReservaPK reservaPK) {
        this.reservaPK = reservaPK;
    }

    public Reserva(ReservaPK reservaPK, Date resfechafin) {
        this.reservaPK = reservaPK;
        this.resfechafin = resfechafin;
    }

    public Reserva(int resescid, long resusuid, Date resfechainicio) {
        this.reservaPK = new ReservaPK(resescid, resusuid, resfechainicio);
    }

    public ReservaPK getReservaPK() {
        return reservaPK;
    }

    public void setReservaPK(ReservaPK reservaPK) {
        this.reservaPK = reservaPK;
    }

    public Date getResfechafin() {
        return resfechafin;
    }

    public void setResfechafin(Date resfechafin) {
        this.resfechafin = resfechafin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        hash += (reservaPK != null ? reservaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.reservaPK == null && other.reservaPK != null) || (this.reservaPK != null && !this.reservaPK.equals(other.reservaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gestorrecursosdeportivos.entities.Reserva[ reservaPK=" + reservaPK + " ]";
    }
    
}
