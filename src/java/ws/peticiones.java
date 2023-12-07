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
        
        ArrayList<Rol> rolesexistentes = rol.roles;
        
        for (Rol rols: rolesexistentes) {
            if (rols.getRol().equals(nombre)) {
                return true;
            }
            
        }
        return false;
    }
}
