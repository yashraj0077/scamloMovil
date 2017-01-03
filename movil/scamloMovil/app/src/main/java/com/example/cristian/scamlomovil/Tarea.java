package com.example.cristian.scamlomovil;

/**
 * Created by Cristian on 26/12/2016.
 */
public class Tarea {
    private int asignacion_id;
    private int solicitud_id;
    private String fecha_inicio_tarea;
    private String tarea;
    private String estado_asignacion;
    private String servicio_a_prestar;
    private String edificio;
    private String piso;
    private String espacio;
    private String numero;
    private String persona_que_envio_solicitud;
    private String dependencia;
    private String estado;
    private String equipo_reparado;
    private String numero_inventario;
    private String observaciones;
    private int estado_id;

    public Tarea() {
    }

    public int getAsignacion_id() {
        return asignacion_id;
    }

    public void setAsignacion_id(int asignacion_id) {
        this.asignacion_id = asignacion_id;
    }

    public int getSolicitud_id() {
        return solicitud_id;
    }

    public void setSolicitud_id(int solicitud_id) {
        this.solicitud_id = solicitud_id;
    }

    public String getFecha_inicio_tarea() {
        return fecha_inicio_tarea;
    }

    public void setFecha_inicio_tarea(String fecha_inicio_tarea) {
        this.fecha_inicio_tarea = fecha_inicio_tarea;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getEstado_asignacion() {
        return estado_asignacion;
    }

    public void setEstado_asignacion(String estado_asignacion) {
        this.estado_asignacion = estado_asignacion;
    }

    public String getServicio_a_prestar() {
        return servicio_a_prestar;
    }

    public void setServicio_a_prestar(String servicio_a_prestar) {
        this.servicio_a_prestar = servicio_a_prestar;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getEspacio() {
        return espacio;
    }

    public void setEspacio(String espacio) {
        this.espacio = espacio;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPersona_que_envio_solicitud() {
        return persona_que_envio_solicitud;
    }

    public void setPersona_que_envio_solicitud(String persona_que_envio_solicitud) {
        this.persona_que_envio_solicitud = persona_que_envio_solicitud;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEquipo_reparado() {
        return equipo_reparado;
    }

    public void setEquipo_reparado(String equipo_reparado) {
        this.equipo_reparado = equipo_reparado;
    }

    public String getNumero_inventario() {
        return numero_inventario;
    }

    public void setNumero_inventario(String numero_inventario) {
        this.numero_inventario = numero_inventario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }
}
