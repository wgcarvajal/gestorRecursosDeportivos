/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.jpacontrollers;

import com.unicauca.gestorrecursosdeportivos.entities.Profesorparticular;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author geovanny
 */
@Stateless
public class ProfesorparticularFacade extends AbstractFacade<Profesorparticular> {
    @PersistenceContext(unitName = "GestorRecursosDeportivosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesorparticularFacade() {
        super(Profesorparticular.class);
    }
    
}
