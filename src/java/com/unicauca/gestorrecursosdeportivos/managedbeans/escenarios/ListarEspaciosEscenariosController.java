/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.managedbeans.escenarios;

import com.unicauca.gestorrecursosdeportivos.entities.Espacioescenarios;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.EspacioescenariosFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class ListarEspaciosEscenariosController implements Serializable{
    
    @EJB
    private EspacioescenariosFacade espacioEscenariosEJB;
    private List<Espacioescenarios> listaEspacioEscenarios;    
    
    
    public ListarEspaciosEscenariosController() 
    {
    }
    @PostConstruct
    private void init()
    {
        this.listaEspacioEscenarios=this.espacioEscenariosEJB.findAll();
    }
    
    
//---------------------setters and getter-------------------------------------
    public List<Espacioescenarios> getListaEspacioEscenarios() 
    {
        return listaEspacioEscenarios;
    }

    public void setListaEspacioEscenarios(List<Espacioescenarios> listaEspacioEscenarios) 
    {
        this.listaEspacioEscenarios = listaEspacioEscenarios;
    }
//---------------------fin setters and getters--------------------------------
    
//---------------------Metodos propios del controlador------------------------
    
    public void generarListaEspacioEscenarios()
    {
        this.listaEspacioEscenarios=this.espacioEscenariosEJB.findAll();
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formularioEspaciosEscenarios");
    }
    
//---------------------fin metodos propios del controlador--------------------    
}
