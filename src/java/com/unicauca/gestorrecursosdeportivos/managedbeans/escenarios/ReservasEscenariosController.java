package com.unicauca.gestorrecursosdeportivos.managedbeans.escenarios;

import com.unicauca.gestorrecursosdeportivos.entities.Escenario;
import com.unicauca.gestorrecursosdeportivos.entities.Reserva;
import com.unicauca.gestorrecursosdeportivos.entities.ReservaPK;
import com.unicauca.gestorrecursosdeportivos.entities.Usuario;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.ReservaFacade;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.UsuarioFacade;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.UsuariogrupoFacade;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class ReservasEscenariosController implements Serializable {

    @EJB
    private UsuariogrupoFacade usuarioGrupoEJB; 
    @EJB
    private ReservaFacade reservaEJB;
    @EJB
    private UsuarioFacade usuarioEJB;
    private Escenario escenarioSeleccionado;
    private Usuario usuario;
    private Date fechaSeleccionadaInicio;
    private Date fechaSeleccionadaFin;
    private SimpleDateFormat formateadorFecha;
    private SimpleDateFormat formateadorHora;
    private String fechaInicialFormateada;
    private String horaInicialFormateada;
    private String horaFinalFormateada;    
    private MostrarInfoReserEventClasesEscenario mgb;
    private int duracionReserva;
    
    public ReservasEscenariosController() 
    {
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

    public Date getFechaSeleccionadaInicio() 
    {
        return fechaSeleccionadaInicio;
    }

    public void setFechaSeleccionadaInicio(Date fechaSeleccionadaInicio) 
    {
        this.fechaSeleccionadaInicio = fechaSeleccionadaInicio;
    }

    public Date getFechaSeleccionadaFin() 
    {
        return fechaSeleccionadaFin;
    }

    public void setFechaSeleccionadaFin(Date fechaSeleccionadaFin) 
    {
        this.fechaSeleccionadaFin = fechaSeleccionadaFin;
    }

    public Usuario getUsuario() 
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario) 
    {
        this.usuario = usuario;
    }    

    public String getFechaInicialFormateada() 
    {
        return fechaInicialFormateada;
    }

    public void setFechaInicialFormateada(String fechaInicialFormateada) 
    {
        this.fechaInicialFormateada = fechaInicialFormateada;
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
    
    

    
    
//--------------------fin setters and getters--------------------------------
    
//---------------------Metodos propios del controlador------------------------    

    public void seleccionarEscenario(Escenario escenarioSeleccionado,MostrarInfoReserEventClasesEscenario mgb)
    {
        this.escenarioSeleccionado=escenarioSeleccionado;
        this.mgb=mgb;
    }
    
    public void solicitarReservaEscenario(SelectEvent event) 
    {        
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        Date fechaInicialReserva=(Date)((Date)event.getObject()).clone();        
        this.fechaSeleccionadaInicio=(Date)((Date)event.getObject()).clone();       
        Date fechaLimite=(Date)fechaInicialReserva.clone();
        fechaLimite.setMinutes(0);
        fechaLimite.setSeconds(0);
        fechaLimite.setHours(this.escenarioSeleccionado.getEspid().getEsphorafin());
        fechaInicialReserva.setMinutes(fechaInicialReserva.getMinutes()+30);
        if(this.escenarioSeleccionado.getEsctipoescid().getTipoescnombre().equals("RESERVA")
                && req.getUserPrincipal() != null 
                && this.usuarioGrupoEJB.esUsuario(req.getUserPrincipal().getName())
                && this.fechaSeleccionadaInicio.after(new Date())
                && fechaInicialReserva.before(fechaLimite)
        )
        {
            this.usuario = this.usuarioEJB.buscarUsuarioPorNombreUsuario(req.getUserPrincipal().getName()).get(0);
            Date fechaIntermedia=(Date)this.fechaSeleccionadaInicio.clone();
            fechaIntermedia.setMinutes(fechaIntermedia.getMinutes()+30);
            if(this.reservaEJB.noEstaReservadaEscidFecha(this.escenarioSeleccionado.getEscid(), this.fechaSeleccionadaInicio)
                    && this.reservaEJB.noEstaReservadaEscidFecha(this.escenarioSeleccionado.getEscid(), fechaIntermedia)
            )
            {
                this.fechaSeleccionadaFin=(Date)this.fechaSeleccionadaInicio.clone();
                this.fechaSeleccionadaFin.setHours(this.fechaSeleccionadaFin.getHours()+1);
                int saldoDuracionReserva=this.escenarioSeleccionado.getEscduracionreserva()-60;
                this.duracionReserva=60;
                while(saldoDuracionReserva>0)
                {
                    if(this.fechaSeleccionadaFin.getHours()<this.escenarioSeleccionado.getEspid().getEsphorafin() && this.reservaEJB.noEstaReservadaEscidFecha(this.escenarioSeleccionado.getEscid(), this.fechaSeleccionadaFin))
                    {
                        this.fechaSeleccionadaFin.setMinutes(this.fechaSeleccionadaFin.getMinutes()+saldoDuracionReserva);
                        this.duracionReserva=this.duracionReserva+saldoDuracionReserva;
                        saldoDuracionReserva=saldoDuracionReserva-60;
                    }
                    else
                    {
                        saldoDuracionReserva=0;
                    }
                }
                this.fechaInicialFormateada=this.formateadorFecha.format(this.fechaSeleccionadaInicio);
                this.horaInicialFormateada=this.formateadorHora.format(this.fechaSeleccionadaInicio);
                this.horaFinalFormateada=this.formateadorHora.format(this.fechaSeleccionadaFin);
                this.mgb.refrescarVentanaReserva();
                this.mgb.abrirVentanaReserva();                
            }
            else
            {
                this.mgb.prepararDatosVentanaInformacionReservasEscenario();
                this.mgb.resfrescarAgendaEscenario();
            }
        }
        else
        {
           this.mgb.prepararDatosVentanaInformacionReservasEscenario();
           this.mgb.resfrescarAgendaEscenario(); 
        }
        
    }
    
    public void AceptarReserva()
    {
        
        if(this.fechaSeleccionadaInicio.after(new Date()))
        {
            Date fechaInicioDia=(Date)this.fechaSeleccionadaInicio.clone();
            Date fechaFinDia=(Date)this.fechaSeleccionadaInicio.clone();
            fechaInicioDia.setHours(0);
            fechaInicioDia.setMinutes(0);
            fechaInicioDia.setSeconds(0);
            fechaFinDia.setHours(23);
            fechaFinDia.setMinutes(59);
            fechaFinDia.setSeconds(59);
            if (this.reservaEJB.contarReservasUsuarioFecha(this.usuario.getUsuid(),this.escenarioSeleccionado.getEspid().getEspid(),fechaInicioDia, fechaFinDia)<this.escenarioSeleccionado.getEspid().getEspresevhoradia()) 
            {
                if (this.reservaEJB.contarReservasEscenarioUsuarioFecha(this.usuario.getUsuid(), this.escenarioSeleccionado.getEscid(), fechaInicioDia, fechaFinDia) < 1) 
                {
                    List<Date>Semana=this.retornarFechaInicioFechaFinSemana();
                    if (this.reservaEJB.contarReservasUsuarioFecha(this.usuario.getUsuid(),this.escenarioSeleccionado.getEspid().getEspid(),Semana.get(0), Semana.get(1)) < this.escenarioSeleccionado.getEspid().getEspresevhorasemana()) 
                    {
                        Date fechaIntermedia = (Date) this.fechaSeleccionadaInicio.clone();
                        fechaIntermedia.setMinutes(fechaIntermedia.getMinutes() + 30);
                        if (this.reservaEJB.noEstaReservadaEscidFecha(this.escenarioSeleccionado.getEscid(), this.fechaSeleccionadaInicio)
                                && this.reservaEJB.noEstaReservadaEscidFecha(this.escenarioSeleccionado.getEscid(), fechaIntermedia)) 
                        {
                            int bandera = 1;
                            this.duracionReserva = this.duracionReserva - 60;
                            fechaIntermedia.setMinutes(fechaIntermedia.getMinutes() + 30);
                            while (this.duracionReserva > 0) 
                            {
                                if (this.reservaEJB.noEstaReservadaEscidFecha(this.escenarioSeleccionado.getEscid(), fechaIntermedia))
                                {
                                    this.duracionReserva = this.duracionReserva - 60;
                                    fechaIntermedia.setHours(fechaIntermedia.getHours() + 1);
                                } 
                                else 
                                {
                                    bandera = 0;
                                    this.duracionReserva = 0;
                                }
                            }
                            if (bandera == 1) 
                            {
                                Reserva reserva = new Reserva();
                                ReservaPK reservaPK = new ReservaPK();
                                reserva.setEscenario(this.escenarioSeleccionado);
                                reserva.setUsuario(this.usuario);
                                reserva.setResfechafin(this.fechaSeleccionadaFin);
                                reserva.setResfechafin(this.fechaSeleccionadaFin);
                                reservaPK.setResfechainicio(this.fechaSeleccionadaInicio);
                                reservaPK.setResescid(this.escenarioSeleccionado.getEscid());
                                reservaPK.setResusuid(this.usuario.getUsuid());
                                reserva.setReservaPK(reservaPK);
                                this.reservaEJB.create(reserva);
                                this.mgb.mostrarMensajeInfo("Se realizo la reserva con exito.");

                            }
                            else 
                            {
                                this.mgb.mostrarMensajeError("Ya existe una reserva en la fecha.");
                            }

                        } else 
                        {
                            this.mgb.mostrarMensajeError("Ya existe una reserva en la  fecha.");
                        }
                    }
                    else
                    {
                        this.mgb.mostrarMensajeError("Limite de reservas por Semana.");
                    }                    
                }
                else
                {
                    this.mgb.mostrarMensajeError("Limite de reservas por dia a un mismo escenario.");
                }                
            }
            else
            {
                this.mgb.mostrarMensajeError("Limite de reservas permitidas por dia.");
            }
            
        }
        else
        {
            this.mgb.mostrarMensajeError("La fecha de la reserva ya no es correcta.");
        }
        this.mgb.refrescarMensajesReservas(); 
        this.mgb.prepararDatosVentanaInformacionReservasEscenario();
        this.mgb.resfrescarAgendaEscenario();
        this.mgb.cerrarVentanaReserva();
                    
    }
    
    public void cambiarTamañoReserva(ScheduleEntryResizeEvent event)
    {   
        Reserva res=(Reserva)event.getScheduleEvent().getData();
        if (res.getReservaPK().getResfechainicio().after(new Date()))
        {
            if (event.getMinuteDelta() > 0) 
            {
                long time = (res.getResfechafin().getTime() - res.getReservaPK().getResfechainicio().getTime()) / (1000 * 60);
                Date fechaLimite = (Date) res.getResfechafin().clone();
                Date fechaFinalNueva = (Date) res.getResfechafin().clone();
                fechaFinalNueva.setMinutes(fechaFinalNueva.getMinutes() + event.getMinuteDelta() - 1);
                fechaLimite.setMinutes(0);
                fechaLimite.setSeconds(0);
                fechaLimite.setHours(this.escenarioSeleccionado.getEspid().getEsphorafin());

                if ((time + event.getMinuteDelta()) <= this.escenarioSeleccionado.getEscduracionreserva()
                        && fechaFinalNueva.before(fechaLimite)) 
                {
                    int minutos=event.getMinuteDelta();
                    Date fechaFinal= (Date) res.getResfechafin().clone();
                    int bandera=1;
                    while(minutos>0)
                    {
                        if(this.reservaEJB.noHayReservaEscidFechaInicio(this.escenarioSeleccionado.getEscid(), fechaFinal))
                        {
                            fechaFinal.setMinutes(fechaFinal.getMinutes()+30);
                            minutos=minutos-30;
                        }
                        else
                        {
                            bandera=0;
                            minutos=0;
                        }
                    }
                    if(bandera==1)
                    {
                        fechaFinalNueva.setMinutes(fechaFinalNueva.getMinutes()+1);
                        res.setResfechafin(fechaFinalNueva);
                        this.reservaEJB.edit(res);
                        this.mgb.mostrarMensajeInfo("Reserva editada.");
                    }
                    
                }
            }
            else 
            {
                Date fechaFinalNueva = (Date) res.getResfechafin().clone();
                fechaFinalNueva.setMinutes(fechaFinalNueva.getMinutes() + event.getMinuteDelta());
                long time = (fechaFinalNueva.getTime() - res.getReservaPK().getResfechainicio().getTime()) / (1000 * 60);
                if (time >= 60) 
                {
                    res.setResfechafin(fechaFinalNueva);
                    this.reservaEJB.edit(res);
                    this.mgb.mostrarMensajeInfo("Reserva editada.");
                }
            }
        }
        this.mgb.refrescarMensajesReservas(); 
        this.mgb.prepararDatosVentanaInformacionReservasEscenario();
        this.mgb.resfrescarAgendaEscenario();
    }
    
    public void moverReserva(ScheduleEntryMoveEvent event)
    {
        Reserva res=(Reserva)event.getScheduleEvent().getData();
        Date fechaInicial=(Date)event.getScheduleEvent().getStartDate().clone();
        if(res.getReservaPK().getResfechainicio().after(new Date())&& fechaInicial.after(new Date()))
        {                        
            Date fechaFinal=(Date)event.getScheduleEvent().getEndDate().clone();
            Date fechaLimiteInicio = (Date)event.getScheduleEvent().getStartDate().clone();
            Date fechaLimiteFinal = (Date)event.getScheduleEvent().getStartDate().clone();            
            fechaInicial.setMinutes(fechaInicial.getMinutes()+1);
            fechaFinal.setMinutes(fechaFinal.getMinutes()-1);                                
            fechaLimiteInicio.setMinutes(0);
            fechaLimiteInicio.setSeconds(0);
            fechaLimiteInicio.setHours(this.escenarioSeleccionado.getEspid().getEsphorainicio());
            fechaLimiteFinal.setMinutes(0);
            fechaLimiteFinal.setSeconds(0);
            fechaLimiteFinal.setHours(this.escenarioSeleccionado.getEspid().getEsphorafin());
            if(fechaInicial.after(fechaLimiteInicio)&& fechaFinal.before(fechaLimiteFinal))
            {                
                Date fechaInicioDia=(Date)event.getScheduleEvent().getStartDate().clone();;
                Date fechaFinDia=(Date)event.getScheduleEvent().getStartDate().clone();;
                fechaInicioDia.setHours(0);
                fechaInicioDia.setMinutes(0);
                fechaInicioDia.setSeconds(0);
                fechaFinDia.setHours(23);
                fechaFinDia.setMinutes(59);
                fechaFinDia.setSeconds(59);
                if(event.getDayDelta()==0 ||this.reservaEJB.contarReservasUsuarioFecha(res.getReservaPK().getResusuid(), this.escenarioSeleccionado.getEspid().getEspid(), fechaInicioDia, fechaFinDia)<this.escenarioSeleccionado.getEspid().getEspresevhoradia())
                {
                    if(event.getDayDelta()==0 || this.reservaEJB.contarReservasEscenarioUsuarioFecha(res.getReservaPK().getResusuid(), this.escenarioSeleccionado.getEscid(), fechaInicioDia, fechaFinDia)<1)
                    {              
                        fechaInicial.setMinutes(fechaInicial.getMinutes()-1);
                        fechaFinal.setMinutes(fechaFinal.getMinutes()+1);
                        Date fecha=(Date)event.getScheduleEvent().getStartDate().clone();                        
                        long tiempo=(fechaFinal.getTime()-fechaInicial.getTime())/(1000*60);                
                        int bandera=1;
                        while(tiempo>0)
                        {
                            if(this.reservaEJB.noEstaReservadaEscidFecha(this.escenarioSeleccionado.getEscid(), fecha))
                            {
                               fecha.setMinutes(fecha.getMinutes()+30);
                               tiempo=tiempo-30;                       
                            }    
                            else
                            {
                                bandera=0;
                                tiempo=0;
                            }
                        }
                        if(bandera==1)
                        {
                            Reserva reserva= new Reserva();
                            ReservaPK reservaPK= new ReservaPK();
                            reservaPK.setResescid(res.getReservaPK().getResescid());
                            reservaPK.setResusuid(res.getReservaPK().getResusuid());
                            reservaPK.setResfechainicio(fechaInicial);
                            reserva.setReservaPK(reservaPK);
                            reserva.setResfechafin(fechaFinal);
                            reserva.setEscenario(res.getEscenario());
                            reserva.setUsuario(res.getUsuario());
                            this.reservaEJB.remove(res);
                            this.reservaEJB.create(reserva);
                            this.mgb.mostrarMensajeInfo("Reserva Movida.");
                        }
                    }
                    else
                    {
                        this.mgb.mostrarMensajeError("No se puede mover limite de reservas por dia permitidas a un mismo escenario.");
                    }
                }
                else
                {
                    this.mgb.mostrarMensajeError("No se puede mover limite de reservas permitidas por dia.");
                }
            }
            
        }
        this.mgb.refrescarMensajesReservas(); 
        this.mgb.prepararDatosVentanaInformacionReservasEscenario();
        this.mgb.resfrescarAgendaEscenario();        
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
//---------------------fin metodos propios del controlador--------------------     
}
