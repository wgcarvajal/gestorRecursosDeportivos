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
@Table(name = "DEPORTE", catalog = "recursosDeportivos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deporte.findAll", query = "SELECT d FROM Deporte d"),
    @NamedQuery(name = "Deporte.findByDepid", query = "SELECT d FROM Deporte d WHERE d.depid = :depid"),
    @NamedQuery(name = "Deporte.findByDepnombre", query = "SELECT d FROM Deporte d WHERE d.depnombre = :depnombre")})
public class Deporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DEPID")
    private Integer depid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DEPNOMBRE")
    private String depnombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "depid")
    private List<Curso> cursoList;

    public Deporte() {
    }

    public Deporte(Integer depid) {
        this.depid = depid;
    }

    public Deporte(Integer depid, String depnombre) {
        this.depid = depid;
        this.depnombre = depnombre;
    }

    public Integer getDepid() {
        return depid;
    }

    public void setDepid(Integer depid) {
        this.depid = depid;
    }

    public String getDepnombre() {
        return depnombre;
    }

    public void setDepnombre(String depnombre) {
        this.depnombre = depnombre;
    }

    @XmlTransient
    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depid != null ? depid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deporte)) {
            return false;
        }
        Deporte other = (Deporte) object;
        if ((this.depid == null && other.depid != null) || (this.depid != null && !this.depid.equals(other.depid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.prueba.entities.Deporte[ depid=" + depid + " ]";
    }
    
}
