/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.validadores;

import com.unicauca.gestorrecursosdeportivos.jpacontrollers.EspacioescenariosFacade;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author geovanny
 */
public class ValidarAtributosEspacioEscenarios implements Serializable
{
    
    
    public ValidarAtributosEspacioEscenarios()
    {
        
    }
    
    public boolean validarNombreEspacioEscenarios(String nombre,EspacioescenariosFacade espacioEscenariosEJB)
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
               if (espacioEscenariosEJB.seEncuentraNombre(nombre)) 
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
    public boolean validarDireccionEspacioEscenarios(String direccion)
    {
        if (direccion.length()>50) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo dirección maximo 50 caracteres.", "Campo dirección maximo 50 caracteres."));
            return false;
        } 
        else 
        {
            Pattern patron = Pattern.compile("[^A-Za-z ñÑáéíóú0-9.#º-]");
            Matcher encaja = patron.matcher(direccion);
            if (encaja.find())
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo dirección caracteres no permitidos.", "Campo dirección caracteres no permitidos."));
                return false;
            }
            return true;

        } 
    }
    
    public boolean validarDescripcionEspacioEscenarios(String descripcion)
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
    
    public boolean validarHorario(int horainicio,int horafin)
    {
        if(horainicio>=horafin)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hora inicial debe ser menor a la hora final.", "Hora inicial debe ser menor a la hora final."));
            return false;
        }
        return true;
    }
    
    public boolean validarReservasNumeroVeces(int numeroVecesSemana,int numeroVecesDia)
    {
        if(numeroVecesDia>numeroVecesSemana)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El número de reservas por semana no puede ser menor al número por día.", "El número de reservas por semana no puede ser menor al número por día."));
            return false;
        }
        return true;
    }
    
}
