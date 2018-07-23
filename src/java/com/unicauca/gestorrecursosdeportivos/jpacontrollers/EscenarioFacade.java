/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.jpacontrollers;

import com.unicauca.gestorrecursosdeportivos.entities.Escenario;
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
public class EscenarioFacade extends AbstractFacade<Escenario> {
    @PersistenceContext(unitName = "GestorRecursosDeportivosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EscenarioFacade() {
        super(Escenario.class);
    }
    
    public List<Escenario> buscarPorEspID(int espid)
    {
        Query query = getEntityManager().createNamedQuery("Escenario.findByEspid");
        query.setParameter("espid", espid);
        List<Escenario> resultList = query.getResultList();
        return resultList;
    }
    public int contarEscenariosPorEspID(int espid)
    {
        Query query = getEntityManager().createNamedQuery("Escenario.contarEscenariosPorEspid");
        query.setParameter("espid", espid);
        String stringContador =""+query.getResultList().get(0);
        int contador=Integer.parseInt(stringContador);
        return contador;
    }
    
    public boolean seEncuentraNombre(String escnombre)
    {
        Query query = getEntityManager().createNamedQuery("Escenario.findByEscnombre");
        query.setParameter("escnombre", escnombre);
        List<Escenario> esc=query.getResultList();
        return !esc.isEmpty();
    }
    
}
