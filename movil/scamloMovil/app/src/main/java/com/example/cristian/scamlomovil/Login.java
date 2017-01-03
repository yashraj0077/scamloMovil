package com.example.cristian.scamlomovil;
/************************************ESTA CLASE NO ESTA EN FUCIONAMIENTO ES DECIR NO HACE NADA :) *******************************/


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Login extends AppCompatActivity implements View.OnClickListener {


    Button ingresar;

    EditText idendificador;

    // IP de mi Url
    String IP = "http://192.168.0.10";

    // Rutas de los Web Services
    String GET_BY_ID = IP + "/yii2/apiRest/trabajador.php";


    ObtenerWebService hiloconexion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Enlaces con elementos visuales del XML

        ingresar = (Button)findViewById(R.id.btnIngresar);

        // Listener de los botones
        ingresar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.btnIngresar){
            hiloconexion = new ObtenerWebService();
            String cadenallamada = GET_BY_ID + "?username=pablo@hotmail.com&password=Univalle123456";
            hiloconexion.execute(cadenallamada,"1");   // Parámetros que recibe doInBackground

        }

    }

    public class ObtenerWebService extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];
            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";


            if(params[1]=="1"){    // login

                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                    //connection.setHeader("content-type", "application/json");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK){


                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                        // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                        // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                        // StringBuilder.

                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                        }

                        //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON.getString("trabajador_asignado");   // estado es el nombre del campo en el JSON
                        devuelve = devuelve+resultJSON;
                        Toast.makeText(getApplicationContext(),
                                devuelve, Toast.LENGTH_SHORT).show();

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return devuelve;
            }
            return null;
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onPostExecute(String s) {
            //resultado.setText(s);
           // Toast.makeText(getApplicationContext(),
                  //  s, Toast.LENGTH_SHORT).show();
            //super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
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
            //Toast.makeText(getApplicationContext(),"Ingresar", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, Tareas.class));

        } else {
            if (id == R.id.salir) {
                //startActivity(new Intent(this, SolicitudRegistro.class));
            }

        }

        return super.onOptionsItemSelected(item);
    }

}
