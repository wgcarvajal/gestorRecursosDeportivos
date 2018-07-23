package com.unicauca.gestorrecursosdeportivos.managedbeans.escenarios;

import com.unicauca.gestorrecursosdeportivos.entities.Escenario;
import com.unicauca.gestorrecursosdeportivos.entities.Reserva;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.ReservaFacade;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.UsuariogrupoFacade;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class MostrarInfoReserEventClasesEscenario implements Serializable{

    @EJB
    private ReservaFacade reservaEJB;
    @EJB
    private UsuariogrupoFacade usuarioGrupoEJB;
    private Escenario escenarioSeleccionado;
    private ScheduleModel eventModel;
    private Reserva eventoReservaSeleccionada;
    private SimpleDateFormat formateadorFecha;
    private SimpleDateFormat formateadorHora;
    private String fechaFormateada;
    private String horaInicialFormateada;
    private String horaFinalFormateada;
    private int horariohorainicio;
    private int horariohorafin;
    private int rangoMinutos;
    
    
    public MostrarInfoReserEventClasesEscenario() 
    {
        this.escenarioSeleccionado= new Escenario();
        this.escenarioSeleccionado.setEscnombreimagenreal("vacio");
        this.formateadorFecha = new SimpleDateFormat("EEEE dd 'de' MMMM 'de' yyyy");
        this.formateadorHora = new SimpleDateFormat("h:mm a");
    }
    
//---------------------setters and getter-------------------------------------

    public Escenario getEscenarioSeleccionado() 
    {
        return escenarioSeleccionado;
    }

    public void setEscenarioSeleccionado(Escenario escenarioSeleccionado) 
    {
        this.escenarioSeleccionado = escenarioSeleccionado;
    }

    public ScheduleModel getEventModel() 
    {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) 
    {
        this.eventModel = eventModel;
    }

    public Reserva getEventoReservaSeleccionada() 
    {
        return eventoReservaSeleccionada;
    }

    public void setEventoReservaSeleccionada(Reserva eventoReservaSeleccionada) 
    {
        this.eventoReservaSeleccionada = eventoReservaSeleccionada;
    }

    public String getFechaFormateada() 
    {
        return fechaFormateada;
    }

    public void setFechaFormateada(String fechaFormateada)
    {
        this.fechaFormateada = fechaFormateada;
    }

    public String getHoraInicialFormateada() 
    {
        return horaInicialFormateada;
    }

    public void setHoraInicialFormateada(String horaInicialFormateada) 
    {
        this.horaInicialFormateada = horaInicialFormateada;
    }

    public String getHoraFinalFormateada() 
    {
        return horaFinalFormateada;
    }

    public void setHoraFinalFormateada(String horaFinalFormateada)
    {
        this.horaFinalFormateada = horaFinalFormateada;
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
    
//--------------------fin setters and getters--------------------------------

//---------------------Metodos propios del controlador------------------------    

    public void seleccionarEscenario(Escenario escenarioSeleccionado,ReservasEscenariosController mgb)
    {
        this.escenarioSeleccionado=escenarioSeleccionado;
        mgb.seleccionarEscenario(escenarioSeleccionado,this);
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
        this.prepararDatosVentanaInformacionReservasEscenario();
        this.refrescarVentanaInformacionReservasEscenario();
        this.abrirVentanaInformacionReservasEscenario();        
    }
    
    public void abrirVentanaInformacionReservasEscenario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.execute("PF('ventanaInformacionReservasEscenario').show()");
    }
    
    public void cerrarVentanaInformacionReservasEscenario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.execute("PF('ventanaInformacionReservasEscenario').hide()");
    }
    
    public void refrescarVentanaInformacionReservasEscenario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formVentanaInformacionReservasEscenario"); 
        requestContext.update("formAgendaEscenario");  
    }
    
    public void resfrescarAgendaEscenario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formAgendaEscenario"); 
    }
    
    public void prepararDatosVentanaInformacionReservasEscenario()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        List <Date> fechasSemana=this.retornarFechaInicioFechaFinSemana();
        List <Reserva> listaReservas=this.reservaEJB.buscarPorEscid(this.escenarioSeleccionado.getEscid(),fechasSemana.get(0),fechasSemana.get(1));
        this.eventModel = new DefaultScheduleModel();
        if (req.getUserPrincipal() != null && this.usuarioGrupoEJB.esUsuario(req.getUserPrincipal().getName()) )
        {
            Long idUsuario=this.usuarioGrupoEJB.retornarIdUsuarioPorNombreUsuario(req.getUserPrincipal().getName());
            for(Reserva res:listaReservas)
            {
                Date fechaInicial=(Date) res.getReservaPK().getResfechainicio().clone();
                Date fechaFinal= (Date)res.getResfechafin().clone();
                DefaultScheduleEvent evento=new DefaultScheduleEvent(res.getUsuario().getUsunombres()+" "+res.getUsuario().getUsuapellidos(), fechaInicial, fechaFinal,res);
                if(idUsuario.equals(res.getReservaPK().getResusuid()))
                {
                    if(res.getReservaPK().getResfechainicio().after(new Date()))
                    {
                         evento.setEditable(true);       
                    }
                    else
                    {
                        evento.setEditable(false);
                    }                        
                    evento.setStyleClass("estiloEventoMiReserva");
                }
                else
                {
                    evento.setEditable(false);
                    evento.setStyleClass("estiloEventoReserva");
                }         
                this.eventModel.addEvent(evento);
            }
        }
        else
        {
            for(Reserva res:listaReservas)
            {
                Date fechaInicial=(Date) res.getReservaPK().getResfechainicio().clone();
                Date fechaFinal= (Date)res.getResfechafin().clone();
                DefaultScheduleEvent evento=new DefaultScheduleEvent(res.getUsuario().getUsunombres()+" "+res.getUsuario().getUsuapellidos(), fechaInicial, fechaFinal,res);
                evento.setEditable(false);
                evento.setStyleClass("estiloEventoReserva");                        
                this.eventModel.addEvent(evento);
            }            
        }        
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
    
    public void refrescarVentanaReserva()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formVentanaReservas");
    }
    
    public void abrirVentanaReserva()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('ventanaReserva').show()");
    }
    
    public void cerrarVentanaReserva()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('ventanaReserva').hide()");
    }
    
    public void mostrarMensajeError(String msgError)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgError, msgError));             
    }
    
    public void mostrarMensajeInfo(String msgInfo)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msgInfo, msgInfo));             
    }    
    
    public void refrescarMensajesReservas()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formMensajesReservas");
    }
    public void seleccionarEvento(SelectEvent selectEvent)
    {
        ScheduleEvent evento=(ScheduleEvent) selectEvent.getObject();        
        String clase=evento.getData().getClass().toString();
        int indice=clase.lastIndexOf('.');
        clase=clase.substring(indice+1);
        if(clase.equals("Reserva"))
        {
            this.eventoReservaSeleccionada=(Reserva)evento.getData();
            this.fechaFormateada=this.formateadorFecha.format(this.eventoReservaSeleccionada.getReservaPK().getResfechainicio());
            this.horaInicialFormateada=this.formateadorHora.format(this.eventoReservaSeleccionada.getReservaPK().getResfechainicio());
            this.horaFinalFormateada=this.formateadorHora.format(this.eventoReservaSeleccionada.getResfechafin());
            this.refrescarVentanaInformacionReserva();
            this.abrirVentanaInformacionReserva();
        }
        
    }
    public void refrescarVentanaInformacionReserva()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formVentanaInformacionReserva");
    }
    public void abrirVentanaInformacionReserva()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('ventanaInformacionReserva').show()");
    }
    public void cerrarVentanaInformacionReserva()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('ventanaInformacionReserva').hide()");
    }
//---------------------fin metodos propios del controlador-------------------- 

    
}
