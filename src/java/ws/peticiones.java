/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import modelo.Competencia;
import modelo.Rol;

/**
 *
 * @author USUARIO
 */
@WebService(serviceName = "peticiones")
public class peticiones {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "siexisterol")
    public Boolean siexisterol(@WebParam(name = "nombre") String nombre) {
        Rol rol = new Rol();
        
        ArrayList<Rol> rolesexistentes = rol.getRoles();
        
        for (Rol rols: rolesexistentes) {
            if (rols.getRol().equals(nombre)) {
                return true;
            }
            
        }
        return false;
    }

    
    
    
    @WebMethod(operationName = "estadorol")
    public Boolean estadorol(@WebParam(name = "nombrerol") String nombrerol) {
        Rol rol = new Rol();
        
        ArrayList<Rol> estadospornombre = rol.getRoles();
        
        for (Rol rols: estadospornombre) {
            if (rols.getRol().equals(nombrerol)) {
                return rols.isEstado();
            }
            
        }
        return null;
    }
    
        @WebMethod(operationName = "siexisteComp")
    public Boolean siexisteComp(@WebParam(name = "idcomp") String idcomp) {
        Competencia competencia = new Competencia();
        
        ArrayList<Competencia> competenciasExistentes = competencia.getCompetencias();
        
        for (Competencia comps: competenciasExistentes) {
            if (comps.getCompetencias().equals(idcomp)) {
                return true;
            }
            
        }
        return false;
    }
}
         