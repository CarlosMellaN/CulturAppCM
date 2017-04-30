package lt.a96.httpcarlosmellan.culturappcm;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CrearEvento extends AppCompatActivity {

    EditText et_nombreevento, et_descripcionevento, et_fechainicio, et_fechafin, et_ubicacion, et_horainicio, et_horafin;
    Button btn_registarevento, btn_cancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);
        et_nombreevento = (EditText)findViewById(R.id.et_nombreevento);
        et_descripcionevento = (EditText)findViewById(R.id.et_descripcionevento);
        et_fechainicio = (EditText)findViewById(R.id.et_fechainicio);
        et_fechafin = (EditText)findViewById(R.id.et_fechafin);
        et_ubicacion = (EditText)findViewById(R.id.et_ubicacion);
        et_horainicio = (EditText)findViewById(R.id.et_horainicio);
        et_horafin = (EditText)findViewById(R.id.et_horafin);
        btn_registarevento = (Button)findViewById(R.id.btn_registarevento);
        btn_cancelar = (Button)findViewById(R.id.btn_cancelar);

        // TODO: hacer que se guarden los datos del usuario para que cuando vuelva al perfil, los siga mostrando
        // TODO: hacer que no se puedan ingresar eventos repetidos

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearEvento.this,Perfil.class);
                startActivity(intent);
            }
        });

        btn_registarevento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Nombre = et_nombreevento.getText().toString();
                final String Descripcion = et_descripcionevento.getText().toString();
                final String FechaInicio = et_fechainicio.getText().toString();
                final String FechaFin = et_fechafin.getText().toString();
                final String Localizacion = et_ubicacion.getText().toString();
                final String HoraInicio = et_horainicio.getText().toString();
                final String HoraFin = et_horafin.getText().toString();

                if (TextUtils.isEmpty(Nombre) || TextUtils.isEmpty(Descripcion) || TextUtils.isEmpty(FechaInicio)
                        || TextUtils.isEmpty(FechaFin)  || TextUtils.isEmpty(Localizacion) || TextUtils.isEmpty(HoraInicio)
                        || TextUtils.isEmpty(HoraFin)) {
                    Toast.makeText(CrearEvento.this, "Debe llenar todo los campos", Toast.LENGTH_SHORT).show();

                }else {
                    Response.Listener<String> responseListener = new Response.Listener<String>(){

                        @Override
                        public void onResponse(String response) {
                            //alerta que muestra el resultado del response
                            /*AlertDialog.Builder builder2 = new AlertDialog.Builder(CrearEvento.this);
                            builder2.setMessage("entra al responselistener")
                                    .setNegativeButton(response, null)
                                    .create()
                                    .show();*/
                            //fin alerta
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if(success){
                                    Toast.makeText(CrearEvento.this,"Evento creado con exito",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(CrearEvento.this, Perfil.class);
                                    startActivity(intent);
                                }else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(CrearEvento.this);
                                    builder.setMessage("Registro Fallido")
                                            .setNegativeButton("Reintentar", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    NuevoEventoRequest nuevoEventoRequest = new NuevoEventoRequest(Nombre, Descripcion, FechaInicio, FechaFin, Localizacion, HoraInicio, HoraFin, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(CrearEvento.this);
                    queue.add(nuevoEventoRequest);
                }
                }

        });
    }
}
