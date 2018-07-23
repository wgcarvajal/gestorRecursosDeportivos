
package com.unicauca.gestorrecursosdeportivos.validadores;


import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ValidarEdicionEscenario implements Serializable
{
    
        
    public  ValidarEdicionEscenario()
    {
        
    }
    
    
    public boolean validarNombreEscenario(String nombre)
    {
       if(nombre.isEmpty())
       {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo nombre obligatorio.", "Campo nombre obligatorio."));
           return false;
       }
       else 
       {
            Pattern patron = Pattern.compile("[^A-Za-z ñÑáéíóú0-9]");
            Matcher encaja = patron.matcher(nombre);
            if (encaja.find()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo nombre solo letras,numeros y espacios.", "Campo nombre solo letras,numeros y espacios."));
                return false;
            }
            return true;
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
    
    
    
}
