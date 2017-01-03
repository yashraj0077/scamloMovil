package com.example.cristian.scamlomovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Tareas extends AppCompatActivity implements OnItemClickListener {
   /* String []arreglo1 = {"Traslado - Edificio: Martin Elias - salon: 101 - piso: 1 - descripcion: Cambiar Ventilador",
                        "Mantenimiento - Edificio: Martin Elias - salon 200 - piso 2 - descripcion: Arreglar Escritorio",
                        "Logistica - Edificio: Martin Elias - salon: 301 - piso: 3 - descripcion: Traer mesas elegantes"};*/
    private ArrayList<String> tareas;
    private String estado;
    private ListView listViewTareas;
    private ArrayList<Integer> asignacionIds;


    @Override
    protected void onResume() {
        super.onResume();
        cargarTareasLista();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);

        listViewTareas = (ListView)findViewById(R.id.listViewTareas);
        listViewTareas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        listViewTareas.setOnItemClickListener(this);
   }

    public  void cargarTareasLista(){
        estado = getIntent().getExtras().getString("estado");
        tareas = new ArrayList<>();
        asignacionIds = new ArrayList<>();
        for(int i=0;i<Sesion.getTrabajador().getTareas().size();i++){
            if(Sesion.getTrabajador().getTareas().get(i).getEstado().equals(estado)) {
              //  System.out.println("********************************************************"+Sesion.getTrabajador().getTareas().size()+"****************************************************************");
                tareas.add("Fecha: " + Sesion.getTrabajador().getTareas().get(i).getFecha_inicio_tarea() + " - " +
                        "Tarea: " + Sesion.getTrabajador().getTareas().get(i).getTarea() + " - " +
                        "Servicio: " + Sesion.getTrabajador().getTareas().get(i).getServicio_a_prestar() + " - " +
                        "Edificio: " + Sesion.getTrabajador().getTareas().get(i).getEdificio() + " - " +
                        "Piso: " + Sesion.getTrabajador().getTareas().get(i).getPiso() + " - " +
                        "Espacio: " + Sesion.getTrabajador().getTareas().get(i).getEspacio() + " - " +
                        "Numero de espacio: " + Sesion.getTrabajador().getTareas().get(i).getNumero() + " - " +
                        "Solicitante: " + Sesion.getTrabajador().getTareas().get(i).getPersona_que_envio_solicitud() + " - " +
                        "Solitud: " + Sesion.getTrabajador().getTareas().get(i).getDependencia()+ " - " +
                        "Asignacion id: " + Sesion.getTrabajador().getTareas().get(i).getAsignacion_id() + " - " +
                        "Solicitud id: " + Sesion.getTrabajador().getTareas().get(i).getSolicitud_id() + " - " +
                        "Estado: " + Sesion.getTrabajador().getTareas().get(i).getEstado_asignacion());
                         asignacionIds.add(Sesion.getTrabajador().getTareas().get(i).getAsignacion_id());
            }
        }
        //System.out.println("******************************************************** Tareas: "+tareas.size()+" ****************************************************************");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tareas);
        ListView lista = (ListView)findViewById(R.id.listViewTareas);
        lista.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(Tareas.this, Registra_solicitud_trabajador.class);
        intent.putExtra("asignacionId",asignacionIds.get(i));
        startActivity(intent);
    }
}
