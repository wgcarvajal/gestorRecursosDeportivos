/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorrecursosdeportivos.services;

import com.unicauca.gestorrecursosdeportivos.entities.Escenario;
import com.unicauca.gestorrecursosdeportivos.entities.Espacioescenarios;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.EscenarioFacade;
import com.unicauca.gestorrecursosdeportivos.jpacontrollers.EspacioescenariosFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 * REST Web Service
 *
 * @author aranda
 */
@Path("webservice")
public class service {
    
    @EJB
    private EspacioescenariosFacade espacioescenariosEJB;
    @EJB
    private EscenarioFacade escenarioEJB;
    

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of service
     */
    public service() {
    }

    /**
     * Retrieves representation of an instance of com.unicauca.gestorrecursosdeportivos.services.service
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        
        Espacioescenarios espacioescenarios =  espacioescenariosEJB.find(1);
        JSONObject jsonEspacio = new JSONObject();
        JSONArray arrayEscenarios = new JSONArray();
        try {
            jsonEspacio.put("color", espacioescenarios.getEspcolor());
            jsonEspacio.put("ancho",932);
            jsonEspacio.put("largo",espacioescenarios.getEspancho());
            List <Escenario> listEscenarios = escenarioEJB.buscarPorEspID(1);
            for(Escenario esc:listEscenarios)
            {
                JSONObject jsonEscenario = new JSONObject();
                jsonEscenario.put("imagen",esc.getEscnombreimagenanimada());
                jsonEscenario.put("ancho",esc.getEscanchoimagen());
                jsonEscenario.put("largo",esc.getEsclargoimagen());
                jsonEscenario.put("margenSuperior",esc.getEscmargensuperior());
                jsonEscenario.put("margenIzquierda",esc.getEscmargenizquierda());
                jsonEscenario.put("rotar",esc.getEscrotarimagen());
                jsonEscenario.put("nombre",esc.getEscnombre());
                arrayEscenarios.put(jsonEscenario);
            }
            
            jsonEspacio.put("escenarios", arrayEscenarios);
        } catch (JSONException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonEspacio.toString();
    }

    /**
     * PUT method for updating or creating an instance of service
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
