package com.example.cristian.scamlomovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Tareas extends AppCompatActivity implements OnItemClickListener, AdapterView.OnItemSelectedListener {
   /* String []arreglo1 = {"Traslado - Edificio: Martin Elias - salon: 101 - piso: 1 - descripcion: Cambiar Ventilador",
                        "Mantenimiento - Edificio: Martin Elias - salon 200 - piso 2 - descripcion: Arreglar Escritorio",
                        "Logistica - Edificio: Martin Elias - salon: 301 - piso: 3 - descripcion: Traer mesas elegantes"};*/
    private ArrayList<String> tareas;
    private String estado;
    private ListView listViewTareas;
    private ArrayList<Integer> asignacionIds;
    private boolean mostrarTareas;
    private TextView textViewTituloEstado;



    @Override
    protected void onResume() {
        super.onResume();
        cargarTareasLista();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);

        textViewTituloEstado = (TextView)findViewById(R.id.textViewTituloTarea);

        listViewTareas = (ListView)findViewById(R.id.listViewTareas);
        listViewTareas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        listViewTareas.setOnItemClickListener(this);
   }

    public  void cargarTareasLista(){
        estado = getIntent().getExtras().getString("estado");
        mostrarTareas = false;

        tareas = new ArrayList<>();
        asignacionIds = new ArrayList<>();
        int contador =0;
        for(int i=0;i<Sesion.getTrabajador().getTareas().size();i++){

            if(Sesion.getTrabajador().getTareas().get(i).getEstado().equals(estado)) {
              //  System.out.println("********************************************************"+Sesion.getTrabajador().getTareas().size()+"****************************************************************");
                tareas.add((++contador)+"). "+"Fecha: " + Sesion.getTrabajador().getTareas().get(i).getFecha_inicio_tarea() + " - " +
                        "Tarea: " + Sesion.getTrabajador().getTareas().get(i).getTarea() + " - " +
                        "Servicio: " + Sesion.getTrabajador().getTareas().get(i).getServicio_a_prestar() + " - " +
                        "Edificio: " + Sesion.getTrabajador().getTareas().get(i).getEdificio() + " - " +
                        "Piso: " + Sesion.getTrabajador().getTareas().get(i).getPiso() + " - " +
                        "Espacio: " + Sesion.getTrabajador().getTareas().get(i).getEspacio() + " - " +
                        "Numero de espacio: " + Sesion.getTrabajador().getTareas().get(i).getNumero() + " - " +
                        "Solicitante: " + Sesion.getTrabajador().getTareas().get(i).getPersona_que_envio_solicitud() + " - " +
                        "Dependencia: " + Sesion.getTrabajador().getTareas().get(i).getDependencia());

                         asignacionIds.add(Sesion.getTrabajador().getTareas().get(i).getAsignacion_id());
                         mostrarTareas = true;

            }
        }
        if (mostrarTareas==false){
            tareas.add("NO TIENES");
        }
        //System.out.println("******************************************************** Tareas: "+tareas.size()+" ****************************************************************");
        textViewTituloEstado.setText("TAREAS CON ESTADO: "+estado);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tareas);
        ListView lista = (ListView)findViewById(R.id.listViewTareas);
        lista.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (mostrarTareas){
            Intent intent = new Intent(Tareas.this, Registra_solicitud_trabajador.class);
            intent.putExtra("asignacionId",asignacionIds.get(i));
            startActivity(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.misTareasNoRealizadas) {
            estado ="No Realizado";
            Intent intent = new Intent(Tareas.this, Tareas.class);
            intent.putExtra("estado", estado);
            startActivity(intent);

        } else {
            if (id == R.id.misTareasPendientes) {
                estado ="Pendiente";
                Intent intent = new Intent(Tareas.this, Tareas.class);
                intent.putExtra("estado", estado);
                startActivity(intent);
            }else{
                if (id == R.id.misTareasSolucionadas) {
                    estado ="Solucionado";
                    Intent intent = new Intent(Tareas.this, Tareas.class);
                    intent.putExtra("estado", estado);
                    startActivity(intent);
                }else{
                    if (id == R.id.salir) {
                        startActivity(new Intent(this, MainActivity.class));
                    }
                }
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {}

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
