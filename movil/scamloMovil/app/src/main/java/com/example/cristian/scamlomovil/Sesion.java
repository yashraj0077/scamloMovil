package com.example.cristian.scamlomovil;

/**
 * Created by Cristian on 25/12/2016.
 */


public class Sesion {

    public  static Trabajador trabajador;



    public  static void setTrabajador(Trabajador t){
        trabajador = t;
    }

    public static  Trabajador getTrabajador(){

        return trabajador;
    }
}
