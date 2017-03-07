package lt.a96.httpcarlosmellan.culturappcm;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv_registarusuario, tv_nombre, tv_descripcion, tv_localizacion, tv_fechainicio, tv_fechafin, tv_horainicio, tv_horafin, tv_estado;
    Button btn_iniciarsesion, btn_eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_eventos=(Button)findViewById(R.id.btn_eventos);
        btn_iniciarsesion=(Button)findViewById(R.id.btn_iniciarsesion);
        tv_nombre = (TextView)findViewById(R.id.tv_nombre);
        tv_descripcion = (TextView)findViewById(R.id.tv_descripcion);
        tv_localizacion = (TextView)findViewById(R.id.tv_localizacion);
        tv_fechainicio = (TextView)findViewById(R.id.tv_fechainicio);
        tv_fechafin =  (TextView)findViewById(R.id.tv_fechafin);
        tv_horainicio = (TextView)findViewById(R.id.tv_horainicio);
        tv_horafin = (TextView)findViewById(R.id.tv_horafin);
        tv_estado = (TextView)findViewById(R.id.tv_estado);
        tv_registarusuario=(TextView)findViewById(R.id.tv_registarusuario);
        tv_registarusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrarUsuarios.class);
                startActivity(intent);
            }
        });

        btn_iniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });


        btn_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Nombre = tv_nombre.getText().toString();
                final String Descripcion = tv_descripcion.getText().toString();
                final String FechaInicio = tv_fechainicio.getText().toString();
                final String FechaFin = tv_fechafin.getText().toString();
                final String HoraInicio = tv_horainicio.getText().toString();
                final String HoraFin = tv_horafin.getText().toString();
                final String Estado = tv_estado.getText().toString();
                final String Localizacion = tv_localizacion.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            //boolean success = jsonResponse.getBoolean("success");
                            if(response!=null){
                                //String Nombre = jsonResponse.getString("Nombre");
                                //String Descripcion = jsonResponse.getString("Descripcion");
                                //String FechaInicio = jsonResponse.getString("FechaInicio");
                                //String FechaFin = jsonResponse.getString("FechaFin");
                                //String Localizacion = jsonResponse.getString("Localizacion");
                                //String HoraInicio = jsonResponse.getString("HoraInicio");
                                //String HoraFin = jsonResponse.getString("HoraFin");
                                //String Estado = jsonResponse.getString("Estado");

                                //alerta para mostrar el resultado del response
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                                builder2.setMessage("entra al if")
                                    .setNegativeButton(response, null)
                                    .create()
                                    .show();
                                    //fin alerta

                                Intent intent = new Intent(MainActivity.this, Eventos.class);
                                intent.putExtra("Nombre", Nombre);
                                intent.putExtra("Descripcion", Descripcion);
                                intent.putExtra("FechaInicio", FechaInicio);
                                intent.putExtra("FechaFin", FechaFin);
                                intent.putExtra("Localizacion", Localizacion);
                                intent.putExtra("HoraInicio", HoraInicio);
                                intent.putExtra("HoraFin", HoraFin);
                                intent.putExtra("Estado", Estado);

                                MainActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("No se han podido cargar los eventos")
                                        .setNegativeButton("Reintentar", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                EventosRequest eventosRequest = new EventosRequest(Nombre, Descripcion, Localizacion, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(eventosRequest);
            }
        });
    }
}