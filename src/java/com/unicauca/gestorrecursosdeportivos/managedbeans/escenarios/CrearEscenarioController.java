package com.unicauca.gestorrecursosdeportivos.managedbeans.escenarios;

import com.unicauca.gestorrecursosdeportivos.entities.Escenario;
import com.unicauca.gestorrecursosdeportivos.entities.Espacioescenarios;
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
public class CrearEscenarioController implements Serializable
{
    @EJB
    private EscenarioFacade escenarioEJB;
    @EJB
    private TipoescenarioFacade tipoEscenarioEJB;
    @EJB
    private EstadoescenarioFacade estadoEscenarioEJB;
    private Escenario nuevoEscenario;
    private UploadedFile imagenEscenario;
    private String rutaImagenEscenario;
    private String nombreEscenario;
    
    public CrearEscenarioController() 
    {
        String OS = System.getProperty("os.name").toLowerCase();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("/"); 
        if(OS.contains("nux") || OS.contains("mac"))
        {
           this.rutaImagenEscenario=realPath.replace("build/", "")+"resources/img/escenarios/";           
        }
        else
        {
            this.rutaImagenEscenario=realPath.replace("build\\", "")+"resources\\img\\escenarios\\";            
        } 
    }
    
//---------------------setters and getter-------------------------------------
    
    public Escenario getNuevoEscenario() 
    {
        return nuevoEscenario;
    }

    public void setNuevoEscenario(Escenario nuevoEscenario) 
    {
        this.nuevoEscenario = nuevoEscenario;
    }
    
    public UploadedFile getImagenEscenario() 
    {
        return imagenEscenario;
    }

    public void setImagenEscenario(UploadedFile imagenEscenario) 
    {
        this.imagenEscenario = imagenEscenario;
    }

    public String getNombreEscenario() 
    {
        return nombreEscenario;
    }

    public void setNombreEscenario(String nombreEscenario) 
    {
        this.nombreEscenario = nombreEscenario;
    }
    
    
//---------------------fin setters and getters--------------------------------

//---------------------Metodos propios del controlador------------------------
    
    public void cargarImagenEscenario(FileUploadEvent event)
    {
        this.imagenEscenario=event.getFile();
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formularioImagenEscenario");
    }
    
    public void crearEscenario(Integer idEspacioEscenariosSeleccionado,MostrarEscenariosController mng) throws InterruptedException, IOException
    {        
        if(this.imagenEscenario!=null)
        {
            ValidarAtributosEscenario validador= new ValidarAtributosEscenario();
            if(validador.validarNombreEscenario(this.nombreEscenario, this.escenarioEJB))
            {
                int contador= this.escenarioEJB.contarEscenariosPorEspID(idEspacioEscenariosSeleccionado);
                contador=contador+1;
                this.nuevoEscenario.setEscposicion(contador);
                this.nuevoEscenario.setEscnombre(this.nombreEscenario);
                this.nuevoEscenario.setEscanchoimagen(100);
                this.nuevoEscenario.setEsclargoimagen(50);
                this.nuevoEscenario.setEscnombreimagenanimada("vacio.png");
                this.nuevoEscenario.setEscnombreimagenreal("vacio");
                this.nuevoEscenario.setEscmargensuperior(0);
                this.nuevoEscenario.setEscmargenizquierda(0);
                this.nuevoEscenario.setEscrotarimagen(0);
                this.nuevoEscenario.setBorde(0);                
                Tipoescenario tipoEscenario= this.tipoEscenarioEJB.buscarPorNombre("RESERVA").get(0);
                Estadoescenario estadoEscenario=this.estadoEscenarioEJB.buscarPorNombre("Disponible").get(0);
                this.nuevoEscenario.setEsctipoescid(tipoEscenario);
                this.nuevoEscenario.setEscestescid(estadoEscenario);
                this.nuevoEscenario.setEscvecesreservadia(1);
                this.nuevoEscenario.setEscduracionreserva(60);
                this.escenarioEJB.create(this.nuevoEscenario);
                mng.volveraGenerarListaEscenarios(idEspacioEscenariosSeleccionado);
                for(Escenario escen:mng.getListaEscenarios())
                {
                    if(Objects.equals(escen.getEspid().getEspid(), this.nuevoEscenario.getEspid().getEspid()) && escen.getEscnombre().equals(this.nuevoEscenario.getEscnombre()))
                    {
                        int i = this.imagenEscenario.getFileName().lastIndexOf('.');            
                        String extension = this.imagenEscenario.getFileName().substring(i+1);
                        escen.setEscnombreimagenanimada("esc"+escen.getEscid()+"_1."+extension);
                        this.escenarioEJB.edit(escen);
                        this.GuardarImagenEscenario(escen.getEscnombreimagenanimada(), this.imagenEscenario.getInputstream());
                        Thread.sleep(4000);
                        break;
                    }
                }
                mng.refrescarVista();
                this.imagenEscenario=null;
                this.nombreEscenario="";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Escenario creado satisfactoriamente.", "Escenario creado satisfactoriamente."));
                RequestContext requestContext = RequestContext.getCurrentInstance();        
                requestContext.update("formularioImagenEscenario");
                requestContext.update("formularioCrearNuevoEscenario");
            }            
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cargue una imagen", "Cargue una imagen"));            
        }
        
    }
    
    private void GuardarImagenEscenario(String filename, InputStream in)
    {
       try 
       { 
            OutputStream out = new FileOutputStream(new File(this.rutaImagenEscenario + filename));              
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
    
    public void abriVentanaNuevoEscenario(Integer idEspacioEscenariosSeleccionado)
    {
        if (idEspacioEscenariosSeleccionado != null) {
            this.nuevoEscenario = new Escenario();
            this.nombreEscenario = "";
            this.imagenEscenario = null;
            Espacioescenarios espaEsc = new Espacioescenarios();
            espaEsc.setEspid(idEspacioEscenariosSeleccionado);
            this.nuevoEscenario.setEspid(espaEsc);
            RequestContext requestContext = RequestContext.getCurrentInstance();            
            requestContext.update("formularioCrearNuevoEscenario");
            requestContext.update("formularioImagenEscenario");
            requestContext.execute("PF('crearEscenario').show()");
        }       
        
    }
    
//---------------------fin metodos propios del controlador--------------------    

    
}
