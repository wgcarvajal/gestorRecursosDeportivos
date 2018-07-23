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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CURSO", catalog = "recursosDeportivos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByCurid", query = "SELECT c FROM Curso c WHERE c.curid = :curid"),
    @NamedQuery(name = "Curso.findByCurnombre", query = "SELECT c FROM Curso c WHERE c.curnombre = :curnombre"),
    @NamedQuery(name = "Curso.findByCurfechainicio", query = "SELECT c FROM Curso c WHERE c.curfechainicio = :curfechainicio"),
    @NamedQuery(name = "Curso.findByCurfechafin", query = "SELECT c FROM Curso c WHERE c.curfechafin = :curfechafin")})
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CURID")
    private Long curid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CURNOMBRE")
    private String curnombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CURFECHAINICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date curfechainicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CURFECHAFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date curfechafin;
    @JoinColumn(name = "ENTID", referencedColumnName = "ENTID")
    @ManyToOne
    private Entidadparticular entid;
    @JoinColumn(name = "DEPID", referencedColumnName = "DEPID")
    @ManyToOne(optional = false)
    private Deporte depid;
    @JoinColumn(name = "USUID", referencedColumnName = "USUID")
    @ManyToOne
    private Usuario usuid;
    @JoinColumn(name = "PROID", referencedColumnName = "PROID")
    @ManyToOne
    private Profesorparticular proid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<Sedictanclases> sedictanclasesList;

    public Curso() {
    }

    public Curso(Long curid) {
        this.curid = curid;
    }

    public Curso(Long curid, String curnombre, Date curfechainicio, Date curfechafin) {
        this.curid = curid;
        this.curnombre = curnombre;
        this.curfechainicio = curfechainicio;
        this.curfechafin = curfechafin;
    }

    public Long getCurid() {
        return curid;
    }

    public void setCurid(Long curid) {
        this.curid = curid;
    }

    public String getCurnombre() {
        return curnombre;
    }

    public void setCurnombre(String curnombre) {
        this.curnombre = curnombre;
    }

    public Date getCurfechainicio() {
        return curfechainicio;
    }

    public void setCurfechainicio(Date curfechainicio) {
        this.curfechainicio = curfechainicio;
    }

    public Date getCurfechafin() {
        return curfechafin;
    }

    public void setCurfechafin(Date curfechafin) {
        this.curfechafin = curfechafin;
    }

    public Entidadparticular getEntid() {
        return entid;
    }

    public void setEntid(Entidadparticular entid) {
        this.entid = entid;
    }

    public Deporte getDepid() {
        return depid;
    }

    public void setDepid(Deporte depid) {
        this.depid = depid;
    }

    public Usuario getUsuid() {
        return usuid;
    }

    public void setUsuid(Usuario usuid) {
        this.usuid = usuid;
    }

    public Profesorparticular getProid() {
        return proid;
    }

    public void setProid(Profesorparticular proid) {
        this.proid = proid;
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
        hash += (curid != null ? curid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.curid == null && other.curid != null) || (this.curid != null && !this.curid.equals(other.curid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.prueba.entities.Curso[ curid=" + curid + " ]";
    }
    
}
