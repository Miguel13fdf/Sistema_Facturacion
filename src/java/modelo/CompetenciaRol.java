package modelo;

import java.util.ArrayList;

public class CompetenciaRol {
   
    private Rol roles;
    private Competencia competencias;

    public CompetenciaRol() {
    }

    public CompetenciaRol(Rol roles, Competencia competencias) {
        this.roles = roles;
        this.competencias = competencias;
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
