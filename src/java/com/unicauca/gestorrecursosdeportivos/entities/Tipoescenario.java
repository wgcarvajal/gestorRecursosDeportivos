/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "TIPOESCENARIO", catalog = "recursosDeportivos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoescenario.findAll", query = "SELECT t FROM Tipoescenario t"),
    @NamedQuery(name = "Tipoescenario.findByTipoescid", query = "SELECT t FROM Tipoescenario t WHERE t.tipoescid = :tipoescid"),
    @NamedQuery(name = "Tipoescenario.findByTipoescnombre", query = "SELECT t FROM Tipoescenario t WHERE t.tipoescnombre = :tipoescnombre")})
public class Tipoescenario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TIPOESCID")
    private Integer tipoescid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TIPOESCNOMBRE")
    private String tipoescnombre;
    @OneToMany(mappedBy = "esctipoescid")
    private List<Escenario> escenarioList;

    public Tipoescenario() {
    }

    public Tipoescenario(Integer tipoescid) {
        this.tipoescid = tipoescid;
    }

    public Tipoescenario(Integer tipoescid, String tipoescnombre) {
        this.tipoescid = tipoescid;
        this.tipoescnombre = tipoescnombre;
    }

    public Integer getTipoescid() {
        return tipoescid;
    }

    public void setTipoescid(Integer tipoescid) {
        this.tipoescid = tipoescid;
    }

    public String getTipoescnombre() {
        return tipoescnombre;
    }

    public void setTipoescnombre(String tipoescnombre) {
        this.tipoescnombre = tipoescnombre;
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
        hash += (tipoescid != null ? tipoescid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoescenario)) {
            return false;
        }
        Tipoescenario other = (Tipoescenario) object;
        if ((this.tipoescid == null && other.tipoescid != null) || (this.tipoescid != null && !this.tipoescid.equals(other.tipoescid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gestorrecursosdeportivos.entities.Tipoescenario[ tipoescid=" + tipoescid + " ]";
    }
    
}
