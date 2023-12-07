package modelo;

import java.util.ArrayList;

public class CompetenciaRol {
   
    private Rol roles;
    private Competencia competencias;

     ArrayList<CompetenciaRol> relaciones;
    public CompetenciaRol() {
    }

    public CompetenciaRol(Rol roles, Competencia competencias) {
        this.roles = roles;
        this.competencias = competencias;
    }
    
  
    
    //Metodos
    public void agregarRelacion(Competencia competencia, Rol rol) {
        CompetenciaRol relacion = new CompetenciaRol(rol, competencia);
        relaciones.add(relacion);
    }

    public ArrayList<CompetenciaRol> getRelaciones() {
        return relaciones;
    }

    

    public Rol getRoles() {
        return roles;
    }

    public void setRoles(Rol roles) {
        this.roles = roles;
    }

    public Competencia getCompetencias() {
        return competencias;
    }

    public void setCompetencias(Competencia competencias) {
        this.competencias = competencias;
    }

    
}
