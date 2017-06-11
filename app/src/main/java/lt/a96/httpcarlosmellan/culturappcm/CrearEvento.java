package lt.a96.httpcarlosmellan.culturappcm;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CrearEvento extends AppCompatActivity {

    EditText et_nombreevento, et_descripcionevento, et_fechainicio, et_fechafin, et_ubicacion, et_horainicio, et_horafin;
    Button btn_registarevento, btn_cancelar;
    Calendar myCalendar = Calendar.getInstance();
    String titulo_timepicker;


    private void updateLabel () {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        et_fechainicio.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabel2 () {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat);

        et_fechafin.setText(sdf2.format(myCalendar.getTime()));
    }


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

        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        final String nombre = intent.getStringExtra("nombre");
        final String nombreusuario = intent.getStringExtra("nombreusuario");
        final String genero = intent.getStringExtra("genero");

        // TODO: hacer que no se puedan ingresar eventos repetidos

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearEvento.this,Perfil.class);
                intent.putExtra("email", email);
                intent.putExtra("nombre", nombre);
                intent.putExtra("nombreusuario", nombreusuario);
                intent.putExtra("genero", genero);
                startActivity(intent);
            }
        });

        //CODIGO PARA EL CALENDARIO, APARECE UN CALENDARIO PARA INGRESAR LA FECHA
        et_fechainicio.setFocusable(false);
        et_fechainicio.setClickable(true);
        et_fechainicio.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();
                }
            };

            @Override
            public void onClick(View v) {
                new DatePickerDialog(CrearEvento.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        et_fechafin.setFocusable(false);
        et_fechafin.setClickable(true);
        et_fechafin.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel2();
                }
            };

            @Override
            public void onClick(View v) {
                new DatePickerDialog(CrearEvento.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //FIN CODIGO DEL CALENDARIO

        //CODIGO PARA LA HORA, APARECE UN RELOJ AL INGRESAR LA HORA
        et_horainicio.setFocusable(false);
        et_horainicio.setClickable(true);
        et_horainicio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CrearEvento.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        et_horainicio.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle(titulo_timepicker);
                mTimePicker.show();

            }
        });

        et_horafin.setFocusable(false);
        et_horafin.setClickable(true);
        et_horafin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CrearEvento.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        et_horafin.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle(titulo_timepicker);
                mTimePicker.show();

            }
        });
        //FIN CODIGO DEL RELOJ

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
                                    intent.putExtra("email", email);
                                    intent.putExtra("nombre", nombre);
                                    intent.putExtra("nombreusuario", nombreusuario);
                                    intent.putExtra("genero", genero);
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
