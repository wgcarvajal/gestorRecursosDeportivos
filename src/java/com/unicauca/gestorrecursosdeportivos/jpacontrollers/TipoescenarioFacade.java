/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.jpacontrollers;



import com.unicauca.gestorrecursosdeportivos.entities.Tipoescenario;
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
public class TipoescenarioFacade extends AbstractFacade<Tipoescenario> {
    @PersistenceContext(unitName = "GestorRecursosDeportivosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoescenarioFacade() {
        super(Tipoescenario.class);
    }
    
    public List<Tipoescenario> buscarPorNombre(String tipoescnombre)
    {
        Query query = getEntityManager().createNamedQuery("Tipoescenario.findByTipoescnombre");
        query.setParameter("tipoescnombre", tipoescnombre);
        List<Tipoescenario> resultList = query.getResultList();
        return resultList;
    }
    
    
}