/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author geovanny
 */
@Entity
@Table(name = "ESCENARIO", catalog = "recursosDeportivos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Escenario.findAll", query = "SELECT e FROM Escenario e"),
    @NamedQuery(name = "Escenario.findByEscid", query = "SELECT e FROM Escenario e WHERE e.escid = :escid"),
    @NamedQuery(name = "Escenario.findByEscnombre", query = "SELECT e FROM Escenario e WHERE e.escnombre = :escnombre"),
    @NamedQuery(name = "Escenario.findByEscanchoimagen", query = "SELECT e FROM Escenario e WHERE e.escanchoimagen = :escanchoimagen"),
    @NamedQuery(name = "Escenario.findByEsclargoimagen", query = "SELECT e FROM Escenario e WHERE e.esclargoimagen = :esclargoimagen"),
    @NamedQuery(name = "Escenario.findByEscnombreimagenanimada", query = "SELECT e FROM Escenario e WHERE e.escnombreimagenanimada = :escnombreimagenanimada"),
    @NamedQuery(name = "Escenario.findByEscnombreimagenreal", query = "SELECT e FROM Escenario e WHERE e.escnombreimagenreal = :escnombreimagenreal"),
    @NamedQuery(name = "Escenario.findByEscmedidas", query = "SELECT e FROM Escenario e WHERE e.escmedidas = :escmedidas"),
    @NamedQuery(name = "Escenario.findByBorde", query = "SELECT e FROM Escenario e WHERE e.borde = :borde"),
    @NamedQuery(name = "Escenario.findByEscmargensuperior", query = "SELECT e FROM Escenario e WHERE e.escmargensuperior = :escmargensuperior"),
    @NamedQuery(name = "Escenario.findByEscmargenizquierda", query = "SELECT e FROM Escenario e WHERE e.escmargenizquierda = :escmargenizquierda"),
    @NamedQuery(name = "Escenario.findByEscrotarimagen", query = "SELECT e FROM Escenario e WHERE e.escrotarimagen = :escrotarimagen"),
    @NamedQuery(name = "Escenario.findByEscposicion", query = "SELECT e FROM Escenario e WHERE e.escposicion = :escposicion"),
    @NamedQuery(name = "Escenario.findByEscvecesreservadia", query = "SELECT e FROM Escenario e WHERE e.escvecesreservadia = :escvecesreservadia"),
    @NamedQuery(name = "Escenario.findByEscduracionreserva", query = "SELECT e FROM Escenario e WHERE e.escduracionreserva = :escduracionreserva"),
    @NamedQuery(name = "Escenario.findByEspid", query = "SELECT e FROM Escenario e WHERE e.espid.espid = :espid order by e.escposicion"),
    @NamedQuery(name = "Escenario.contarEscenariosPorEspid", query = "SELECT COUNT(e) FROM Escenario e WHERE e.espid.espid = :espid")})
public class Escenario implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ESCID")
    private Integer escid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ESCNOMBRE")
    private String escnombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESCANCHOIMAGEN")
    private int escanchoimagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESCLARGOIMAGEN")
    private int esclargoimagen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ESCNOMBREIMAGENANIMADA")
    private String escnombreimagenanimada;
    @Size(max = 50)
    @Column(name = "ESCNOMBREIMAGENREAL")
    private String escnombreimagenreal;
    @Size(max = 50)
    @Column(name = "ESCMEDIDAS")
    private String escmedidas;
    @Lob
    @Size(max = 65535)
    @Column(name = "ESCDESCRIPCION")
    private String escdescripcion;
    @Lob
    @Size(max = 65535)
    @Column(name = "ESCRECOMENDACIONES")
    private String escrecomendaciones;
    @Column(name = "BORDE")
    private Integer borde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESCMARGENSUPERIOR")
    private int escmargensuperior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESCMARGENIZQUIERDA")
    private int escmargenizquierda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESCROTARIMAGEN")
    private int escrotarimagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESCPOSICION")
    private int escposicion;
    @Column(name = "ESCVECESRESERVADIA")
    private Integer escvecesreservadia;
    @Column(name = "ESCDURACIONRESERVA")
    private Integer escduracionreserva;
    @JoinColumn(name = "ESPID", referencedColumnName = "ESPID")
    @ManyToOne(optional = false)
    private Espacioescenarios espid;
    @JoinColumn(name = "ESCTIPOESCID", referencedColumnName = "TIPOESCID")
    @ManyToOne(optional = false)
    private Tipoescenario esctipoescid;
    @JoinColumn(name = "ESCESTESCID", referencedColumnName = "ESTESCID")
    @ManyToOne(optional = false)
    private Estadoescenario escestescid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "escenario")
    private List<Reserva> reservaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "escenario")
    private List<Sedictanclases> sedictanclasesList;

    public Escenario() {
    }

    public Escenario(Integer escid) {
        this.escid = escid;
    }

    public Escenario(Integer escid, String escnombre, int escanchoimagen, int esclargoimagen, String escnombreimagenanimada, int escmargensuperior, int escmargenizquierda, int escrotarimagen, int escposicion) {
        this.escid = escid;
        this.escnombre = escnombre;
        this.escanchoimagen = escanchoimagen;
        this.esclargoimagen = esclargoimagen;
        this.escnombreimagenanimada = escnombreimagenanimada;
        this.escmargensuperior = escmargensuperior;
        this.escmargenizquierda = escmargenizquierda;
        this.escrotarimagen = escrotarimagen;
        this.escposicion = escposicion;
    }

    public Integer getEscid() {
        return escid;
    }

    public void setEscid(Integer escid) {
        this.escid = escid;
    }

    public String getEscnombre() {
        return escnombre;
    }

    public void setEscnombre(String escnombre) {
        this.escnombre = escnombre;
    }

    public int getEscanchoimagen() {
        return escanchoimagen;
    }

    public void setEscanchoimagen(int escanchoimagen) {
        this.escanchoimagen = escanchoimagen;
    }

    public int getEsclargoimagen() {
        return esclargoimagen;
    }

    public void setEsclargoimagen(int esclargoimagen) {
        this.esclargoimagen = esclargoimagen;
    }

    public String getEscnombreimagenanimada() {
        return escnombreimagenanimada;
    }

    public void setEscnombreimagenanimada(String escnombreimagenanimada) {
        this.escnombreimagenanimada = escnombreimagenanimada;
    }

    public String getEscnombreimagenreal() {
        return escnombreimagenreal;
    }

    public void setEscnombreimagenreal(String escnombreimagenreal) {
        this.escnombreimagenreal = escnombreimagenreal;
    }

    public String getEscmedidas() {
        return escmedidas;
    }

    public void setEscmedidas(String escmedidas) {
        this.escmedidas = escmedidas;
    }

    public String getEscdescripcion() {
        return escdescripcion;
    }

    public void setEscdescripcion(String escdescripcion) {
        this.escdescripcion = escdescripcion;
    }

    public String getEscrecomendaciones() {
        return escrecomendaciones;
    }

    public void setEscrecomendaciones(String escrecomendaciones) {
        this.escrecomendaciones = escrecomendaciones;
    }

    public Integer getBorde() {
        return borde;
    }

    public void setBorde(Integer borde) {
        this.borde = borde;
    }

    public int getEscmargensuperior() {
        return escmargensuperior;
    }

    public void setEscmargensuperior(int escmargensuperior) {
        this.escmargensuperior = escmargensuperior;
    }

    public int getEscmargenizquierda() {
        return escmargenizquierda;
    }

    public void setEscmargenizquierda(int escmargenizquierda) {
        this.escmargenizquierda = escmargenizquierda;
    }

    public int getEscrotarimagen() {
        return escrotarimagen;
    }

    public void setEscrotarimagen(int escrotarimagen) {
        this.escrotarimagen = escrotarimagen;
    }

    public int getEscposicion() {
        return escposicion;
    }

    public void setEscposicion(int escposicion) {
        this.escposicion = escposicion;
    }

    public Integer getEscvecesreservadia() {
        return escvecesreservadia;
    }

    public void setEscvecesreservadia(Integer escvecesreservadia) {
        this.escvecesreservadia = escvecesreservadia;
    }

    public Integer getEscduracionreserva() {
        return escduracionreserva;
    }

    public void setEscduracionreserva(Integer escduracionreserva) {
        this.escduracionreserva = escduracionreserva;
    }

    public Espacioescenarios getEspid() {
        return espid;
    }

    public void setEspid(Espacioescenarios espid) {
        this.espid = espid;
    }

    public Tipoescenario getEsctipoescid() {
        return esctipoescid;
    }

    public void setEsctipoescid(Tipoescenario esctipoescid) {
        this.esctipoescid = esctipoescid;
    }

    public Estadoescenario getEscestescid() {
        return escestescid;
    }

    public void setEscestescid(Estadoescenario escestescid) {
        this.escestescid = escestescid;
    }

    @XmlTransient
    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    @XmlTransient
    public List<Sedictanclases> getSedictanclasesList() {
        return sedictanclasesList;
    }

    public void setSedictanclasesList(List<Sedictanclases> sedictanclasesList) {
        this.sedictanclasesList = sedictanclasesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (escid != null ? escid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Escenario)) {
            return false;
        }
        Escenario other = (Escenario) object;
        if ((this.escid == null && other.escid != null) || (this.escid != null && !this.escid.equals(other.escid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.prueba.entities.Escenario[ escid=" + escid + " ]";
    }
    
    
}
