package com.unicauca.gestorrecursosdeportivos.validadores;

import com.unicauca.gestorrecursosdeportivos.jpacontrollers.EscenarioFacade;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author geovanny
 */
public class ValidarAtributosEscenario implements Serializable
{
    public ValidarAtributosEscenario()
    {
        
    }
    
    public boolean validarNombreEscenario(String nombre,EscenarioFacade escenariosEJB)
    {
       if(nombre.isEmpty())
       {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo nombre obligatorio.", "Campo nombre obligatorio."));
           return false;
       }
       else 
       {
           if (nombre.length()>80) 
           {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo nombre maximo 80 caracteres.", "Campo nombre maximo 80 caracteres."));
               return false;
           } 
           else 
           {
               if (escenariosEJB.seEncuentraNombre(nombre)) 
               {
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre ya se encuentra registrado.", "El nombre ya se encuentra registrado."));
                   return false;
               } else 
               {
                   Pattern patron = Pattern.compile("[^A-Za-z ñÑáéíóú0-9()]");
                   Matcher encaja = patron.matcher(nombre);
                   if (encaja.find()) 
                   {
                       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo nombre solo letras,numeros,espacios y parentesis.", "Campo nombre solo letras,numeros,espacios y parentesis."));
                       return false;
                   }
                   return true;
               }
           }        
       }
        
    }
    
    public boolean validarMedidaEscenarioAncho(String ancho)
    {
        ancho=ancho.replace(',', '.');
        try
        {
            double med=Double.parseDouble(ancho);
            
            if(med<0.0 || med>1000.0)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor ancho (0-1000).", "Valor ancho (0-1000)."));
                return false;
            }
            else
            {
                return true;
            }
            
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor ancho de el escenario incorrecto.", "Valor ancho de el escenario incorrecto."));
            return false;
        }
        
    }
    public boolean validarMedidaEscenarioLargo(String largo)
    {
        largo=largo.replace(',', '.');
        try
        {
            double med=Double.parseDouble(largo);
            if(med<0.0 || med>1000.0)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor largo (0-1000).", "Valor largo (0-1000)."));
                return false;
            }
            else
            {
                return true;
            }
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor largo de el escenario incorrecto.", "Valor largo de el escenario incorrecto."));
            return false;
        }
        
    }
    
    public boolean validarDescripcionEscenarios(String descripcion)
    {        
            Pattern patron = Pattern.compile("[^A-Za-z ñÑáéíóú0-9.:,;()\"]");
            Matcher encaja = patron.matcher(descripcion);
            if (encaja.find())
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo descripción caracteres no permitidos.", "Campo descripción caracteres no permitidos."));
                return false;
            }
            return true;       
    }
    
    public boolean validarRecomendacionesEscenarios(String recomendaciones)
    {        
            Pattern patron = Pattern.compile("[^A-Za-z ñÑáéíóú0-9.:,;()\"]");
            Matcher encaja = patron.matcher(recomendaciones);
            if (encaja.find())
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo Recomendaciones caracteres no permitidos.", "Campo Recomendaciones caracteres no permitidos."));
                return false;
            }
            return true;       
    }
}
