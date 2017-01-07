package com.example.cristian.scamlomovil;

/**
 * Created by Cristian on 01/01/2017.
 */
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.net.*;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Trabajador trabajador;
    private TextView mensaje;
    private TextView mensajeEmail;
    private TextView mensajeContrasenia;
    private Button ingresar;
    private Sesion sesion;
    private  EditText username;
    private EditText password;

    private  String SERVER_PATH;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mensaje  = (TextView)findViewById(R.id.textViewMensaje);
        mensajeEmail  = (TextView)findViewById(R.id.textViewMensajeEmail);
        mensajeContrasenia  = (TextView)findViewById(R.id.textViewMensajeContrasenia);
        username = (EditText)findViewById(R.id.editTextEmail);
        password = (EditText)findViewById(R.id.editTextContrasenia);
        ingresar = (Button)findViewById(R.id.btnIngresar);
        // Listener de los botones
        ingresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.btnIngresar){
            limpiarMensajes();
            if(!username.getText().toString().equals("")) {
                if(!password.getText().toString().equals("")) {
                    capturarTareasTrabajadador();
                    limpiarEditTexts();
                }else{
                    mensajeContrasenia.setText("Contraseña es obligatoria");
                }
            }else{
                mensajeEmail.setText("Email es obligatorio");
            }
        }
    }
    public void capturarTareasTrabajadador(){
        SERVER_PATH = "http://scamlo.webcindario.com/apiRest/ControladorLogin.php";
         SERVER_PATH+="?username="+username.getText().toString()+"&password="+password.getText().toString();
        //SERVER_PATH+="/?username=pablo@hotmail.com&password=Univalle123456";
        ConexionApiRest conexionApiRest = new ConexionApiRest();
        conexionApiRest.setServerPath(SERVER_PATH);
        //System.out.println(SERVER_PATH+"***************************************************************************");
        conexionApiRest.connect();
        //connect();
        if(conexionApiRest.getTrabajador()!=null) {

            Intent in = new Intent(this, Bienvenido.class );
            startActivity(in);
        }else{
            mensaje.setText("Datos erróneos.Inténtelo otra vez.");
        }
    }
    public void limpiarMensajes(){
        mensajeEmail.setText("");
        mensajeContrasenia.setText("");
        mensaje.setText("");

    }
    public void limpiarEditTexts(){
       username.setText("");
        password.setText("");
    }

}


