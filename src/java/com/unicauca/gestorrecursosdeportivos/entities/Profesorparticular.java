/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "PROFESORPARTICULAR", catalog = "recursosDeportivos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesorparticular.findAll", query = "SELECT p FROM Profesorparticular p"),
    @NamedQuery(name = "Profesorparticular.findByProid", query = "SELECT p FROM Profesorparticular p WHERE p.proid = :proid"),
    @NamedQuery(name = "Profesorparticular.findByPronombre", query = "SELECT p FROM Profesorparticular p WHERE p.pronombre = :pronombre"),
    @NamedQuery(name = "Profesorparticular.findByProtelefono", query = "SELECT p FROM Profesorparticular p WHERE p.protelefono = :protelefono"),
    @NamedQuery(name = "Profesorparticular.findByProemail", query = "SELECT p FROM Profesorparticular p WHERE p.proemail = :proemail")})
public class Profesorparticular implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PROID")
    private Long proid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PRONOMBRE")
    private String pronombre;
    @Column(name = "PROTELEFONO")
    private BigInteger protelefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROEMAIL")
    private String proemail;
    @OneToMany(mappedBy = "proid")
    private List<Curso> cursoList;

    public Profesorparticular() {
    }

    public Profesorparticular(Long proid) {
        this.proid = proid;
    }

    public Profesorparticular(Long proid, String pronombre, String proemail) {
        this.proid = proid;
        this.pronombre = pronombre;
        this.proemail = proemail;
    }

    public Long getProid() {
        return proid;
    }

    public void setProid(Long proid) {
        this.proid = proid;
    }

    public String getPronombre() {
        return pronombre;
    }

    public void setPronombre(String pronombre) {
        this.pronombre = pronombre;
    }

    public BigInteger getProtelefono() {
        return protelefono;
    }

    public void setProtelefono(BigInteger protelefono) {
        this.protelefono = protelefono;
    }

    public String getProemail() {
        return proemail;
    }

    public void setProemail(String proemail) {
        this.proemail = proemail;
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
        hash += (proid != null ? proid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesorparticular)) {
            return false;
        }
        Profesorparticular other = (Profesorparticular) object;
        if ((this.proid == null && other.proid != null) || (this.proid != null && !this.proid.equals(other.proid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.prueba.entities.Profesorparticular[ proid=" + proid + " ]";
    }
    
}
