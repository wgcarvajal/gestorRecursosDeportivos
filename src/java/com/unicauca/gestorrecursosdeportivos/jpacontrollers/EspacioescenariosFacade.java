/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.jpacontrollers;

import com.unicauca.gestorrecursosdeportivos.entities.Espacioescenarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author geovanny
 */
@Stateless
public class EspacioescenariosFacade extends AbstractFacade<Espacioescenarios> {
    @PersistenceContext(unitName = "GestorRecursosDeportivosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EspacioescenariosFacade() {
        super(Espacioescenarios.class);
    }
    
    public boolean seEncuentraNombre(String espnombre)
    {
        Query query = getEntityManager().createNamedQuery("Espacioescenarios.findByEspnombre");
        query.setParameter("espnombre", espnombre);
        List<Espacioescenarios> esp=query.getResultList();
        return !esp.isEmpty();
    }
    
}
