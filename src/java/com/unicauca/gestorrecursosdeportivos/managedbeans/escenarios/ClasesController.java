/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.managedbeans.escenarios;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class ClasesController implements Serializable
{

    
    public ClasesController() 
    {
    }
    
    public void agregarClases()
    {
        this.refrescarVentanaAgregarClases();
        this.abrirVentanaAgregarClases();
        
    }
    
    public void abrirVentanaAgregarClases()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.execute("PF('ventanaAgregarClases').show()");
    }
    
    public void refrescarVentanaAgregarClases()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.update("formAgregarClases");  
    }
    
}
