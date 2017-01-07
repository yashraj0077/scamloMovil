package com.example.cristian.scamlomovil;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Registra_solicitud_trabajador extends AppCompatActivity  implements OnItemSelectedListener,View.OnClickListener {

    private String[] ciudades ={"SELECCIONA","Solucionado","Pendiente","No Realizado"};
    private Spinner spinner;
    private ArrayAdapter adapter;
    private EditText equipoReparado;
    private EditText numeroInventario;
    private EditText observaciones;
    private int asignacionId;
    private  String SERVER_PATH;
    private int posicion;
    private int posicionEstado;
    private Button guardarCambios;
    private String estado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_solicitud_trabajador);

        asignacionId = getIntent().getExtras().getInt("asignacionId");

        equipoReparado = (EditText)findViewById(R.id.editTextEquipoReparado);
        numeroInventario = (EditText)findViewById(R.id.editTextNumeroInvetario);
        observaciones = (EditText)findViewById(R.id.editTextObservaciones);
        guardarCambios = (Button)findViewById(R.id.btnGuardarCambios);

        guardarCambios.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.estado);
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.textview_spinner, ciudades));
        //spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        cargarInformacion();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String dato = (String) adapterView.getItemAtPosition(i);
        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
        Toast toast = Toast.makeText(getBaseContext(),"Estado "+dato,Toast.LENGTH_SHORT);
        toast.show();
        posicionEstado =i;
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
            Intent intent = new Intent(Registra_solicitud_trabajador.this, Tareas.class);
            intent.putExtra("estado", estado);
            startActivity(intent);

        } else {
            if (id == R.id.misTareasPendientes) {
                estado ="Pendiente";
                Intent intent = new Intent(Registra_solicitud_trabajador.this, Tareas.class);
                intent.putExtra("estado", estado);
                startActivity(intent);
            }else{
                if (id == R.id.misTareasSolucionadas) {
                    estado ="Solucionado";
                    Intent intent = new Intent(Registra_solicitud_trabajador.this, Tareas.class);
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
    public void onNothingSelected(AdapterView<?> parent) {}

    public void cargarInformacion(){

        for (int i=0;i<Sesion.getTrabajador().getTareas().size();i++) {

            if (Sesion.getTrabajador().getTareas().get(i).getAsignacion_id()==asignacionId) {
                spinner.setSelection(Sesion.getTrabajador().getTareas().get(i).getEstado_id());
                if(Sesion.getTrabajador().getTareas().get(i).getEquipo_reparado().equals("null")){
                    equipoReparado.setText("");
                }else{
                    equipoReparado.setText(Sesion.getTrabajador().getTareas().get(i).getEquipo_reparado());
                }
                if(Sesion.getTrabajador().getTareas().get(i).getNumero_inventario().equals("0")){
                    numeroInventario.setText("0");
                }else{
                    numeroInventario.setText(Sesion.getTrabajador().getTareas().get(i).getNumero_inventario());
                }
                if(Sesion.getTrabajador().getTareas().get(i).getObservaciones().equals("null")){
                    observaciones.setText("");
                }else{
                    observaciones.setText(Sesion.getTrabajador().getTareas().get(i).getObservaciones());
                }
                posicion = i;
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnGuardarCambios){
            if(posicionEstado!=0) {
                registrarTareas();
                capturarTareasTrabajadador();

                Intent in = new Intent(this, Bienvenido.class );
                startActivity(in);
            }else{
                Toast toast = Toast.makeText(getBaseContext(),"Debe seleccionar el estado de la tarea",Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void registrarTareas(){

        SERVER_PATH = "http://scamlo.webcindario.com/apiRest/ControladorRegistrarTarea.php";
        if(equipoReparado.getText().toString().equals("")){
            equipoReparado.setText("null");
        }
        if(numeroInventario.getText().toString().equals("")||numeroInventario.getText().toString().equals("0")){
            numeroInventario.setText("0");
        }
        if(observaciones.getText().toString().equals("")){
            observaciones.setText("null");
        }
        SERVER_PATH +=( "?asignacion_id=" + Sesion.getTrabajador().getTareas().get(posicion).getAsignacion_id() +
                "&solicitud_id=" + Sesion.getTrabajador().getTareas().get(posicion).getSolicitud_id() +
                "&estado_id=" + posicionEstado + "&equipo_reparado=" + equipoReparado.getText().toString() +
                "&numero_inventario=" + numeroInventario.getText().toString() + "&observaciones=" + observaciones.getText().toString()).replace(" ","%20");
        ConexionApiRest conexionApiRest = new ConexionApiRest();
        conexionApiRest.setServerPath(SERVER_PATH);
       // System.out.println(SERVER_PATH+"111111111111***************************************************************************");
        conexionApiRest.connect();
        conexionApiRest = null;

    }
    public void capturarTareasTrabajadador(){

        ConexionApiRest conexionApiRest = new ConexionApiRest();

         SERVER_PATH = "http://scamlo.webcindario.com/apiRest/ControladorLogin.php";
         SERVER_PATH+="?username="+Sesion.getTrabajador().getUsername()+"&password="+Sesion.getTrabajador().getPassword_hash();
        //SERVER_PATH+="/?username=pablo@hotmail.com&password=Univalle123456";
         conexionApiRest.setServerPath(SERVER_PATH);
       // System.out.println(SERVER_PATH+"22222222222222222222222***************************************************************************");
         conexionApiRest.connect();

    }
}
