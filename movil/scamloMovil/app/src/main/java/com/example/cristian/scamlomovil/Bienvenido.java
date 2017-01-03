package com.example.cristian.scamlomovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView.OnItemSelectedListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class Bienvenido extends AppCompatActivity implements OnItemSelectedListener{

    TextView nombreTrabajador;
    Trabajador trabajador;
    String estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        trabajador = Sesion.getTrabajador();

        nombreTrabajador = (TextView) findViewById(R.id.textNombreUsuario);
        nombreTrabajador.setText(trabajador.getTrabajador_asignado());
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
            Intent intent = new Intent(Bienvenido.this, Tareas.class);
            intent.putExtra("estado", estado);
            startActivity(intent);

        } else {
            if (id == R.id.misTareasPendientes) {
                estado ="Pendiente";
                Intent intent = new Intent(Bienvenido.this, Tareas.class);
                intent.putExtra("estado", estado);
                startActivity(intent);
            }else{
                if (id == R.id.misTareasSolucionadas) {
                    estado ="Solucionado";
                    Intent intent = new Intent(Bienvenido.this, Tareas.class);
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
