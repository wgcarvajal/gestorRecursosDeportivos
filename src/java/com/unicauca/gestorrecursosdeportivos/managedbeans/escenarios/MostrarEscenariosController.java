/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.managedbeans.escenarios;

import com.unicauca.gestorrecursosdeportivos.entities.Escenario;
import com.unicauca.gestorrecursosdeportivos.entities.Espacioescenarios;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.EscenarioFacade;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.EspacioescenariosFacade;
import java.io.Serializable;
import java.util.List;
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
public class MostrarEscenariosController implements Serializable 
{
    @EJB
    private EscenarioFacade escenarioEJB;
    @EJB
    private EspacioescenariosFacade espacioEscenariosEJB;
    private List<Escenario> listaEscenarios;
    private Espacioescenarios espacioEscenarioSeleccionado;
    private boolean habilitarBotonCrearEscenario;
    private String horario;
    
    public MostrarEscenariosController() 
    {
        this.habilitarBotonCrearEscenario=false;
    }
    
//---------------------setters and getter-------------------------------------
    
    public List<Escenario> getListaEscenarios() 
    {
        return listaEscenarios;
    }

    public void setListaEscenarios(List<Escenario> listaEscenarios) 
    {
        this.listaEscenarios = listaEscenarios;
    }

    public Espacioescenarios getEspacioEscenarioSeleccionado() 
    {
        return espacioEscenarioSeleccionado;
    }

    public void setEspacioEscenarioSeleccionado(Espacioescenarios espacioEscenarioSeleccionado)
    {
        this.espacioEscenarioSeleccionado = espacioEscenarioSeleccionado;
    }

    public boolean isHabilitarBotonCrearEscenario() 
    {
        return habilitarBotonCrearEscenario;
    }

    public void setHabilitarBotonCrearEscenario(boolean habilitarBotonCrearEscenario) 
    {
        this.habilitarBotonCrearEscenario = habilitarBotonCrearEscenario;
    }

    public String getHorario()
    {
        return horario;
    }

    public void setHorario(String horario)
    {
        this.horario = horario;
    }
    
//---------------------fin setters and getters--------------------------------

//---------------------Metodos propios del controlador------------------------
    
    public List<Escenario> generarListaEscenarios(Integer idEspacioEscenariosSeleccionado)
    {
        if(idEspacioEscenariosSeleccionado!=null)
        {
            this.listaEscenarios = this.escenarioEJB.buscarPorEspID(idEspacioEscenariosSeleccionado);
            this.espacioEscenarioSeleccionado=this.espacioEscenariosEJB.find(idEspacioEscenariosSeleccionado);
            this.habilitarBotonCrearEscenario=true;
            this.generarHorario();
            return this.listaEscenarios;            
        }
        return null;
        
    }
    public void volveraGenerarListaEscenarios(Integer idEspacioEscenariosSeleccionado)
    {
        this.listaEscenarios = this.escenarioEJB.buscarPorEspID(idEspacioEscenariosSeleccionado);
    }
    
    public void refrescarVista()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionCentroDeportivoCSS");
        requestContext.update("formGestionCentroDeportivo");
    }
    
    public void refrescarVistaEspacioEscenarios()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionCentroDeportivo");
    }
    
    private void generarHorario()
    {
        if(this.espacioEscenarioSeleccionado.getEsphorainicio()<12)
        {
            this.horario=this.espacioEscenarioSeleccionado.getEsphorainicio()+" AM";
        }
        else
        {
            if(this.espacioEscenarioSeleccionado.getEsphorainicio()==12)
            {
                this.horario=this.espacioEscenarioSeleccionado.getEsphorainicio()+" PM";
            }
            else
            {
               if(this.espacioEscenarioSeleccionado.getEsphorainicio()<24)
               {
                   this.horario=(this.espacioEscenarioSeleccionado.getEsphorainicio()-12)+" PM";
               }
               else
               {
                   this.horario=(this.espacioEscenarioSeleccionado.getEsphorainicio()-12)+" AM";
               }
                
               
            }
            
        }
        this.horario=this.horario+"-";
        if(this.espacioEscenarioSeleccionado.getEsphorafin()<12)
        {
            this.horario=this.horario+this.espacioEscenarioSeleccionado.getEsphorafin()+" AM";
        }
        else
        {
            if(this.espacioEscenarioSeleccionado.getEsphorafin()==12)
            {
                this.horario=this.horario+this.espacioEscenarioSeleccionado.getEsphorafin()+" PM";
            }
            else
            {
               if(this.espacioEscenarioSeleccionado.getEsphorafin()<24)
               {
                   this.horario=this.horario+(this.espacioEscenarioSeleccionado.getEsphorafin()-12)+" PM";
               }
               else
               {
                   this.horario=this.horario+(this.espacioEscenarioSeleccionado.getEsphorafin()-12)+" AM";
               } 
            }
            
        }
    }
    
//---------------------fin metodos propios del controlador-------------------- 
    
    
}
