/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.managedbeans.escenarios;

import com.unicauca.gestorrecursosdeportivos.entities.Escenario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class MostrarCalendarioEscenarioController implements Serializable 
{
    private Escenario escenarioSeleccionado;
    private int horariohorainicio;
    private int horariohorafin;
    private int rangoMinutos;
    private Date initialDate;
    private Date finalDate;
    private boolean desHabilitarBotonFechaActual;

    
    
    public MostrarCalendarioEscenarioController() 
    {
        this.escenarioSeleccionado= new Escenario();
        this.escenarioSeleccionado.setEscnombreimagenreal("vacio");
        List<Date> arrayDate=this.retornarFechaInicioFechaFinSemana();
        this.initialDate= arrayDate.get(0);
        this.finalDate=arrayDate.get(1);
        desHabilitarBotonFechaActual=true;
    }
    
    public Escenario getEscenarioSeleccionado() 
    {
        return escenarioSeleccionado;
    }

    public void setEscenarioSeleccionado(Escenario escenarioSeleccionado) 
    {
        this.escenarioSeleccionado = escenarioSeleccionado;
    }  

    public int getHorariohorainicio() 
    {
        return horariohorainicio;
    }

    public void setHorariohorainicio(int horariohorainicio) 
    {
        this.horariohorainicio = horariohorainicio;
    }

    public int getHorariohorafin()
    {
        return horariohorafin;
    }

    public void setHorariohorafin(int horariohorafin) 
    {
        this.horariohorafin = horariohorafin;
    }

    public int getRangoMinutos() 
    {
        return rangoMinutos;
    }

    public void setRangoMinutos(int rangoMinutos) 
    {
        this.rangoMinutos = rangoMinutos;
    }

    public Date getInitialDate() 
    {
        return initialDate;
    }

    public void setInitialDate(Date initialDate)
    {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() 
    {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) 
    {
        this.finalDate = finalDate;
    }

    public boolean isDesHabilitarBotonFechaActual() 
    {
        return desHabilitarBotonFechaActual;
    }

    public void setDesHabilitarBotonFechaActual(boolean habilitarBotonFechaActual) 
    {
        this.desHabilitarBotonFechaActual = habilitarBotonFechaActual;
    } 
    
    
    public void seleccionarEscenario(Escenario escenario)
    {
        this.escenarioSeleccionado=escenario;        
        this.horariohorainicio=escenarioSeleccionado.getEspid().getEsphorainicio();
        this.horariohorafin=escenarioSeleccionado.getEspid().getEsphorafin();
        if(escenarioSeleccionado.getEscduracionreserva()==90)
        {
            this.rangoMinutos=30;
        }
        else
        {
            this.rangoMinutos=60;
        }        
        this.refrescarVentanaInformacionReservasEscenario();
        this.abrirVentanaCalendarioEscenario();
    }
    
    public void refrescarVentanaInformacionReservasEscenario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formInformacionEscenario"); 
        requestContext.update("formCalendarioEscenario");  
    }
    
    public void abrirVentanaCalendarioEscenario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.execute("PF('ventanaCalendarioEscenario').show()");
    }
    
    public void refrescarCalendario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formCalendarioEscenario");  
    }
    
    private List<Date> retornarFechaInicioFechaFinSemana()
    {
        Date fechainicial= new Date();
        Date fechafinal=(Date)fechainicial.clone();
        
        if(fechainicial.getDay()==1)
        {            
            while(fechafinal.getDay()!=0)
            {
                fechafinal.setDate(fechafinal.getDate()+1);
            }
        }
        else
        {
            if(fechainicial.getDay()==0)
            {                
                fechainicial.setDate(fechainicial.getDate()-1);
                while(fechainicial.getDay()>1)
                {
                    fechainicial.setDate(fechainicial.getDate()-1);
                }
            }
            else
            {
                while(fechainicial.getDay()>1)
                {
                    fechainicial.setDate(fechainicial.getDate()-1);
                }        
        
                while(fechafinal.getDay()!=0)
                {
                    fechafinal.setDate(fechafinal.getDate()+1);
                }
            }
        }
        fechainicial.setHours(0);
        fechainicial.setMinutes(0);
        fechainicial.setSeconds(0);       
        fechafinal.setHours(23);
        fechafinal.setMinutes(59);
        fechafinal.setSeconds(59);
        ArrayList<Date> fechasSemana=new ArrayList();
        fechasSemana.add(fechainicial);
        fechasSemana.add(fechafinal);
        return fechasSemana;
    }
    
    public void siguienteSemana()
    {
        this.initialDate.setDate(this.initialDate.getDate()+7);
        //System.out.println("Nueva Fecha Inicial: "+initialDate);        
        this.finalDate.setDate(this.finalDate.getDate()+7);
        //System.out.println("Nueva Fecha final: "+finalDate);
        Date fechaActual=new Date();
        if(fechaActual.after(initialDate) && fechaActual.before(finalDate))
        {
            this.desHabilitarBotonFechaActual=true;
        }
        else
        {
            this.desHabilitarBotonFechaActual=false;
        }
        
        refrescarCalendario();
    }
    
    public void anteriorSemana()
    {
        this.initialDate.setDate(this.initialDate.getDate()-7);
        System.out.println("Nueva Fecha Inicial: "+initialDate);        
        this.finalDate.setDate(this.finalDate.getDate()-7);
        System.out.println("Nueva Fecha final: "+finalDate);
        Date fechaActual=new Date();
        if(fechaActual.after(initialDate) && fechaActual.before(finalDate))
        {
            this.desHabilitarBotonFechaActual=true;
        }
        else
        {
            this.desHabilitarBotonFechaActual=false;
        }
        
        refrescarCalendario();
    }
    
    public void semanaActual()
    {
        List<Date> arrayDate=this.retornarFechaInicioFechaFinSemana();
        this.initialDate= arrayDate.get(0);
        this.finalDate=arrayDate.get(1);
        desHabilitarBotonFechaActual=true;
        refrescarCalendario();
    }
    
    
}
