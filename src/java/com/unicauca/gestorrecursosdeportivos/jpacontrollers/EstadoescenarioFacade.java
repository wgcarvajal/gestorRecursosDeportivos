/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.jpacontrollers;

import com.unicauca.gestorrecursosdeportivos.entities.Estadoescenario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author seven
 */
@Stateless
public class EstadoescenarioFacade extends AbstractFacade<Estadoescenario> {
    @PersistenceContext(unitName = "GestorRecursosDeportivosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoescenarioFacade() {
        super(Estadoescenario.class);
    }
    
    public List<Estadoescenario>buscarPorNombre(String estescnombre)
    {        
        Query query = getEntityManager().createNamedQuery("Estadoescenario.findByEstescnombre");
        query.setParameter("estescnombre", estescnombre);
        List<Estadoescenario> resultList = query.getResultList();
        return resultList;
    }
    
    
}
