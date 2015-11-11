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
@Table(name = "ENTIDADPARTICULAR", catalog = "recursosDeportivos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entidadparticular.findAll", query = "SELECT e FROM Entidadparticular e"),
    @NamedQuery(name = "Entidadparticular.findByEntid", query = "SELECT e FROM Entidadparticular e WHERE e.entid = :entid"),
    @NamedQuery(name = "Entidadparticular.findByEntnombre", query = "SELECT e FROM Entidadparticular e WHERE e.entnombre = :entnombre")})
public class Entidadparticular implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ENTID")
    private Long entid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ENTNOMBRE")
    private String entnombre;
    @OneToMany(mappedBy = "entid")
    private List<Curso> cursoList;

    public Entidadparticular() {
    }

    public Entidadparticular(Long entid) {
        this.entid = entid;
    }

    public Entidadparticular(Long entid, String entnombre) {
        this.entid = entid;
        this.entnombre = entnombre;
    }

    public Long getEntid() {
        return entid;
    }

    public void setEntid(Long entid) {
        this.entid = entid;
    }

    public String getEntnombre() {
        return entnombre;
    }

    public void setEntnombre(String entnombre) {
        this.entnombre = entnombre;
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
        hash += (entid != null ? entid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entidadparticular)) {
            return false;
        }
        Entidadparticular other = (Entidadparticular) object;
        if ((this.entid == null && other.entid != null) || (this.entid != null && !this.entid.equals(other.entid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.prueba.entities.Entidadparticular[ entid=" + entid + " ]";
    }
    
}
