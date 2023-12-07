/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author joelu
 */
public class Competencia {
    public ArrayList<Competencia> competencias;

    private int id_competencia;
    private String nombre;
    private boolean Estado;

    public Competencia() {
    }

    public Competencia(ArrayList<Competencia> competencias, int id_competencia, String nombre, boolean Estado) {
        this.competencias = competencias;
        this.id_competencia = id_competencia;
        this.nombre = nombre;
        this.Estado = Estado;
    }

    public Competencia(int id_competencia, String nombre, boolean Estado) {
        this.id_competencia = id_competencia;
        this.nombre = nombre;
        this.Estado = Estado;
    }
    

    public ArrayList<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(ArrayList<Competencia> competencias) {
        this.competencias = competencias;
    }
    
 
    
    public int getId_competencia() {
        return id_competencia;
    }

    public void setId_competencia(int id_competencia) {
        this.id_competencia = id_competencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }


    
    
}
