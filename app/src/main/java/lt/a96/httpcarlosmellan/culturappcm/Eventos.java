package lt.a96.httpcarlosmellan.culturappcm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Eventos extends AppCompatActivity {
    SearchView searchView;
    TextView tv_evento, tv_descripcion, tv_localizacionn, tv_eventoss, tv_descripcionn;
    RequestQueue requestQueue;
    ListView lv_eventoss;
    String getEventos = "http://192.168.1.158/culturappcm/ver_eventos.php";
    String getEventos2 = "http://192.168.1.158/culturappcm/ver_eventos2.php";
    String texto;
    ArrayAdapter<String> adapter;
    ArrayList<String> items;
    SearchView sv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        //searchView = (SearchView)findViewById(R.id.searchView);
        /*tv_evento = (TextView)findViewById(R.id.tv_evento);
        tv_descripcion = (TextView)findViewById(R.id.tv_descripcion);
        tv_localizacion = (TextView)findViewById(R.id.tv_localizacion);*/
        lv_eventoss = (ListView)findViewById(R.id.lv_eventoss);
        tv_eventoss = (TextView)findViewById(R.id.tv_eventoss);
        //tv_localizacionn = (TextView)findViewById(R.id.tv_localizacionn);
        //tv_descripcionn = (TextView)findViewById(R.id.tv_descripcionn);
        //sv = (SearchView)findViewById(R.id.sv);
        requestQueue = Volley.newRequestQueue(Eventos.this);

        items=new ArrayList<String>();
        adapter=new ArrayAdapter(this, R.layout.item_layout,R.id.tv_eventoss,items);
        lv_eventoss.setAdapter(adapter);

        //Carga los eventos con la funcion
        //RecibeEventos();
        RecibeDatos();

        /*sv.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(final String text) {

                if (sv.getWidth() > 0) {
                    // your code here
                    adapter.getFilter().filter(text);
                    return false;
                }
                return false;
            }
        });*/


        lv_eventoss.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Al pasar la funcion recibedatos aca, hay un problema con el RequestQueue que dice que no se puede aplicar
                //en un clicklistener
                Intent intent =  new Intent(Eventos.this, DetalleEvento.class);
                //intent.putExtra(texto, lv_eventoss.getItemAtPosition(position).toString());
                intent.putExtra("Nombre", lv_eventoss.getItemAtPosition(position).toString());
                //intent.putExtra("Descripcion", lv_eventoss.getItemAtPosition(position).toString());
                //intent.putExtra("Localizacion", lv_eventoss.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

        /*lv_eventoss.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // Create request queue
                RequestQueue requestQueue= Volley.newRequestQueue(Eventos.this);
                //  Create json array request
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getEventos, null, new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response){
                        // Successfully download json
                        // So parse it and populate the listview
                        try {
                            JSONArray jsonArray = response.getJSONArray("eventos");
                            texto="";
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                String Nombre = jsonObject.getString("Nombre");
                                String Descripcion = jsonObject.getString("Descripcion");
                                String Localizacion = jsonObject.getString("Localizacion");
                                String FechaInicio = jsonObject.getString("FechaInicio");
                                String FechaFin = jsonObject.getString("FechaFin");
                                String HoraInicio = jsonObject.getString("HoraInicio");
                                String HoraFin = jsonObject.getString("HoraFin");
                                //items.add(jsonObject.getString("Descripcion"));
                                //items.add(jsonObject.getString("Localizacion"));

                                texto = "Nombre: " +
                                        jsonObject.getString("Nombre") +
                                        "\n"+"Hora de comienzo: \n" +
                                        jsonObject.getString("Descripcion") +
                                        "\n"+"Hora de comienzo: \n" +
                                        jsonObject.getString("FechaInicio") +
                                        "\n"+"Direccion del evento: \n" +
                                        jsonObject.getString("Localizacion");

                                Intent intent = new Intent(Eventos.this, DetalleEvento.class);
                                //intent.putExtra("IdEvento", IdEvento);
                                intent.putExtra("Nombre", Nombre);
                                intent.putExtra("Descripcion", Descripcion);
                                intent.putExtra("Localizacion", Localizacion);
                                intent.putExtra("FechaInicio", FechaInicio);
                                intent.putExtra("FechaFin", FechaFin);
                                intent.putExtra("HoraInicio", HoraInicio);
                                intent.putExtra("HoraFin", HoraFin);
                                //System.out.println(IdEvento);
                                //Eventos.this.startActivity(intent);
                                //items.add(texto);
                                items.add("Nombre: "+ Nombre+"\n"+"Descripcion: "+Descripcion);

                                /*intent.putExtra(texto, lv_eventoss.getItemAtPosition(position).toString());
                                intent.putExtra(lv_eventoss.getItemAtPosition(position).toString(), "Nombre"+ Nombre);*/
                                //intent.putExtra("Descripcion", lv_eventoss.getItemAtPosition(position).toString());
                                //intent.putExtra("Localizacion", lv_eventoss.getItemAtPosition(position).toString());
                                //startActivity(intent);

                                //items.add(jsonObject.getString("Localizacion"));

                            /*}
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("Error", "No se pueden cargar los eventos");
                    }
                });

                Intent intent = new Intent(Eventos.this, DetalleEvento.class);
                //intent.putExtra(texto, lv_eventoss.getItemAtPosition(position).toString());
                intent.putExtra("Nombre", lv_eventoss.getItemAtPosition(position).toString());
                //intent.putExtra("Localizacion", lv_eventoss.getItemAtPosition(position).toString());
                startActivity(intent);
                // add json array request to the request queue
                requestQueue.add(jsonObjectRequest);

            }
        });*/

    }


    //muestra todos los eventos uno por uno, en vez de solo el seleccionado
    public void EventoDetalle(){
        //final String IdEvento = tv_localizacion.getText().toString();
        //final int value = Integer.parseInt(IdEvento);
        //Volver a poner GEt y getEventos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getEventos2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("eventos");
                    //for (int i=0 ; i < jsonArray.length(); i++){
                    SharedPreferences sharefpref= getSharedPreferences("saveid", Context.MODE_PRIVATE);
                    String IdEvento = sharefpref.getString("IdEvento","");
                    int value = Integer.parseInt(IdEvento);
                        JSONObject eventos = jsonArray.getJSONObject(Integer.parseInt(IdEvento.toString()));
                        //String IdEvento = eventos.getString("IdEvento");
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
                        System.out.println(IdEvento);
                        Eventos.this.startActivity(intent);
                    //}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY ERROR", error.toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(Eventos.this);
                //builder.setMessage(value)
                builder.setMessage(error.toString())
                        .setNegativeButton("Reintentar", null)
                        .create()
                        .show();
            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }


    //LISTVIEW LLENADO
    public void RecibeDatos(){
        //super.onStart();
        // Create request queue
        //esto es donde esta el error al moverlo al onitemclicklistener
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        //  Create json array request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getEventos, null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response){
                // Successfully download json
                // So parse it and populate the listview
                try {
                    JSONArray jsonArray = response.getJSONArray("eventos");
                    texto="";
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        String Nombre = jsonObject.getString("Nombre");
                        String Descripcion = jsonObject.getString("Descripcion");
                        String Localizacion = jsonObject.getString("Localizacion");
                        String FechaInicio = jsonObject.getString("FechaInicio");
                        String FechaFin = jsonObject.getString("FechaFin");
                        String HoraInicio = jsonObject.getString("HoraInicio");
                        String HoraFin = jsonObject.getString("HoraFin");
                        
                        texto = "Nombre: " +
                                jsonObject.getString("Nombre") +
                                "\n"+"Hora de comienzo: \n" +
                                jsonObject.getString("Descripcion") +
                                "\n"+"Hora de comienzo: \n" +
                                jsonObject.getString("FechaInicio") +
                                "\n"+"Direccion del evento: \n" +
                                jsonObject.getString("Localizacion");

                        Intent intent = new Intent(Eventos.this, DetalleEvento.class);
                        //intent.putExtra("IdEvento", IdEvento);
                        intent.putExtra("Nombre", Nombre);
                        intent.putExtra("Descripcion", Descripcion);
                        intent.putExtra("Localizacion", Localizacion);
                        intent.putExtra("FechaInicio", FechaInicio);
                        intent.putExtra("FechaFin", FechaFin);
                        intent.putExtra("HoraInicio", HoraInicio);
                        intent.putExtra("HoraFin", HoraFin);
                        //System.out.println(IdEvento);
                        //Eventos.this.startActivity(intent);
                        items.add("Nombre: "+ Nombre+"\n"+"Descripcion: "+Descripcion);
                        //items.add("Localizacion");

                        //items.add(jsonObject.getString("Localizacion"));
                        //items.add(texto);
                        //Eventos.this.startActivity(intent);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Error", "No se pueden cargar los eventos");
            }
        });
        // add json array request to the request queue
        requestQueue.add(jsonObjectRequest);
    }
}



