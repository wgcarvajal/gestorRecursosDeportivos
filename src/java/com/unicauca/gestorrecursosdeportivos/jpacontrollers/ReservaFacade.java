/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.jpacontrollers;



import com.unicauca.gestorrecursosdeportivos.entities.Reserva;
import java.util.Date;
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
public class ReservaFacade extends AbstractFacade<Reserva> {
    @PersistenceContext(unitName = "GestorRecursosDeportivosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservaFacade() {
        super(Reserva.class);
    }
    
    public List<Reserva> buscarPorEscid(int resescid,Date fechainicio,Date fechafin)
    {
       Query query = getEntityManager().createNamedQuery("Reserva.findByResescidRangoFecha");
       query.setParameter("resescid", resescid);
       query.setParameter("fechainicio", fechainicio);
       query.setParameter("fechafin", fechafin);
       List<Reserva> resultList = query.getResultList();
       return resultList;
    }
    public boolean noHayReservaEscidFechaInicio(int resescid,Date resfechainicio)
    {
       Query query = getEntityManager().createNamedQuery("Reserva.findByResescidFechainicio");
       query.setParameter("resescid", resescid);
       query.setParameter("resfechainicio", resfechainicio);       
       List<Reserva> resultList = query.getResultList();
       return resultList.isEmpty();
    }
    public boolean noHayReservaEscidFechaIntermedia(int resescid,Date fechaintermedia)
    {
       Query query = getEntityManager().createNamedQuery("Reserva.findByResescidFechaIntermedia");
       query.setParameter("resescid", resescid);
       query.setParameter("fechaintermedia", fechaintermedia);       
       List<Reserva> resultList = query.getResultList();
       return resultList.isEmpty();
    }
    public boolean noEstaReservadaEscidFecha(int resescid,Date resfechainicio)
    {
       Query query = getEntityManager().createNamedQuery("Reserva.findByReservadaFeha");
       query.setParameter("resescid", resescid);
       query.setParameter("resfechainicio", resfechainicio);
       Date fechaintermedia=(Date)resfechainicio.clone();
       fechaintermedia.setMinutes(fechaintermedia.getMinutes()+1);
       query.setParameter("fechaintermedia", fechaintermedia);
       List<Reserva> resultList = query.getResultList();
       return resultList.isEmpty();
    }
    
    public long contarReservasUsuarioFecha(long resusuid,int espid,Date resfechainicio,Date resfechafin)
    {
       Query query = getEntityManager().createNamedQuery("Reserva.findByContarReservasUsuarioFecha");
       query.setParameter("resusuid", resusuid);
       query.setParameter("espid", espid);
       query.setParameter("resfechainicio", resfechainicio);
       query.setParameter("resfechafin", resfechafin);       
       long result = (Long)query.getResultList().get(0);
       return result;
    }
    
    public long contarReservasEscenarioUsuarioFecha(long resusuid,int resescid,Date resfechainicio,Date resfechafin)
    {
       Query query = getEntityManager().createNamedQuery("Reserva.findByContarReservasEscenarioUsuarioFecha");
       query.setParameter("resusuid", resusuid);
       query.setParameter("resescid", resescid);
       query.setParameter("resfechainicio", resfechainicio);
       query.setParameter("resfechafin", resfechafin);       
       long result = (Long)query.getResultList().get(0);
       return result;
    }
    
    
}