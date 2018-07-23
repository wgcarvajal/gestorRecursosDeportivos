/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.managedbeans.escenarios;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author geovanny
 */
@ManagedBean
@SessionScoped
public class EstadoSeleccionController implements Serializable {
    
    private Integer idEspacioEscenarioSeleccionado;
    private Integer idEscenarioSeleccionado;    
    
    
    public EstadoSeleccionController() 
    {
    }
    
//---------------------setters and getter-------------------------------------
    public Integer getIdEspacioEscenarioSeleccionado() 
    {
        return idEspacioEscenarioSeleccionado;
    }

    public void setIdEspacioEscenarioSeleccionado(Integer idEspacioEscenarioSeleccionado) 
    {
        this.idEspacioEscenarioSeleccionado = idEspacioEscenarioSeleccionado;
    }

    public Integer getIdEscenarioSeleccionado() 
    {
        return idEscenarioSeleccionado;
    }

    public void setIdEscenarioSeleccionado(Integer idEscenarioSeleccionado) 
    {
        this.idEscenarioSeleccionado = idEscenarioSeleccionado;
    }
//---------------------fin setters and getters--------------------------------

//---------------------Metodos propios del controlador------------------------
    public String seleccionarEspacioEscenarios(Integer idEspacioEscenarioSeleccionado)
    {
        this.idEspacioEscenarioSeleccionado = idEspacioEscenarioSeleccionado;
        return "editarCentroDeportivo?faces-redirect=true";
    }
    public String seleccionarEspacioEscenariosGestionCalendario(Integer idEspacioEscenarioSeleccionado)
    {
        this.idEspacioEscenarioSeleccionado = idEspacioEscenarioSeleccionado;
        return "gestionCalendarioCentroDeportivo?faces-redirect=true";
    }
    public String seleccionarEscenario(Integer idEscenarioSeleccionado)
    {
        this.idEscenarioSeleccionado = idEscenarioSeleccionado;
        return "editarEscenario?faces-redirect=true";
    }
    public String seleccionarEspacioEscenariosRevervasOinformacion(Integer idEspacioEscenarioSeleccionado)
    {        
        this.idEspacioEscenarioSeleccionado = idEspacioEscenarioSeleccionado;
        return "centroDeportivo?faces-redirect=true";
    }
//---------------------fin metodos propios del controlador--------------------
}
