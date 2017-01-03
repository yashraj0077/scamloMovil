package com.example.cristian.scamlomovil;

import java.util.ArrayList;

/**
 * Created by Cristian on 26/12/2016.
 */
public class Trabajador {

    private String trabajador_asignado;
    private String username;
    private String password_hash;
    private ArrayList <Tarea>tareas;

    public Trabajador() {
    }

    public String getTrabajador_asignado() {
        return trabajador_asignado;
    }

    public void setTrabajador_asignado(String trabajador_asignado) {
        this.trabajador_asignado = trabajador_asignado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }
}
