/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author geovanny
 */
@Entity
@Table(name = "ESPACIOESCENARIOS", catalog = "recursosDeportivos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Espacioescenarios.findAll", query = "SELECT e FROM Espacioescenarios e"),
    @NamedQuery(name = "Espacioescenarios.findByEspid", query = "SELECT e FROM Espacioescenarios e WHERE e.espid = :espid"),
    @NamedQuery(name = "Espacioescenarios.findByEspnombre", query = "SELECT e FROM Espacioescenarios e WHERE e.espnombre = :espnombre"),
    @NamedQuery(name = "Espacioescenarios.findByEspancho", query = "SELECT e FROM Espacioescenarios e WHERE e.espancho = :espancho"),
    @NamedQuery(name = "Espacioescenarios.findByEspdireccion", query = "SELECT e FROM Espacioescenarios e WHERE e.espdireccion = :espdireccion"),
    @NamedQuery(name = "Espacioescenarios.findByEspcolor", query = "SELECT e FROM Espacioescenarios e WHERE e.espcolor = :espcolor"),
    @NamedQuery(name = "Espacioescenarios.findByEspnombreimagen", query = "SELECT e FROM Espacioescenarios e WHERE e.espnombreimagen = :espnombreimagen")})
public class Espacioescenarios implements Serializable {
    @Column(name = "ESPHORAINICIO")
    private Integer esphorainicio;
    @Column(name = "ESPHORAFIN")
    private Integer esphorafin;
    @Column(name = "ESPRESEVHORASEMANA")
    private Integer espresevhorasemana;
    @Column(name = "ESPRESEVHORADIA")
    private Integer espresevhoradia;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ESPID")
    private Integer espid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ESPNOMBRE")
    private String espnombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESPANCHO")
    private int espancho;
    @Size(max = 50)
    @Column(name = "ESPDIRECCION")
    private String espdireccion;
    @Lob
    @Size(max = 65535)
    @Column(name = "ESPDESCRIPCION")
    private String espdescripcion;
    @Size(max = 50)
    @Column(name = "ESPCOLOR")
    private String espcolor;
    @Size(max = 50)
    @Column(name = "ESPNOMBREIMAGEN")
    private String espnombreimagen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "espid")
    private List<Escenario> escenarioList;

    public Espacioescenarios() {
    }

    public Espacioescenarios(Integer espid) {
        this.espid = espid;
    }

    public Espacioescenarios(Integer espid, String espnombre, int espancho) {
        this.espid = espid;
        this.espnombre = espnombre;
        this.espancho = espancho;
    }

    public Integer getEspid() {
        return espid;
    }

    public void setEspid(Integer espid) {
        this.espid = espid;
    }

    public String getEspnombre() {
        return espnombre;
    }

    public void setEspnombre(String espnombre) {
        this.espnombre = espnombre;
    }

    public int getEspancho() {
        return espancho;
    }

    public void setEspancho(int espancho) {
        this.espancho = espancho;
    }

    public String getEspdireccion() {
        return espdireccion;
    }

    public void setEspdireccion(String espdireccion) {
        this.espdireccion = espdireccion;
    }

    public String getEspdescripcion() {
        return espdescripcion;
    }

    public void setEspdescripcion(String espdescripcion) {
        this.espdescripcion = espdescripcion;
    }

    public String getEspcolor() {
        return espcolor;
    }

    public void setEspcolor(String espcolor) {
        this.espcolor = espcolor;
    }

    public String getEspnombreimagen() {
        return espnombreimagen;
    }

    public void setEspnombreimagen(String espnombreimagen) {
        this.espnombreimagen = espnombreimagen;
    }

    @XmlTransient
    public List<Escenario> getEscenarioList() {
        return escenarioList;
    }

    public void setEscenarioList(List<Escenario> escenarioList) {
        this.escenarioList = escenarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (espid != null ? espid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Espacioescenarios)) {
            return false;
        }
        Espacioescenarios other = (Espacioescenarios) object;
        if ((this.espid == null && other.espid != null) || (this.espid != null && !this.espid.equals(other.espid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gestorrecursosdeportivos.entities.Espacioescenarios[ espid=" + espid + " ]";
    }


    public Integer getEspresevhorasemana() {
        return espresevhorasemana;
    }

    public void setEspresevhorasemana(Integer espresevhorasemana) {
        this.espresevhorasemana = espresevhorasemana;
    }

    public Integer getEspresevhoradia() {
        return espresevhoradia;
    }

    public void setEspresevhoradia(Integer espresevhoradia) {
        this.espresevhoradia = espresevhoradia;
    }

    public Integer getEsphorainicio() {
        return esphorainicio;
    }

    public void setEsphorainicio(Integer esphorainicio) {
        this.esphorainicio = esphorainicio;
    }

    public Integer getEsphorafin() {
        return esphorafin;
    }

    public void setEsphorafin(Integer esphorafin) {
        this.esphorafin = esphorafin;
    }
    
}
