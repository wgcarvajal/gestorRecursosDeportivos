package com.unicauca.gestorrecursosdeportivos.managedbeans.escenarios;

import com.unicauca.gestorrecursosdeportivos.entities.Escenario;
import com.unicauca.gestorrecursosdeportivos.entities.Estadoescenario;
import com.unicauca.gestorrecursosdeportivos.entities.Tipoescenario;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.EscenarioFacade;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.EstadoescenarioFacade;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.TipoescenarioFacade;
import com.unicauca.gestorrecursosdeportivos.validadores.ValidarAtributosEscenario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class EditarEscenarioController implements Serializable
{

    @EJB
    private EscenarioFacade escenarioEJB;
    @EJB
    private EstadoescenarioFacade estadoEscenarioEJB;
    @EJB
    private TipoescenarioFacade tipoEscenarioEJB;
    private Integer  nuevoAnchoImagenEscenario;    
    private Integer  nuevoLargoImagenEscenario;    
    private Integer  nuevaMargenSuperiorImagenEscenario;    
    private Integer  nuevaMargenIzquierdaImagenEscenario;    
    private Integer  nuevaRotacionImagenEscenario;
    private int      nuevaDuracionReservaEscenario;
    private int      nuevoNumeroReservasDiaEscenario;
    private UploadedFile nuevaImagenEscenario;
    private UploadedFile nuevaFotoEscenario;
    private String  rutaImagenEscenario;
    private String  rutaFotoEscenario;
    private String  nuevoNombreEscenario;
    private String  nuevoAnchoMedidaEscenario;
    private String  nuevoLargoMedidaEscenario;
    private String  nuevaDescripcionEscenario;
    private String  nuevaRecomendacionEscenario;
    private String  nuevoEstadoEscenario;
    private String  nuevoTipoEscenario;
    private boolean desabilitarEditarImagenEscenario;
    private boolean desabilitarEditarConfiguracionInterfazEscenario;
    private boolean desabilitarEditarFotoEscenario;
    private boolean desabilitarEditarNombreEscenario;
    private boolean desabilitarEditarMedidasEscenario;
    private boolean desabilitarEditarDescripcionEscenario;
    private boolean desabilitarEditarRecomendacionesEscenario;
    private boolean desabilitarEditarEstadoEscenario;
    private boolean desabilitarEditarDuracionReservaEscenario;
    private boolean desabilitarEditarNumReservasDiaEscenario;
    private boolean desabilitarEditarTipoEscenario;
    private ArrayList<Escenario>listaEscenariosPosicion;
    private List<Estadoescenario> listaEstadosEscenario;
    private List<Integer> listaNumeroReservasDiaEscenario;
    private List<Tipoescenario> listaTiposEscenario;
    
    public EditarEscenarioController() 
    {
       this.desabilitarEditarImagenEscenario=true;
       this.desabilitarEditarConfiguracionInterfazEscenario=true;
       this.desabilitarEditarFotoEscenario=true;
       this.desabilitarEditarNombreEscenario=true;
       this.desabilitarEditarMedidasEscenario=true;
       this.desabilitarEditarDescripcionEscenario=true;
       this.desabilitarEditarRecomendacionesEscenario=true;
       this.desabilitarEditarEstadoEscenario=true;
       this.desabilitarEditarDuracionReservaEscenario=true;
       this.desabilitarEditarNumReservasDiaEscenario=true;
       this.desabilitarEditarTipoEscenario=true;
       String OS = System.getProperty("os.name").toLowerCase();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("/"); 
        if(OS.contains("nux"))
        {
           this.rutaImagenEscenario=realPath.replace("build/", "")+"resources/img/escenarios/"; 
           this.rutaFotoEscenario=realPath.replace("build/", "")+"resources/img/fotosEscenarios/"; 
        }
        else
        {
            this.rutaImagenEscenario=realPath.replace("build\\", "")+"resources\\img\\escenarios\\";
            this.rutaFotoEscenario=realPath.replace("build\\", "")+"resources/img/fotosEscenarios\\";
        }
    }

//---------------------setters and getter-------------------------------------
   
    public boolean isDesabilitarEditarImagenEscenario() 
    {
        return desabilitarEditarImagenEscenario;
    }

    public void setDesabilitarEditarImagenEscenario(boolean desabilitarEditarImagenEscenario) 
    {
        this.desabilitarEditarImagenEscenario = desabilitarEditarImagenEscenario;
    }
    
    public boolean isDesabilitarEditarConfiguracionInterfazEscenario() 
    {
        return desabilitarEditarConfiguracionInterfazEscenario;
    }

    public void setDesabilitarEditarConfiguracionInterfazEscenario(boolean desabilitarEditarConfiguracionInterfazEscenario) 
    {
        this.desabilitarEditarConfiguracionInterfazEscenario = desabilitarEditarConfiguracionInterfazEscenario;
    }
    
    
    public boolean isDesabilitarEditarFotoEscenario() 
    {
        return desabilitarEditarFotoEscenario;
    }

    public void setDesabilitarEditarFotoEscenario(boolean desabilitarEditarFotoEscenario) 
    {
        this.desabilitarEditarFotoEscenario = desabilitarEditarFotoEscenario;
    }
    
    public boolean isDesabilitarEditarNombreEscenario()
    {
        return desabilitarEditarNombreEscenario;
    }

    public void setDesabilitarEditarNombreEscenario(boolean desabilitarEditarNombreEscenario)
    {
        this.desabilitarEditarNombreEscenario = desabilitarEditarNombreEscenario;
    }

    public boolean isDesabilitarEditarMedidasEscenario() 
    {
        return desabilitarEditarMedidasEscenario;
    }

    public void setDesabilitarEditarMedidasEscenario(boolean desabilitarEditarMedidasEscenario) 
    {
        this.desabilitarEditarMedidasEscenario = desabilitarEditarMedidasEscenario;
    }

    public Integer getNuevoAnchoImagenEscenario() 
    {
        return nuevoAnchoImagenEscenario;
    }

    public void setNuevoAnchoImagenEscenario(Integer nuevoAnchoImagenEscenario) 
    {
        this.nuevoAnchoImagenEscenario = nuevoAnchoImagenEscenario;
    }    

    public Integer getNuevoLargoImagenEscenario() 
    {
        return nuevoLargoImagenEscenario;
    }

    public void setNuevoLargoImagenEscenario(Integer nuevoLargoImagenEscenario) 
    {
        this.nuevoLargoImagenEscenario = nuevoLargoImagenEscenario;
    }    

    public Integer getNuevaMargenSuperiorImagenEscenario() 
    {
        return nuevaMargenSuperiorImagenEscenario;
    }

    public void setNuevaMargenSuperiorImagenEscenario(Integer nuevaMargenSuperiorImagenEscenario) 
    {
        this.nuevaMargenSuperiorImagenEscenario = nuevaMargenSuperiorImagenEscenario;
    }    

    public Integer getNuevaMargenIzquierdaImagenEscenario() 
    {
        return nuevaMargenIzquierdaImagenEscenario;
    }

    public void setNuevaMargenIzquierdaImagenEscenario(Integer nuevaMargenIzquierdaImagenEscenario) 
    {
        this.nuevaMargenIzquierdaImagenEscenario = nuevaMargenIzquierdaImagenEscenario;
    }

    public Integer getNuevaRotacionImagenEscenario() 
    {
        return nuevaRotacionImagenEscenario;
    }

    public void setNuevaRotacionImagenEscenario(Integer nuevaRotacionImagenEscenario) 
    {
        this.nuevaRotacionImagenEscenario = nuevaRotacionImagenEscenario;
    }

    public UploadedFile getNuevaImagenEscenario() 
    {
        return nuevaImagenEscenario;
    }

    public void setNuevaImagenEscenario(UploadedFile nuevaImagenEscenario) 
    {
        this.nuevaImagenEscenario = nuevaImagenEscenario;
    }

    public UploadedFile getNuevaFotoEscenario() 
    {
        return nuevaFotoEscenario;
    }

    public void setNuevaFotoEscenario(UploadedFile nuevaFotoEscenario) 
    {
        this.nuevaFotoEscenario = nuevaFotoEscenario;
    }

    public String getNuevoNombreEscenario() 
    {
        return nuevoNombreEscenario;
    }

    public void setNuevoNombreEscenario(String nuevoNombreEscenario) 
    {
        this.nuevoNombreEscenario = nuevoNombreEscenario;
    }

    public String getNuevoAnchoMedidaEscenario() 
    {
        return nuevoAnchoMedidaEscenario;
    }

    public void setNuevoAnchoMedidaEscenario(String nuevoAnchoMedidaEscenario) 
    {
        this.nuevoAnchoMedidaEscenario = nuevoAnchoMedidaEscenario;
    }

    public String getNuevoLargoMedidaEscenario() 
    {
        return nuevoLargoMedidaEscenario;
    }

    public void setNuevoLargoMedidaEscenario(String nuevoLargoMedidaEscenario) 
    {
        this.nuevoLargoMedidaEscenario = nuevoLargoMedidaEscenario;
    }

    public boolean isDesabilitarEditarDescripcionEscenario() 
    {
        return desabilitarEditarDescripcionEscenario;
    }

    public void setDesabilitarEditarDescripcionEscenario(boolean desabilitarEditarDescripcionEscenario) 
    {
        this.desabilitarEditarDescripcionEscenario = desabilitarEditarDescripcionEscenario;
    }

    public String getNuevaDescripcionEscenario() 
    {
        return nuevaDescripcionEscenario;
    }

    public void setNuevaDescripcionEscenario(String nuevaDescripcionEscenario) 
    {
        this.nuevaDescripcionEscenario = nuevaDescripcionEscenario;
    }

    public String getNuevaRecomendacionEscenario() 
    {
        return nuevaRecomendacionEscenario;
    }

    public void setNuevaRecomendacionEscenario(String nuevaRecomendacionEscenario) 
    {
        this.nuevaRecomendacionEscenario = nuevaRecomendacionEscenario;
    }

    public boolean isDesabilitarEditarRecomendacionesEscenario() 
    {
        return desabilitarEditarRecomendacionesEscenario;
    }

    public void setDesabilitarEditarRecomendacionesEscenario(boolean desabilitarEditarRecomendacionesEscenario)
    {
        this.desabilitarEditarRecomendacionesEscenario = desabilitarEditarRecomendacionesEscenario;
    }

    public String getNuevoEstadoEscenario() 
    {
        return nuevoEstadoEscenario;
    }

    public void setNuevoEstadoEscenario(String nuevoEstadoEscenario)
    {
        this.nuevoEstadoEscenario = nuevoEstadoEscenario;
    }

    public boolean isDesabilitarEditarEstadoEscenario()
    {
        return desabilitarEditarEstadoEscenario;
    }

    public void setDesabilitarEditarEstadoEscenario(boolean desabilitarEditarEstadoEscenario) 
    {
        this.desabilitarEditarEstadoEscenario = desabilitarEditarEstadoEscenario;
    }

    public List<Estadoescenario> getListaEstadosEscenario() 
    {
        return listaEstadosEscenario;
    }

    public void setListaEstadosEscenario(List<Estadoescenario> listaEstadosEscenario) 
    {
        this.listaEstadosEscenario = listaEstadosEscenario;
    }

    public int getNuevaDuracionReservaEscenario()
    {
        return nuevaDuracionReservaEscenario;
    }

    public void setNuevaDuracionReservaEscenario(int nuevaDuracionReservaEscenario) 
    {
        this.nuevaDuracionReservaEscenario = nuevaDuracionReservaEscenario;
    }

    public boolean isDesabilitarEditarDuracionReservaEscenario() 
    {
        return desabilitarEditarDuracionReservaEscenario;
    }

    public void setDesabilitarEditarDuracionReservaEscenario(boolean desabilitarEditarDuracionReservaEscenario)
    {
        this.desabilitarEditarDuracionReservaEscenario = desabilitarEditarDuracionReservaEscenario;
    }

    public int getNuevoNumeroReservasDiaEscenario() 
    {
        return nuevoNumeroReservasDiaEscenario;
    }

    public void setNuevoNumeroReservasDiaEscenario(int nuevoNumeroReservasDiaEscenario)
    {
        this.nuevoNumeroReservasDiaEscenario = nuevoNumeroReservasDiaEscenario;
    }

    public boolean isDesabilitarEditarNumReservasDiaEscenario() 
    {
        return desabilitarEditarNumReservasDiaEscenario;
    }

    public void setDesabilitarEditarNumReservasDiaEscenario(boolean desabilitarEditarNumReservasDiaEscenario) 
    {
        this.desabilitarEditarNumReservasDiaEscenario = desabilitarEditarNumReservasDiaEscenario;
    }

    public List<Integer> getListaNumeroReservasDiaEscenario()
    {
        return listaNumeroReservasDiaEscenario;
    }

    public void setListaNumeroReservasDiaEscenario(List<Integer> listaNumeroReservasDiaEscenario) 
    {
        this.listaNumeroReservasDiaEscenario = listaNumeroReservasDiaEscenario;
    }

    public String getNuevoTipoEscenario() 
    {
        return nuevoTipoEscenario;
    }

    public void setNuevoTipoEscenario(String nuevoTipoEscenario)
    {
        this.nuevoTipoEscenario = nuevoTipoEscenario;
    }

    public boolean isDesabilitarEditarTipoEscenario() 
    {
        return desabilitarEditarTipoEscenario;
    }

    public void setDesabilitarEditarTipoEscenario(boolean desabilitarEditarTipoEscenario)
    {
        this.desabilitarEditarTipoEscenario = desabilitarEditarTipoEscenario;
    }

    public List<Tipoescenario> getListaTiposEscenario() 
    {
        return listaTiposEscenario;
    }

    public void setListaTiposEscenario(List<Tipoescenario> listaTiposEscenario)
    {
        this.listaTiposEscenario = listaTiposEscenario;
    }
    
//---------------------fin setters and getters--------------------------------    

//---------------------Metodos propios del controlador------------------------
    
    private void guardarArchivo(String filename, InputStream in,String rutaArchivo)
    {
       try 
       { 
            OutputStream out = new FileOutputStream(new File(rutaArchivo + filename));              
            int read = 0;
            byte[] bytes = new byte[1024];              
            while ((read = in.read(bytes)) != -1) 
            {
                out.write(bytes, 0, read);
            }              
            in.close();
            out.flush();
            out.close();
       } catch (IOException e)
       {
            System.out.println(e.getMessage());
       } 
    }
    
    public void editarConfiguracionInterfaz(MostrarEditarEscenarioController mgb)
    {
        this.desabilitarEditarConfiguracionInterfazEscenario=false;
        this.listaEscenariosPosicion=new ArrayList();
        this.nuevoAnchoImagenEscenario=mgb.getEscenarioSeleccionado().getEscanchoimagen();
        this.nuevoLargoImagenEscenario=mgb.getEscenarioSeleccionado().getEsclargoimagen();
        this.nuevaMargenSuperiorImagenEscenario=mgb.getEscenarioSeleccionado().getEscmargensuperior();
        this.nuevaMargenIzquierdaImagenEscenario=mgb.getEscenarioSeleccionado().getEscmargenizquierda();
        this.nuevaRotacionImagenEscenario=mgb.getEscenarioSeleccionado().getEscrotarimagen();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    
    public void aumentarAnchoImagenEscenario(MostrarEditarEscenarioController mgb)
    {
        this.nuevoAnchoImagenEscenario=this.nuevoAnchoImagenEscenario+1;
        mgb.getEscenarioSeleccionado().setEscanchoimagen(this.nuevoAnchoImagenEscenario);
        mgb.refrescarVista();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    
    public void disminuirAnchoImagenEscenario(MostrarEditarEscenarioController mgb)
    {
        if(this.nuevoAnchoImagenEscenario>0)
        {
            this.nuevoAnchoImagenEscenario = this.nuevoAnchoImagenEscenario - 1;
            mgb.getEscenarioSeleccionado().setEscanchoimagen(this.nuevoAnchoImagenEscenario);
            mgb.refrescarVista();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionEscenario");
        }
        
    }
    
    public void aumentarLargoImagenEscenario(MostrarEditarEscenarioController mgb)
    {
        this.nuevoLargoImagenEscenario=this.nuevoLargoImagenEscenario+1;
        mgb.getEscenarioSeleccionado().setEsclargoimagen(this.nuevoLargoImagenEscenario);
        mgb.refrescarVista();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    
    public void disminuirLargoImagenEscenario(MostrarEditarEscenarioController mgb)
    {
        if(this.nuevoLargoImagenEscenario>0)
        {
            this.nuevoLargoImagenEscenario = this.nuevoLargoImagenEscenario - 1;
            mgb.getEscenarioSeleccionado().setEsclargoimagen(this.nuevoLargoImagenEscenario);
            mgb.refrescarVista();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionEscenario");
        }
        
    }
    
    public void aumentarMargenSuperiorImagenEscenario(MostrarEditarEscenarioController mgb)
    {
        this.nuevaMargenSuperiorImagenEscenario=this.nuevaMargenSuperiorImagenEscenario+1;
        mgb.getEscenarioSeleccionado().setEscmargensuperior(this.nuevaMargenSuperiorImagenEscenario);
        mgb.refrescarVista();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    
    public void disminuirMargenSuperiorImagenEscenario(MostrarEditarEscenarioController mgb)
    {
        if(this.nuevaMargenSuperiorImagenEscenario>0)
        {
            this.nuevaMargenSuperiorImagenEscenario = this.nuevaMargenSuperiorImagenEscenario - 1;
            mgb.getEscenarioSeleccionado().setEscmargensuperior(this.nuevaMargenSuperiorImagenEscenario);
            mgb.refrescarVista();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionEscenario");
        }
        
    }
    
    public void margenSuperiorImagenArriba(MostrarEditarEscenarioController mgb)
    {
        this.nuevaMargenSuperiorImagenEscenario =0;
        mgb.getEscenarioSeleccionado().setEscmargensuperior(this.nuevaMargenSuperiorImagenEscenario);
        mgb.refrescarVista();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    
    public void margenSuperiorImagenMitad(MostrarEditarEscenarioController mgb)
    {
        int tamano=mgb.getEscenarioSeleccionado().getEsclargoimagen();
        int tamanoEspacio=mgb.getEspacioEscenarioSeleccionado().getEspancho();
        int tamanoReal=tamanoEspacio- tamano;
        tamanoReal=tamanoReal/2;
        this.nuevaMargenSuperiorImagenEscenario =tamanoReal;
        mgb.getEscenarioSeleccionado().setEscmargensuperior(this.nuevaMargenSuperiorImagenEscenario);
        mgb.refrescarVista();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    
    public void margenSuperiorImagenAbajo(MostrarEditarEscenarioController mgb)
    {
        int tamano=mgb.getEscenarioSeleccionado().getEsclargoimagen();
        int tamanoEspacio=mgb.getEspacioEscenarioSeleccionado().getEspancho();
        int tamanoReal=tamanoEspacio- tamano;        
        this.nuevaMargenSuperiorImagenEscenario =tamanoReal-1;
        mgb.getEscenarioSeleccionado().setEscmargensuperior(this.nuevaMargenSuperiorImagenEscenario);
        mgb.refrescarVista();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    
    public void aumentarMargenIzquierdaImagenEscenario(MostrarEditarEscenarioController mgb)
    {
        this.nuevaMargenIzquierdaImagenEscenario=this.nuevaMargenIzquierdaImagenEscenario+1;
        mgb.getEscenarioSeleccionado().setEscmargenizquierda(this.nuevaMargenIzquierdaImagenEscenario);
        mgb.refrescarVista();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    
    public void disminuirMargenIzquierdaImagenEscenario(MostrarEditarEscenarioController mgb)
    {
        if(this.nuevaMargenIzquierdaImagenEscenario>0)
        {
            this.nuevaMargenIzquierdaImagenEscenario = this.nuevaMargenIzquierdaImagenEscenario - 1;
            mgb.getEscenarioSeleccionado().setEscmargenizquierda(this.nuevaMargenIzquierdaImagenEscenario);
            mgb.refrescarVista();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionEscenario");
        }        
    }
    
    public void margenIzquierdaImagenIzquierda(MostrarEditarEscenarioController mgb)
    {
        this.nuevaMargenIzquierdaImagenEscenario =0;
        mgb.getEscenarioSeleccionado().setEscmargenizquierda(this.nuevaMargenIzquierdaImagenEscenario);
        mgb.refrescarVista();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    
    public void margenIzquierdaImagenMitad(MostrarEditarEscenarioController mgb)
    {
        int tamano=mgb.getEscenarioSeleccionado().getEscanchoimagen();
        int tamanoEspacio=932;
        int tamanoReal=tamanoEspacio- tamano;
        tamanoReal=tamanoReal/2;
        this.nuevaMargenIzquierdaImagenEscenario =tamanoReal;
        mgb.getEscenarioSeleccionado().setEscmargenizquierda(this.nuevaMargenIzquierdaImagenEscenario);
        mgb.refrescarVista();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    
    public void margenIzquierdaImagenDerecha(MostrarEditarEscenarioController mgb)
    {
        int tamano=mgb.getEscenarioSeleccionado().getEscanchoimagen();
        int tamanoEspacio=932;
        int tamanoReal=tamanoEspacio- tamano;        
        this.nuevaMargenIzquierdaImagenEscenario =tamanoReal-1;
        mgb.getEscenarioSeleccionado().setEscmargenizquierda(this.nuevaMargenIzquierdaImagenEscenario);
        mgb.refrescarVista();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    
    public void rotarImagenDerecha(MostrarEditarEscenarioController mgb)
    {
               
        if(this.nuevaRotacionImagenEscenario==359)
        {
            this.nuevaRotacionImagenEscenario=0;
        }
        else
        {
            this.nuevaRotacionImagenEscenario=this.nuevaRotacionImagenEscenario+1;
        }
        mgb.getEscenarioSeleccionado().setEscrotarimagen(this.nuevaRotacionImagenEscenario);
        mgb.refrescarVista();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    
    public void rotarImagenIzquierda(MostrarEditarEscenarioController mgb)
    {
               
        if(this.nuevaRotacionImagenEscenario==0)
        {
            this.nuevaRotacionImagenEscenario=359;
        }
        else
        {
            this.nuevaRotacionImagenEscenario=this.nuevaRotacionImagenEscenario-1;
        }
        mgb.getEscenarioSeleccionado().setEscrotarimagen(this.nuevaRotacionImagenEscenario);
        mgb.refrescarVista();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    
    public void abrirVentanaPosicionEscenario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formularioPosicionEscenario");
        requestContext.execute("PF('PosicionEscenario').show()");
    }
    public void subirEscenario(MostrarEditarEscenarioController mgb)
    {
       if(mgb.getEscenarioSeleccionado().getEscposicion()>1)
       {
            mgb.getListaEscenarios().get(mgb.getEscenarioSeleccionado().getEscposicion()-2).setEscposicion(mgb.getEscenarioSeleccionado().getEscposicion());
            if(!this.listaEscenariosPosicion.contains(mgb.getListaEscenarios().get(mgb.getEscenarioSeleccionado().getEscposicion()-2)))
            {
                this.listaEscenariosPosicion.add(mgb.getListaEscenarios().get(mgb.getEscenarioSeleccionado().getEscposicion()-2));
            }
            mgb.getEscenarioSeleccionado().setEscposicion(mgb.getEscenarioSeleccionado().getEscposicion()-1);
            Collections.sort(mgb.getListaEscenarios(), new Comparator(){            

                @Override
                public int compare(Object e1, Object e2) {
                     return new Integer(((Escenario)e1).getEscposicion()).compareTo(((Escenario)e2).getEscposicion());
                }
            });
            mgb.refrescarVista();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionEscenario");
            requestContext.update("formularioPosicionEscenario");
       }
    }
    
    public void bajarEscenario(MostrarEditarEscenarioController mgb)
    {
       if(mgb.getEscenarioSeleccionado().getEscposicion()<mgb.getListaEscenarios().size())
       {
            mgb.getListaEscenarios().get(mgb.getEscenarioSeleccionado().getEscposicion()).setEscposicion(mgb.getEscenarioSeleccionado().getEscposicion());
            if(!this.listaEscenariosPosicion.contains(mgb.getListaEscenarios().get(mgb.getEscenarioSeleccionado().getEscposicion())))
            {
                this.listaEscenariosPosicion.add(mgb.getListaEscenarios().get(mgb.getEscenarioSeleccionado().getEscposicion()));
            }
            mgb.getEscenarioSeleccionado().setEscposicion(mgb.getEscenarioSeleccionado().getEscposicion()+1);
            Collections.sort(mgb.getListaEscenarios(), new Comparator(){            

                @Override
                public int compare(Object e1, Object e2) {
                     return new Integer(((Escenario)e1).getEscposicion()).compareTo(((Escenario)e2).getEscposicion());
                }
            });
            mgb.refrescarVista();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionEscenario");
            requestContext.update("formularioPosicionEscenario");
       }
    }
    
    public void cancelarEditarConfiguracionInterfaz(MostrarEditarEscenarioController mgb)
    {
        this.desabilitarEditarConfiguracionInterfazEscenario=true;
        mgb.setListaEscenarios(this.escenarioEJB.buscarPorEspID(mgb.getEspacioEscenarioSeleccionado().getEspid()));        
        for(Escenario esc: mgb.getListaEscenarios())
        {
            if(Objects.equals(esc.getEscid(), mgb.getEscenarioSeleccionado().getEscid()))
            {
                mgb.setEscenarioSeleccionado(esc);                
                mgb.getEscenarioSeleccionado().setBorde(1);
                break;
            }
        }
        mgb.refrescarVista();       
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    
    public void AceptarEditarConfiguracionInterfazEscenario(MostrarEditarEscenarioController mgb)
    {
        mgb.getEscenarioSeleccionado().setBorde(0);
        for(Escenario es:this.listaEscenariosPosicion)
        {
            this.escenarioEJB.edit(es);
        }
        this.escenarioEJB.edit(mgb.getEscenarioSeleccionado());
        mgb.getEscenarioSeleccionado().setBorde(1);        
        this.listaEscenariosPosicion=null;
        this.desabilitarEditarConfiguracionInterfazEscenario=true;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");        
    }
    
    public void cambiarImagenAnimada()
    {
        this.desabilitarEditarImagenEscenario=false; 
        this.nuevaImagenEscenario=null;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioImagenAnimada");
        requestContext.update("formOpcionesImagenAnimada");
    }
    
    public void cargarEditarImagenEscenario(FileUploadEvent event)
    {
        this.nuevaImagenEscenario=event.getFile();
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenarioImagenAnimada");
    }
    
    public void cancelarCambiarImagenAnimada()
    {
        this.nuevaImagenEscenario=null;
        this.desabilitarEditarImagenEscenario=true;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioImagenAnimada");
        requestContext.update("formOpcionesImagenAnimada");
    }
    
    public void aceptarCambiarImagenAnimada(MostrarEditarEscenarioController mgb) throws IOException, InterruptedException
    {
        if(this.nuevaImagenEscenario!=null)
        {
          String nombreImagenAnterior=mgb.getEscenarioSeleccionado().getEscnombreimagenanimada();
          int i = this.nuevaImagenEscenario.getFileName().lastIndexOf('.');            
          String extension = this.nuevaImagenEscenario.getFileName().substring(i+1);
          
          int indiceImagenAnterior=nombreImagenAnterior.lastIndexOf(".");
          String imagenAnterior=nombreImagenAnterior.substring(0, indiceImagenAnterior);
          int indiceImagenFiltrada=imagenAnterior.lastIndexOf("_");
          String numerostring=imagenAnterior.substring(indiceImagenFiltrada+1);
          int numero=Integer.parseInt(numerostring);
          numero=numero+1;
          String nombreImagenNueva="esc"+mgb.getEscenarioSeleccionado().getEscid()+"_"+numero+"."+extension;
          this.guardarArchivo(nombreImagenNueva, this.nuevaImagenEscenario.getInputstream(),this.rutaImagenEscenario);
          Thread.sleep(5000);
          mgb.getEscenarioSeleccionado().setEscnombreimagenanimada(nombreImagenNueva);
          Escenario escen=this.escenarioEJB.find(mgb.getEscenarioSeleccionado().getEscid());
          escen.setEscnombreimagenanimada(nombreImagenNueva);
          this.escenarioEJB.edit(escen);
          File fichero = new File(this.rutaImagenEscenario+nombreImagenAnterior);
          fichero.delete();
          this.desabilitarEditarImagenEscenario=true;
          mgb.refrescarVista();
          RequestContext requestContext = RequestContext.getCurrentInstance();
          requestContext.update("formGestionEscenarioImagenAnimada");
          requestContext.update("formOpcionesImagenAnimada");          
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha cargado una imagen.", "No se ha cargado una imagen."));
        }
    }
    
    public void cambiarFotoEscenario()
    {
        this.desabilitarEditarFotoEscenario=false;
        this.nuevaFotoEscenario=null;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioFotoEscenario");
        requestContext.update("opcionesCambiarFotoEscenario");        
    }
    
    public void cargarFotoEscenario(FileUploadEvent event)
    {
        this.nuevaFotoEscenario=event.getFile();
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenarioFotoEscenario");
    }
    
    public void cancelarCambiarFotoEscenario()
    {
        this.desabilitarEditarFotoEscenario=true;
        this.nuevaFotoEscenario=null;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioFotoEscenario");
        requestContext.update("opcionesCambiarFotoEscenario");
    }
    
    public void aceptarCambiarFotoEscenario(MostrarEditarEscenarioController mgb) throws IOException, InterruptedException
    {
        if(this.nuevaFotoEscenario!=null)
        {            
          String nombreFotoAnterior=mgb.getEscenarioSeleccionado().getEscnombreimagenreal();
          int i = this.nuevaFotoEscenario.getFileName().lastIndexOf('.');            
          String extension = this.nuevaFotoEscenario.getFileName().substring(i+1);
          if(nombreFotoAnterior.equals("vacio"))
          {
              String nombreFotoNueva="esc"+mgb.getEscenarioSeleccionado().getEscid()+"_1"+"."+extension;
              this.guardarArchivo(nombreFotoNueva, this.nuevaFotoEscenario.getInputstream(),this.rutaFotoEscenario);
              Thread.sleep(5000);
              mgb.getEscenarioSeleccionado().setEscnombreimagenreal(nombreFotoNueva);
              Escenario escen=this.escenarioEJB.find(mgb.getEscenarioSeleccionado().getEscid());
              escen.setEscnombreimagenreal(nombreFotoNueva);
              this.escenarioEJB.edit(escen);
          }
          else
          { 
            int indiceImagenAnterior=nombreFotoAnterior.lastIndexOf(".");
            String fotoAnterior=nombreFotoAnterior.substring(0, indiceImagenAnterior);
            int indiceImagenFiltrada=fotoAnterior.lastIndexOf("_");
            String numerostring=fotoAnterior.substring(indiceImagenFiltrada+1);
            int numero=Integer.parseInt(numerostring);
            numero=numero+1;
            String nombreFotoNueva="esc"+mgb.getEscenarioSeleccionado().getEscid()+"_"+numero+"."+extension;
            this.guardarArchivo(nombreFotoNueva, this.nuevaFotoEscenario.getInputstream(),this.rutaFotoEscenario);
            Thread.sleep(5000);
            mgb.getEscenarioSeleccionado().setEscnombreimagenreal(nombreFotoNueva);
            Escenario escen=this.escenarioEJB.find(mgb.getEscenarioSeleccionado().getEscid());
            escen.setEscnombreimagenreal(nombreFotoNueva);
            this.escenarioEJB.edit(escen);
            File fichero = new File(this.rutaFotoEscenario+nombreFotoAnterior);
            fichero.delete(); 
          }
          this.desabilitarEditarFotoEscenario=true;   
          RequestContext requestContext = RequestContext.getCurrentInstance();
          requestContext.update("formGestionEscenarioFotoEscenario");
          requestContext.update("opcionesCambiarFotoEscenario");           
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha cargado una foto.", "No se ha cargado una foto."));
        }
    }
    
    public void editarNombreEscenario(MostrarEditarEscenarioController mgb)
    {
        this.desabilitarEditarNombreEscenario=false;
        this.nuevoNombreEscenario=mgb.getEscenarioSeleccionado().getEscnombre();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void cancelarEditarNombreEscenario()
    {
        this.desabilitarEditarNombreEscenario=true;
        this.nuevoNombreEscenario="";
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void aceptarEditarNombreEscenario(MostrarEditarEscenarioController mgb)
    {
        ValidarAtributosEscenario validador = new ValidarAtributosEscenario();        
        if(validador.validarNombreEscenario(this.nuevoNombreEscenario,this.escenarioEJB))
        {
            mgb.getEscenarioSeleccionado().setEscnombre(this.nuevoNombreEscenario);
            Escenario esc = this.escenarioEJB.find(mgb.getEscenarioSeleccionado().getEscid());
            esc.setEscnombre(this.nuevoNombreEscenario);
            this.escenarioEJB.edit(esc);
            this.desabilitarEditarNombreEscenario=true;
            this.nuevoNombreEscenario="";
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionEscenarioInformacionGeneral");
        }
    }
    
    public void editarMedidasEscenario(MostrarEditarEscenarioController mgb)
    {
        this.desabilitarEditarMedidasEscenario=false;
        if(mgb.getEscenarioSeleccionado().getEscmedidas()==null)
        {
            this.nuevoAnchoMedidaEscenario="0";
            this.nuevoLargoMedidaEscenario="0";
        }
        else
        {
          int i = mgb.getEscenarioSeleccionado().getEscmedidas().indexOf(':');            
          String subcadena = mgb.getEscenarioSeleccionado().getEscmedidas().substring(i+1);
          i=subcadena.indexOf('m');
          this.nuevoAnchoMedidaEscenario=subcadena.substring(0,i);          
          subcadena=subcadena.substring(i+1);
          i=subcadena.indexOf(' ');
          subcadena=subcadena.substring(i+1);          
          i=subcadena.indexOf(':');
          subcadena=subcadena.substring(i+1);
          i=subcadena.indexOf('m');
          this.nuevoLargoMedidaEscenario=subcadena.substring(0,i);
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void cancelarEditarMedidasEscenario()
    {
        this.desabilitarEditarMedidasEscenario=true;
        this.nuevoAnchoMedidaEscenario="";
        this.nuevoLargoMedidaEscenario="";
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void aceptarEditarMedidasEscenario(MostrarEditarEscenarioController mgb)
    {
        ValidarAtributosEscenario validador = new ValidarAtributosEscenario();        
        if(validador.validarMedidaEscenarioAncho(this.nuevoAnchoMedidaEscenario) && validador.validarMedidaEscenarioLargo(this.nuevoLargoMedidaEscenario))
        {
            mgb.getEscenarioSeleccionado().setEscmedidas("Ancho:"+this.nuevoAnchoMedidaEscenario+"mts Largo:"+this.nuevoLargoMedidaEscenario+"mts");
            Escenario esc = this.escenarioEJB.find(mgb.getEscenarioSeleccionado().getEscid());
            esc.setEscmedidas("Ancho:"+this.nuevoAnchoMedidaEscenario+"mts Largo:"+this.nuevoLargoMedidaEscenario+"mts");
            this.escenarioEJB.edit(esc);
            this.desabilitarEditarMedidasEscenario=true;
            this.nuevoAnchoMedidaEscenario="";
            this.nuevoLargoMedidaEscenario="";
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionEscenarioInformacionGeneral");
        }
    }
    
    public void editarDescripcionEscenario(MostrarEditarEscenarioController mgb)
    {
        this.desabilitarEditarDescripcionEscenario=false;
        this.nuevaDescripcionEscenario=mgb.getEscenarioSeleccionado().getEscdescripcion();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void cancelarEditarDescripcionEscenario()
    {
        this.desabilitarEditarDescripcionEscenario=true;
        this.nuevaDescripcionEscenario="";
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void aceptarEditarDescripcionEscenario(MostrarEditarEscenarioController mgb)
    {
        ValidarAtributosEscenario validador = new ValidarAtributosEscenario();        
        if(validador.validarDescripcionEscenarios(this.nuevaDescripcionEscenario))
        {
            mgb.getEscenarioSeleccionado().setEscdescripcion(this.nuevaDescripcionEscenario);
            Escenario esc = this.escenarioEJB.find(mgb.getEscenarioSeleccionado().getEscid());
            esc.setEscdescripcion(this.nuevaDescripcionEscenario);
            this.escenarioEJB.edit(esc);
            this.desabilitarEditarDescripcionEscenario=true;
            this.nuevaDescripcionEscenario="";
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionEscenarioInformacionGeneral");
        }
    }
    
    public void editarRecomendacionesEscenario(MostrarEditarEscenarioController mgb)
    {
        this.desabilitarEditarRecomendacionesEscenario=false;
        this.nuevaRecomendacionEscenario=mgb.getEscenarioSeleccionado().getEscrecomendaciones();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void cancelarEditarRecomendacionesEscenario()
    {
        this.desabilitarEditarRecomendacionesEscenario=true;
        this.nuevaRecomendacionEscenario="";
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void aceptarEditarRecomendacionesEscenario(MostrarEditarEscenarioController mgb)
    {
        ValidarAtributosEscenario validador = new ValidarAtributosEscenario();        
        if(validador.validarRecomendacionesEscenarios(this.nuevaRecomendacionEscenario))
        {
            mgb.getEscenarioSeleccionado().setEscrecomendaciones(this.nuevaRecomendacionEscenario);
            Escenario esc = this.escenarioEJB.find(mgb.getEscenarioSeleccionado().getEscid());
            esc.setEscrecomendaciones(this.nuevaRecomendacionEscenario);
            this.escenarioEJB.edit(esc);
            this.desabilitarEditarRecomendacionesEscenario=true;
            this.nuevaRecomendacionEscenario="";
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionEscenarioInformacionGeneral");
        }
    }
    
    public void editarEstadoEscenario(MostrarEditarEscenarioController mgb)
    {
        this.desabilitarEditarEstadoEscenario=false;
        this.nuevoEstadoEscenario=mgb.getEscenarioSeleccionado().getEscestescid().getEstescnombre();
        this.listaEstadosEscenario=this.estadoEscenarioEJB.findAll();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void cancelarEditarEstadoEscenario()
    {
        this.desabilitarEditarEstadoEscenario=true;
        this.nuevoEstadoEscenario="";
        this.listaEstadosEscenario=null;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void aceptarEditarEstadoEscenario(MostrarEditarEscenarioController mgb)
    {
        Estadoescenario estadoescenario=this.estadoEscenarioEJB.buscarPorNombre(this.nuevoEstadoEscenario).get(0);
        mgb.getEscenarioSeleccionado().setEscestescid(estadoescenario);
        Escenario esc = this.escenarioEJB.find(mgb.getEscenarioSeleccionado().getEscid());
        esc.setEscestescid(estadoescenario);
        this.escenarioEJB.edit(esc);
        this.desabilitarEditarEstadoEscenario=true;
        this.nuevoEstadoEscenario="";
        this.listaEstadosEscenario=null;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
       
    }
    
    public void editarDuracionReservaEscenario(MostrarEditarEscenarioController mgb)
    {
        this.desabilitarEditarDuracionReservaEscenario=false;
        this.nuevaDuracionReservaEscenario=mgb.getEscenarioSeleccionado().getEscduracionreserva();        
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void cancelarEditarDuracionReservaEscenario()
    {
        this.desabilitarEditarDuracionReservaEscenario=true;        
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void aceptarEditarDuracionReservaEscenario(MostrarEditarEscenarioController mgb)
    {            
        mgb.getEscenarioSeleccionado().setEscduracionreserva(this.nuevaDuracionReservaEscenario);
        Escenario esc = this.escenarioEJB.find(mgb.getEscenarioSeleccionado().getEscid());
        esc.setEscduracionreserva(this.nuevaDuracionReservaEscenario);
        this.escenarioEJB.edit(esc);
        this.desabilitarEditarDuracionReservaEscenario=true;            
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");       
    }
    
    public void editarNumeroReservasDiaEscenario(MostrarEditarEscenarioController mgb)
    {
        this.desabilitarEditarNumReservasDiaEscenario=false;
        this.nuevoNumeroReservasDiaEscenario=mgb.getEscenarioSeleccionado().getEscvecesreservadia();
        this.listaNumeroReservasDiaEscenario= new ArrayList();
        int tamaño=mgb.getEscenarioSeleccionado().getEspid().getEspresevhoradia();
        for(int i=1;i<=tamaño;i++)
        {
            this.listaNumeroReservasDiaEscenario.add(i);
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void cancelarEditarNumeroReservasDiaEscenario()
    {
        this.desabilitarEditarNumReservasDiaEscenario=true;
        this.listaNumeroReservasDiaEscenario=null;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void aceptarEditarNumeroReservasDiaEscenario(MostrarEditarEscenarioController mgb)
    {            
        mgb.getEscenarioSeleccionado().setEscvecesreservadia(this.nuevoNumeroReservasDiaEscenario);
        Escenario esc = this.escenarioEJB.find(mgb.getEscenarioSeleccionado().getEscid());
        esc.setEscvecesreservadia(this.nuevoNumeroReservasDiaEscenario);
        this.escenarioEJB.edit(esc);
        this.desabilitarEditarNumReservasDiaEscenario=true;            
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");       
    }
    
    public void editarTipoEscenario(MostrarEditarEscenarioController mgb)
    {
        this.desabilitarEditarTipoEscenario=false;
        this.nuevoTipoEscenario=mgb.getEscenarioSeleccionado().getEsctipoescid().getTipoescnombre();
        this.listaTiposEscenario=this.tipoEscenarioEJB.findAll();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void cancelarEditarTipoEscenario()
    {
        this.desabilitarEditarTipoEscenario=true;
        this.nuevoTipoEscenario="";
        this.listaTiposEscenario=null;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    
    public void aceptarEditarTipoEscenario(MostrarEditarEscenarioController mgb)
    {
        Tipoescenario tipoEscenario= this.tipoEscenarioEJB.buscarPorNombre(this.nuevoTipoEscenario).get(0);
        mgb.getEscenarioSeleccionado().setEsctipoescid(tipoEscenario);
        Escenario esc = this.escenarioEJB.find(mgb.getEscenarioSeleccionado().getEscid());
        esc.setEsctipoescid(tipoEscenario);
        this.escenarioEJB.edit(esc);
        this.desabilitarEditarTipoEscenario=true;
        this.nuevoTipoEscenario="";
        this.listaTiposEscenario=null;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");       
    }
    
//---------------------fin metodos propios del controlador--------------------   
  
}
