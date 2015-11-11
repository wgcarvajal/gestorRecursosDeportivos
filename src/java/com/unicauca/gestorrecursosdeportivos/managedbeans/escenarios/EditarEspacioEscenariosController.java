package com.unicauca.gestorrecursosdeportivos.managedbeans.escenarios;

import com.unicauca.gestorrecursosdeportivos.entities.Espacioescenarios;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.EspacioescenariosFacade;
import com.unicauca.gestorrecursosdeportivos.validadores.ValidarAtributosEspacioEscenarios;
import java.io.Serializable;
import java.util.ArrayList;
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
public class EditarEspacioEscenariosController implements Serializable
{
    @EJB
    private EspacioescenariosFacade espacioEscenariosEJB;    
    private Integer anchoAnteriorEspacioEscenarioSeleccionado;
    private String  colorAnteriorEspacioEscenarioSeleccionado;
    private Integer anchoNuevoEspacioEscenarioSeleccionado;
    private String  colorNuevoEspacioEscenarioSeleccionado;    
    private String  nombreNuevoEspacioEscenarioSeleccionado;
    private String  direccionNuevaEspacioEscenarioSeleccionado;
    private String  descripcionNuevaEspacioEsenarioSeleccionado;
    private String  horarioNuevoEspacioEscenarioSeleccionado;
    private String  horaInicioNuevoEspacioEscenarioSeleccionado;
    private String  horaFinNuevoEspacioEscenarioSeleccionado;
    private boolean deshabilitarEdicionEspacioEscenarios;
    private boolean deshablitarEdicionNombreEspacioEscenarios;
    private boolean deshabilitarEdicionDireccionEspacioEscenarios;
    private boolean deshabilitarEdicionDescripcionEspacioEscenarios;
    private boolean deshabilitarEdicionHorarioEspacioEscenarios;
    private boolean deshabilitarEdicionReservaNumeroVecesEspacioEscenarios;
    private ArrayList<String> listahoras;
    private int reservaNumeroVecesSemana;
    private int reservaNumeroVecesDia;
    private List<Integer> listaNumeroVecesSemana;
    private List<Integer> listaNumeroVecesDia;
   
    public EditarEspacioEscenariosController() 
    {
        this.deshabilitarEdicionEspacioEscenarios=true;
        this.deshablitarEdicionNombreEspacioEscenarios=true;
        this.deshabilitarEdicionDireccionEspacioEscenarios=true;
        this.deshabilitarEdicionDescripcionEspacioEscenarios=true;
        this.deshabilitarEdicionHorarioEspacioEscenarios=true;
        this.deshabilitarEdicionReservaNumeroVecesEspacioEscenarios=true;
                
    }
    
//---------------------setters and getter-------------------------------------
 
    public boolean isDeshabilitarEdicionEspacioEscenarios() 
    {
        return deshabilitarEdicionEspacioEscenarios;
    }

    public void setDeshabilitarEdicionEspacioEscenarios(boolean deshabilitarEdicionEspacioEscenarios) 
    {
        this.deshabilitarEdicionEspacioEscenarios = deshabilitarEdicionEspacioEscenarios;
    }

    public Integer getAnchoNuevoEspacioEscenarioSeleccionado() 
    {
        return anchoNuevoEspacioEscenarioSeleccionado;
    }

    public void setAnchoNuevoEspacioEscenarioSeleccionado(Integer anchoNuevoEspacioEscenarioSeleccionado) {
        this.anchoNuevoEspacioEscenarioSeleccionado = anchoNuevoEspacioEscenarioSeleccionado;
    }

    public String getColorNuevoEspacioEscenarioSeleccionado() 
    {
        return colorNuevoEspacioEscenarioSeleccionado;
    }

    public void setColorNuevoEspacioEscenarioSeleccionado(String colorNuevoEspacioEscenarioSeleccionado) 
    {
        this.colorNuevoEspacioEscenarioSeleccionado = colorNuevoEspacioEscenarioSeleccionado;
    }

    public boolean isDeshablitarEdicionNombreEspacioEscenarios()
    {
        return deshablitarEdicionNombreEspacioEscenarios;
    }

    public void setDeshablitarEdicionNombreEspacioEscenarios(boolean deshablitarEdicionNombreEspacioEscenarios)
    {
        this.deshablitarEdicionNombreEspacioEscenarios = deshablitarEdicionNombreEspacioEscenarios;
    }    

    public String getNombreNuevoEspacioEscenarioSeleccionado() 
    {
        return nombreNuevoEspacioEscenarioSeleccionado;
    }

    public void setNombreNuevoEspacioEscenarioSeleccionado(String nombreNuevoEspacioEscenarioSeleccionado) 
    {
        this.nombreNuevoEspacioEscenarioSeleccionado = nombreNuevoEspacioEscenarioSeleccionado;
    }

    public boolean isDeshabilitarEdicionDireccionEspacioEscenarios() 
    {
        return deshabilitarEdicionDireccionEspacioEscenarios;
    }

    public void setDeshabilitarEdicionDireccionEspacioEscenarios(boolean deshabilitarEdicionDireccionEspacioEscenarios) 
    {
        this.deshabilitarEdicionDireccionEspacioEscenarios = deshabilitarEdicionDireccionEspacioEscenarios;
    }

    public String getDireccionNuevaEspacioEscenarioSeleccionado()
    {
        return direccionNuevaEspacioEscenarioSeleccionado;
    }

    public void setDireccionNuevaEspacioEscenarioSeleccionado(String direccionNuevaEspacioEscenarioSeleccionado) 
    {
        this.direccionNuevaEspacioEscenarioSeleccionado = direccionNuevaEspacioEscenarioSeleccionado;
    }

    public boolean isDeshabilitarEdicionDescripcionEspacioEscenarios() 
    {
        return deshabilitarEdicionDescripcionEspacioEscenarios;
    }

    public void setDeshabilitarEdicionDescripcionEspacioEscenarios(boolean deshabilitarEdicionDescripcionEspacioEscenarios) 
    {
        this.deshabilitarEdicionDescripcionEspacioEscenarios = deshabilitarEdicionDescripcionEspacioEscenarios;
    }

    public String getDescripcionNuevaEspacioEsenarioSeleccionado()
    {
        return descripcionNuevaEspacioEsenarioSeleccionado;
    }

    public void setDescripcionNuevaEspacioEsenarioSeleccionado(String descripcionNuevaEspacioEsenarioSeleccionado) 
    {
        this.descripcionNuevaEspacioEsenarioSeleccionado = descripcionNuevaEspacioEsenarioSeleccionado;
    }

    public boolean isDeshabilitarEdicionHorarioEspacioEscenarios() 
    {
        return deshabilitarEdicionHorarioEspacioEscenarios;
    }

    public void setDeshabilitarEdicionHorarioEspacioEscenarios(boolean deshabilitarEdicionHorarioEspacioEscenarios) 
    {
        this.deshabilitarEdicionHorarioEspacioEscenarios = deshabilitarEdicionHorarioEspacioEscenarios;
    }

    public String getHorarioNuevoEspacioEscenarioSeleccionado() 
    {
        return horarioNuevoEspacioEscenarioSeleccionado;
    }

    public void setHorarioNuevoEspacioEscenarioSeleccionado(String horarioNuevoEspacioEscenarioSeleccionado) 
    {
        this.horarioNuevoEspacioEscenarioSeleccionado = horarioNuevoEspacioEscenarioSeleccionado;
    }

    public String getHoraInicioNuevoEspacioEscenarioSeleccionado() 
    {
        return horaInicioNuevoEspacioEscenarioSeleccionado;
    }

    public void setHoraInicioNuevoEspacioEscenarioSeleccionado(String horaInicioNuevoEspacioEscenarioSeleccionado) 
    {
        this.horaInicioNuevoEspacioEscenarioSeleccionado = horaInicioNuevoEspacioEscenarioSeleccionado;
    }

    public String getHoraFinNuevoEspacioEscenarioSeleccionado() 
    {
        return horaFinNuevoEspacioEscenarioSeleccionado;
    }

    public void setHoraFinNuevoEspacioEscenarioSeleccionado(String horaFinNuevoEspacioEscenarioSeleccionado) 
    {
        this.horaFinNuevoEspacioEscenarioSeleccionado = horaFinNuevoEspacioEscenarioSeleccionado;
    }

    public ArrayList<String> getListahoras() 
    {
        return listahoras;
    }

    public void setListahoras(ArrayList<String> listahoras) 
    {
        this.listahoras = listahoras;
    }

    public boolean isDeshabilitarEdicionReservaNumeroVecesEspacioEscenarios() 
    {
        return deshabilitarEdicionReservaNumeroVecesEspacioEscenarios;
    }

    public void setDeshabilitarEdicionReservaNumeroVecesEspacioEscenarios(boolean deshabilitarEdicionReservaNumeroVecesEspacioEscenarios) 
    {
        this.deshabilitarEdicionReservaNumeroVecesEspacioEscenarios = deshabilitarEdicionReservaNumeroVecesEspacioEscenarios;
    }

    public int getReservaNumeroVecesSemana() 
    {
        return reservaNumeroVecesSemana;
    }

    public void setReservaNumeroVecesSemana(int reservaNumeroVecesSemana)
    {
        this.reservaNumeroVecesSemana = reservaNumeroVecesSemana;
    }

    public int getReservaNumeroVecesDia() 
    {
        return reservaNumeroVecesDia;
    }

    public void setReservaNumeroVecesDia(int reservaNumeroVecesDia) 
    {
        this.reservaNumeroVecesDia = reservaNumeroVecesDia;
    }

    public List<Integer> getListaNumeroVecesSemana()
    {
        return listaNumeroVecesSemana;
    }

    public void setListaNumeroVecesSemana(List<Integer> listaNumeroVecesSemana) 
    {
        this.listaNumeroVecesSemana = listaNumeroVecesSemana;
    }

    public List<Integer> getListaNumeroVecesDia() 
    {
        return listaNumeroVecesDia;
    }

    public void setListaNumeroVecesDia(List<Integer> listaNumeroVecesDia) 
    {
        this.listaNumeroVecesDia = listaNumeroVecesDia;
    }
    
    
//---------------------fin setters and getters--------------------------------

//---------------------Metodos propios del controlador------------------------
    
    public void editarConfiguracionInterfazEspacioEscenarios(MostrarEscenariosController mgb)
    {
        this.anchoNuevoEspacioEscenarioSeleccionado=this.anchoAnteriorEspacioEscenarioSeleccionado=mgb.getEspacioEscenarioSeleccionado().getEspancho();
        this.colorNuevoEspacioEscenarioSeleccionado=this.colorAnteriorEspacioEscenarioSeleccionado=mgb.getEspacioEscenarioSeleccionado().getEspcolor();
        this.deshabilitarEdicionEspacioEscenarios=false;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionConfiguracionInterfazEspacioEscenarios");
    }
    public void aumentarAnchoEspacioEscenarios(MostrarEscenariosController mgb)
    {
        this.anchoNuevoEspacioEscenarioSeleccionado=this.anchoNuevoEspacioEscenarioSeleccionado+1;
        mgb.getEspacioEscenarioSeleccionado().setEspancho(this.anchoNuevoEspacioEscenarioSeleccionado);
        mgb.refrescarVistaEspacioEscenarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionConfiguracionInterfazEspacioEscenarios");
    }
    public void disminuirAnchoEspacioEscenarios(MostrarEscenariosController mgb)
    {
        if(this.anchoNuevoEspacioEscenarioSeleccionado>0)
        {
            this.anchoNuevoEspacioEscenarioSeleccionado=this.anchoNuevoEspacioEscenarioSeleccionado-1;
            mgb.getEspacioEscenarioSeleccionado().setEspancho(this.anchoNuevoEspacioEscenarioSeleccionado);
            mgb.refrescarVistaEspacioEscenarios();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionConfiguracionInterfazEspacioEscenarios");
        }        
    }
    
    public void seleccionarColorEspacioEscenarios(MostrarEscenariosController mgb,String color)
    {
        this.colorNuevoEspacioEscenarioSeleccionado=color;
        mgb.getEspacioEscenarioSeleccionado().setEspcolor(this.colorNuevoEspacioEscenarioSeleccionado);
        mgb.refrescarVistaEspacioEscenarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionConfiguracionInterfazEspacioEscenarios");
        requestContext.execute("PF('seleccionarColor').hide()");
    }
    
    public void cancelarEditarConfiguracionInterfazEspacioEscenarios(MostrarEscenariosController mgb)
    {
        this.deshabilitarEdicionEspacioEscenarios=true;
        mgb.getEspacioEscenarioSeleccionado().setEspancho(this.anchoAnteriorEspacioEscenarioSeleccionado);
        mgb.getEspacioEscenarioSeleccionado().setEspcolor(this.colorAnteriorEspacioEscenarioSeleccionado);
        mgb.refrescarVistaEspacioEscenarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionConfiguracionInterfazEspacioEscenarios");
    }
    
    public void aceptarEditarConfiguracionInterfazEspaciosEscenarios(MostrarEscenariosController mgb)
    {
        this.deshabilitarEdicionEspacioEscenarios=true;
        this.espacioEscenariosEJB.edit(mgb.getEspacioEscenarioSeleccionado());
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionConfiguracionInterfazEspacioEscenarios");
    }
    
    public void editarNombreEspacioEscenarios(MostrarEscenariosController mgb)
    {
        this.nombreNuevoEspacioEscenarioSeleccionado=mgb.getEspacioEscenarioSeleccionado().getEspnombre();        
        this.deshablitarEdicionNombreEspacioEscenarios=false;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");
        
    }
    
    public void cancelarEditarNombreEspacioEscenarios()
    {
        this.deshablitarEdicionNombreEspacioEscenarios=true;        
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");
    }
    
    public void aceptarEditarNombreEspacioEscenarios(MostrarEscenariosController mgb)
    {
        ValidarAtributosEspacioEscenarios validador=new ValidarAtributosEspacioEscenarios();
        if (validador.validarNombreEspacioEscenarios(this.nombreNuevoEspacioEscenarioSeleccionado, this.espacioEscenariosEJB)) 
        {
            this.deshablitarEdicionNombreEspacioEscenarios = true;
            mgb.getEspacioEscenarioSeleccionado().setEspnombre(this.nombreNuevoEspacioEscenarioSeleccionado);
            Espacioescenarios esp=this.espacioEscenariosEJB.find(mgb.getEspacioEscenarioSeleccionado().getEspid());
            esp.setEspnombre(this.nombreNuevoEspacioEscenarioSeleccionado);
            this.espacioEscenariosEJB.edit(esp);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");
        }     
    }
    
    public void editarDireccionEspacioEscenarios(MostrarEscenariosController mgb)
    {
        this.direccionNuevaEspacioEscenarioSeleccionado=mgb.getEspacioEscenarioSeleccionado().getEspdireccion();        
        this.deshabilitarEdicionDireccionEspacioEscenarios=false;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");        
    }
    
    public void cancelarEditarDireccionEspacioEscenarios()
    {
        this.deshabilitarEdicionDireccionEspacioEscenarios=true;        
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");
    }
    
    public void aceptarEditarDireccionEspacioEscenarios(MostrarEscenariosController mgb)
    {
        ValidarAtributosEspacioEscenarios validador=new ValidarAtributosEspacioEscenarios();
        if (validador.validarDireccionEspacioEscenarios(this.direccionNuevaEspacioEscenarioSeleccionado)) 
        {
            this.deshabilitarEdicionDireccionEspacioEscenarios = true;
            mgb.getEspacioEscenarioSeleccionado().setEspdireccion(this.direccionNuevaEspacioEscenarioSeleccionado);
            Espacioescenarios esp=this.espacioEscenariosEJB.find(mgb.getEspacioEscenarioSeleccionado().getEspid());
            esp.setEspdireccion(this.direccionNuevaEspacioEscenarioSeleccionado);
            this.espacioEscenariosEJB.edit(esp);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");
        }     
    }
    
    public void editarDescripcionEspacioEscenarios(MostrarEscenariosController mgb)
    {
        this.descripcionNuevaEspacioEsenarioSeleccionado=mgb.getEspacioEscenarioSeleccionado().getEspdescripcion();        
        this.deshabilitarEdicionDescripcionEspacioEscenarios=false;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");        
    }
    
    public void cancelarEditarDescripcionEspacioEscenarios()
    {
        this.deshabilitarEdicionDescripcionEspacioEscenarios=true;        
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");
    }
    
    public void aceptarEditarDescripcionEspacioEscenarios(MostrarEscenariosController mgb)
    {
        ValidarAtributosEspacioEscenarios validador=new ValidarAtributosEspacioEscenarios();
        if (validador.validarDescripcionEspacioEscenarios(this.descripcionNuevaEspacioEsenarioSeleccionado)) 
        {
            this.deshabilitarEdicionDescripcionEspacioEscenarios = true;
            mgb.getEspacioEscenarioSeleccionado().setEspdescripcion(this.descripcionNuevaEspacioEsenarioSeleccionado);
            Espacioescenarios esp=this.espacioEscenariosEJB.find(mgb.getEspacioEscenarioSeleccionado().getEspid());
            esp.setEspdescripcion(this.descripcionNuevaEspacioEsenarioSeleccionado);
            this.espacioEscenariosEJB.edit(esp);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");
        }     
    }
    
    public void editarHorarioEspacioEscenarios(MostrarEscenariosController mgb)
    {
        this.listahoras=new ArrayList();
        for(int i=1;i<12;i++)
        {
            this.listahoras.add(i+" AM");
        }
        this.listahoras.add(12+" PM");
        for(int i=1;i<12;i++)
        {
            this.listahoras.add(i+" PM");
        }
        this.listahoras.add(12+" AM");
        this.horarioNuevoEspacioEscenarioSeleccionado=mgb.getHorario();
        int indice=this.horarioNuevoEspacioEscenarioSeleccionado.indexOf('-');
        this.horaInicioNuevoEspacioEscenarioSeleccionado=this.horarioNuevoEspacioEscenarioSeleccionado.substring(0, indice);
        this.horaFinNuevoEspacioEscenarioSeleccionado=this.horarioNuevoEspacioEscenarioSeleccionado.substring(indice+1);        
        this.deshabilitarEdicionHorarioEspacioEscenarios=false;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");        
    }
    
    public void cancelarEditarHorarioEspacioEscenarios()
    {
        this.deshabilitarEdicionHorarioEspacioEscenarios=true;        
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");
    }
    
    public void aceptarEditarHorarioEspacioEscenarios(MostrarEscenariosController mgb)
    {
        Espacioescenarios esp=this.espacioEscenariosEJB.find(mgb.getEspacioEscenarioSeleccionado().getEspid());
        int horai=this.horaMilitar(this.horaInicioNuevoEspacioEscenarioSeleccionado);
        int horaf=this.horaMilitar(this.horaFinNuevoEspacioEscenarioSeleccionado);
        ValidarAtributosEspacioEscenarios validador=new ValidarAtributosEspacioEscenarios();
        if (validador.validarHorario(horai, horaf))
        {
            this.deshabilitarEdicionHorarioEspacioEscenarios= true;
            mgb.setHorario(this.horaInicioNuevoEspacioEscenarioSeleccionado+"-"+this.horaFinNuevoEspacioEscenarioSeleccionado);
            esp.setEsphorainicio(horai);
            esp.setEsphorafin(horaf);
            this.espacioEscenariosEJB.edit(esp);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");
        }   
    }
    
    public void editarNumeroVecesReservaEspacioEscenarios(MostrarEscenariosController mgb)
    { 
        this.listaNumeroVecesSemana=new ArrayList();
        this.listaNumeroVecesDia=new ArrayList();
        for(int i=1;i<=15;i++)
        {
            this.listaNumeroVecesSemana.add(i);
        }
        for(int i=1;i<=5;i++)
        {
            this.listaNumeroVecesDia.add(i);
        }
        this.reservaNumeroVecesSemana=mgb.getEspacioEscenarioSeleccionado().getEspresevhorasemana();
        this.reservaNumeroVecesDia=mgb.getEspacioEscenarioSeleccionado().getEspresevhoradia();
        this.deshabilitarEdicionReservaNumeroVecesEspacioEscenarios=false;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");        
    }
    
    public void cancelarEditarNumeroVecesReservaEspacioEscenarios()
    {
        this.deshabilitarEdicionReservaNumeroVecesEspacioEscenarios=true;        
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");
    }
    
    public void aceptarEditarNumeroVecesReservaEspacioEscenarios(MostrarEscenariosController mgb)
    {        
        ValidarAtributosEspacioEscenarios validador=new ValidarAtributosEspacioEscenarios();
        if (validador.validarReservasNumeroVeces(this.reservaNumeroVecesSemana, this.reservaNumeroVecesDia))
        {
            Espacioescenarios esp=this.espacioEscenariosEJB.find(mgb.getEspacioEscenarioSeleccionado().getEspid());
            this.deshabilitarEdicionReservaNumeroVecesEspacioEscenarios= true;
            mgb.getEspacioEscenarioSeleccionado().setEspresevhorasemana(this.reservaNumeroVecesSemana);
            mgb.getEspacioEscenarioSeleccionado().setEspresevhoradia(this.reservaNumeroVecesDia);
            esp.setEspresevhorasemana(this.reservaNumeroVecesSemana);
            esp.setEspresevhoradia(this.reservaNumeroVecesDia);
            this.espacioEscenariosEJB.edit(esp);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionInformacionGenerlaEspacioEscenarios");
        }   
    }
    
    private int horaMilitar(String hora)
    {
        if(hora.equals("12 AM"))
        {
            return 24;
        }
        else
        {
            if(hora.equals("12 PM"))
            {
                return 12;
            }
            else
            {
                int indice=hora.indexOf(' ');
                int numero=Integer.parseInt((hora.substring(0, indice)));
                String jornada=hora.substring(indice+1);
                if(jornada.equals("AM"))
                {
                    return numero;
                }
                else
                {
                    return (numero+12);
                }
            }
        }
    }
//---------------------fin metodos propios del controlador--------------------     
    
    
}
