/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.managedbeans.escenarios;

import com.unicauca.gestorrecursosdeportivos.entities.Escenario;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.EscenarioFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class gestionEscenario implements Serializable
{
    @EJB
    private EscenarioFacade escenarioEJB;
    
    private List<Escenario> listaEscenario;   

    
    public gestionEscenario() 
    {
    }
    
    public List<Escenario> getListaEscenario()
    {
        return listaEscenario;
    }

    public void setListaEscenario(List<Escenario> listaEscenario) 
    {
        this.listaEscenario = listaEscenario;
    }
    
    public List<Escenario> crearlistaEscenarios(Escenario escenarioSeleccionado)
    {
        this.listaEscenario=this.escenarioEJB.buscarPorEspID(escenarioSeleccionado.getEspid().getEspid());
        for(Escenario esc:this.listaEscenario)
        {
            if(Objects.equals(escenarioSeleccionado.getEscid(), esc.getEscid()))
            {
                esc.setBorde(1);
            }
        }
        return listaEscenario;
    }
    
    
}
