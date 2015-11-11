/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.jpacontrollers;


import com.unicauca.gestorrecursosdeportivos.entities.Usuariogrupo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author geovanny
 */
@Stateless
public class UsuariogrupoFacade extends AbstractFacade<Usuariogrupo> {
    @PersistenceContext(unitName = "GestorRecursosDeportivosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariogrupoFacade() {
        super(Usuariogrupo.class);
    }
    public int actualizarNombreUsuario(String gruid,Long usuid,String usunombreusuario)
    {
        Query query = getEntityManager().createQuery(
        "UPDATE Usuariogrupo u SET u.usunombreusuario = "+"'"+usunombreusuario+"'"+
        " WHERE u.usuariogrupoPK.usuid = :usuid AND u.usuariogrupoPK.gruid = :gruid");
        query.setParameter("usuid", usuid);
        query.setParameter("gruid", gruid);
        return query.executeUpdate();
          
    }
    
    public List<Usuariogrupo> buscarPorNombreUsuario(String usunombreusuario)
    {
        Query query = getEntityManager().createNamedQuery("Usuariogrupo.findByUsunombreusuario");
        query.setParameter("usunombreusuario", usunombreusuario);
        List<Usuariogrupo> resultList = query.getResultList();
        return resultList;
    }
    
    public boolean esUsuario(String usunombreusuario)
    {
        Query query = getEntityManager().createNamedQuery("Usuariogrupo.findByUsunombreusuario");
        query.setParameter("usunombreusuario", usunombreusuario);
        List<Usuariogrupo> resultList = query.getResultList();
        return resultList.get(0).getGrupo().getGruid().equals("user");
    }
    
    public Long retornarIdUsuarioPorNombreUsuario(String usunombreusuario)
    {
        Query query = getEntityManager().createNamedQuery("Usuariogrupo.findByUsunombreusuario");
        query.setParameter("usunombreusuario", usunombreusuario);
        List<Usuariogrupo> resultList = query.getResultList();
        return resultList.get(0).getUsuario().getUsuid();
    }
}
