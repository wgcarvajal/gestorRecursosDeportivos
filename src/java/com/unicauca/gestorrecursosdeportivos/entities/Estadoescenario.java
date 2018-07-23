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
@Table(name = "ESTADOESCENARIO", catalog = "recursosDeportivos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadoescenario.findAll", query = "SELECT e FROM Estadoescenario e"),
    @NamedQuery(name = "Estadoescenario.findByEstescid", query = "SELECT e FROM Estadoescenario e WHERE e.estescid = :estescid"),
    @NamedQuery(name = "Estadoescenario.findByEstescnombre", query = "SELECT e FROM Estadoescenario e WHERE e.estescnombre = :estescnombre")})
public class Estadoescenario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ESTESCID")
    private Integer estescid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ESTESCNOMBRE")
    private String estescnombre;
    @OneToMany(mappedBy = "escestescid")
    private List<Escenario> escenarioList;

    public Estadoescenario() {
    }

    public Estadoescenario(Integer estescid) {
        this.estescid = estescid;
    }

    public Estadoescenario(Integer estescid, String estescnombre) {
        this.estescid = estescid;
        this.estescnombre = estescnombre;
    }

    public Integer getEstescid() {
        return estescid;
    }

    public void setEstescid(Integer estescid) {
        this.estescid = estescid;
    }

    public String getEstescnombre() {
        return estescnombre;
    }

    public void setEstescnombre(String estescnombre) {
        this.estescnombre = estescnombre;
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
        hash += (estescid != null ? estescid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadoescenario)) {
            return false;
        }
        Estadoescenario other = (Estadoescenario) object;
        if ((this.estescid == null && other.estescid != null) || (this.estescid != null && !this.estescid.equals(other.estescid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gestorrecursosdeportivos.entities.Estadoescenario[ estescid=" + estescid + " ]";
    }
    
}
