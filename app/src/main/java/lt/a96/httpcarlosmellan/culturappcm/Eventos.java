package lt.a96.httpcarlosmellan.culturappcm;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Eventos extends AppCompatActivity {
    SearchView searchView;
    TextView tv_evento, tv_descripcion, tv_localizacion;
    RequestQueue requestQueue;
    String getEventos = "http://192.168.1.158/culturappcm/ver_eventos.php";
    String getEventos2 = "http://192.168.1.158/culturappcm/ver_eventos2.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        //searchView = (SearchView)findViewById(R.id.searchView);
        tv_evento = (TextView)findViewById(R.id.tv_evento);
        tv_descripcion = (TextView)findViewById(R.id.tv_descripcion);
        tv_localizacion = (TextView)findViewById(R.id.tv_localizacion);
        requestQueue = Volley.newRequestQueue(this);

        tv_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent =  new Intent(Eventos.this, DetalleEvento.class);
                startActivity(intent);*/
                //final String IdEvento = tv_localizacion.getText().toString();
                EventoDetalle();
                /*AlertDialog.Builder builder = new AlertDialog.Builder(Eventos.this);
                builder.setMessage("????")
                        .setNegativeButton("go", null)
                        .create()
                        .show();*/

            }
        });

        //Carga los eventos con la funcion
        RecibeEventos();
    }

    public void RecibeEventos(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getEventos, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("eventos");
                    for (int i=0 ; i < jsonArray.length(); i++){
                        JSONObject eventos = jsonArray.getJSONObject(i);
                        String IdEvento = eventos.getString("IdEvento");
                        String Nombre = eventos.getString("Nombre");
                        String Descripcion = eventos.getString("Descripcion");
                        String Localizacion = eventos.getString("Localizacion");
                        String FechaInicio = eventos.getString("FechaInicio");

                        tv_evento.append(IdEvento + " " + "Nombre del evento: \n" +Nombre + "\n"  + Descripcion + "\nLugar: \n" + Localizacion + "\nComienza el \n" + FechaInicio + "\n \n");

                        //solo muestran el ultimo evento en pantalla
                        //tv_evento.setText(IdEvento+Nombre + Descripcion);
                        //tv_localizacion.setText(IdEvento);
                        //String IdEventoPost=IdEvento;

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY","ERROR");
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //envia los datos del php en un objeto map<clave, valor>
                Map<String, String> params = new HashMap<>();
                //params.put("IdEvento", );
                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    //muestra todos los eventos uno por uno, en vez de solo el seleccionado
    public void EventoDetalle(){
        //final String IdEvento = tv_localizacion.getText().toString();
        //RecibeEventos();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getEventos, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("eventos");
                    for (int i=0 ; i < jsonArray.length(); i++){

                        JSONObject eventos = jsonArray.getJSONObject(i);
                        String IdEvento = eventos.getString("IdEvento");
                        String Nombre = eventos.getString("Nombre");
                        String Descripcion = eventos.getString("Descripcion");
                        String Localizacion = eventos.getString("Localizacion");
                        String FechaInicio = eventos.getString("FechaInicio");
                        String FechaFin = eventos.getString("FechaFin");
                        String HoraInicio = eventos.getString("HoraInicio");
                        String HoraFin = eventos.getString("HoraFin");


                        Intent intent = new Intent(Eventos.this, DetalleEvento.class);
                        intent.putExtra("IdEvento", IdEvento);
                        intent.putExtra("Nombre", Nombre);
                        intent.putExtra("Descripcion", Descripcion);
                        intent.putExtra("Localizacion", Localizacion);
                        intent.putExtra("FechaInicio", FechaInicio);
                        intent.putExtra("FechaFin", FechaFin);
                        intent.putExtra("HoraInicio", HoraInicio);
                        intent.putExtra("HoraFin", HoraFin);
                        //System.out.println(IdEvento);
                        Eventos.this.startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY ERROR", error.toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(Eventos.this);
                //builder.setMessage()
                builder.setMessage(error.toString())
                        .setNegativeButton("Reintentar", null)
                        .create()
                        .show();
            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }


    //No muestra nada
    public void EventoDetalle2(){
        //final String IdEvento = tv_localizacion.getText().toString();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getEventos2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject jsonResponse = new JSONObject();
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        final String IdEvento = jsonResponse.getString("IdEvento");
                        String Nombre = jsonResponse.getString("Nombre");
                        String Descripcion = jsonResponse.getString("Descripcion");
                        String Localizacion = jsonResponse.getString("Localizacion");
                        String FechaInicio = jsonResponse.getString("FechaInicio");
                        String FechaFin = jsonResponse.getString("FechaFin");
                        String HoraInicio = jsonResponse.getString("HoraInicio");
                        String HoraFin = jsonResponse.getString("HoraFin");
                        Toast cont = Toast.makeText(Eventos.this, IdEvento, Toast.LENGTH_SHORT);
                        cont.show();

                        Intent intent = new Intent(Eventos.this, DetalleEvento.class);
                        intent.putExtra("IdEvento", IdEvento);
                        intent.putExtra("Nombre", Nombre);
                        intent.putExtra("Descripcion", Descripcion);
                        intent.putExtra("Localizacion", Localizacion);
                        intent.putExtra("FechaInicio", FechaInicio);
                        intent.putExtra("FechaFin", FechaFin);
                        intent.putExtra("HoraInicio", HoraInicio);
                        intent.putExtra("HoraFin", HoraFin);

                        Eventos.this.startActivity(intent);
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Eventos.this);
                        builder.setMessage("No se han encontrado eventos")
                                .setNegativeButton("Reintentar", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY ERROR", error.toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(Eventos.this);
                //builder.setMessage(IdEvento)
                builder.setMessage(error.toString())
                        .setNegativeButton("Reintentar", null)
                        .create()
                        .show();
            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }
}



