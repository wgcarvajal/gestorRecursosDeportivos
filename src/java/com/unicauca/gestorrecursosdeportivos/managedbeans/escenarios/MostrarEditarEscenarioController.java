package com.unicauca.gestorrecursosdeportivos.managedbeans.escenarios;

import com.unicauca.gestorrecursosdeportivos.entities.Escenario;
import com.unicauca.gestorrecursosdeportivos.entities.Espacioescenarios;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.EscenarioFacade;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.EspacioescenariosFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
public class MostrarEditarEscenarioController implements Serializable
{
    @EJB
    private EscenarioFacade escenarioEJB;
    @EJB
    private EspacioescenariosFacade espacioEscenariosEJB;
    private List<Escenario> listaEscenarios;
    private Espacioescenarios espacioEscenarioSeleccionado;
    private Escenario escenarioSeleccionado;
    private boolean habilitarGestionEscenario;
    
    public MostrarEditarEscenarioController() 
    {
        this.habilitarGestionEscenario=false;
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

    public Escenario getEscenarioSeleccionado() 
    {
        return escenarioSeleccionado;
    }

    public void setEscenarioSeleccionado(Escenario escenarioSeleccionado) 
    {
        this.escenarioSeleccionado = escenarioSeleccionado;
    }

    public boolean isHabilitarGestionEscenario() 
    {
        return habilitarGestionEscenario;
    }

    public void setHabilitarGestionEscenario(boolean habilitarGestionEscenario) 
    {
        this.habilitarGestionEscenario = habilitarGestionEscenario;
    }
    
    
    
//---------------------fin setters and getters--------------------------------
    
//---------------------Metodos propios del controlador------------------------
    
    public List<Escenario> inicializarListaEscenarios(Integer idEspacioEscenariosSeleccionado,Integer idEscenariosSeleccionado)
    {
        if(idEspacioEscenariosSeleccionado!=null && idEscenariosSeleccionado!=null )
        {
            if(this.listaEscenarios==null)
            {
                this.listaEscenarios = this.escenarioEJB.buscarPorEspID(idEspacioEscenariosSeleccionado);
                for (Escenario esc : this.listaEscenarios) 
                {
                    if (Objects.equals(esc.getEscid(), idEscenariosSeleccionado)) 
                    {
                        this.escenarioSeleccionado = esc;
                        this.escenarioSeleccionado.setBorde(1);
                    }
                }
                this.espacioEscenarioSeleccionado = this.espacioEscenariosEJB.find(idEspacioEscenariosSeleccionado);
                this.habilitarGestionEscenario = true;
                return this.listaEscenarios;
            }
            else
            {
                return this.listaEscenarios;
            }
                        
        }
        return null;        
    }
    
    public void refrescarVista()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioCSS");
        requestContext.update("formEspacioTrabajoEscenarios");
    }
    
//---------------------fin metodos propios del controlador-------------------- 
    
}

