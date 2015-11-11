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
import com.unicauca.gestorrecursosdeportivos.validadores.ValidarEdicionEscenario;
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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
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
@SessionScoped
public class gestionEspacioEscenariosController implements Serializable{

    @EJB
    private EspacioescenariosFacade espacioEscenariosEJB;
    @EJB
    private EscenarioFacade escenarioEJB;
    private List<Espacioescenarios> listaEspacioEscenario;
    private List<Escenario> listaEscenario;    
    private Espacioescenarios espacioEscenario;
    private Espacioescenarios espacioEscenarioSeleccionado;
    private Escenario nuevoEscenario;
    private Escenario escenarioSeleccionado;
    private ValidarEdicionEscenario validarEdicionEscenario;
    private UploadedFile imagenEscenario;
    private UploadedFile fotoEscenario;   
    private String rutaImagenEscenario;
    private String rutaFotosEscenarios;
    private String nombreEscenario;
    private String nombreEditarEscenario;
    private String medidaEscenarioAncho;    
    private String medidaEscenarioLargo;
    private ArrayList<Escenario>listaEscenariosPosicion;
    private boolean habilitarEditarConfiguraionInterfaz;
    private boolean habilitarEditarImagenEscenario;
    private boolean habilitarEditarFotoEscenario;
    private boolean habilitarEditarNombreEscenario;
    private boolean habilitarEditarMedidasEscenario;   
   
    
    public gestionEspacioEscenariosController() 
    {
        this.nombreEscenario="";
        this.espacioEscenario= new Espacioescenarios();        
        String OS = System.getProperty("os.name").toLowerCase();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("/"); 
        if(OS.contains("nux"))
        {
           this.rutaImagenEscenario=realPath.replace("build/", "")+"resources/img/escenarios/"; 
           this.rutaFotosEscenarios=realPath.replace("build/", "")+"resources/img/fotosEscenarios/"; 
        }
        else
        {
            this.rutaImagenEscenario=realPath.replace("build\\", "")+"resources\\img\\escenarios\\";
            this.rutaFotosEscenarios=realPath.replace("build/", "")+"resources/img/fotosEscenarios/";
        }        
    }
    @PostConstruct
    private void init()
    {
        this.listaEspacioEscenario= this.espacioEscenariosEJB.findAll();
    }
    public String getMedidaEscenarioAncho() 
    {
        return medidaEscenarioAncho;
    }

    public void setMedidaEscenarioAncho(String medidaEscenarioAncho) 
    {
        this.medidaEscenarioAncho = medidaEscenarioAncho;
    }

    public String getMedidaEscenarioLargo() 
    {
        return medidaEscenarioLargo;
    }

    public void setMedidaEscenarioLargo(String medidaEscenarioLargo) 
    {
        this.medidaEscenarioLargo = medidaEscenarioLargo;
    }
    public boolean isHabilitarEditarMedidasEscenario() 
    {
        return habilitarEditarMedidasEscenario;
    }

    public void setHabilitarEditarMedidasEscenario(boolean habilitarEditarMedidasEscenario) 
    {
        this.habilitarEditarMedidasEscenario = habilitarEditarMedidasEscenario;
    }
    public String getNombreEditarEscenario() 
    {
        return nombreEditarEscenario;
    }

    public void setNombreEditarEscenario(String nombreEditarEscenario) 
    {
        this.nombreEditarEscenario = nombreEditarEscenario;
    }
    public boolean isHabilitarEditarNombreEscenario() 
    {
        return habilitarEditarNombreEscenario;
    }

    public void setHabilitarEditarNombreEscenario(boolean habilitarEditarNombreEscenario) {
        this.habilitarEditarNombreEscenario = habilitarEditarNombreEscenario;
    }
    public UploadedFile getFotoEscenario() 
    {
        return fotoEscenario;
    }
    public void setFotoEscenario(UploadedFile fotoEscenario) 
    {
        this.fotoEscenario = fotoEscenario;
    }
    public boolean isHabilitarEditarFotoEscenario() 
    {
        return habilitarEditarFotoEscenario;
    }

    public void setHabilitarEditarFotoEscenario(boolean habilitarEditarFotoEscenario) 
    {
        this.habilitarEditarFotoEscenario = habilitarEditarFotoEscenario;
    }
     public boolean isHabilitarEditarImagenEscenario() 
    {
        return habilitarEditarImagenEscenario;
    }

    public void setHabilitarEditarImagenEscenario(boolean habilitarEditarImagenEscenario) 
    {
        this.habilitarEditarImagenEscenario = habilitarEditarImagenEscenario;
    }
    public boolean isHabilitarEditarConfiguraionInterfaz() 
    {
        return habilitarEditarConfiguraionInterfaz;
    }

    public void setHabilitarEditarConfiguraionInterfaz(boolean habilitarEditarConfiguraionInterfaz) 
    {
        this.habilitarEditarConfiguraionInterfaz = habilitarEditarConfiguraionInterfaz;
    }
    public String getNombreEscenario() 
    {
        return nombreEscenario;
    }

    public void setNombreEscenario(String nombreEscenario) 
    {
        this.nombreEscenario = nombreEscenario;
    }
    public Escenario getEscenarioSeleccionado() 
    {
        return escenarioSeleccionado;
    }
    public UploadedFile getImagenEscenario() 
    {
        return imagenEscenario;
    }

    public void setImagenEscenario(UploadedFile imagenEscenario) 
    {
        this.imagenEscenario = imagenEscenario;
    }
    public void setEscenarioSeleccionado(Escenario escenarioSeleccionado) 
    {
        this.escenarioSeleccionado = escenarioSeleccionado;
    }
    public Escenario getNuevoEscenario() 
    {
        return nuevoEscenario;
    }

    public void setNuevoEscenario(Escenario nuevoEscenario)
    {
        this.nuevoEscenario = nuevoEscenario;
    }
    public List<Escenario> getListaEscenario() 
    {
        return listaEscenario;
    }

    public void setListaEscenario(List<Escenario> listaEscenario) 
    {
        this.listaEscenario = listaEscenario;
    }
    public Espacioescenarios getEspacioEscenarioSeleccionado() 
    {
        return espacioEscenarioSeleccionado;
    }

    public void setEspacioEscenarioSeleccionado(Espacioescenarios espacioEscenarioSeleccionado)
    {
        this.espacioEscenarioSeleccionado = espacioEscenarioSeleccionado;
    }
    public Espacioescenarios getEspacioEscenario() 
    {
        return espacioEscenario;
    }

    public void setEspacioEscenario(Espacioescenarios espacioEscenario) 
    {
        this.espacioEscenario = espacioEscenario;
    }
    public List<Espacioescenarios> getListaEspacioEscenario() 
    {
        return listaEspacioEscenario;
    }

    public void setListaEspacioEscenario(List<Espacioescenarios> listaEspacioEscenario) 
    {
        this.listaEspacioEscenario = listaEspacioEscenario;
    }
    public void aumentarAncho()
    {
        this.espacioEscenario.setEspancho(this.espacioEscenario.getEspancho()+1);
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("espacioEscenario");
    }
    public void disminuirAncho()
    {
        if(this.espacioEscenario.getEspancho()>0)
        {
            this.espacioEscenario.setEspancho(this.espacioEscenario.getEspancho()-1);
            RequestContext requestContext = RequestContext.getCurrentInstance();        
            requestContext.update("espacioEscenario");
        }
        
    }
    public void crearEspacioEscenarios() throws InterruptedException
    {
        this.espacioEscenario.setEspancho(500);
        this.espacioEscenariosEJB.create(this.espacioEscenario);
        this.listaEspacioEscenario=this.espacioEscenariosEJB.findAll();        
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formularioEspaciosEscenarios");
    }    
    public String verEscenario(Espacioescenarios espacioEscenarioSeleccionado)
    {
        this.espacioEscenarioSeleccionado=espacioEscenarioSeleccionado;        
        this.listaEscenario=this.escenarioEJB.buscarPorEspID(this.espacioEscenarioSeleccionado.getEspid());
        return "gestionCentroDeportivo?faces-redirect=true";
    }
    public void abriVentanaNuevoEscenario()
    {
        this.nuevoEscenario= new Escenario();
        this.nombreEscenario="";
        this.imagenEscenario=null;
        Espacioescenarios espaEsc= new Espacioescenarios();
        espaEsc.setEspid(this.espacioEscenarioSeleccionado.getEspid());
        this.nuevoEscenario.setEspid(espaEsc);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);       
        context.renderResponse(); 
        requestContext.update("formularioCrearNuevoEscenario");
        requestContext.update("formularioImagenEscenario");
        requestContext.execute("PF('crearEscenario').show()");
        
    }
    public void crearEscenario() throws InterruptedException, IOException
    {      
        if(this.imagenEscenario!=null)
        {
            int contador= this.escenarioEJB.contarEscenariosPorEspID(this.espacioEscenarioSeleccionado.getEspid());
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
            this.escenarioEJB.create(this.nuevoEscenario);
            this.listaEscenario=this.escenarioEJB.buscarPorEspID(this.espacioEscenarioSeleccionado.getEspid());
            for(Escenario escen:this.listaEscenario)
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
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionCentroDeportivoCSS");
            requestContext.update("formGestionCentroDeportivo");            
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cargue una imagen", "Cargue una imagen"));
            RequestContext requestContext = RequestContext.getCurrentInstance();        
            requestContext.update("formularioImagenEscenario");
        }
        
    }    
    public String gestionEscenario(Escenario escenarioSeleccionado)
    {
        this.listaEscenario=this.escenarioEJB.buscarPorEspID(this.espacioEscenarioSeleccionado.getEspid());
        for(Escenario esc:this.listaEscenario)
        {
            if(Objects.equals(esc.getEscid(), escenarioSeleccionado.getEscid()))
            {
                this.escenarioSeleccionado=esc; 
            }
        }       
        this.escenarioSeleccionado.setBorde(1);
        this.habilitarEditarConfiguraionInterfaz=true;
        this.habilitarEditarImagenEscenario=true;
        this.habilitarEditarFotoEscenario=true;
        this.habilitarEditarNombreEscenario=true;
        this.habilitarEditarMedidasEscenario=true;
        this.imagenEscenario=null;
        this.fotoEscenario=null;
        this.listaEscenariosPosicion= new ArrayList();
        return "gestionEscenario?faces-redirect=true";
    }
    public void aumentarAnchoImagen()
    {
        this.escenarioSeleccionado.setEscanchoimagen(this.escenarioSeleccionado.getEscanchoimagen()+1);
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenario");
        requestContext.update("formGestionEscenarioCSS");
    }
    public void disminuirAnchoImagen()
    {
        if(this.escenarioSeleccionado.getEscanchoimagen()>0)
        {
            this.escenarioSeleccionado.setEscanchoimagen(this.escenarioSeleccionado.getEscanchoimagen()-1);
            RequestContext requestContext = RequestContext.getCurrentInstance();        
            requestContext.update("formGestionEscenario");
            requestContext.update("formGestionEscenarioCSS");
        }
        
    }
    public void aumentarLargoImagen()
    {
        this.escenarioSeleccionado.setEsclargoimagen(this.escenarioSeleccionado.getEsclargoimagen()+1);
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenario");
        requestContext.update("formGestionEscenarioCSS");
    }
    public void disminuirLargoImagen()
    {
        if(this.escenarioSeleccionado.getEsclargoimagen()>0)
        {
            this.escenarioSeleccionado.setEsclargoimagen(this.escenarioSeleccionado.getEsclargoimagen()-1);
            RequestContext requestContext = RequestContext.getCurrentInstance();        
            requestContext.update("formGestionEscenario");
            requestContext.update("formGestionEscenarioCSS");
        }
        
    }
    public void aumentarMargenSuperiorImagen()
    {
        this.escenarioSeleccionado.setEscmargensuperior(this.escenarioSeleccionado.getEscmargensuperior()+1);
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenario");
        requestContext.update("formGestionEscenarioCSS");
    }
    public void disminuirMargenSuperiorImagen()
    {
        if(this.escenarioSeleccionado.getEscmargensuperior()>0)
        {
            this.escenarioSeleccionado.setEscmargensuperior(this.escenarioSeleccionado.getEscmargensuperior()-1);
            RequestContext requestContext = RequestContext.getCurrentInstance();        
            requestContext.update("formGestionEscenario");
            requestContext.update("formGestionEscenarioCSS");
        }
        
    }
    public void margenSuperiorMitad()
    {
        int tamano=this.escenarioSeleccionado.getEsclargoimagen();
        int tamanoEspacio=this.espacioEscenarioSeleccionado.getEspancho();
        int tamanoReal=tamanoEspacio- tamano;
        tamanoReal=tamanoReal/2;
        this.escenarioSeleccionado.setEscmargensuperior(tamanoReal);
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenario");
        requestContext.update("formGestionEscenarioCSS");
        
    }
    public void margenSuperiorArriba()
    {
        this.escenarioSeleccionado.setEscmargensuperior(0);
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenario");
        requestContext.update("formGestionEscenarioCSS");
        
    }
    public void margenSuperiorAbajo()
    {
        int tamano=this.escenarioSeleccionado.getEsclargoimagen();
        int tamanoEspacio=this.espacioEscenarioSeleccionado.getEspancho();
        int tamanoReal=tamanoEspacio- tamano;
        this.escenarioSeleccionado.setEscmargensuperior(tamanoReal-1);
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenario");
        requestContext.update("formGestionEscenarioCSS");
        
    }
    public void aumentarMargenIzquierdaImagen()
    {   
        
        this.escenarioSeleccionado.setEscmargenizquierda(this.escenarioSeleccionado.getEscmargenizquierda()+1);
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenario");
        requestContext.update("formGestionEscenarioCSS");
    }
    public void disminuirMargenIzquierdaImagen()
    {
        if(this.escenarioSeleccionado.getEscmargenizquierda()>0)
        {
            this.escenarioSeleccionado.setEscmargenizquierda(this.escenarioSeleccionado.getEscmargenizquierda()-1);
            RequestContext requestContext = RequestContext.getCurrentInstance();        
            requestContext.update("formGestionEscenario");
            requestContext.update("formGestionEscenarioCSS");
        }
        
    }
    public void margenIzquierdaMitad()
    {
        int tamano=this.escenarioSeleccionado.getEscanchoimagen();
        int tamanoEspacio=932;
        int tamanoReal=tamanoEspacio- tamano;
        tamanoReal=tamanoReal/2;
        this.escenarioSeleccionado.setEscmargenizquierda(tamanoReal);
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenario");
        requestContext.update("formGestionEscenarioCSS");
    }
    public void margenIzquierdaIzquierda()
    {
        this.escenarioSeleccionado.setEscmargenizquierda(0);
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenario");
        requestContext.update("formGestionEscenarioCSS");
    }
    public void margenIzquierdaDerecha()
    {
        int tamano=this.escenarioSeleccionado.getEscanchoimagen();
        int tamanoEspacio=932;
        int tamanoReal=tamanoEspacio- tamano;
        this.escenarioSeleccionado.setEscmargenizquierda(tamanoReal-1);
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenario");
        requestContext.update("formGestionEscenarioCSS");
    }
    public void rotarDerecha()
    {
        if(this.escenarioSeleccionado.getEscrotarimagen()==359)
        {
            this.escenarioSeleccionado.setEscrotarimagen(0);

        }
        else
        {
            this.escenarioSeleccionado.setEscrotarimagen(this.escenarioSeleccionado.getEscrotarimagen()+1);
  
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenario");
        requestContext.update("formGestionEscenarioCSS");
    }
    public void rotarIzquierda()
    {
        if(this.escenarioSeleccionado.getEscrotarimagen()==-359)
        {
            this.escenarioSeleccionado.setEscrotarimagen(0);

        }
        else
        {
            this.escenarioSeleccionado.setEscrotarimagen(this.escenarioSeleccionado.getEscrotarimagen()-1);
  
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenario");
        requestContext.update("formGestionEscenarioCSS");   
    }
    public void AceptarEditarConfiguracionInterfazEscenario()
    {
        this.escenarioSeleccionado.setBorde(0);
        for(Escenario es:this.listaEscenariosPosicion)
        {
            this.escenarioEJB.edit(es);
        }
        this.escenarioEJB.edit(this.escenarioSeleccionado);
        this.escenarioSeleccionado.setBorde(1);        
        this.listaEscenariosPosicion=new ArrayList();
        this.habilitarEditarConfiguraionInterfaz=true;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");        
    }
    public void cargarImagenEscenario(FileUploadEvent event)
    {
        this.imagenEscenario=event.getFile();
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formularioImagenEscenario");
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
    private void GuardarFotoEscenario(String filename, InputStream in)
    {
       try 
       { 
            OutputStream out = new FileOutputStream(new File(this.rutaFotosEscenarios + filename));              
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
    public void abrirVentanaPosicionEscenario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formularioPosicionEscenario");
        requestContext.execute("PF('PosicionEscenario').show()");
    }
    public void subirEscenario()
    {
        if(this.escenarioSeleccionado.getEscposicion()>1)
        {
            this.listaEscenario.get(this.escenarioSeleccionado.getEscposicion()-2).setEscposicion(this.escenarioSeleccionado.getEscposicion());
            if(!listaEscenariosPosicion.contains(this.listaEscenario.get(this.escenarioSeleccionado.getEscposicion()-2)))
            {
                this.listaEscenariosPosicion.add(this.listaEscenario.get(this.escenarioSeleccionado.getEscposicion()-2));
            }
            this.escenarioSeleccionado.setEscposicion(this.escenarioSeleccionado.getEscposicion()-1);
            Collections.sort(this.listaEscenario, new Comparator(){            

                @Override
                public int compare(Object e1, Object e2) {
                     return new Integer(((Escenario)e1).getEscposicion()).compareTo(((Escenario)e2).getEscposicion());
                }
            });
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formularioPosicionEscenario");
            requestContext.update("formGestionEscenario");
            requestContext.update("formGestionEscenarioCSS");
            requestContext.update("formEspacioTrabajoEscenarios");
        }
    }
    public void bajarEscenario()
    {
        if(this.escenarioSeleccionado.getEscposicion()< this.listaEscenario.size())
        {
            this.listaEscenario.get(this.escenarioSeleccionado.getEscposicion()).setEscposicion(this.escenarioSeleccionado.getEscposicion());
            if(!listaEscenariosPosicion.contains(this.listaEscenario.get(this.escenarioSeleccionado.getEscposicion())))
            {
                this.listaEscenariosPosicion.add(this.listaEscenario.get(this.escenarioSeleccionado.getEscposicion()));
            }
            
            this.escenarioSeleccionado.setEscposicion(this.escenarioSeleccionado.getEscposicion()+1);
            Collections.sort(this.listaEscenario, new Comparator(){            

                @Override
                public int compare(Object e1, Object e2) {
                     return new Integer(((Escenario)e1).getEscposicion()).compareTo(((Escenario)e2).getEscposicion());
                }
            });
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formularioPosicionEscenario");
            requestContext.update("formGestionEscenario");
            requestContext.update("formGestionEscenarioCSS");
            requestContext.update("formEspacioTrabajoEscenarios");
        }
    }
    public void editarConfiguracionInterfaz()
    {
        this.habilitarEditarConfiguraionInterfaz=false;        
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
    }
    public void cancelarEditarConfiguracionInterfaz()
    {
        this.habilitarEditarConfiguraionInterfaz=true;
        this.listaEscenario=this.escenarioEJB.buscarPorEspID(this.espacioEscenarioSeleccionado.getEspid());
        for(Escenario esc: this.listaEscenario)
        {
            if(Objects.equals(esc.getEscid(), this.escenarioSeleccionado.getEscid()))
            {
                this.escenarioSeleccionado=esc;
                this.escenarioSeleccionado.setBorde(1);
                break;
            }
        }        
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenario");
        requestContext.update("formGestionEscenarioCSS");
        requestContext.update("formEspacioTrabajoEscenarios");
    }
    public void cargarEditarImagenEscenario(FileUploadEvent event)
    {
        this.imagenEscenario=event.getFile();
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenarioImagenAnimada");
    }
    public void cambiarImagenAnimada()
    {
        this.habilitarEditarImagenEscenario=false; 
        this.imagenEscenario=null;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioImagenAnimada");
        requestContext.update("formOpcionesImagenAnimada");
    }
    public void cancelarCambiarImagenAnimada()
    {
        this.imagenEscenario=null;
        this.habilitarEditarImagenEscenario=true;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioImagenAnimada");
        requestContext.update("formOpcionesImagenAnimada");
    }
    public void aceptarCambiarImagenAnimada() throws IOException, InterruptedException
    {
        if(this.imagenEscenario!=null)
        {
          String nombreImagenAnterior=this.escenarioSeleccionado.getEscnombreimagenanimada();
          int i = this.imagenEscenario.getFileName().lastIndexOf('.');            
          String extension = this.imagenEscenario.getFileName().substring(i+1);
          
          int indiceImagenAnterior=nombreImagenAnterior.lastIndexOf(".");
          String imagenAnterior=nombreImagenAnterior.substring(0, indiceImagenAnterior);
          int indiceImagenFiltrada=imagenAnterior.lastIndexOf("_");
          String numerostring=imagenAnterior.substring(indiceImagenFiltrada+1);
          int numero=Integer.parseInt(numerostring);
          numero=numero+1;
          String nombreImagenNueva="esc"+this.escenarioSeleccionado.getEscid()+"_"+numero+"."+extension;
          this.GuardarImagenEscenario(nombreImagenNueva, this.imagenEscenario.getInputstream());
          Thread.sleep(5000);
          this.escenarioSeleccionado.setEscnombreimagenanimada(nombreImagenNueva);
          Escenario escen=this.escenarioEJB.find(this.escenarioSeleccionado.getEscid());
          escen.setEscnombreimagenanimada(nombreImagenNueva);
          this.escenarioEJB.edit(escen);
          File fichero = new File(this.rutaImagenEscenario+nombreImagenAnterior);
          fichero.delete();
          this.habilitarEditarImagenEscenario=true;        
          RequestContext requestContext = RequestContext.getCurrentInstance();
          requestContext.update("formGestionEscenarioImagenAnimada");
          requestContext.update("formOpcionesImagenAnimada");
          requestContext.update("formGestionEscenarioCSS");
          requestContext.update("formEspacioTrabajoEscenarios");
        }
    }
    public void cargarFotoEscenario(FileUploadEvent event)
    {
        this.fotoEscenario=event.getFile();
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formGestionEscenarioFotoEscenario");
    }
    public void cambiarFotoEscenario()
    {
        this.habilitarEditarFotoEscenario=false;
        this.fotoEscenario=null;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioFotoEscenario");
        requestContext.update("opcionesCambiarFotoEscenario");        
    }
    public void cancelarCambiarFotoEscenario()
    {
        this.fotoEscenario=null;
        this.habilitarEditarFotoEscenario=true;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioFotoEscenario");
        requestContext.update("opcionesCambiarFotoEscenario"); 
    }
    public void aceptarCambiarFotoEscenario() throws IOException, InterruptedException
    {
        if(this.fotoEscenario!=null)
        {            
          String nombreFotoAnterior=this.escenarioSeleccionado.getEscnombreimagenreal();
          int i = this.fotoEscenario.getFileName().lastIndexOf('.');            
          String extension = this.fotoEscenario.getFileName().substring(i+1);
          if(nombreFotoAnterior.equals("vacio"))
          {
              String nombreFotoNueva="esc"+this.escenarioSeleccionado.getEscid()+"_1"+"."+extension;
              this.GuardarFotoEscenario(nombreFotoNueva, this.fotoEscenario.getInputstream());
              Thread.sleep(5000);
              this.escenarioSeleccionado.setEscnombreimagenreal(nombreFotoNueva);
              Escenario escen=this.escenarioEJB.find(this.escenarioSeleccionado.getEscid());
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
            String nombreFotoNueva="esc"+this.escenarioSeleccionado.getEscid()+"_"+numero+"."+extension;
            this.GuardarFotoEscenario(nombreFotoNueva, this.fotoEscenario.getInputstream());
            Thread.sleep(5000);
            this.escenarioSeleccionado.setEscnombreimagenreal(nombreFotoNueva);
            Escenario escen=this.escenarioEJB.find(this.escenarioSeleccionado.getEscid());
            escen.setEscnombreimagenreal(nombreFotoNueva);
            this.escenarioEJB.edit(escen);
            File fichero = new File(this.rutaFotosEscenarios+nombreFotoAnterior);
            fichero.delete(); 
          }
          this.habilitarEditarFotoEscenario=true;   
          RequestContext requestContext = RequestContext.getCurrentInstance();
          requestContext.update("formGestionEscenarioFotoEscenario");
          requestContext.update("opcionesCambiarFotoEscenario");           
        }
    }
    public void editarNombreEscenario()
    {
        this.habilitarEditarNombreEscenario=false;
        this.nombreEditarEscenario=this.escenarioSeleccionado.getEscnombre();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    public void cancelarEditarNombreEscenario()
    {
        this.habilitarEditarNombreEscenario=true;
        this.nombreEditarEscenario="";
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    public void aceptarEditarNombreEscenario()
    {
        this.validarEdicionEscenario= new ValidarEdicionEscenario();        
        if(this.validarEdicionEscenario.validarNombreEscenario(this.nombreEditarEscenario))
        {
            this.escenarioSeleccionado.setEscnombre(this.nombreEditarEscenario);
            Escenario esc = this.escenarioEJB.find(this.escenarioSeleccionado.getEscid());
            esc.setEscnombre(this.nombreEditarEscenario);
            this.escenarioEJB.edit(esc);
            this.habilitarEditarNombreEscenario=true;
            this.nombreEditarEscenario="";
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionEscenarioInformacionGeneral");
        }
    }
    public void editarMedidasEscenario()
    {
        this.habilitarEditarMedidasEscenario=false;
        if(this.escenarioSeleccionado.getEscmedidas()==null)
        {
            this.medidaEscenarioAncho="0";
            this.medidaEscenarioLargo="0";
        }
        else
        {
          int i = this.escenarioSeleccionado.getEscmedidas().indexOf(':');            
          String subcadena = this.escenarioSeleccionado.getEscmedidas().substring(i+1);
          i=subcadena.indexOf('m');
          this.medidaEscenarioAncho=subcadena.substring(0,i);
          System.out.println(this.medidaEscenarioAncho);
          subcadena=subcadena.substring(i+1);
          i=subcadena.indexOf(' ');
          subcadena=subcadena.substring(i+1);          
          i=subcadena.indexOf(':');
          subcadena=subcadena.substring(i+1);
          i=subcadena.indexOf('m');
          this.medidaEscenarioLargo=subcadena.substring(0,i);
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    public void cancelarEditarMedidasEscenario()
    {
        this.habilitarEditarMedidasEscenario=true;
        this.medidaEscenarioAncho="";
        this.medidaEscenarioLargo="";
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formGestionEscenarioInformacionGeneral");
    }
    public void aceptarEditarMedidasEscenario()
    {
        this.validarEdicionEscenario= new ValidarEdicionEscenario();        
        if(this.validarEdicionEscenario.validarMedidaEscenarioAncho(this.medidaEscenarioAncho) && this.validarEdicionEscenario.validarMedidaEscenarioLargo(this.medidaEscenarioLargo))
        {
            this.escenarioSeleccionado.setEscmedidas("Ancho:"+this.medidaEscenarioAncho+"mts Largo:"+this.medidaEscenarioLargo+"mts");
            Escenario esc = this.escenarioEJB.find(this.escenarioSeleccionado.getEscid());
            esc.setEscmedidas("Ancho:"+this.medidaEscenarioAncho+"mts Largo:"+this.medidaEscenarioLargo+"mts");
            this.escenarioEJB.edit(esc);
            this.habilitarEditarMedidasEscenario=true;
            this.medidaEscenarioAncho="";
            this.medidaEscenarioLargo="";
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formGestionEscenarioInformacionGeneral");
        }
    }
}
