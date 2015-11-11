/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.managedbeans.escenarios;

import com.unicauca.gestorrecursosdeportivos.entities.Espacioescenarios;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.EspacioescenariosFacade;
import com.unicauca.gestorrecursosdeportivos.validadores.ValidarAtributosEspacioEscenarios;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class CrearEspacioEscenariosController implements Serializable {

    @EJB
    private EspacioescenariosFacade espacioEscenariosEJB;
    private Espacioescenarios nuevoEspacioEscenarios;
    private String nombreNuevoEspacioEscenarios;
    
    public CrearEspacioEscenariosController() 
    {
        
    }

//---------------------setters and getter-------------------------------------

    public Espacioescenarios getNuevoEspacioEscenarios() 
    {
        return nuevoEspacioEscenarios;
    }

    public void setNuevoEspacioEscenarios(Espacioescenarios nuevoEspacioEscenarios) 
    {
        this.nuevoEspacioEscenarios = nuevoEspacioEscenarios;
    }

    public String getNombreNuevoEspacioEscenarios() 
    {
        return nombreNuevoEspacioEscenarios;
    }

    public void setNombreNuevoEspacioEscenarios(String nombreNuevoEspacioEscenarios) 
    {
        this.nombreNuevoEspacioEscenarios = nombreNuevoEspacioEscenarios;
    }    
    
//---------------------fin setters and getters--------------------------------

//---------------------Metodos propios del controlador------------------------
    public void abrirVentanaCrearEspacioEscenarios()
    {
        this.nuevoEspacioEscenarios = new Espacioescenarios();
        this.nombreNuevoEspacioEscenarios = "";
        RequestContext requestContext = RequestContext.getCurrentInstance();            
        requestContext.update("formVentanaCrearEspacioEscenarios");        
        requestContext.execute("PF('crearEspacioEscenarios').show()");
    }
    public void crearEspacioEscenarios(ListarEspaciosEscenariosController mng)
    {
        ValidarAtributosEspacioEscenarios validador= new ValidarAtributosEspacioEscenarios();
        if(validador.validarNombreEspacioEscenarios(this.nombreNuevoEspacioEscenarios,this.espacioEscenariosEJB))
        {
            this.nuevoEspacioEscenarios.setEspnombre(this.nombreNuevoEspacioEscenarios);
            this.nuevoEspacioEscenarios.setEspancho(500);
            this.nuevoEspacioEscenarios.setEspcolor("#d3f07d");
            this.nuevoEspacioEscenarios.setEsphorainicio(7);
            this.nuevoEspacioEscenarios.setEsphorafin(22);
            this.nuevoEspacioEscenarios.setEspresevhorasemana(3);
            this.nuevoEspacioEscenarios.setEspresevhoradia(2);
            this.espacioEscenariosEJB.create(this.nuevoEspacioEscenarios);            
            mng.generarListaEspacioEscenarios();
            this.nuevoEspacioEscenarios= new Espacioescenarios();
            this.nombreNuevoEspacioEscenarios = "";
            RequestContext requestContext = RequestContext.getCurrentInstance();            
            requestContext.update("formVentanaCrearEspacioEscenarios");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Centro deportivo creado satisfactoriamente.", "Centro deportivo creado satisfactoriamente."));
        }
               
    }
    
//---------------------fin metodos propios del controlador--------------------
}
